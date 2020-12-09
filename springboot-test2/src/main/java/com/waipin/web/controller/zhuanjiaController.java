package com.waipin.web.controller;

import com.waipin.model.*;
import com.waipin.service.GuanliyuanService;
import com.waipin.service.JiaoshiService;
import com.waipin.service.ZhuanjiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller//Rest风格的控制器
@EnableAutoConfiguration//自动配置，相当于写了spring的配置文件
@RequestMapping("zhuanjia")
public class zhuanjiaController {
    @Autowired
    ZhuanjiaService zhuanjiaService;
    @Autowired
    GuanliyuanService guanliyuanService;

    String denglurenid="";
    String denglurenxingming="";
    Zhuanjia zhuanjia=null;
    Map<String, Object> map = new HashMap<String, Object>();
    @GetMapping("showgerenzhongxin")
    public String showgerenzhongxin(@RequestParam(name="dengluren") String dengluren ){
        if (dengluren==null||dengluren==""){
            dengluren=denglurenid;
        }else {
            denglurenid=dengluren;
            System.out.println("静态参数denglurenid是"+denglurenid);
        }
        int id = Integer.parseInt(dengluren);
        System.out.println("id"+id);
        Zhuanjia denglu = zhuanjiaService.chazhaoid(id);
        denglurenxingming=denglu.getXingming();
        zhuanjia=denglu;
        System.out.println("zhuanjia"+zhuanjia);
//      Map<String, Object> map = new HashMap<String, Object>();
        map.put("denglurenid",denglurenid);
        return "zhuanjia/gerenzhongxin";
    }
    @RequestMapping("gerenxinxizhanshi")
    @ResponseBody
    public Map<String,Object> gerenxinxizhanshi(HttpServletRequest request, HttpServletResponse response)
    {
//        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println("gerenxinxizhanshi个人信息展示测试 ajax ");
        //String denglurenStr= (String) request.getSession().getAttribute("denglurenid");
        map.put("denglurenxingming",denglurenxingming);
        map.put("zhuanjia",zhuanjia);
        return map;
    }
    @RequestMapping ("gerenzhongxin")
    public String gerenzhongxin(){
        return "zhuanjia/gerenzhongxin";
    }
    /*个人信息修改*/
    @RequestMapping("gerenxinxixiugai")
    @ResponseBody
    public String gerenxinxixiugai(@Valid Zhuanjia zhuanjia1, BindingResult bindingResult){
        String result ="";
        if(bindingResult.hasErrors()){
            System.out.println("个人信息修改有问题");
            return bindingResult.getFieldError().getDefaultMessage();
        }else{
            System.out.println(zhuanjia1);
            User selectuserid=new User();
            User selectuseryonghuming = guanliyuanService.selectuseryonghuming(zhuanjia1.getYonghuming());
            selectuserid = guanliyuanService.selectuserid(zhuanjia.getYonghuming(), zhuanjia.getMima());
            if (selectuseryonghuming==null||zhuanjia.getYonghuming()==zhuanjia1.getYonghuming()){
                selectuserid.setMima(zhuanjia1.getMima());
                selectuserid.setShenfen("zhuanjia");
                guanliyuanService.updateuserinfo(selectuserid);
                zhuanjia.setMima(zhuanjia1.getMima());
                zhuanjia.setDianhua(zhuanjia1.getDianhua());
                zhuanjia.setYouxiang(zhuanjia1.getYouxiang());
                boolean updateinfo = zhuanjiaService.updateinfo(zhuanjia);
                if (updateinfo) {
                    result="修改成功";
                    denglurenid=zhuanjia.getId().toString();
                }
                else {
                    result="修改失败";
                }
            }else {
                result="用户名已存在";
            }
        }
        System.out.println("result是"+result);
        return result;
    }

    /*教学督导模块**************************************************/
    @RequestMapping ("jiaoxuedudao")/*督导记录页面展示*/
    public String jiaoxuedudao(Map<String,Object> data){
        List<Jiaoshi> jiaoshiList = guanliyuanService.selectAlljiaoshi();
        List<Chufadengji> chufadengjiList = guanliyuanService.selectAllchufadengji();
        //List<Guanliyuan> guanliyuanList = guanliyuanService.selectAllguanliyuan();
        data.put("jiaoshiList",jiaoshiList);
        data.put("chufadengjiList",chufadengjiList);
       // data.put("guanliyuanList",guanliyuanList);
        return "zhuanjia/jiaoxuedudao";
    }
    /*督导记录分页*/
    @RequestMapping("dudaojilufenye")
    @ResponseBody
    public Map<String,Object> dudaojilufenye(HttpServletRequest request,HttpServletResponse response){
//        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println("教师信息分页测试 ajax ");
        String currentPageStr=request.getParameter("currentPage");
        String rowStr = request.getParameter("row");
        String xingming = request.getParameter("xingming");
        String shenhezhuangtai = request.getParameter("shenhezhuangtai");
        String fashengshijian = request.getParameter("fashengshijian");
        int currentPage = 0;//当前页码，默认为第一页
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }

        System.out.println("当前页码currentPage"+currentPage);
        int row=0;//每一页显示的条数，默认为5
        if(rowStr!=null&&rowStr.length()>0){
            row=Integer.parseInt(rowStr);
        }else {
            row=5;
        }
        //System.out.println(username+password);
        PageBean<Jiaoxuedudao> pb = guanliyuanService.jiaoxuedudaofindBypage(currentPage, row,xingming,shenhezhuangtai,fashengshijian);
        map.put("currentPage",pb.getCurrentPage());
        map.put("totalPage",pb.getTotalPage());
        map.put("totalCount",pb.getTotalCount());
        map.put("list",pb.getList());
        map.put("denglurenxingming",denglurenxingming);

        return map;
    }
    /*督导记录修改*/
    @RequestMapping("dudaojiluxiugai")
    @ResponseBody
    public String dudaojiluxiugai(@Valid Jiaoxuedudao jiaoxuedudao, BindingResult bindingResult){
        String result ="";
        if(bindingResult.hasErrors()){
            System.out.println("教学督导修改有问题");
            result="fail";
            return bindingResult.getFieldError().getDefaultMessage();
        }else{
            Jiaoxuedudao jiaoxuedudao1=null;
            System.out.println("教学督导id是"+jiaoxuedudao.getId());
            if (jiaoxuedudao.getId()==null||jiaoxuedudao.getId()==0){
                int nextid=guanliyuanService.selectjiaoxuedudaoLastid();
                jiaoxuedudao.setId(nextid);
            }
                boolean inserinfo = guanliyuanService.insertJiaoxuedudaoinfo(jiaoxuedudao);
                if (inserinfo) {
                    result="添加成功";
                }else {
                    result="添加失败";
                }

        }
        return result;
    }

}
