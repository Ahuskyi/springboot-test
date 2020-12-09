package com.waipin.util;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 *
 * <p>Title: ExportExcel.java</p>
 * <p>Description: Excel导出工具类</p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: </p>
 * @author wangdali
 * @date 2018年6月18日
 * @version 1.0
 */
public class ExportExcel {


    private final static Logger LOGGER = LoggerFactory.getLogger(ExportExcel.class);

    /**
     * 使用freemarker模版文件导出excel(具体方法)
     * 因为项目使用的是freemarker开发前端页面，所以可以通过公共common中配置在default-root.xml的FreeMarkerConfigurer(freemarker解析器)对象
     * 得到Configuration或者通过单例模式实现configuration;
     *
     * @param configuration 官网-不需要重复创建 Configuration 实例； 它的代价很高，尤其是会丢失缓存
     * @param templateName
     * @param data
     * @param outputStream
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    private static void parse(Configuration configuration, String templateName, Map<String, Object> data,
                              OutputStream outputStream) throws IOException, TemplateException {
        OutputStreamWriter writer = null;
        try {
            // 加载模板
            configuration.setDirectoryForTemplateLoading(new File("C:/Users/Administrator/IdeaProjects/springboot-test2/src/main/resources"));
            Template template = configuration.getTemplate(templateName, "utf-8"); //
            // 填充数据至Excel
            writer = new OutputStreamWriter(outputStream, "UTF-8");
            template.process(data, writer);
            writer.flush();
        } finally {
            // 关闭文件流
            if (writer != null) {
                writer.close();
            }
        }
    }


    /**
     * freemarker模版导出Excel(调用方法)
     * 扩展性强，适合复杂excel的导出
     * @param freemarkerConfig default-root.xml的freemarker视图解析器
     * @param response	HttpServletResponse
     * @param fileName	导出的文件名
     * @param templateName	模版名(路径默认请参考freemarkerConfig的配置)
     * @param data	写入的数据
     * @return
     */
    public static String exportExcel(FreeMarkerConfigurer freemarkerConfig, HttpServletResponse  response, String fileName, String templateName, Map<String, Object> data) {
        String result = "系统提示：Excel文件导出成功！";
        // 以下开始输出到EXCEL
        try {
            // 定义输出流，以便打开保存对话框______________________begin
            OutputStream outputStream = response.getOutputStream();// 取得输出流
            response.reset();// 清空输出流
            response.setHeader("Content-disposition", "attachment; filename="
                    + new String(fileName.getBytes("GB2312"), "ISO8859-1")); 	// 设定输出文件头
            response.setContentType("application/msexcel");// 定义输出类型
            // 定义输出流，以便打开保存对话框_______________________end

            /** **********创建工作簿************ */
            System.out.println("templateName"+templateName);
            parse(freemarkerConfig.getConfiguration(), templateName, data, outputStream);
            return result;
        } catch (Exception e) {
            result = "系统提示：Excel文件导出失败，原因：" + e.toString();
            LOGGER.warn("excel export fail.", e);
        }
        return result;
    }
}
