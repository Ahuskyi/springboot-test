package com.waipin.web.controller;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.waipin.model.*;
import com.waipin.service.GuanliyuanService;
import com.waipin.util.ExportWordUtils;
import com.waipin.util.FileUtils;

import com.waipin.util.TemplateParseUtil;
import com.waipin.util.WordUtils;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;


import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import static com.waipin.util.ExportExcel.exportExcel;

@Controller//Rest风格的控制器
@EnableAutoConfiguration//自动配置，相当于写了spring的配置文件
@RequestMapping("guanliyuan")
public class GuanliyuanController {
    private final ResourceLoader resourceLoader;
    static String denglurenxingming="";
    static String denglurenid="";
    static Guanliyuan guanliyuan=null;
    static String jiaoshi_id="";
    static String kecheng_id="";
    static String ziliao_id="";
    static String shenfenzheng="";
    static String jianli="";
    static String xueweizheng="";
    static String xuelizheng="";
    static String zhichengzheng="";
    static String zongheshuipingfenshu="";
    static String shenfenzhengmsg="";
    static String jianlimsg="";
    static String xueweizhengmsg="";
    static String xuelizhengmsg="";
    static String zhichengzhengmsg="";
    static String zongheshuipingfenshumsg="";
    static String shijiang_id="";
    static String shijiangneirongmsg="";
    static String shijiangneirong="";
    static String shijiangfenshumsg="";
    static String shijiangfenshu="";
    static String hetong_id="";
    static String xueqispan="";

    @Autowired
    public GuanliyuanService guanliyuanService;
    FreeMarkerConfigurer freemarkerConfig=new FreeMarkerConfigurer();



    Map<String, Object> map = new HashMap<String, Object>();
    //
     public static String dateToStr(java.util.Date dateDate) {
         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
         String dateString = formatter.format(dateDate);
         return dateString;
     }
         public static Date strToDate(String strDate) {
         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
         ParsePosition pos = new ParsePosition(0);
         Date strtodate = formatter.parse(strDate, pos);
         return strtodate;
     }
         public static String getYear() {
         Date currentTime = new Date();
         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         String dateString = formatter.format(currentTime);
         String year;
         year = dateString.substring(0, 4);
         return year;
     }
     public static String getMouth() {
         Date currentTime = new Date();
         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         String dateString = formatter.format(currentTime);
         String mouth;
         String shiwei;
         shiwei=dateString.substring(5,6);
         if (Integer.parseInt(shiwei)==1){
             mouth = dateString.substring(5,7);
         }else {
             mouth = dateString.substring(6,7);
         }
         return mouth;
     }

    @GetMapping ("showgerenzhongxin")
    public String showgerenzhongxin(@RequestParam(name="dengluren") String dengluren ){
        if (dengluren==null||dengluren==""){
            dengluren=denglurenid;
        }else {
            denglurenid=dengluren;
            System.out.println("静态参数denglurenid是"+denglurenid);
        }
        int id = Integer.parseInt(dengluren);
        System.out.println("id"+id);
        Guanliyuan denglu = guanliyuanService.chazhaoid(id);
        denglurenxingming=denglu.getXingming();
        guanliyuan=denglu;
//        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> getxueqi = getxueqi();
        xueqispan =(String) getxueqi.get("xueqi");
        System.out.println("xueqispan"+xueqispan);
        map.put("xueqispan",xueqispan);
        map.put("denglurenid",denglurenid);
        return "guanliyuan/gerenzhongxin";
    }
    @RequestMapping("gerenxinxizhanshi")
    @ResponseBody
    public Map<String,Object> gerenxinxizhanshi(HttpServletRequest request, HttpServletResponse response)
    {
//        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println("gerenxinxizhanshi个人信息展示测试 ajax ");
        //String denglurenStr= (String) request.getSession().getAttribute("denglurenid");
        if (denglurenid!=""){
            int dengluren = Integer.parseInt(denglurenid);
            Guanliyuan denglu = guanliyuanService.chazhaoid(dengluren);
        }
        map.put("guanliyuan",guanliyuan);
        return map;
    }

    @RequestMapping ("gerenzhongxin")
        public String gerenzhongxin(){
        return "guanliyuan/gerenzhongxin";
    }
    @RequestMapping ("tuichu")
    public String tuichu(){
        return "login/showlogin";
    }
    /*个人信息修改*/
    @RequestMapping("gerenxinxixiugai")
    @ResponseBody
    public Map<String,Object> gerenxinxixiugai(@Valid Guanliyuan guanliyuan1, BindingResult bindingResult){
        String result ="";
        if(bindingResult.hasErrors()){//bindingResult作用，监听这个注解是否配置成功
            System.out.println("个人信息修改有问题");
            //return bindingResult.getFieldError().getDefaultMessage();
        }else{
            System.out.println(guanliyuan1);
            User selectuserid=new User();
            User selectuseryonghuming = guanliyuanService.selectuseryonghuming(guanliyuan1.getYonghuming());
            selectuserid = guanliyuanService.selectuserid(guanliyuan.getYonghuming(), guanliyuan.getMima());
            if (selectuseryonghuming==null||guanliyuan.getYonghuming().equals(guanliyuan1.getYonghuming())){
                selectuserid.setYonghuming(guanliyuan1.getYonghuming());
                selectuserid.setMima(guanliyuan1.getMima());
                selectuserid.setShenfen("guanliyuan");
                guanliyuanService.updateuserinfo(selectuserid);
                boolean updateinfo = guanliyuanService.updateinfo(guanliyuan1);
                if (updateinfo) {
                    result="修改成功";
                    guanliyuan=guanliyuan1;
                    denglurenid=guanliyuan1.getId().toString();
                }
                else {
                    result="修改失败";
                }
            }else {
                result="用户名已存在";
            }


        }
        map.put("result",result);
        System.out.println("result是"+result);
        return map;
    }

    /*教师管理模块*****************************************************/
    @RequestMapping ("jiaoshiguanli")
    public String jiaoshiguanli(Map<String,Object> data){
        List<Xuewei> xueweiList = guanliyuanService.selectAllxuewei();
        List<Zhicheng> zhichengList = guanliyuanService.selectAllzhicheng();
        data.put("xueweiList",xueweiList);
        data.put("zhichengList",zhichengList);
        return "guanliyuan/jiaoshiguanli";
    }

    /*教师信息分页*/
    @RequestMapping("jiaoshixinxifenye")
    @ResponseBody
    public Map<String,Object> jiaoshixinxifenye(HttpServletRequest request,HttpServletResponse response){
//        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println("教师信息分页测试 ajax ");
        String currentPageStr=request.getParameter("currentPage");
        String rowStr = request.getParameter("row");
        String search_id = request.getParameter("search_id");
        String search_yonghuming = request.getParameter("search_yonghuming");
        String search_xingming = request.getParameter("search_xingming");
        int currentPage = 0;//当前页码，默认为第一页
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }
        int id = 0;//当前页码，默认为第一页
        if (search_id != null && search_id.length() > 0) {
            id = Integer.parseInt(search_id);
        } else {
            id = 0;
        }
//        request.getSession().removeAttribute("curr");
//        System.out.println("存入session 的 currentPage :"+currentPage);
//        request.getSession().setAttribute("curr",currentPage);
        System.out.println("当前页码currentPage"+currentPage);
        int row=0;//每一页显示的条数，默认为5
        if(rowStr!=null&&rowStr.length()>0){
            row=Integer.parseInt(rowStr);
        }else {
            row=5;
        }
        //System.out.println(username+password);
        PageBean<Jiaoshi> pb = guanliyuanService.jiaoshifindBypage(currentPage,row,id,search_yonghuming,search_xingming);

        map.put("currentPage",pb.getCurrentPage());
        map.put("totalPage",pb.getTotalPage());
        map.put("totalCount",pb.getTotalCount());
        map.put("list",pb.getList());
        map.put("denglurenxingming",guanliyuan.getXingming());
        return map;
    }
        /*教师信息修改*/
    @RequestMapping("jiaoshixinxixiugai")
    @ResponseBody
    public Map<String,Object> jiaoshixinxixiugai(@Valid Jiaoshi jiaoshi, BindingResult bindingResult){
        String result ="";
        System.out.println("教师修改信息"+jiaoshi);
        if(bindingResult.hasErrors()){
            System.out.println("教师信息修改有问题");
            result="fail";
            map.put("errormsg",bindingResult.getFieldError().getDefaultMessage());
            return map ;
        }else{
            //System.out.println(guanliyuan);
            if (jiaoshi.getId()==null){
                int nextid = guanliyuanService.selectjiaoshiLastid();
                jiaoshi.setId(nextid);
            }
            Jiaoshi jiaoshi1 = guanliyuanService.selectByteavherid(jiaoshi.getId());
            User selectuserid=new User();
            User selectuseryonghuming = guanliyuanService.selectuseryonghuming(jiaoshi.getYonghuming());
                if (jiaoshi1!=null){
                    jiaoshi.setCanjiagongzuoshijianlujing(jiaoshi1.getCanjiagongzuoshijianlujing());
                    jiaoshi.setShenfenzhenglujing(jiaoshi1.getShenfenzhenglujing());
                    jiaoshi.setXuelizhenglujing(jiaoshi1.getXuelizhenglujing());
                    jiaoshi.setXueweizhenglujing(jiaoshi1.getXueweizhenglujing());
                    jiaoshi.setZongheshuipingfenshulujing(jiaoshi1.getZongheshuipingfenshulujing());
                    jiaoshi.setZhichengzhenglujing(jiaoshi1.getZhichengzhenglujing());
                    jiaoshi.setZongheshuipingfenshu(jiaoshi1.getZongheshuipingfenshu());
                    jiaoshi.setPingdingdengji(jiaoshi1.getPingdingdengji());
                    selectuserid = guanliyuanService.selectuserid(jiaoshi1.getYonghuming(), jiaoshi1.getMima());
                    if (selectuseryonghuming==null||jiaoshi1.getYonghuming().equals(jiaoshi.getYonghuming())){
                        selectuserid.setYonghuming(jiaoshi.getYonghuming());
                        selectuserid.setMima(jiaoshi.getMima());
                        selectuserid.setShenfen("jiaoshi");
                        guanliyuanService.updateuserinfo(selectuserid);
                        boolean updateinfo = guanliyuanService.updateTeacherinfo(jiaoshi);
                        if (updateinfo) {
                            result="修改成功";
                            System.out.println("修改成功");
                        }
                        else {
                            result="修改失败";
                        }
                    }else {
                        result="用户名已存在";
                    }

                }else {
                        if (selectuseryonghuming==null){
                            selectuserid.setYonghuming(jiaoshi.getYonghuming());
                            selectuserid.setMima(jiaoshi.getMima());
                            selectuserid.setShenfen("jiaoshi");
                            guanliyuanService.saveuserinfo(selectuserid);
                            boolean inserinfo = guanliyuanService.insertTeacherinfo(jiaoshi);
                            if (inserinfo) {
                                result="添加成功";
                            }else {
                                result="添加失败";
                            }
                        }else {
                            result="用户名已存在";
                        }
                     }


        }
        map.put("result",result);
        return map;
    }
    /*教师信息删除*/
    @RequestMapping("jiaoshidel")
    public String jiaoshidel(@RequestParam("jiaoshiid") String jiaoshiid){
    if (jiaoshiid!=null&&jiaoshiid!=""){
        boolean jiaoshidel = guanliyuanService.jiaoshidel(Integer.parseInt(jiaoshiid));
    }
        return "redirect:jiaoshiguanli";
    }
    /*教师等级核算*/
    @RequestMapping("dengjihesuan")
    public String dengjihesuan(@RequestParam("jiaoshiid") String jiaoshiid){//得到jiaoshiid参数
        if (jiaoshiid!=null&&jiaoshiid!=""){
            String year = getYear();
            Jiaoshi jiaoshi = guanliyuanService.selectByteavherid(Integer.parseInt(jiaoshiid));
            int xueweifenshu=guanliyuanService.fenshuSelectbyxuewei(jiaoshi.getXueweizheng());
            int zhichengfenshu=guanliyuanService.fenshuSelectbyzhicheng(jiaoshi.getZhichengzheng());
            double shijiangfenshu=guanliyuanService.fenshuSelectbyshijiang(jiaoshi.getId())*0.4;
            int gongzuonianxianfenshu=guanliyuanService.fenshuSelectbygongniannianxian(Integer.parseInt(year)-Integer.parseInt(jiaoshi.getCanjiagongzuoshijian()));
            double s=xueweifenshu+zhichengfenshu+shijiangfenshu+gongzuonianxianfenshu;
            String pingdingdengji = guanliyuanService.dengjiSelectbyfenshu(s);
            String zongheshuipingfenshu = Double.toString(s);
            jiaoshi.setPingdingdengji(pingdingdengji);
            jiaoshi.setZongheshuipingfenshu(zongheshuipingfenshu);
            boolean b = guanliyuanService.updateTeacherinfo(jiaoshi);
            if (b){
                System.out.println("等级核算成功");
            }
        }
        return "redirect:jiaoshiguanli";
    }


    /*教学督导模块**************************************************/
    @RequestMapping ("dudaojilu")/*督导记录页面展示*/
    public String dudaojilu(Map<String,Object> data){
        List<Jiaoshi> jiaoshiList = guanliyuanService.selectAlljiaoshi();
        List<Chufadengji> chufadengjiList = guanliyuanService.selectAllchufadengji();
        List<Guanliyuan> guanliyuanList = guanliyuanService.selectAllguanliyuan();
        data.put("jiaoshiList",jiaoshiList);
        data.put("chufadengjiList",chufadengjiList);
        data.put("guanliyuanList",guanliyuanList);
        data.put("tijiaorenbianhao",denglurenid);
        return "guanliyuan/dudaojilu";
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
//        request.getSession().removeAttribute("curr");
//        System.out.println("存入session 的 currentPage :"+currentPage);
//        request.getSession().setAttribute("curr",currentPage);
        System.out.println("当前页码currentPage"+currentPage);
        int row=0;//每一页显示的条数，默认为5
        if(rowStr!=null&&rowStr.length()>0){
            row=Integer.parseInt(rowStr);
        }else {
            row=5;
        }
        //System.out.println(username+password);
        PageBean<Jiaoxuedudao> pb = guanliyuanService.jiaoxuedudaofindBypage(currentPage, row,xingming,shenhezhuangtai,fashengshijian);
        int nextid=guanliyuanService.selectjiaoxuedudaoLastid();
        System.out.println("nextid"+nextid);
        map.put("currentPage",pb.getCurrentPage());
        map.put("totalPage",pb.getTotalPage());
        map.put("totalCount",pb.getTotalCount());
        map.put("list",pb.getList());
        map.put("denglurenxingming",guanliyuan.getXingming());
        map.put("nextid",nextid);
        return map;
    }
    /*督导记录修改*/
    @RequestMapping("dudaojiluxiugai")
    @ResponseBody
    public Map<String,Object> dudaojiluxiugai(@Valid Jiaoxuedudao jiaoxuedudao, BindingResult bindingResult){
        String result ="";
        System.out.println("jiaoxuedudao"+jiaoxuedudao);
        if(bindingResult.hasErrors()){
            System.out.println("教学督导修改有问题");
            result="fail";
//            //return bindingResult.getFieldError().getDefaultMessage();
        }else{
            Jiaoxuedudao jiaoxuedudao1=null;
            System.out.println("教学督导id是"+jiaoxuedudao.getId());
            if (jiaoxuedudao.getId()==null||jiaoxuedudao.getId()==0){
                int nextid=guanliyuanService.selectjiaoxuedudaoLastid();
                jiaoxuedudao.setId(nextid);
            }
            jiaoxuedudao1 = guanliyuanService.selectByjiaoxuedudaoid(jiaoxuedudao.getId());
            List<Chufadengji> chufadengjis = guanliyuanService.selectAllchufadengji();
            double chufajine=0;
            double shifajine=0;
            for (int i = 0; i <chufadengjis.size() ; i++) {
                if (chufadengjis.get(i).getChufadengji().equals(jiaoxuedudao.getChufadengji())){
                    chufajine=chufadengjis.get(i).getChufajine();
                }
            }

            if (jiaoxuedudao1!=null){
                if (jiaoxuedudao1.getShenhezhuangtai().equals("未审核")&&jiaoxuedudao.getShenhezhuangtai().equals("已审核")){
                    String chuangjianshijian = jiaoxuedudao.getChuangjianshijian().substring(0,7);
                    boolean updategongzifakuaninfo = guanliyuanService.updategongzifakuaninfo(jiaoxuedudao.getJiaoshibianhao(), chufajine, chuangjianshijian);
                    if (updategongzifakuaninfo){
                        System.out.println("对应的工资已修改");
                    }
                }
                if (jiaoxuedudao1.getShenhezhuangtai().equals("已审核")&&jiaoxuedudao.getShenhezhuangtai().equals("未审核")){
                    String chuangjianshijian = jiaoxuedudao.getChuangjianshijian().substring(0,7);
                    boolean updategongzifakuaninfo = guanliyuanService.updategongzifakuaninfo(jiaoxuedudao.getJiaoshibianhao(), -chufajine, chuangjianshijian);
                    if (updategongzifakuaninfo){
                        System.out.println("对应的工资已修改");
                    }
                }
                boolean updateinfo = guanliyuanService.updateJiaoxuedudaoinfo(jiaoxuedudao);
                if (updateinfo) {
                    result="修改成功";
                }
                else {
                    result="修改失败";
                }
            }else {
                if (jiaoxuedudao.getShenhezhuangtai().equals("已审核")){
                    String chuangjianshijian = jiaoxuedudao.getChuangjianshijian().substring(0,7);
                    boolean updategongzifakuaninfo = guanliyuanService.updategongzifakuaninfo(jiaoxuedudao.getJiaoshibianhao(), chufajine, chuangjianshijian);
                    if (updategongzifakuaninfo){
                        System.out.println("对应的工资已修改");
                    }
                }
                boolean inserinfo = guanliyuanService.insertJiaoxuedudaoinfo(jiaoxuedudao);
                if (inserinfo) {
                    result="添加成功";
                }else {
                    result="添加失败";
                }
            }
        }
        map.put("result",result);
        return map;
    }
    /*督导信息删除*/
    @RequestMapping("dudaodel")
    public String dudaodel(@RequestParam("dudaoid") String dudaoid){
        if (dudaoid!=null&&dudaoid!=""){
            guanliyuanService.dudaodel(Integer.parseInt(dudaoid));
        }
        return "redirect:dudaojilu";
    }
    /*处罚等级管理模块**********************************************/
    @RequestMapping ("chufaguanli")/*处罚等级管理页面展示*/
    public String chufaguanli(){
        return "guanliyuan/chufaguanli";
    }
    /*处罚等级分页*/
    @RequestMapping("chufadengjifenye")
    @ResponseBody
    public Map<String,Object> chufadengjifenye(HttpServletRequest request,HttpServletResponse response){
//        Map<String, Object> map = new HashMap<String, Object>();
        //System.out.println("处罚等级分页测试 ajax ");
        String currentPageStr=request.getParameter("currentPage");
        String rowStr = request.getParameter("row");
        int currentPage = 0;//当前页码，默认为第一页
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }
        List<Chufadengji> chufadengjis = guanliyuanService.selectAllchufadengji();
        map.put("denglurenxingming",denglurenxingming);
        map.put("list",chufadengjis);
        return map;
    }
    /*处罚等级修改*/
    @RequestMapping("chufadengjixiugai")
    @ResponseBody
    public  Map<String,Object> chufadengjixiugai(@Valid Chufadengji chufadengji, BindingResult bindingResult){
        String result ="";
        if(bindingResult.hasErrors()){
            System.out.println("处罚等级修改有问题");
            result="fail";
//            //return bindingResult.getFieldError().getDefaultMessage();
        }else{
            System.out.println("处罚等级id是"+chufadengji.getId());
            if (chufadengji.getId()==null){
                int nextid = guanliyuanService.selectchufadengjiLastid();
                chufadengji.setId(nextid);
            }
            Chufadengji chufadengji1 = guanliyuanService.selectBychufadengjiid(chufadengji.getId());
            if (chufadengji1!=null){
                boolean updateinfo = guanliyuanService.updateChufadengjiinfo(chufadengji);
                if (updateinfo) {
                    result="修改成功";
                }
                else {
                    result="修改失败";
                }
            }else {
                boolean inserinfo = guanliyuanService.insertChufadengjiinfo(chufadengji);
                if (inserinfo) {
                    result="添加成功";
                }else {
                    result="添加失败";
                }
            }
        }
        map.put("result",result);
        return map;
    }

    /*处罚信息信息删除*/
    @RequestMapping("chufadengjidel")
    public String chufadengjidel(@RequestParam("chufadengjiid") String chufadengjiid){
        if (chufadengjiid!=null&&chufadengjiid!=""){
            guanliyuanService.chufadengjidel(Integer.parseInt(chufadengjiid));
        }
        return "redirect:chufaguanli";
    }
    /****************文件测试**************/
    @Autowired
    public GuanliyuanController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    @Value("${web.upload-path}")
    private String path;

    /**
     * 跳转到文件上传页面
     * @return
     */
    @RequestMapping("toUpload")
    public String toUpload(@RequestParam("jiaoshiid") String jiaoshiid){
        jiaoshi_id=jiaoshiid;
        return "guanliyuan/wenjianshangchuan";
    }
    @RequestMapping("tohetongUpload")
    public String tohetongUpload(@RequestParam("hetongid") String hetongid){
        hetong_id=hetongid;
        return "guanliyuan/hetongshangchuan";
    }
    /**
     *
     * @param file 要上传的文件
     * @return
     */
    @RequestMapping("hetongUpload")
    public String hetongUpload(@RequestParam("fileName") MultipartFile file, Map<String, Object> map){
        // 要上传的目标文件存放路径
        String localPath = "C:/Users/Administrator/IdeaProjects/springboot-test2/src/main/resources/static/images";
        String hetongurlmsg="";
        // 上传成功或者失败的提示
        String hetongurl=file.getOriginalFilename();
        int id=Integer.parseInt(hetong_id);
        Hetong hetong = guanliyuanService.selectByhetongid(id);
        hetong.setHetongname(hetongurl);
        hetong.setHetongurl(hetongurl);
        boolean b = guanliyuanService.updatehetonginfo(hetong);
        //boolean b = guanliyuanService.updateTeachershenfenzhenglujing(id,hetongurl);
        System.out.println("hetongurl是"+hetongurl);
        if (FileUtils.upload(file, localPath, file.getOriginalFilename())&&b){
            // 上传成功，给出页面提示
             hetongurlmsg = "上传成功！";
        }else {
             hetongurlmsg  = "上传失败！";
        }
        // 显示图片

        map.remove("hetongurl");
        map.put("hetongurlmsg", hetongurlmsg);
        map.remove("hetongurl");
        map.put("hetongurl",hetongurl);

        return "forward:tohetongUpload?hetongid="+hetong_id;
    }

    @RequestMapping("shenfenzhengUpload")
    public String shenfenzhengUpload(@RequestParam("fileName") MultipartFile file, Map<String, Object> map){
        // 要上传的目标文件存放路径
        String localPath = "C:/Users/Administrator/IdeaProjects/springboot-test2/src/main/resources/static/images";

        // 上传成功或者失败的提示
        String shenfenzhenglujing=file.getOriginalFilename();
        int id=Integer.parseInt(jiaoshi_id);
        boolean b = guanliyuanService.updateTeachershenfenzhenglujing(id,shenfenzhenglujing);
        System.out.println("shenfenzhenglujing是"+shenfenzhenglujing);
        if (FileUtils.upload(file, localPath, file.getOriginalFilename())&&b){
            // 上传成功，给出页面提示
            shenfenzhengmsg = "上传成功！";
        }else {
            shenfenzhengmsg = "上传失败！";
        }
        // 显示图片
        shenfenzheng = file.getOriginalFilename();
        map.remove("shenfenzhengmsg");
        map.put("shenfenzhengmsg", shenfenzhengmsg);
        map.remove("jianlimsg");
        map.put("jianlimsg", jianlimsg);
        map.remove("xueweizhengmsg");
        map.put("xueweizhengmsg", xueweizhengmsg);
        map.remove("xuelizhengmsg");
        map.put("xuelizhengmsg", xuelizhengmsg);
        map.remove("zhichengzhengmsg");
        map.put("zhichengzhengmsg", zhichengzhengmsg);
        map.remove("zongheshuipingfenshumsg");
        map.put("zongheshuipingfenshumsg", zongheshuipingfenshumsg);
        map.remove("shenfenzheng");
        map.put("shenfenzheng",shenfenzheng);
        map.remove("jianli");
        map.put("jianli", jianli);
        map.remove("xueweizheng");
        map.put("xueweizheng", xueweizheng);
        map.remove("xuelizheng");
        map.put("xuelizheng", xuelizheng);
        map.remove("zhichengzheng");
        map.put("zhichengzheng", zhichengzheng);
        map.remove("zongheshuipingfenshu");
        map.put("zongheshuipingfenshu", zongheshuipingfenshu);
        map.put("denglurenxingming",denglurenxingming);
        return "forward:toUpload?jiaoshiid="+jiaoshi_id;
    }

    @RequestMapping("jianliUpload")
    public String jianliUpload(@RequestParam("fileName") MultipartFile file, Map<String, Object> map){

        // 要上传的目标文件存放路径
        String localPath = "C:/Users/Administrator/IdeaProjects/springboot-test2/src/main/resources/static/images";
        // 上传成功或者失败的提示
        String jianlilujing=file.getOriginalFilename();
        int id=Integer.parseInt(jiaoshi_id);
        boolean b = guanliyuanService.updateTeachercanjiagongzuoshijianlujing(id,jianlilujing);
        System.out.println("jianlilujing"+jianlilujing);
        String jianliurl = file.getOriginalFilename();
        if (FileUtils.upload(file, localPath, file.getOriginalFilename())){
            // 上传成功，给出页面提示
            jianlimsg = "上传成功！";
        }else {
            jianlimsg = "上传失败！";
        }
        jianli=file.getOriginalFilename();
        // 显示图片
        map.remove("shenfenzhengmsg");
        map.put("shenfenzhengmsg", shenfenzhengmsg);
        map.remove("jianlimsg");
        map.put("jianlimsg", jianlimsg);
        map.remove("xueweizhengmsg");
        map.put("xueweizhengmsg", xueweizhengmsg);
        map.remove("xuelizhengmsg");
        map.put("xuelizhengmsg", xuelizhengmsg);
        map.remove("zhichengzhengmsg");
        map.put("zhichengzhengmsg", zhichengzhengmsg);
        map.remove("zongheshuipingfenshumsg");
        map.put("zongheshuipingfenshumsg", zongheshuipingfenshumsg);
        map.remove("shenfenzheng");
        map.put("shenfenzheng",shenfenzheng);
        map.remove("jianli");
        map.put("jianli", jianli);
        map.remove("xueweizheng");
        map.put("xueweizheng", xueweizheng);
        map.remove("xuelizheng");
        map.put("xuelizheng", xuelizheng);
        map.remove("zhichengzheng");
        map.put("zhichengzheng", zhichengzheng);
        map.remove("zongheshuipingfenshu");
        map.put("zongheshuipingfenshu", zongheshuipingfenshu);
        map.put("denglurenxingming",denglurenxingming);
        return "forward:toUpload?jiaoshiid="+jiaoshi_id;
    }

    @RequestMapping("xueweizhengUpload")
    public String xueweizhengUpload(@RequestParam("fileName") MultipartFile file, Map<String, Object> map){

        // 要上传的目标文件存放路径
        String localPath = "C:/Users/Administrator/IdeaProjects/springboot-test2/src/main/resources/static/images";
        // 上传成功或者失败的提示

        String xueweizhenglujing=file.getOriginalFilename();
        int id=Integer.parseInt(jiaoshi_id);
        boolean b = guanliyuanService.updateTeacherxueweizhenglujing(id,xueweizhenglujing);
        System.out.println("shenfenzhenglujing是"+xueweizhenglujing);
        if (FileUtils.upload(file, localPath, file.getOriginalFilename())){
            // 上传成功，给出页面提示
            xueweizhengmsg = "上传成功！";
        }else {
            xueweizhengmsg = "上传失败！";

        }
        xueweizheng=file.getOriginalFilename();
        // 显示图片
        map.remove("shenfenzhengmsg");
        map.put("shenfenzhengmsg", shenfenzhengmsg);
        map.remove("jianlimsg");
        map.put("jianlimsg", jianlimsg);
        map.remove("xueweizhengmsg");
        map.put("xueweizhengmsg", xueweizhengmsg);
        map.remove("xuelizhengmsg");
        map.put("xuelizhengmsg", xuelizhengmsg);
        map.remove("zhichengzhengmsg");
        map.put("zhichengzhengmsg", zhichengzhengmsg);
        map.remove("zongheshuipingfenshumsg");
        map.put("zongheshuipingfenshumsg", zongheshuipingfenshumsg);
        map.remove("shenfenzheng");
        map.put("shenfenzheng",shenfenzheng);
        map.remove("jianli");
        map.put("jianli", jianli);
        map.remove("xueweizheng");
        map.put("xueweizheng", xueweizheng);
        map.remove("xuelizheng");
        map.put("xuelizheng", xuelizheng);
        map.remove("zhichengzheng");
        map.put("zhichengzheng", zhichengzheng);
        map.remove("zongheshuipingfenshu");
        map.put("zongheshuipingfenshu", zongheshuipingfenshu);
        map.put("denglurenxingming",denglurenxingming);
        return "forward:toUpload?jiaoshiid="+jiaoshi_id;
    }

    @RequestMapping("xuelizhengUpload")
    public String xuelizhengUpload(@RequestParam("fileName") MultipartFile file, Map<String, Object> map){

        // 要上传的目标文件存放路径
        String localPath = "C:/Users/Administrator/IdeaProjects/springboot-test2/src/main/resources/static/images";
        // 上传成功或者失败的提示
        String xuelizhenglujing=file.getOriginalFilename();
        int id=Integer.parseInt(jiaoshi_id);
        boolean b = guanliyuanService.updateTeacherxuelizhenglujing(id,xuelizhenglujing);
        System.out.println("shenfenzhenglujing是"+xuelizhenglujing);
        if (FileUtils.upload(file, localPath, file.getOriginalFilename())){
            // 上传成功，给出页面提示
            xuelizhengmsg = "上传成功！";
        }else {
            xuelizhengmsg = "上传失败！";

        }
        xuelizheng=file.getOriginalFilename();

        map.remove("shenfenzhengmsg");
        map.put("shenfenzhengmsg", shenfenzhengmsg);
        map.remove("jianlimsg");
        map.put("jianlimsg", jianlimsg);
        map.remove("xueweizhengmsg");
        map.put("xueweizhengmsg", xueweizhengmsg);
        map.remove("xuelizhengmsg");
        map.put("xuelizhengmsg", xuelizhengmsg);
        map.remove("zhichengzhengmsg");
        map.put("zhichengzhengmsg", zhichengzhengmsg);
        map.remove("zongheshuipingfenshumsg");
        map.put("zongheshuipingfenshumsg", zongheshuipingfenshumsg);
        map.remove("shenfenzheng");
        map.put("shenfenzheng",shenfenzheng);
        map.remove("jianli");
        map.put("jianli", jianli);
        map.remove("xueweizheng");
        map.put("xueweizheng", xueweizheng);
        map.remove("xuelizheng");
        map.put("xuelizheng", xuelizheng);
        map.remove("zhichengzheng");
        map.put("zhichengzheng", zhichengzheng);
        map.remove("zongheshuipingfenshu");
        map.put("zongheshuipingfenshu", zongheshuipingfenshu);
        map.put("denglurenxingming",denglurenxingming);
        return "forward:toUpload?jiaoshiid="+jiaoshi_id;
    }

    @RequestMapping("zhichengzhengUpload")
    public String zhichengzhengUpload(@RequestParam("fileName") MultipartFile file, Map<String, Object> map){

        // 要上传的目标文件存放路径
        String localPath = "C:/Users/Administrator/IdeaProjects/springboot-test2/src/main/resources/static/images";
        // 上传成功或者失败的提示
        String zhichengzhenglujing=file.getOriginalFilename();
        int id=Integer.parseInt(jiaoshi_id);
        boolean b = guanliyuanService.updateTeacherzhichengzhenglujing(id,zhichengzhenglujing);
        System.out.println("shenfenzhenglujing是"+zhichengzhenglujing);
        if (FileUtils.upload(file, localPath, file.getOriginalFilename())){
            // 上传成功，给出页面提示
            zhichengzhengmsg = "上传成功！";
        }else {
            zhichengzhengmsg = "上传失败！";

        }

        zhichengzheng=file.getOriginalFilename();
        map.remove("shenfenzhengmsg");
        map.put("shenfenzhengmsg", shenfenzhengmsg);
        map.remove("jianlimsg");
        map.put("jianlimsg", jianlimsg);
        map.remove("xueweizhengmsg");
        map.put("xueweizhengmsg", xueweizhengmsg);
        map.remove("xuelizhengmsg");
        map.put("xuelizhengmsg", xuelizhengmsg);
        map.remove("zhichengzhengmsg");
        map.put("zhichengzhengmsg", zhichengzhengmsg);
        map.remove("zongheshuipingfenshumsg");
        map.put("zongheshuipingfenshumsg", zongheshuipingfenshumsg);
        map.remove("shenfenzheng");
        map.put("shenfenzheng",shenfenzheng);
        map.remove("jianli");
        map.put("jianli", jianli);
        map.remove("xueweizheng");
        map.put("xueweizheng", xueweizheng);
        map.remove("xuelizheng");
        map.put("xuelizheng", xuelizheng);
        map.remove("zhichengzheng");
        map.put("zhichengzheng", zhichengzheng);
        map.remove("zongheshuipingfenshu");
        map.put("zongheshuipingfenshu", zongheshuipingfenshu);
        map.put("denglurenxingming",denglurenxingming);
        return "forward:toUpload?jiaoshiid="+jiaoshi_id;
    }

    @RequestMapping("zongheshuipingfenshuUpload")
    public String zongheshuipingfenshuUpload(@RequestParam("fileName") MultipartFile file, Map<String, Object> map){

        // 要上传的目标文件存放路径
        String localPath = "C:/Users/Administrator/IdeaProjects/springboot-test2/src/main/resources/static/images";
        // 上传成功或者失败的提示
        String zongheshuipingfenshulujing=file.getOriginalFilename();
        int id=Integer.parseInt(jiaoshi_id);
        boolean b = guanliyuanService.updateTeacherzongheshuipingfenshulujing(id,zongheshuipingfenshulujing);
        System.out.println("shenfenzhenglujing是"+zongheshuipingfenshulujing);
        if (FileUtils.upload(file, localPath, file.getOriginalFilename())){
            // 上传成功，给出页面提示
            zongheshuipingfenshumsg = "上传成功！";
        }else {
            zongheshuipingfenshumsg = "上传失败！";

        }
        zongheshuipingfenshu=file.getOriginalFilename();

        // 显示图片
        map.remove("shenfenzhengmsg");
        map.put("shenfenzhengmsg", shenfenzhengmsg);
        map.remove("jianlimsg");
        map.put("jianlimsg", jianlimsg);
        map.remove("xueweizhengmsg");
        map.put("xueweizhengmsg", xueweizhengmsg);
        map.remove("xuelizhengmsg");
        map.put("xuelizhengmsg", xuelizhengmsg);
        map.remove("zhichengzhengmsg");
        map.put("zhichengzhengmsg", zhichengzhengmsg);
        map.remove("zongheshuipingfenshumsg");
        map.put("zongheshuipingfenshumsg", zongheshuipingfenshumsg);
        map.remove("shenfenzheng");
        map.put("shenfenzheng",shenfenzheng);
        map.remove("jianli");
        map.put("jianli", jianli);
        map.remove("xueweizheng");
        map.put("xueweizheng", xueweizheng);
        map.remove("xuelizheng");
        map.put("xuelizheng", xuelizheng);
        map.remove("zhichengzheng");
        map.put("zhichengzheng", zhichengzheng);
        map.remove("zongheshuipingfenshu");
        map.put("zongheshuipingfenshu", zongheshuipingfenshu);
        map.put("denglurenxingming",denglurenxingming);
        return "forward:toUpload?jiaoshiid="+jiaoshi_id;
    }

    /**
     * 显示单张图片
     * @return
     */
    @RequestMapping("show")
    public ResponseEntity showPhotos(String fileName){
        System.out.println("show fileName"+fileName);
        try {
            // 由于是读取本机的文件，file是一定要加上的， path是在application配置文件中的路径
            System.out.println("path是"+path);
            return ResponseEntity.ok(resourceLoader.getResource("file:" + path +"/"+ fileName));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @RequestMapping("fujianshuaxin")
    @ResponseBody
    public Map<String,Object> fujianshuaxin(HttpServletRequest request, HttpServletResponse response)
    {

        System.out.println("附件信息信息展示测试 ajax ");
        map.put("denglurenxingming",denglurenxingming);
        return map;
    }
    /*学生管理模块*/
    @RequestMapping ("xueshengguanli")/*督导记录页面展示*/
    public String xueshengguanli(Map<String,Object> data){
        List<Banji> banjiList = guanliyuanService.selectAllbanji();
        data.put("banjiList",banjiList);
        return "guanliyuan/xueshengguanli";
    }
    /*学生管理分页*/
    @RequestMapping("xueshengfenye")
    @ResponseBody
    public Map<String,Object> xueshengfenye(HttpServletRequest request,HttpServletResponse response){

        System.out.println("学生管理分页测试 ajax ");
        String currentPageStr=request.getParameter("currentPage");
        String rowStr = request.getParameter("row");
        String search_id = request.getParameter("search_id");
        String search_yonghuming = request.getParameter("search_yonghuming");
        String search_xingming = request.getParameter("search_xingming");
        int currentPage = 0;//当前页码，默认为第一页
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }
        int id = 0;//当前页码，默认为第一页
        if (search_id != null && search_id.length() > 0) {
            id = Integer.parseInt(search_id);
        } else {
            id = 0;
        }
        System.out.println("当前页码currentPage"+currentPage);
        int row=0;//每一页显示的条数，默认为5
        if(rowStr!=null&&rowStr.length()>0){
            row=Integer.parseInt(rowStr);
        }else {
            row=5;
        }
        //System.out.println(username+password);
        PageBean<Xuesheng> pb = guanliyuanService.xueshengfindBypage(currentPage, row,id,search_yonghuming,search_xingming);
        map.put("currentPage",pb.getCurrentPage());
        map.put("totalPage",pb.getTotalPage());
        map.put("totalCount",pb.getTotalCount());
        map.put("list",pb.getList());
        map.put("denglurenxingming",guanliyuan.getXingming());
        return map;
    }
    /*学生管理修改*/
    @RequestMapping("xueshengxiugai")
    @ResponseBody
    public  Map<String,Object> xueshengxiugai(@Valid Xuesheng xuesheng, BindingResult bindingResult){
        String result ="";
        if(bindingResult.hasErrors()){
            System.out.println("学生修改有问题");
            result="fail";
//            //return bindingResult.getFieldError().getDefaultMessage();
        }else{
            System.out.println("学生id是"+xuesheng.getXuehao());
            if (xuesheng.getXuehao()==null){
                int nextid = guanliyuanService.selectxueshengLastid();
                xuesheng.setXuehao(nextid);
            }
            Xuesheng xuesheng1 = guanliyuanService.selectByxueshengid(xuesheng.getXuehao());
            User selectuserid=new User();
            User selectuseryonghuming = guanliyuanService.selectuseryonghuming(xuesheng.getYonghuming());
            if (xuesheng1!=null){
//                selectuserid = guanliyuanService.selectuserid(xuesheng1.getYonghuming(), xuesheng1.getMima());
                if (selectuseryonghuming==null||xuesheng1.getYonghuming().equals(xuesheng.getYonghuming())){
                    selectuserid.setYonghuming(xuesheng.getYonghuming());
                    selectuserid.setMima(xuesheng.getMima());
                    selectuserid.setShenfen("xuesheng");
                    guanliyuanService.updateuserinfo(selectuserid);
                    boolean updateinfo = guanliyuanService.updateXueshenginfo(xuesheng);
                    if (updateinfo) {
                        result="修改成功";
                    }
                    else {
                        result="修改失败";
                    }
                }else {
                    result="用户名已存在";
                }
            }else {
                if (selectuseryonghuming==null){
                    selectuserid.setYonghuming(xuesheng.getYonghuming());
                    selectuserid.setMima(xuesheng.getMima());
                    selectuserid.setShenfen("xuesheng");
                    guanliyuanService.saveuserinfo(selectuserid);
                    boolean inserinfo = guanliyuanService.insertXueshenginfo(xuesheng);
                    if (inserinfo) {
                        result="添加成功";
                    }else {
                        result="添加失败";
                    }
                }else {
                    result="用户名已存在";
                }
            }
        }
        map.put("result",result);
        return map;
    }
    /*学生信息删除*/
    @RequestMapping("xueshengdel")
    public String xueshengdel(@RequestParam("xueshengid") String xueshengid){
        if (xueshengid!=null&&xueshengid!=""){
            guanliyuanService.xueshengdel(Integer.parseInt(xueshengid));
        }
        return "redirect:xueshengguanli";
    }
    /*课程管理模块*************************************************/
    @RequestMapping ("kechengguanli")/*督导记录页面展示*/
    public String kechengguanli(){
        return "guanliyuan/kechengguanli";
    }
    /*课程管理分页*/
    @RequestMapping("kechengfenye")
    @ResponseBody
    public Map<String,Object> kechengfenye(HttpServletRequest request,HttpServletResponse response){

        System.out.println("学生管理分页测试 ajax ");
        String currentPageStr=request.getParameter("currentPage");
        String rowStr = request.getParameter("row");
        String idStr = request.getParameter("id");
        String kechenghaoStr = request.getParameter("kechenghao");
        String kechengmingcheng = request.getParameter("kechengmingcheng");
        int currentPage = 0;//当前页码，默认为第一页
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }
        int id = 0;//当前页码，默认为第一页
        if (idStr != null && idStr.length() > 0) {
            id = Integer.parseInt(idStr);
        } else {
            id = 0;
        }

        System.out.println("当前页码currentPage"+currentPage);
        int row=0;//每一页显示的条数，默认为5
        if(rowStr!=null&&rowStr.length()>0){
            row=Integer.parseInt(rowStr);
        }else {
            row=5;
        }
        //System.out.println(username+password);
        PageBean<Kecheng> pb = guanliyuanService.kechengfindBypage(currentPage, row,id,kechenghaoStr,kechengmingcheng);
        map.put("currentPage",pb.getCurrentPage());
        map.put("totalPage",pb.getTotalPage());
        map.put("totalCount",pb.getTotalCount());
        map.put("list",pb.getList());
        map.put("denglurenxingming",guanliyuan.getXingming());
        return map;
    }
    /*课程管理修改*/
    @RequestMapping("kechengxiugai")
    @ResponseBody
    public  Map<String,Object> kechengxiugai(@Valid Kecheng kecheng, BindingResult bindingResult){
        String result ="";
        if(bindingResult.hasErrors()){
            System.out.println("学生修改有问题");
            result="fail";
//            //return bindingResult.getFieldError().getDefaultMessage();
        }else{
            System.out.println("课程id是"+kecheng.getId());
            if (kecheng.getId()==null){
                int nextid = guanliyuanService.selectkechengLastid();
                kecheng.setId(nextid);
            }
            Kecheng kecheng1 = guanliyuanService.selectBykechengid(kecheng.getId());
            if (kecheng1!=null){
                kecheng.setJiaoxuedaganglujing(kecheng1.getJiaoxuedaganglujing());
                boolean updateinfo = guanliyuanService.updateKechenginfo(kecheng);
                if (updateinfo) {
                    result="修改成功";
                }
                else {
                    result="修改失败";
                }
            }else {
                boolean inserinfo = guanliyuanService.insertKechenginfo(kecheng);
                if (inserinfo) {
                    result="添加成功";
                }else {
                    result="添加失败";
                }
            }
        }
        map.put("result",result);
        return map;
    }
    @RequestMapping("jiaoxueUpload")
    public String kechengUpload(@RequestParam("kechengid") String kechengid){
        kecheng_id=kechengid;
        return "guanliyuan/jiaoxuedagang";
    }
    @RequestMapping("jiaoxuedagangUpload")
    public String jiaoxuedagangUpload(@RequestParam("fileName") MultipartFile file, Map<String, Object> map){
        // 要上传的目标文件存放路径
        String localPath = "C:/Users/Administrator/IdeaProjects/springboot-test2/src/main/resources/static/images";

        // 上传成功或者失败的提示
        String jiaoxuedagang = file.getOriginalFilename();
        int id=Integer.parseInt(kecheng_id);
        String jiaoxuedagangmsg="";
        boolean b = guanliyuanService.updateKechengjiaogxuedaganglujing(id,jiaoxuedagang);
        System.out.println("shenfenzhenglujing是"+jiaoxuedagang);
        if (FileUtils.upload(file, localPath, file.getOriginalFilename())&&b){
            // 上传成功，给出页面提示
            jiaoxuedagangmsg = "上传成功！";
        }else {
            jiaoxuedagangmsg = "上传失败！";
        }
        // 显示图片

        map.remove("jiaoxuedagangmsg");
        map.put("jiaoxuedagangmsg", jiaoxuedagangmsg);
        map.remove("jiaoxuedagang");
        map.put("jiaoxuedagang",jiaoxuedagang);
        map.put("denglurenxingming",denglurenxingming);
        return "forward:jiaoxueUpload?kechengid="+kecheng_id;
    }
    /*课程信息删除*/
    @RequestMapping("kechengdel")
    public String kechengdel(@RequestParam("kechengid") String kechengid){
        if (kechengid!=null&&kechengid!=""){
            guanliyuanService.kechengdel(Integer.parseInt(kechengid));
        }
        return "redirect:kechengguanli";
    }
    /*培训资料管理模块*************************************************/
    @RequestMapping ("peixunziliaoguanli")
    public String peixunziliao(){
        return "guanliyuan/peixunziliaoguanli";
    }
    /*资料管理分页*/
    @RequestMapping("ziliaofenye")
    @ResponseBody
    public Map<String,Object> ziliaofenye(HttpServletRequest request,HttpServletResponse response){

        System.out.println("资料管理分页测试 ajax ");
        String currentPageStr=request.getParameter("currentPage");
        String rowStr = request.getParameter("row");
        String idStr = request.getParameter("id");
        String ziliaomingcheng = request.getParameter("ziliaomingcheng");

        int currentPage = 0;//当前页码，默认为第一页
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }
        int id = 0;//当前页码，默认为第一页
        if (idStr != null && idStr.length() > 0) {
            id = Integer.parseInt(idStr);
        } else {
            id = 0;
        }
        System.out.println("当前页码currentPage"+currentPage);
        int row=0;//每一页显示的条数，默认为5
        if(rowStr!=null&&rowStr.length()>0){
            row=Integer.parseInt(rowStr);
        }else {
            row=5;
        }
        //System.out.println(username+password);
        PageBean<Gangqianpeixunziliao> pb = guanliyuanService.GangqianpeixunziliaoBypage(currentPage, row,id,ziliaomingcheng);
        map.put("currentPage",pb.getCurrentPage());
        map.put("totalPage",pb.getTotalPage());
        map.put("totalCount",pb.getTotalCount());
        map.put("list",pb.getList());
        map.put("denglurenxingming",guanliyuan.getXingming());
        return map;
    }
    /*资料管理修改*/
    @RequestMapping("ziliaoxiugai")
    @ResponseBody
    public Map<String,Object> ziliaoxiugai(@Valid Gangqianpeixunziliao gangqianpeixunziliao, BindingResult bindingResult){
        String result ="";
        if(bindingResult.hasErrors()){
            System.out.println("资料修改有问题");
            result="fail";
//            //return bindingResult.getFieldError().getDefaultMessage();
        }else{
            System.out.println("资料id是"+gangqianpeixunziliao.getId());
            if (gangqianpeixunziliao.getId()==null){
                int nextid = guanliyuanService.selectgangqianpeixunziliaoLastid();
                gangqianpeixunziliao.setId(nextid);
            }
            Gangqianpeixunziliao kecheng1 = guanliyuanService.selectByGangqianpeixunziliaoid(gangqianpeixunziliao.getId());
            if (kecheng1!=null){
                gangqianpeixunziliao.setZiliaolujing(kecheng1.getZiliaolujing());
                boolean updateinfo = guanliyuanService.updateGangqianpeixunziliaoinfo(gangqianpeixunziliao);
                if (updateinfo) {
                    result="修改成功";
                }
                else {
                    result="修改失败";
                }
            }else {
                boolean inserinfo = guanliyuanService.insertGangqianpeixunziliaoinfo(gangqianpeixunziliao);
                if (inserinfo) {
                    result="添加成功";
                }else {
                    result="添加失败";
                }
            }
        }
        map.put("result",result);
        return map;
    }
    @RequestMapping("ziliaolujingUpload")
    public String ziliaolujingUpload(@RequestParam("ziliaoid") String ziliaoid){
        ziliao_id=ziliaoid;
        return "guanliyuan/peixunziliao";
    }
    @RequestMapping("ziliaoUpload")
    public String ziliaoUpload(@RequestParam("fileName") MultipartFile file, Map<String, Object> map){
        // 要上传的目标文件存放路径
        String localPath = "C:/Users/Administrator/IdeaProjects/springboot-test2/src/main/resources/static/images";

        // 上传成功或者失败的提示
        String ziliao = file.getOriginalFilename();
        int id=Integer.parseInt(ziliao_id);
        String ziliaomsg="";
        boolean b = guanliyuanService.updateGangqianpeixunziliaolujing(id,ziliao);
        System.out.println("资料路径是"+ziliao);
        if (FileUtils.upload(file, localPath, file.getOriginalFilename())&&b){
            // 上传成功，给出页面提示
            ziliaomsg = "上传成功！";
        }else {
            ziliaomsg = "上传失败！";
        }
        // 显示图片

        map.remove("ziliaomsg");
        map.put("ziliaomsg", ziliaomsg);
        map.remove("ziliao");
        map.put("ziliao",ziliao);
        map.put("denglurenxingming",denglurenxingming);
        return "forward:ziliaolujingUpload?ziliaoid="+ziliao_id;
    }
    /*资料信息删除*/
    @RequestMapping("ziliaodel")
    public String ziliaodel(@RequestParam("ziliaoid") String ziliaoid){
        if (ziliaoid!=null&&ziliaoid!=""){
            guanliyuanService.ziliaodel(Integer.parseInt(ziliaoid));
        }
        return "redirect:peixunziliaoguanli";
    }
    /*班级管理模块*************************************************/
    @RequestMapping ("banjiguanli")
    public String banjiguanli(){
        return "guanliyuan/banjiguanli";
    }
    /*班级管理分页*/
    @RequestMapping("banjifenye")
    @ResponseBody
    public Map<String,Object> banjifenye(HttpServletRequest request,HttpServletResponse response){

        System.out.println("资料管理分页测试 ajax ");
        String currentPageStr=request.getParameter("currentPage");
        String rowStr = request.getParameter("row");
        String idStr = request.getParameter("id");
        String banjimingcheng = request.getParameter("banjimingcheng");
        int currentPage = 0;//当前页码，默认为第一页
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }
        int id = 0;//当前页码，默认为第一页
        if (idStr != null && idStr.length() > 0) {
            id = Integer.parseInt(idStr);
        } else {
            id = 0;
        }
        System.out.println("当前页码currentPage"+currentPage);
        int row=0;//每一页显示的条数，默认为5
        if(rowStr!=null&&rowStr.length()>0){
            row=Integer.parseInt(rowStr);
        }else {
            row=5;
        }
        //System.out.println(username+password);
        PageBean<Banji> pb = guanliyuanService.banjifindBypage(currentPage, row,id,banjimingcheng);
        map.put("currentPage",pb.getCurrentPage());
        map.put("totalPage",pb.getTotalPage());
        map.put("totalCount",pb.getTotalCount());
        map.put("list",pb.getList());
        map.put("denglurenxingming",guanliyuan.getXingming());
        return map;
    }
    /*班级管理修改*/
    @RequestMapping("banjixiugai")
    @ResponseBody
    public Map<String,Object> banjixiugai(@Valid Banji banji, BindingResult bindingResult){
        String result ="";
        if(bindingResult.hasErrors()){
            System.out.println("班级修改有问题");
            result="fail";
//            //return bindingResult.getFieldError().getDefaultMessage();
        }else{
            System.out.println("资料id是"+banji.getId());
            if (banji.getId()==null){
                int nextid = guanliyuanService.selectbanjiLastid();
                banji.setId(nextid);
            }
            Banji kecheng1 = guanliyuanService.selectBybanjiid(banji.getId());
            if (kecheng1!=null){
                boolean updateinfo = guanliyuanService.updatebanjiinfo(banji);
                if (updateinfo) {
                    result="修改成功";
                }
                else {
                    result="修改失败";
                }
            }else {
                boolean inserinfo = guanliyuanService.insertbanjiinfo(banji);
                if (inserinfo) {
                    result="添加成功";
                }else {
                    result="添加失败";
                }
            }
        }
        map.put("result",result);
        return map;
    }
    /*班级信息删除*/
    @RequestMapping("banjidel")
    public String banjidel(@RequestParam("banjiid") String banjiid){
        if (banjiid!=null&&banjiid!=""){
            guanliyuanService.banjidel(Integer.parseInt(banjiid));
        }
        return "redirect:banjiguanli";
    }
    /*上课班级管理模块*************************************************/
    /*获取近5年的学期*/
    public List<Xueqi> xueqiList(){
        String year = getYear();
        int nian = Integer.parseInt(year);
        List<Xueqi> xueqiList= new ArrayList<Xueqi>();
        for (int i =(nian-2) ; i <(nian+2) ; i++) {
            Xueqi xueqi1=new Xueqi();
            Xueqi xueqi2=new Xueqi();
            xueqi1.setDate((i+"-08-01"));
            xueqi1.setText(i+"-"+(i+1)+"-"+1);
            xueqi2.setDate((i+1)+"-03-01");
            xueqi2.setText(i+"-"+(i+1)+"-"+2);
            xueqiList.add(xueqi1);
            xueqiList.add(xueqi2);
        }
        return xueqiList;
    }
    @RequestMapping ("shangkeguanli")
        public String shangkebanjiguanli(Map<String,Object> data){
        List<Banji> banjiList = guanliyuanService.selectAllbanji();
        List<Kecheng> kechengList = guanliyuanService.selectAllkecheng();
        List<Jiaoshi> jiaoshiList = guanliyuanService.selectAlljiaoshi();
        List<Xueqi> xueqiList = xueqiList();
        data.put("xueqiList",xueqiList);
        data.put("banjiList",banjiList);
        data.put("kechengList",kechengList);
        data.put("jiaoshiList",jiaoshiList);
        //data.put("xueqi",xueqiList.get(3).getText());
        return "guanliyuan/shangkebanjiguanli";
    }
    /*上课班级管理分页*/
    @RequestMapping("shangkebanjifenye")
    @ResponseBody
    public Map<String,Object> shangkebanjifenye(HttpServletRequest request,HttpServletResponse response){

        System.out.println("上课班级管理分页测试 ajax ");
        String currentPageStr=request.getParameter("currentPage");
        String rowStr = request.getParameter("row");
        String search_jiaoshixingmingStr=request.getParameter("search_jiaoshixingming");
        String search_kechengmingchengStr = request.getParameter("search_kechengmingcheng");
        String search_xueqi=request.getParameter("search_xueqi");
        if (search_xueqi.equals("")&&search_kechengmingchengStr.equals("")&&search_jiaoshixingmingStr.equals("")){
            map.clear();
            map.put("list",null);
            map.put("denglurenxingming",denglurenxingming);
            return map;
        }
        else {
            int currentPage = 0;//当前页码，默认为第一页
            //判断页码
            if (currentPageStr != null && currentPageStr.length() > 0) {
                currentPage = Integer.parseInt(currentPageStr);
            } else {
                currentPage = 1;
            }
            System.out.println("当前页码currentPage" + currentPage);
            int row = 0;//每一页显示的条数，默认为5
            if (rowStr != null && rowStr.length() > 0) {
                row = Integer.parseInt(rowStr);
            } else {
                row = 5;
            }


            String kechengmingcheng = null;
            if (search_kechengmingchengStr != null && !search_kechengmingchengStr.equals("请选择") && search_kechengmingchengStr.length() > 0) {
                kechengmingcheng = search_kechengmingchengStr;
            } else {
                kechengmingcheng = null;
            }
            //System.out.println(username+password);
            PageBean<Shangkebanji> pb = guanliyuanService.shangkebanjifindBypage(currentPage, row, search_jiaoshixingmingStr, kechengmingcheng, search_xueqi);
            map.put("currentPage", pb.getCurrentPage());
            map.put("totalPage", pb.getTotalPage());
            map.put("totalCount", pb.getTotalCount());
            map.put("list", pb.getList());
            map.put("denglurenxingming", guanliyuan.getXingming());
            return map;
        }
    }
    /*上课班级管理修改*/
    @RequestMapping("shangkebanjixiugai")
    @ResponseBody
    public Map<String,Object> shangkebanjixiugai(@Valid Shangkebanji shangkebanji, BindingResult bindingResult){
        String result ="";
        if(bindingResult.hasErrors()){
            System.out.println("班级修改有问题");
            result="fail";
//            //return bindingResult.getFieldError().getDefaultMessage();
        }else{
            System.out.println("上课班级id是"+shangkebanji.getId());
            if (shangkebanji.getId()==null){
                int nextid = guanliyuanService.selectshangkebanjiLastid();
                shangkebanji.setId(nextid);
            }
            Shangkebanji kecheng1 = guanliyuanService.selectByshangkebanjiid(shangkebanji.getId());
            if (kecheng1!=null){
                boolean updateinfo = guanliyuanService.updateshangkebanjiinfo(shangkebanji);
                if (updateinfo) {
                    result="修改成功";
                    System.out.println("修改成功");
                }
                else {
                    result="修改失败";
                    System.out.println("修改失败");

                }
            }else {
                boolean inserinfo = guanliyuanService.insertshangkebanjiinfo(shangkebanji);
                if (inserinfo) {
                    result="添加成功";
                    System.out.println("添加成功");
                }else {
                    result="添加失败";
                    System.out.println("添加失败");
                }
            }
        }
        map.put("result",result);
        return map;
    }
    /*上课班级信息删除*/
    @RequestMapping("shangkebanjidel")
    public String shangkebanjidel(@RequestParam("shangkebanjiid") String shangkebanjiid){
        if (shangkebanjiid!=null&&shangkebanjiid!=""){
            guanliyuanService.shangkebanjidel(Integer.parseInt(shangkebanjiid));
        }
        return "redirect:shangkeguanli";
    }
    /*班级添加****/
    @RequestMapping("shangkebanjitianjia")
    public String shangkebanjitianjia(HttpServletRequest request,HttpServletResponse response){
        String banjimingcheng1=request.getParameter("banjimingcheng1").trim();
        String banjimingcheng2=request.getParameter("banjimingcheng2").trim();
        String banjimingcheng3=request.getParameter("banjimingcheng3").trim();
        String banjiid1=request.getParameter("banjiid1").trim();
        String banjiid2=request.getParameter("banjiid2").trim();
        String banjiid3=request.getParameter("banjiid3").trim();
        System.out.println("banjiid3"+banjiid3);
        String banjimingcheng="";
        if (!banjimingcheng1.equals(null)&&!banjimingcheng1.equals("")&&!banjiid1.equals("请选择")){
            banjimingcheng=banjimingcheng+banjimingcheng1;
        }
        if (!banjimingcheng2.equals(null)&&!banjimingcheng2.equals("")&&!banjiid2.equals("请选择")){
            banjimingcheng=banjimingcheng+"/"+banjimingcheng2;
        }
        if (!banjimingcheng3.equals(null)&&!banjimingcheng3.equals("")&&!banjiid3.equals("请选择")){
            banjimingcheng=banjimingcheng+"/"+banjimingcheng3;
        }
        System.out.println("班级名称"+banjimingcheng);
        Banji banji=new Banji();
        banji.setBanjimingcheng(banjimingcheng);
        boolean insertbanjiinfo = guanliyuanService.insertbanjiinfo(banji);
        if (insertbanjiinfo){
            System.out.println("上课班级添加成功");
        }
        return "redirect:shangkebanjiguanli";
    }
    /*学位管理模块**********************************************/
    @RequestMapping ("xueweiguanli")/*处罚等级管理页面展示*/
    public String xueweiguanli(){
        return "guanliyuan/xueweiguanli";
    }
    /*学位分页*/
    @RequestMapping("xueweifenye")
    @ResponseBody
    public Map<String,Object> xueweifenye(HttpServletRequest request,HttpServletResponse response){
//        Map<String, Object> map = new HashMap<String, Object>();
        //System.out.println("处罚等级分页测试 ajax ");
        List<Xuewei> xueweis = guanliyuanService.selectAllxuewei();
        map.put("denglurenxingming",denglurenxingming);
        map.put("list",xueweis);
        return map;
    }
    /*学位修改*/
    @RequestMapping("xueweixiugai")
    @ResponseBody
    public Map<String,Object> xueweixiugai(@Valid Xuewei xuewei, BindingResult bindingResult){
        String result ="";
        if(bindingResult.hasErrors()){
            System.out.println("xuewi修改有问题");
            result="fail";
            ////return bindingResult.getFieldError().getDefaultMessage();
        }else{
            System.out.println("xuewei id是"+xuewei.getId());
            if (xuewei.getId()==null){
                int nextid = guanliyuanService.selectxueweiLastid();
                xuewei.setId(nextid);
            }
            Xuewei xuewei1 = guanliyuanService.selectByxueweiid(xuewei.getId());
            if (xuewei1!=null){
                boolean updateinfo = guanliyuanService.updatexueweiinfo(xuewei);
                if (updateinfo) {
                    result="修改成功";
                }
                else {
                    result="修改失败";
                }
            }else {
                boolean inserinfo = guanliyuanService.insertxueweiinfo(xuewei);
                if (inserinfo) {
                    result="添加成功";
                }else {
                    result="添加失败";
                }
            }
        }
        map.put("result",result);
        return map;
    }

    /*学位信息信息删除*/
    @RequestMapping("xueweidel")
    public String xueweidel(@RequestParam("xueweiid") String xueweiid){
        if (xueweiid!=null&&xueweiid!=""){
            guanliyuanService.xueweidel(Integer.parseInt(xueweiid));
        }
        return "redirect:xueweiguanli";
    }
    /*职称管理模块**********************************************/
    @RequestMapping ("zhichengguanli")/*处罚等级管理页面展示*/
    public String zhichengguanli(){
        return "guanliyuan/zhichengguanli";
    }
    /*职称分页*/
    @RequestMapping("zhichengfenye")
    @ResponseBody
    public Map<String,Object> zhichengfenye(HttpServletRequest request,HttpServletResponse response){
//        Map<String, Object> map = new HashMap<String, Object>();
        //System.out.println("处罚等级分页测试 ajax ");
        List<Zhicheng> zhichengs = guanliyuanService.selectAllzhicheng();
        map.put("denglurenxingming",denglurenxingming);
        map.put("list",zhichengs);
        return map;
    }
    /*职称修改*/
    @RequestMapping("zhichengxiugai")
    @ResponseBody
    public Map<String,Object> zhichengxiugai(@Valid Zhicheng zhicheng, BindingResult bindingResult){
        String result ="";
        if(bindingResult.hasErrors()){
            System.out.println("xuewi修改有问题");
            result="fail";
            //return bindingResult.getFieldError().getDefaultMessage();
        }else{
            System.out.println("zhicheng id是"+zhicheng.getId());
            if (zhicheng.getId()==null){
                int nextid = guanliyuanService.selectzhichengLastid();
                zhicheng.setId(nextid);
            }
            Zhicheng zhicheng1 = guanliyuanService.selectByzhichengid(zhicheng.getId());
            if (zhicheng1!=null){
                boolean updateinfo = guanliyuanService.updatezhichenginfo(zhicheng);
                if (updateinfo) {
                    result="修改成功";
                }
                else {
                    result="修改失败";
                }
            }else {
                boolean inserinfo = guanliyuanService.insertzhichenginfo(zhicheng);
                if (inserinfo) {
                    result="添加成功";
                }else {
                    result="添加失败";
                }
            }
        }
        map.put("result",result);
        return map;
    }

    /*职称信息信息删除*/
    @RequestMapping("zhichengdel")
    public String zhichengdel(@RequestParam("zhichengid") String zhichengid){
        if (zhichengid!=null&&zhichengid!=""){
            guanliyuanService.zhichengdel(Integer.parseInt(zhichengid));
        }
        return "redirect:zhichengguanli";
    }
    /*工作年限管理模块**********************************************/
    @RequestMapping ("gongzuonianxianguanli")
    public String gongzuonianxianguanli(){
        return "guanliyuan/gongzuonianxianguanli";
    }
    /*工作年限分页*/
    @RequestMapping("gongzuonianxianfenye")
    @ResponseBody
    public Map<String,Object> gongzuonianxianfenye(HttpServletRequest request,HttpServletResponse response){
        List<Gongzuonianxian> gongzuonianxians = guanliyuanService.selectAllgongzuonianxian();
        map.put("denglurenxingming",denglurenxingming);
        map.put("list",gongzuonianxians);
        return map;
    }
    /*工作年限修改*/
    @RequestMapping("gongzuonianxianxiugai")
    @ResponseBody
    public Map<String,Object> gongzuonianxianxiugai(@Valid Gongzuonianxian gongzuonianxian, BindingResult bindingResult){
        String result ="";
        if(bindingResult.hasErrors()){
            System.out.println("gongzuonianxian修改有问题");
            result="fail";
            //return bindingResult.getFieldError().getDefaultMessage();
        }else{
            System.out.println("gongzuonianxian id是"+gongzuonianxian.getId());
            if (gongzuonianxian.getId()==null){
                int nextid = guanliyuanService.selectgongzuonianxianLastid();
                gongzuonianxian.setId(nextid);
            }
            Gongzuonianxian gongzuonianxian1 = guanliyuanService.selectBygongzuonianxianid(gongzuonianxian.getId());
            if (gongzuonianxian1!=null){
                boolean updateinfo = guanliyuanService.updategongzuonianxianinfo(gongzuonianxian);
                if (updateinfo) {
                    result="修改成功";
                }
                else {
                    result="修改失败";
                }
            }else {
                boolean inserinfo = guanliyuanService.insertgongzuonianxianinfo(gongzuonianxian);
                if (inserinfo) {
                    result="添加成功";
                }else {
                    result="添加失败";
                }
            }
        }
        map.put("result",result);
        return map;
    }

    /*工作年限信息信息删除*/
    @RequestMapping("gongzuonianxiandel")
    public String gongzuonianxiandel(@RequestParam("gongzuonianxianid") String gongzuonianxianid){
        if (gongzuonianxianid!=null&&gongzuonianxianid!=""){
            guanliyuanService.gongzuonianxiandel(Integer.parseInt(gongzuonianxianid));
        }
        return "redirect:gongzuonianxianguanli";
    }
    /*综合结果管理模块**********************************************/
    @RequestMapping ("zonghepingdingjieguoguanli")
    public String zonghepingdingjieguoguanli(){
        return "guanliyuan/zonghepingdingjieguoguanli";
    }
    /*综合结果分页*/
    @RequestMapping("zonghepingdingjieguofenye")
    @ResponseBody
    public Map<String,Object> zonghepingdingjieguofenye(HttpServletRequest request,HttpServletResponse response){
        List<Zonghepingdingjieguo> zonghepingdingjieguos = guanliyuanService.selectAllzonghepingdingjieguo();
        map.put("denglurenxingming",denglurenxingming);
        map.put("list",zonghepingdingjieguos);
        return map;
    }
    /*综合结果修改*/
    @RequestMapping("zonghepingdingjieguoxiugai")
    @ResponseBody
    public Map<String,Object> zonghepingdingjieguoxiugai(@Valid Zonghepingdingjieguo zonghepingdingjieguo, BindingResult bindingResult){
        String result ="";
        if(bindingResult.hasErrors()){
            System.out.println("zonghepingdingjieguo修改有问题");
            result="fail";
            //return bindingResult.getFieldError().getDefaultMessage();
        }else{
            System.out.println("zonghepingdingjieguo id是"+zonghepingdingjieguo.getId());
            if (zonghepingdingjieguo.getId()==null){
                int nextid = guanliyuanService.selectzonghepingdingjieguoLastid();
                zonghepingdingjieguo.setId(nextid);
            }
            Zonghepingdingjieguo zonghepingdingjieguo1 = guanliyuanService.selectByzonghepingdingjieguoid(zonghepingdingjieguo.getId());
            if (zonghepingdingjieguo1!=null){
                boolean updateinfo = guanliyuanService.updatezonghepingdingjieguoinfo(zonghepingdingjieguo);
                if (updateinfo) {
                    result="修改成功";
                }
                else {
                    result="修改失败";
                }
            }else {
                boolean inserinfo = guanliyuanService.insertzonghepingdingjieguoinfo(zonghepingdingjieguo);
                if (inserinfo) {
                    result="添加成功";
                }else {
                    result="添加失败";
                }
            }
        }
        map.put("result",result);
        return map;
    }

    /*工作年限信息信息删除*/
    @RequestMapping("zonghepingdingjieguodel")
    public String zonghepingdingjieguodel(@RequestParam("zonghepingdingjieguoid") String zonghepingdingjieguoid){
        if (zonghepingdingjieguoid!=null&&zonghepingdingjieguoid!=""){
            guanliyuanService.zonghepingdingjieguodel(Integer.parseInt(zonghepingdingjieguoid));
        }
        return "redirect:zonghepingdingjieguoguanli";
    }

    /*专家管理模块*******************************************************/
    @RequestMapping ("zhuajiaguanli")/*督导记录页面展示*/
    public String zhuanjiaguanli(){
        return "guanliyuan/zhuanjiaguanli";
    }
    /*专家管理分页*/
    @RequestMapping("zhuanjiafenye")
    @ResponseBody
    public Map<String,Object> zhuanjiafenye(HttpServletRequest request,HttpServletResponse response){

        System.out.println("zhuanjia管理分页测试 ajax ");
        String currentPageStr=request.getParameter("currentPage");
        String rowStr = request.getParameter("row");
        String  idStr = request.getParameter("search_zhuanjiaid");
        String xingmingStr = request.getParameter("search_zhuanjiaxingming");
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
        int id=0;
        if(idStr!=null&&idStr.length()>0){
            id=Integer.parseInt(idStr);
        }else {
            id=0;
        }
        //System.out.println(username+password);
        PageBean<Zhuanjia> pb = guanliyuanService.zhuanjiafindBypage(currentPage, row,id,xingmingStr);
        map.put("currentPage",pb.getCurrentPage());
        map.put("totalPage",pb.getTotalPage());
        map.put("totalCount",pb.getTotalCount());
        map.put("list",pb.getList());
        map.put("denglurenxingming",guanliyuan.getXingming());
        return map;
    }
    /*专家管理修改*/
    @RequestMapping("zhuanjiaxiugai")
    @ResponseBody
    public Map<String,Object> zhuanjiaxiugai(@Valid Zhuanjia zhuanjia, BindingResult bindingResult){
        String result ="";
        if(bindingResult.hasErrors()){
            System.out.println("zhuanjia修改有问题");
            result="fail";
            //return bindingResult.getFieldError().getDefaultMessage();
        }else{
            System.out.println("专家是"+zhuanjia);
            if (zhuanjia.getId()==null){
                int nextid = guanliyuanService.selectzhuanjiaLastid();
                zhuanjia.setId(nextid);
            }
            Zhuanjia zhuanjia1 = guanliyuanService.selectByzhuanjiaid(zhuanjia.getId());
            User selectuserid=new User();
            User selectuseryonghuming = guanliyuanService.selectuseryonghuming(zhuanjia.getYonghuming());
            if (zhuanjia1!=null){
                selectuserid = guanliyuanService.selectuserid(zhuanjia1.getYonghuming(),zhuanjia1.getMima());
                if (selectuseryonghuming==null||zhuanjia1.getYonghuming().equals(zhuanjia.getYonghuming())){
                    selectuserid.setYonghuming(zhuanjia.getYonghuming());
                    selectuserid.setMima(zhuanjia.getMima());
                    selectuserid.setShenfen("zhuanjia");
                    guanliyuanService.updateuserinfo(selectuserid);
                    boolean updateinfo = guanliyuanService.updateZhuanjiainfo(zhuanjia);
                    if (updateinfo) {
                        result="修改成功";
                    }
                    else {
                        result="修改失败";
                    }
                }else {
                    result="用户名已存在";
                }
            }else {
                if (selectuseryonghuming==null){
                    selectuserid.setYonghuming(zhuanjia.getYonghuming());
                    selectuserid.setMima(zhuanjia.getMima());
                    selectuserid.setShenfen("zhuanjia");
                    guanliyuanService.saveuserinfo(selectuserid);
                    boolean inserinfo = guanliyuanService.insertZhuanjiainfo(zhuanjia);
                    if (inserinfo) {
                        result="添加成功";
                    }else {
                        result="添加失败";
                    }
                }else {
                    result="用户名已存在";
                }
            }
        }
        map.put("result",result);
        return map;
    }
    /*专家信息删除*/
    @RequestMapping("zhuanjiadel")
    public String zhuanjiadel(@RequestParam("zhuanjiaid") String zhuanjiaid){
        if (zhuanjiaid!=null&&zhuanjiaid!=""){
            guanliyuanService.zhuanjiadel(Integer.parseInt(zhuanjiaid));
        }
        return "redirect:zhuajiaguanli";
    }
    //test
    @RequestMapping("test")
    public String test(Map<String,Object> data){

        List<Zhuanjia> zhuanjiaList = guanliyuanService.selectAllzhuanjia();
        System.out.println("zhuanjiaList"+zhuanjiaList);
        data.put("zhuanjiaList",zhuanjiaList);
        List<Jiaoshi> jiaoshiList = guanliyuanService.selectAlljiaoshi();
        System.out.println("jiaoshiList"+jiaoshiList);
        data.put("jiaoshiList",jiaoshiList);

        return "guanliyuan/shijiangguanli";
    }
    /*试讲管理*********************************************************************/
    @RequestMapping ("shijiangguanli")
    public String shijiangguanli(Map<String,Object> data){
        List<Zhuanjia> zhuanjiaList = guanliyuanService.selectAllzhuanjia();
        //System.out.println("zhuanjiaList"+zhuanjiaList);
        data.put("zhuanjiaList",zhuanjiaList);
        List<Jiaoshi> jiaoshiList = guanliyuanService.selectAlljiaoshi();
        //System.out.println("jiaoshiList"+jiaoshiList);
        data.put("jiaoshiList",jiaoshiList);
        List<Kecheng> kechengList = guanliyuanService.selectAllkecheng();
        System.out.println("kechengList"+kechengList);
        data.put("kechengList",kechengList);
        return "guanliyuan/shijiangguanli";
    }

    /*试讲信息分页*/
    @RequestMapping("shijiangfenye")
    @ResponseBody
    public Map<String,Object> shijiangfenye(HttpServletRequest request,HttpServletResponse response){
//        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println("试讲分页测试 ajax ");
        String currentPageStr=request.getParameter("currentPage");
        String rowStr = request.getParameter("row");
        String search_id = request.getParameter("search_id");
        String search_jiaoshixingming = request.getParameter("search_jiaoshixingming");
        String search_kechengmingcheng = request.getParameter("search_shijiangkecheng");
        int currentPage = 0;//当前页码，默认为第一页
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }
        int id = 0;//当前页码，默认为第一页
        if (search_id != null && search_id.length() > 0) {
            id = Integer.parseInt(search_id);
        } else {
            id = 0;
        }
        System.out.println("当前页码currentPage"+currentPage);
        int row=0;//每一页显示的条数，默认为5
        if(rowStr!=null&&rowStr.length()>0){
            row=Integer.parseInt(rowStr);
        }else {
            row=5;
        }
        //System.out.println(username+password);
        PageBean<Shijiangdangan> pb = guanliyuanService.shijiangfindBypage(currentPage, row,id,search_jiaoshixingming,search_kechengmingcheng);
        map.put("currentPage",pb.getCurrentPage());
        map.put("totalPage",pb.getTotalPage());
        map.put("totalCount",pb.getTotalCount());
        map.put("list",pb.getList());
        map.put("denglurenxingming",guanliyuan.getXingming());
        return map;
    }
    /*试讲信息修改*/
    @RequestMapping("shijiangxiugai")
    @ResponseBody
    public Map<String,Object> shijiangxiugai(@Valid Shijiangdangan shijiangdangan, BindingResult bindingResult){
        String result ="";
        System.out.println("shijiangdangan是"+shijiangdangan);
        if(bindingResult.hasErrors()){
            System.out.println("试讲修改有问题");
            result="fail";
            //return bindingResult.getFieldError().getDefaultMessage();
        }else{
            if (shijiangdangan.getId()==null){
                int nextid = guanliyuanService.selectshijiangdanganLastid();
                shijiangdangan.setId(nextid);
            }
            Shijiangdangan shijiangdangan1 = guanliyuanService.selectByshijiangid(shijiangdangan.getId());
              if (shijiangdangan1!=null){
                shijiangdangan.setShijiangneirongurl(shijiangdangan1.getShijiangneirongurl());
                shijiangdangan.setShijiangfenshuurl(shijiangdangan1.getShijiangfenshuurl());
                    boolean updateinfo = guanliyuanService.updateshijiangdanganinfo(shijiangdangan);
                    if (updateinfo) {
                        result="修改成功";
                    }
                    else {
                        result="修改失败";
                    }

            }else
                {
                    boolean inserinfo = guanliyuanService.insertshijiangdanganinfo(shijiangdangan);
                    if (inserinfo) {
                        result="添加成功";
                    }else {
                        result="添加失败";
                    }
                }



        }map.put("result",result);
        return map;
    }
    /*试讲信息删除*/
    @RequestMapping("shijiangdel")
    //@ResponseBody
    public String shijiangdel(@RequestParam("shijiangid") String shijiangid){
        if (shijiangid!=null&&shijiangid!=""){
            boolean jiaoshidel = guanliyuanService.shijiangdel(Integer.parseInt(shijiangid));
        }
        return "redirect:shijiangguanli";
    }
    @RequestMapping("shijiangUpload")
    public String shijiangUpload(@RequestParam("shijiangid") String shijiangid){
        shijiang_id=shijiangid;
        return "guanliyuan/shijiangfujian";
    }
    @RequestMapping("shijiangneirongUpload")
    public String shijiangneirongUpload(@RequestParam("fileName") MultipartFile file, Map<String, Object> map){
        // 要上传的目标文件存放路径
        String localPath = "C:/Users/Administrator/IdeaProjects/springboot-test2/src/main/resources/static/images";

        // 上传成功或者失败的提示
        String shijiangneirongurl=file.getOriginalFilename();
        int id=Integer.parseInt(shijiang_id);
        boolean b = guanliyuanService.updateShijiangneirongurl(id,shijiangneirongurl);
        System.out.println("shijiangneirongurl是"+shijiangneirongurl);
        if (FileUtils.upload(file, localPath, file.getOriginalFilename())&&b){
            shijiangneirongmsg = "上传成功！";
        }else {
            shijiangneirongmsg = "上传失败！";
        }
        shijiangneirong = file.getOriginalFilename();
        map.remove("shijiangneirongmsg");
        map.put("shijiangneirongmsg", shijiangneirongmsg);
        map.remove("shijiangneirong");
        map.put("shijiangneirong", shijiangneirong);
        map.remove("shijiangfenshumsg");
        map.put("shijiangfenshumsg", shijiangfenshumsg);
        map.remove("shijiangfenshu");
        map.put("shijiangfenshu", shijiangfenshu);
        map.put("denglurenxingming",denglurenxingming);
        return "forward:shijiangUpload?shijiangid="+shijiang_id;
    }
    @RequestMapping("shijiangfenshuUpload")
    public String shijiangfenshuUpload(@RequestParam("fileName") MultipartFile file, Map<String, Object> map){
        // 要上传的目标文件存放路径
        String localPath = "C:/Users/Administrator/IdeaProjects/springboot-test2/src/main/resources/static/images";

        // 上传成功或者失败的提示
        String shijiangfenshuurl=file.getOriginalFilename();
        int id=Integer.parseInt(shijiang_id);
        boolean b = guanliyuanService.updateShijiangfenshuurl(id,shijiangfenshuurl);
        System.out.println("shijiangfenshuurl是"+shijiangfenshuurl);
        if (FileUtils.upload(file, localPath, file.getOriginalFilename())&&b){
            // 上传成功，给出页面提示
            shijiangfenshumsg = "上传成功！";
        }else {
            shijiangfenshumsg = "上传失败！";
        }
        // 显示图片
        shijiangfenshu = file.getOriginalFilename();
        map.remove("shijiangneirongmsg");
        map.put("shijiangneirongmsg", shijiangneirongmsg);
        map.remove("shijiangneirong");
        map.put("shijiangneirong", shijiangneirong);
        map.remove("shijiangfenshumsg");
        map.put("shijiangfenshumsg", shijiangfenshumsg);
        map.remove("shijiangfenshu");
        map.put("shijiangfenshu", shijiangfenshu);
        map.put("denglurenxingming",denglurenxingming);
        return "forward:shijiangUpload?shijiangid="+shijiang_id;
    }
    /*本学期合同管理模块**************************************************/
    /*获取当前学期*/
    public Map<String,Object> getxueqi(){
        int year = Integer.parseInt(getYear());
        System.out.println("year"+year);
        String mouth = getMouth();
        if (Integer.parseInt(mouth)<10){
            mouth="0"+mouth;
        }
        System.out.println("mouth"+mouth);
        int yue = Integer.parseInt(mouth);
        String timestart="";
        String timeend="";
        String xueqi="";
        if (yue<=7&&yue>=3){
            timestart=year+"-"+"03"+"-"+"01";
            timeend=year+"-"+"07"+"-"+"31";
            xueqi=(year-1)+"-"+year+"-"+2;
        }else {
            if (yue>7){
                int y=year+1;
                String s = Integer.toString(y);
                timestart=year+"-"+"08"+"-"+"01";
                timeend=s+"-"+"01"+"-"+"01";
                xueqi=year+"-"+(year+1)+"-"+1;
            }
            if (yue<3){
                int y=year-1;
                String s = Integer.toString(y);
                timestart=s+"-"+"08"+"-"+"01";
                timeend=year+"-"+"01"+"-"+"01";
                int i = year-1;
                xueqi=(year-1)+"-"+year+"-"+1;
            }
        }
        Map<String,Object> pp=new HashMap<>();
        pp.put("timestart",timestart);
        pp.put("timeend",timeend);
        pp.put("xueqi",xueqi);
        pp.put("year",year);
        pp.put("mouth",mouth);
        return pp;
    }
    @RequestMapping ("hetongguanli")/*督导记录页面展示*/
    public String hetongguanli(Map<String,Object> data){
        List<Jiaoshi> jiaoshiList = guanliyuanService.selectAlljiaoshi();
        List<Xueqi> xueqiList = xueqiList();
        data.put("jiaoshiList",jiaoshiList);
        data.put("xueqiList",xueqiList);
        String text = xueqiList.get(3).getText();
        System.out.println(text);
        data.put("xueqi",text);
        return "guanliyuan/hetongguanli";
    }
    /*本学期合同分页*/
    @RequestMapping("hetongfenye")
    @ResponseBody
    public Map<String,Object> hetongfenye(HttpServletRequest request,HttpServletResponse response){
//        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println("合同信息分页测试 ajax ");
        String currentPageStr=request.getParameter("currentPage");
        String rowStr = request.getParameter("row");
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
        Map<String, Object> getxueqi = getxueqi();
        String xueqi = (String) getxueqi.get("xueqi");
        System.out.println("start**********"+xueqi);
        PageBean<Hetong> pb = guanliyuanService.hetongfindBypage(currentPage,row,xueqi);
        List<Hetong> list = pb.getList();
        List<String> time=new ArrayList<String>();
        for (int i = 0; i <list.size() ; i++) {
            String s = dateToStr(list.get(i).getChuangjianshijian());
            time.add(i,s);
        }
        List<hetonginfo> hetonginfoList=new ArrayList<hetonginfo> ();
        String zongxueshiitem[];
        String renkebanjiitem[];
        String kechengmingchengitem[];
        String kechengbianhaoitem[];
        for (int i = 0; i < list.size(); i++) {
            String zongxueshi = list.get(i).getZongxueshi();
            String renkebanji = list.get(i).getRenkebanji();
            String kechengmingcheng = list.get(i).getKechengmingcheng();
            String kechengbianhao = list.get(i).getKechengbianhao();
            System.out.println("kechengmingcheng"+kechengmingcheng);
            zongxueshiitem=zongxueshi.split(",");
            renkebanjiitem=renkebanji.split(",");
            kechengmingchengitem=kechengmingcheng.split(",");
            //System.out.println("kechengmingchengitem[0]"+kechengmingchengitem[0]);
            kechengbianhaoitem=kechengbianhao.split(",");
            System.out.println("kechengbianhaoitem的长度"+kechengbianhaoitem.length);
            for (int j = 0; j <kechengbianhaoitem.length ; j++) {
                hetonginfo hetonginfo=new hetonginfo();
                hetonginfo.setZongxueshi(zongxueshiitem[j]);
                hetonginfo.setRenkebanji(renkebanjiitem[j]);
                //System.out.println("kechengmingchengitem[j]"+kechengmingchengitem[j]);
                hetonginfo.setKechengmingcheng(kechengmingchengitem[j]);
                hetonginfo.setKechengbianhao(kechengbianhaoitem[j]);
                hetonginfo.setSize(kechengbianhaoitem.length);
                System.out.println("hetonginfo"+hetonginfo);
                hetonginfoList.add(hetonginfo);
            }
        }
        System.out.println("hetonginfoList"+hetonginfoList);
        map.put("currentPage",pb.getCurrentPage());
        map.put("totalPage",pb.getTotalPage());
        map.put("totalCount",pb.getTotalCount());
        map.put("time",time);
        map.put("list",pb.getList());
        map.put("hetonginfoList",hetonginfoList);
        map.put("denglurenxingming",guanliyuan.getXingming());
        return map;
    }
    /*合同修改*/
    @RequestMapping("hetongxiugai")
    @ResponseBody
    public Map<String,Object> hetongxiugai(@Valid Hetong hetong, BindingResult bindingResult){
        String result ="";
        System.out.println("合同是"+hetong);
        if(bindingResult.hasErrors()){
            System.out.println("合同修改有问题");
            result="fail";
            //return bindingResult.getFieldError().getDefaultMessage();
        }else{
            Hetong hetong1=null;
            System.out.println("合同id是"+hetong.getId());
            int nextid=guanliyuanService.selecthetongLastid();
            if (hetong.getId()==null||hetong.getId()==0||hetong.getId()>nextid){
                if (hetong.getId()!=null){
                    nextid=hetong.getId();
                }
                //设置属性
                String xueqi = hetong.getXueqi();
                System.out.println("nextid"+nextid);
                hetong.setId(nextid);
                Integer jiaoshibianhao = hetong.getJiaoshibianhao();
                String pinyongxingshi = hetong.getPinyongxingshi();
                Jiaoshi jiaoshi = guanliyuanService.selectByteavherid(jiaoshibianhao);
                String pingdingdengji = jiaoshi.getPingdingdengji();
                String xueshi = guanliyuanService.selectxueshibyjiaoshibianhao(jiaoshibianhao,xueqi,pinyongxingshi);
                System.out.println("xueshi"+xueshi);
                String kechengbianhao = guanliyuanService.selectkechengbianhaobyjiaoshibianhao(jiaoshibianhao,xueqi,pinyongxingshi);
                System.out.println("kechengbianhao"+kechengbianhao);
                String kechengmingcheng = guanliyuanService.selectkechengmingchengbyjiaoshibianhao(jiaoshibianhao,xueqi,pinyongxingshi);
                System.out.println("kechengmingcheng"+kechengmingcheng);
                String renkebanji = guanliyuanService.selectrenkebanjibyjiaoshibianhao(jiaoshibianhao,xueqi,pinyongxingshi);
                System.out.println("renkebanji"+renkebanji);
                String xueshiitem[];
                int zongxueshi=0;
                //判断是否含有 “,”
                if (xueshi.contains(",")){
                    xueshiitem=xueshi.split(",");
                    for (int j = 0; j < xueshiitem.length; j++) {
                        zongxueshi+=Integer.parseInt(xueshiitem[j]);
                    }
                }else {
                    zongxueshi=Integer.parseInt(xueshi);
                }
                List<Xinshui> xinshuis = guanliyuanService.selectAllxinshui();
                int danjia=0;
                String danjiastr="";
                int zongjine=0;
                int yueshu=0;
                for (int i = 0; i <xinshuis.size() ; i++) {
                    if (xinshuis.get(i).getDengji().equals(pingdingdengji)&&pinyongxingshi.equals("外聘制")){
                        danjia=xinshuis.get(i).getWaipin();
                        danjiastr=danjia+"/每学时";
                        zongjine=danjia*zongxueshi;
                        hetong.setZhuangzhangjine(Integer.toString(zongjine));
                    }
                    if (xinshuis.get(i).getDengji().equals(pingdingdengji)&&pinyongxingshi.equals("派遣制")){
                        danjia=xinshuis.get(i).getPaiqian();
                        danjiastr=danjia+"/每月";
                        zongjine=danjia;
                        String mouth = dateToStr(hetong.getChuangjianshijian()).substring(6,7);
                        int yue=Integer.parseInt(mouth);

                        if (yue<=7&&yue>=2) {
                            if (yue == 2) {
                                yue = yue + 1;
                            }
                            yueshu = 8 - yue;
                        }
                        if (yue>7) {
                            if (yue == 8) {
                                yue = yue + 1;
                            }
                             yueshu = 14 - yue;
                        }
                        if (yue<2){
                            yueshu=1;
                        }
                        int pp=zongjine*yueshu;
                        hetong.setZhuangzhangjine(Integer.toString(pp));

                }
                }
                System.out.println("danjiastr"+danjiastr);
                System.out.println("总金额"+zongjine);
                hetong.setKeshidanjia(danjiastr);
                hetong.setZongxueshi(xueshi);
                hetong.setKechengbianhao(kechengbianhao);
                hetong.setKechengmingcheng(kechengmingcheng);
//                hetong.setZhuangzhangjine(Integer.toString(zongjine));
                hetong.setRenkebanji(renkebanji);
                hetong.setXueqi(xueqi);
                /*自动生成5个月的工资报表*/
                Gongzi gongzi=new Gongzi();
                System.out.println("自动生成的工资报表时的合同信息是"+hetong);
                //Integer jiaoshibianhao = hetong.getJiaoshibianhao();
                gongzi.setJiaoshibianhao(jiaoshibianhao);
                gongzi.setJiaoshixingming(hetong.getJiaoshixingming());

                gongzi.setFakuanjine(0.0);

                gongzi.setFafangzhuangtai("未发放");
                Date chuangjianshijian = hetong.getChuangjianshijian();
                String shijian = dateToStr(chuangjianshijian);
                String year=shijian.substring(0,4);

                String mouth = shijian.substring(6,7);
                int yue=Integer.parseInt(mouth);
                String gongzichuangjianshijian=null;
                //Date end=null;
                String biaoti=null;
                String s="月份工资报表";
                if (yue<=7&&yue>=2){
                    if (yue==2){
                        yue=yue+1;
                    }
                     yueshu=8-yue;
                    Double shengyujine=0.0;
//                    if(hetong.getPinyongxingshi().equals("外聘制")){
                        gongzi.setYingfajine(Double.parseDouble(hetong.getZhuangzhangjine())/yueshu);
                        shengyujine=Double.parseDouble(hetong.getZhuangzhangjine());
                        gongzi.setShifajine(Double.parseDouble(hetong.getZhuangzhangjine())/yueshu);
//                    }
//                    if(hetong.getPinyongxingshi().equals("派遣制")) {
//                        gongzi.setYingfajine(Double.parseDouble(hetong.getZhuangzhangjine()));
//                        shengyujine=Double.parseDouble(hetong.getZhuangzhangjine());
//                        gongzi.setShifajine(Double.parseDouble(hetong.getZhuangzhangjine()));
//                    }
//                    gongzi.setShifajine(gongzi.getYingfajine());
//                    shengyujine=yueshu*Double.parseDouble(hetong.getZhuangzhangjine());
                    gongzi.setShengyujine(shengyujine);
                    for (int j = yue; j <= 7; j++) {
                        gongzichuangjianshijian=year+"-"+"0"+j;
                        biaoti=year+"年"+j+s;
                        gongzi.setShijian(gongzichuangjianshijian);
                        gongzi.setChuangjianshijian(chuangjianshijian);
                        gongzi.setBiaoti(biaoti);
                        guanliyuanService.insertgongziinfo(gongzi);
                    }
                }else {
                    if (yue>7){
                        if (yue==8){
                            yue=yue+1;
                        }
                        yueshu=14-yue;
                        Double shengyujine=0.0;
                        gongzi.setYingfajine(Double.parseDouble(hetong.getZhuangzhangjine())/yueshu);
                        shengyujine=Double.parseDouble(hetong.getZhuangzhangjine());
                        gongzi.setShifajine(Double.parseDouble(hetong.getZhuangzhangjine())/yueshu);
//                        Double shengyujine=yueshu*Double.parseDouble(hetong.getZhuangzhangjine());
                        for (int m = yue; m <= 13; m++) {
                            gongzichuangjianshijian=year+"-"+"0"+m;
                            biaoti=year+"年"+m+s;
                            if (m>=10){
                                gongzichuangjianshijian=year+"-"+m;
                                biaoti=year+"年"+m+s;
                            }
                            if (m==13){
                                int i1 = Integer.parseInt(year)+1;
                                gongzichuangjianshijian=i1+"-"+"01";
                                biaoti=i1+"年"+1+s;
                            }
                            gongzi.setShengyujine(shengyujine);
                            gongzi.setShijian(gongzichuangjianshijian);
                            gongzi.setChuangjianshijian(chuangjianshijian);
                            gongzi.setBiaoti(biaoti);
                            guanliyuanService.insertgongziinfo(gongzi);
                        }
                    }
                    if (yue<2){
                        Double shengyujine=0.0;
                        gongzi.setYingfajine(Double.parseDouble(hetong.getZhuangzhangjine())/yueshu);
                        shengyujine=Double.parseDouble(hetong.getZhuangzhangjine());
                        gongzi.setShifajine(Double.parseDouble(hetong.getZhuangzhangjine())/yueshu);
//                        Double shengyujine=Double.parseDouble(hetong.getZhuangzhangjine());
                        gongzichuangjianshijian=year+"-"+"01";
                        biaoti=year+"年"+1+s;
                        gongzi.setShengyujine(shengyujine);
                        gongzi.setShijian(gongzichuangjianshijian);
                        gongzi.setChuangjianshijian(chuangjianshijian);
                        gongzi.setBiaoti(biaoti);
                        guanliyuanService.insertgongziinfo(gongzi);
                    }
                }
                boolean inserinfo = guanliyuanService.inserthetonginfo(hetong);

                if (inserinfo) {
                    result="添加成功";
                }else {
                    result="添加失败";
                }

            }else {
                Hetong hetong2 = guanliyuanService.selectByhetongid(hetong.getId());
                hetong.setJiaoshibianhao(hetong2.getJiaoshibianhao());
                hetong.setZhuanzhanghuming(hetong2.getZhuanzhanghuming());
                hetong.setShenfenzheng(hetong2.getShenfenzheng());
                hetong.setPinyongxingshi(hetong2.getPinyongxingshi());
                hetong.setHetongleixing(hetong2.getHetongleixing());
                hetong.setChuangjianshijian(hetong2.getChuangjianshijian());
                hetong.setKeshidanjia(hetong2.getKeshidanjia());
                hetong.setZongxueshi(hetong2.getZongxueshi());
                hetong.setKechengbianhao(hetong2.getKechengbianhao());
                hetong.setKechengmingcheng(hetong2.getKechengmingcheng());
                hetong.setZhuangzhangjine(hetong2.getZhuangzhangjine());
                hetong.setRenkebanji(hetong2.getRenkebanji());
                hetong.setHetongname(hetong2.getHetongname());
                hetong.setHetongurl(hetong2.getHetongurl());
                hetong.setXueqi(hetong2.getXueqi());
                boolean updateinfo = guanliyuanService.updatehetonginfo(hetong);
                if (updateinfo) {
                    result="修改成功";
                }
                else {
                    result="修改失败";
                }
            }
        }map.put("result",result);
        return map;
    }
    /*合同信息信息删除*/
    @RequestMapping("hetongdel")
    public String hetongdel(@RequestParam("hetongid") String hetongid){
        if (hetongid!=null&&hetongid!=""){
            guanliyuanService.hetongdel(Integer.parseInt(hetongid));
        }
        return "redirect:hetongguanli";
    }
    /*合同档案记录*/
    @RequestMapping ("hetongdangan")
    public String hetongdangan(Map<String,Object> data){
        List<Jiaoshi> jiaoshiList = guanliyuanService.selectAlljiaoshi();
        data.put("jiaoshiList",jiaoshiList);
        return "guanliyuan/hetongdangan";
    }

    /*合同档案分页*/
    @RequestMapping("hetongdanganfenye")
    @ResponseBody
    public Map<String,Object> hetongdanganfenye(HttpServletRequest request,HttpServletResponse response){
//        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println("合同档案分页测试 ajax ");
        String currentPageStr=request.getParameter("currentPage");
        String rowStr = request.getParameter("row");
        String jiaoshibianhaoStr =request.getParameter("jiaoshibianhao");
        String jiaoshixingmingStr =request.getParameter("jiaoshixingming");
        String zhuanzhanghumingStr =request.getParameter("zhuanzhanghuming");
        String timeStr = request.getParameter("time");
        if (timeStr.equals("")&&jiaoshibianhaoStr.equals("")&&jiaoshixingmingStr.equals("")){
            map.clear();
            map.put("list",null);
            map.put("denglurenxingming",denglurenxingming);
            return map;
        }
        else {
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
        int jiaoshibianhao = 0;//当前页码，默认为第一页
        if (jiaoshibianhaoStr != null &&!jiaoshibianhaoStr.equals("请选择") && jiaoshibianhaoStr.length() > 0) {
            jiaoshibianhao = Integer.parseInt(jiaoshibianhaoStr);
        } else {
            jiaoshibianhao = 0;
        }
        if (zhuanzhanghumingStr != null &&!zhuanzhanghumingStr .equals("请选择") && zhuanzhanghumingStr.length() > 0) {
            System.out.println("zhuanzhanghumingStr"+zhuanzhanghumingStr);
        } else {
            zhuanzhanghumingStr = null;
        }
        if (jiaoshixingmingStr != null &&!jiaoshixingmingStr .equals("请选择") && jiaoshixingmingStr.length() > 0) {
            System.out.println("jiaoshixingmingStr"+jiaoshixingmingStr);
        } else {
            jiaoshixingmingStr = null;
        }
        //System.out.println("start**********"+start);
        PageBean<Hetong> pb = guanliyuanService.hetongdanganfindBypage(currentPage,row,jiaoshibianhao,jiaoshixingmingStr,timeStr);
        List<Hetong> list = pb.getList();
        List<String> time=new ArrayList<String>();
        for (int i = 0; i <list.size() ; i++) {
            String s = dateToStr(list.get(i).getChuangjianshijian());
            time.add(i,s);
        }
        List<hetonginfo> hetonginfoList=new ArrayList<hetonginfo> ();
        String zongxueshiitem[];
        String renkebanjiitem[];
        String kechengmingchengitem[];
        String kechengbianhaoitem[];
        for (int i = 0; i < list.size(); i++) {
            String zongxueshi = list.get(i).getZongxueshi();
            String renkebanji = list.get(i).getRenkebanji();
            String kechengmingcheng = list.get(i).getKechengmingcheng();
            String kechengbianhao = list.get(i).getKechengbianhao();
            System.out.println("kechengmingcheng"+kechengmingcheng);
            zongxueshiitem=zongxueshi.split(",");
            renkebanjiitem=renkebanji.split(",");
            kechengmingchengitem=kechengmingcheng.split(",");
            kechengbianhaoitem=kechengbianhao.split(",");
            //System.out.println("kechengbianhaoitem的长度"+kechengbianhaoitem.length);
            for (int j = 0; j <kechengbianhaoitem.length ; j++) {
                hetonginfo hetonginfo=new hetonginfo();
                hetonginfo.setZongxueshi(zongxueshiitem[j]);
                hetonginfo.setRenkebanji(renkebanjiitem[j]);

                hetonginfo.setKechengmingcheng(kechengmingchengitem[j]);
                hetonginfo.setKechengbianhao(kechengbianhaoitem[j]);
                hetonginfo.setSize(kechengbianhaoitem.length);
                //System.out.println("hetonginfo"+hetonginfo);
                hetonginfoList.add(hetonginfo);
            }
        }
        System.out.println("hetonginfoList"+hetonginfoList);
        map.put("currentPage",pb.getCurrentPage());
        map.put("totalPage",pb.getTotalPage());
        map.put("totalCount",pb.getTotalCount());
        map.put("time",time);
        map.put("list",pb.getList());
        map.put("hetonginfoList",hetonginfoList);
        map.put("denglurenxingming",guanliyuan.getXingming());
        map.put("xueqi",xueqispan);
        return map;
        }
    }

    /*薪水单价********************************************************/

    @RequestMapping ("xinshuiguanli")/*处罚等级管理页面展示*/
    public String xinshuiguanli(){
        return "guanliyuan/xinshuiguanli";
    }
    /*薪水分页*/
    @RequestMapping("xinshuifenye")
    @ResponseBody
    public Map<String,Object> xinshuifenye(HttpServletRequest request,HttpServletResponse response){
//        Map<String, Object> map = new HashMap<String, Object>();
        //System.out.println("处罚等级分页测试 ajax ");
        List<Xinshui> xinshuis = guanliyuanService.selectAllxinshui();
        map.put("denglurenxingming",denglurenxingming);
        map.put("list",xinshuis);
        return map;
    }
    /*薪水修改*/
    @RequestMapping("xinshuixiugai")
    @ResponseBody
    public Map<String,Object> xinshuixiugai(@Valid Xinshui xinshui, BindingResult bindingResult){
        String result ="";
        if(bindingResult.hasErrors()){
            System.out.println("xinshuis修改有问题");
            result="fail";
            //return bindingResult.getFieldError().getDefaultMessage();
        }else{
            System.out.println("xinshui id是"+xinshui.getId());
            if (xinshui.getId()==null){
                int nextid = guanliyuanService.selectxinshuiLastid();
                xinshui.setId(nextid);
            }
            Xinshui xinshui1 = guanliyuanService.selectByxinshuiid(xinshui.getId());
            if (xinshui1!=null){
                boolean updateinfo = guanliyuanService.updatexinshuiinfo(xinshui);
                if (updateinfo) {
                    result="修改成功";
                }
                else {
                    result="修改失败";
                }
            }else {
                boolean inserinfo = guanliyuanService.insertxinshuiinfo(xinshui);
                if (inserinfo) {
                    result="添加成功";
                }else {
                    result="添加失败";
                }
            }
        }map.put("result",result);
        return map;
    }

    /*薪水信息信息删除*/
    @RequestMapping("xinshuidel")
    public String xinshuidel(@RequestParam("xinshuiid") String xinshuiid){
        if (xinshuiid!=null&&xinshuiid!=""){
            guanliyuanService.xinshuidel(Integer.parseInt(xinshuiid));
        }
        return "redirect:xinshuiguanli";
    }
    /*****************************工资管理********************************/
    @RequestMapping ("gongziguanli")
    public String gongziguanli(){
        return "guanliyuan/gongziguanli";
    }
    /*本月工资分页*/
    @RequestMapping("gongzifenye")
    @ResponseBody
    public Map<String,Object> gongzifenye(HttpServletRequest request,HttpServletResponse response){
//        Map<String, Object> map = new HashMap<String, Object>();
        //System.out.println("处罚等级分页测试 ajax ");
        String currentPageStr=request.getParameter("currentPage");
        String rowStr = request.getParameter("row");
        String jiaoshibianhaoStr =request.getParameter("jiaoshibianhao");
        String jiaoshixingmingStr =request.getParameter("jiaoshixingming");
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
        String year = getYear();
        System.out.println("year"+year);
        String mouth = getMouth();
        System.out.println("mouth"+mouth);
        if (Integer.parseInt(mouth)<10){
            mouth="0"+mouth;
        }
        String time=year+"-"+mouth;
        PageBean<Gongzi> pb = guanliyuanService.gongzifindBypage(currentPage, row, time,jiaoshibianhaoStr,jiaoshixingmingStr);
        List<Gongzi> list = pb.getList();
        List<String> time1=new ArrayList<String>();
        for (int i = 0; i <list.size() ; i++) {
            String s = dateToStr(list.get(i).getChuangjianshijian());
            time1.add(i,s);
        }
        map.put("currentPage",pb.getCurrentPage());
        map.put("totalPage",pb.getTotalPage());
        map.put("totalCount",pb.getTotalCount());
        map.put("denglurenxingming",denglurenxingming);
        map.put("list",pb.getList());
        map.put("time",time1);
        return map;
    }
    /*工资修改*/
    @RequestMapping("gongzixiugai")
    @ResponseBody
    public Map<String,Object> gongzixiugai(@Valid Gongzi gongzi, BindingResult bindingResult){
        String result ="";
        if(bindingResult.hasErrors()){
            System.out.println("gongzi修改有问题");
            result="fail";
            //return bindingResult.getFieldError().getDefaultMessage();
        }else{
            System.out.println("gongzi 是"+gongzi);
            Gongzi gongzi1 = guanliyuanService.selectBygongziid(gongzi.getId());
            double shengyujine=gongzi1.getShengyujine();
            double yingfajine =gongzi.getYingfajine();
            gongzi.setJiaoshibianhao(gongzi1.getJiaoshibianhao());
            gongzi.setJiaoshixingming(gongzi1.getJiaoshixingming());
            gongzi.setShengyujine(gongzi1.getShengyujine());
            gongzi.setBiaoti(gongzi1.getBiaoti());
            gongzi.setFakuanjine(gongzi1.getFakuanjine());
            gongzi.setShifajine(gongzi1.getShifajine());
            gongzi.setShijian(gongzi1.getShijian());
            gongzi.setChuangjianshijian(gongzi1.getChuangjianshijian());
            if (gongzi1.getFafangzhuangtai().equals("已发放")&&gongzi.getFafangzhuangtai().equals("已发放")){
            result="不允许修改";
            }else
                {
            if (gongzi1.getFafangzhuangtai().equals("未发放")&&gongzi.getFafangzhuangtai().equals("已发放")){
                 shengyujine = gongzi1.getShengyujine() - gongzi.getYingfajine();
                gongzi.setShengyujine(shengyujine);
                double shifajine =gongzi.getYingfajine()-gongzi.getFakuanjine();
                gongzi.setShifajine(shifajine);
            }
            if (gongzi1.getFafangzhuangtai().equals("已发放")&&gongzi.getFafangzhuangtai().equals("未发放")){
                shengyujine = gongzi1.getShengyujine() + gongzi1.getYingfajine();
                gongzi.setShengyujine(shengyujine);
                double shifajine =gongzi1.getYingfajine()-gongzi1.getFakuanjine();
                gongzi.setYingfajine(gongzi1.getYingfajine());
                gongzi.setShifajine(shifajine);
            }
            if (gongzi1.getFafangzhuangtai().equals("未发放")&&gongzi.getFafangzhuangtai().equals("未发放")){
//                shengyujine = gongzi1.getShengyujine() - gongzi.getYingfajine();
                gongzi.setShengyujine(gongzi1.getShengyujine());
                double shifajine =gongzi.getYingfajine()-gongzi.getFakuanjine();
                gongzi.setShifajine(shifajine);
            }

            Integer id = gongzi.getId();
            Date chuangjianshijian = gongzi1.getChuangjianshijian();
            Integer jiaoshibianhao = gongzi1.getJiaoshibianhao();

            List<Gongzi> gongzis = guanliyuanService.selectByjiaoshibianhaoandcjsj(jiaoshibianhao,chuangjianshijian);
            System.out.println("gongzis  ***"+gongzis);
            for (int i = 0; i < gongzis.size(); i++) {
                if (gongzis.get(i).getId()>id){
                    gongzis.get(i).setShengyujine(shengyujine);
                    System.out.println("改变其他月的剩余金额"+i+shengyujine);
                    guanliyuanService.updategongziinfo(gongzis.get(i));
                }
            }
            if (gongzi1!=null){
                boolean updateinfo = guanliyuanService.updategongziinfo(gongzi);
                if (updateinfo) {
                    result="修改成功";
                }
                else {
                    result="修改失败";
                }
            }
            }
        }map.put("result",result);
        return map;
    }


    /*****************************工资档案**********************************************/
    /*工资档案记录*/
    @RequestMapping ("gongzidangan")
    public String gongzidangan(Map<String,Object> data){
        List<Jiaoshi> jiaoshiList = guanliyuanService.selectAlljiaoshi();
        data.put("jiaoshiList",jiaoshiList);
        return "guanliyuan/gongzidangan";
    }

    /*工资档案分页*/
    @RequestMapping("gongzidanganfenye")
    @ResponseBody
    public Map<String,Object> gongzidanganfenye(HttpServletRequest request,HttpServletResponse response){

        System.out.println("合同档案分页测试 ajax ");
        String currentPageStr=request.getParameter("currentPage");
        String rowStr = request.getParameter("row");
        String jiaoshibianhaoStr =request.getParameter("jiaoshibianhao");
        String jiaoshixingmingStr =request.getParameter("jiaoshixingming");
        String timeStr = request.getParameter("time");
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
        if (timeStr != null &&!timeStr.equals("全部") && timeStr.length() > 0) {
            System.out.println("timeStr"+timeStr);
        } else {
            timeStr = null;
        }

        int jiaoshibianhao = 0;//当前页码，默认为第一页
        if (jiaoshibianhaoStr != null &&!jiaoshibianhaoStr.equals("请选择") && jiaoshibianhaoStr.length() > 0) {
            jiaoshibianhao = Integer.parseInt(jiaoshibianhaoStr);
        } else {
            jiaoshibianhao = 0;
        }
        if (jiaoshixingmingStr != null &&!jiaoshixingmingStr .equals("请选择") && jiaoshixingmingStr.length() > 0) {
            System.out.println("jiaoshixingmingStr"+jiaoshixingmingStr);
        } else {
            jiaoshixingmingStr = null;
        }
        PageBean<Gongzi> pb = guanliyuanService.gongzidanganfindBypage(currentPage,row,jiaoshibianhao,jiaoshixingmingStr,timeStr);
        List<Gongzi> list = pb.getList();
        List<String> time1=new ArrayList<String>();
        for (int i = 0; i <list.size() ; i++) {
            String s = dateToStr(list.get(i).getChuangjianshijian());
            time1.add(i,s);
        }
        map.put("currentPage",pb.getCurrentPage());
        map.put("totalPage",pb.getTotalPage());
        map.put("totalCount",pb.getTotalCount());
        map.put("denglurenxingming",denglurenxingming);
        map.put("list",pb.getList());
        map.put("time",time1);
        return map;
    }
    /*单人工资生成*/
    @RequestMapping("gongzishengcheng")
    public void gongzishengcheng(HttpServletRequest request, HttpServletResponse response) throws IOException, TemplateException {
        String gongziid = request.getParameter("gongziid");
        Gongzi gongzi = guanliyuanService.selectBygongziid(Integer.parseInt(gongziid));
        Map<String, Object> getxueqi = getxueqi();
        String shijian = gongzi.getShijian();
        String xueqi=null;
        int year=Integer.parseInt(shijian.substring(0,4));
        int mouth=0;
        if (Integer.parseInt(shijian.substring(5,6))==1){
            mouth = Integer.parseInt(shijian.substring(5,7));
        }else{
            mouth = Integer.parseInt(shijian.substring(6,7));
        }
        if (mouth>=2&&mouth<=7){
            xueqi=(year-1)+"-"+year+"-"+2;
        }
        if(mouth>7){
            xueqi=year+"-"+(year+1)+"-"+1;
        }
        if (mouth==1){
            xueqi=(year-1)+"-"+year+"-"+1;
        }
        System.out.println("xueqi"+xueqi);
        List<Gongzishengcheng> gongzishengchengList=new ArrayList<Gongzishengcheng>();
        List<Hetong> hetongs = guanliyuanService.selectAllhetong(xueqi);
        System.out.println("hetongs"+hetongs);
        Gongzishengcheng gongzishengcheng=new Gongzishengcheng();
        gongzishengcheng.setShengyujine(gongzi.getShengyujine());
        gongzishengcheng.setShifajine(gongzi.getYingfajine()-gongzi.getFakuanjine());
        gongzishengcheng.setFakuanjine(gongzi.getFakuanjine());
        gongzishengcheng.setBiaoti(gongzi.getBiaoti());
        gongzishengcheng.setJiaoshixingming(gongzi.getJiaoshixingming());
        gongzishengcheng.setShijian(gongzi.getShijian());
        gongzishengcheng.setYingfajine(gongzi.getYingfajine());
        for (int i = 0; i < hetongs.size(); i++) {
            if (hetongs.get(i).getJiaoshibianhao()==gongzi.getJiaoshibianhao()){
                gongzishengcheng.setZhuanzhanghuming(hetongs.get(i).getZhuanzhanghuming());
                gongzishengcheng.setYinhang(hetongs.get(i).getYinhang());
                gongzishengcheng.setYinhangkahao(hetongs.get(i).getYinhangkahao());
                gongzishengcheng.setKaihuhanghuhao(hetongs.get(i).getKaihuhanghuhao());
                gongzishengcheng.setShenfenzheng(hetongs.get(i).getShenfenzheng());
                break;
            }
        }
        gongzishengchengList.add(gongzishengcheng);
        System.out.println("gongzishengchengList"+gongzishengchengList);
        Map<String,Object> params = new HashMap<>();
        params.put("gongziList",gongzishengchengList);
       //Configuration configuration=new Configuration(new Version("2.3.0"));
//        FreeMarkerConfigurer freemarkerConfig=new FreeMarkerConfigurer();
        freemarkerConfig.setConfiguration(Configuration.getDefaultConfiguration());
        String filename=gongzi.getJiaoshixingming()+"-工资报表";
//        String templateName=;
        exportExcel(freemarkerConfig,response,filename,"excel/gongzi2.ftl",params);
        //TemplateParseUtil.parse("excel","工资表.ftl","excel/excel.xsl",params);
    }
    /*一键工资生成*/

    @RequestMapping("yijiangongzishengcheng")
    public void yijiangongzishengcheng(HttpServletRequest request, HttpServletResponse response) throws IOException, TemplateException {

        String gongziid = request.getParameter("gongziid");
        Map<String, Object> getxueqi = getxueqi();
        Object year = getxueqi.get("year");
        Object mouth = getxueqi.get("mouth");
        String xueqi = (String) getxueqi.get("xueqi");
        String shijian=year+"-"+mouth;
        List<Hetong> hetongs = guanliyuanService.selectAllhetong(xueqi);
        List<Gongzi> gongziList = guanliyuanService.yijianshengchenggongzi("", shijian);
        List<Gongzishengcheng> gongzishengchengList=new ArrayList<Gongzishengcheng>();
        for (int i = 0; i <gongziList.size() ; i++) {
            Gongzishengcheng gongzishengcheng=new Gongzishengcheng();
            gongzishengcheng.setShengyujine(gongziList.get(i).getShengyujine()-gongziList.get(i).getYingfajine());
            gongzishengcheng.setShifajine(gongziList.get(i).getYingfajine()-gongziList.get(i).getFakuanjine());
            gongzishengcheng.setFakuanjine(gongziList.get(i).getFakuanjine());
            gongzishengcheng.setBiaoti(gongziList.get(i).getBiaoti());
            gongzishengcheng.setJiaoshixingming(gongziList.get(i).getJiaoshixingming());
            gongzishengcheng.setShijian(gongziList.get(i).getShijian());
            gongzishengcheng.setYingfajine(gongziList.get(i).getYingfajine());
            for (int j = 0; j <hetongs.size() ; j++) {
                if (gongziList.get(i).getJiaoshibianhao()==hetongs.get(j).getJiaoshibianhao()){
                            gongzishengcheng.setZhuanzhanghuming(hetongs.get(j).getZhuanzhanghuming());
                            gongzishengcheng.setYinhang(hetongs.get(j).getYinhang());
                            gongzishengcheng.setYinhangkahao(hetongs.get(j).getYinhangkahao());
                            gongzishengcheng.setKaihuhanghuhao(hetongs.get(j).getKaihuhanghuhao());
                            gongzishengcheng.setShenfenzheng(hetongs.get(j).getShenfenzheng());
                            continue;
                }
            }
            gongzishengchengList.add(gongzishengcheng);
        }
        System.out.println("一键生成"+gongzishengchengList);
        //List<Gongzi> gongziList =new ArrayList<Gongzi>();
        //gongziList.add(gongzi);
        Map<String,Object> params = new HashMap<>();
        params.put("gongziList",gongzishengchengList);
        //Configuration configuration=new Configuration(new Version("2.3.0"));
        freemarkerConfig.setConfiguration(Configuration.getDefaultConfiguration());
        String filename=shijian+"-工资报表";
//      String templateName=;
        exportExcel(freemarkerConfig,response,filename,"excel/gongzi2.ftl",params);
        //TemplateParseUtil.parse("excel","工资表.ftl","excel/excel.xsl",params);
    }
    /*一键发放*/
    @RequestMapping("yijianfafang")
    public void yijianfafang(HttpServletRequest request, HttpServletResponse response) throws IOException, TemplateException {

        String gongziid = request.getParameter("gongziid");
        Map<String, Object> getxueqi = getxueqi();
        Object year = getxueqi.get("year");
        Object mouth = getxueqi.get("mouth");
        String xueqi = (String) getxueqi.get("xueqi");
        String shijian=year+"-"+mouth;

         }

    /*生成合同文档*/
    @RequestMapping("shengcheng")
    public void shengcheng(HttpServletRequest request, HttpServletResponse response){
        String hetongid = request.getParameter("hetongid");
        Map<String,Object> params = new HashMap<>();
        Hetong hetong = guanliyuanService.selectByhetongid(Integer.parseInt(hetongid));
        String zhuanzhanghuming = hetong.getZhuanzhanghuming();
        Date chuangjianshijian = hetong.getChuangjianshijian();
        String nian=dateToStr(chuangjianshijian).substring(0,4);
        int year = Integer.parseInt(nian);
        String mouth=dateToStr(chuangjianshijian).substring(6,7);
        int yue = Integer.parseInt(mouth);
        String timestart="";
        String timeend="";
        String xueqi="";
        if (yue<=7&&yue>=3){
            timestart=year+"-"+"03"+"-"+"01";
            timeend=year+"-"+"07"+"-"+"31";
            xueqi=(year-1)+"-"+year+"-"+2;
        }else {
            if (yue>7){
                int y=year+1;
                String s = Integer.toString(y);
                timestart=year+"-"+"08"+"-"+"01";
                timeend=s+"-"+"01"+"-"+"01";
                xueqi=year+"-"+(year+1)+"-"+1;
            }
            if (yue<3){
                int y=year-1;
                String s = Integer.toString(y);
                timestart=s+"-"+"08"+"-"+"01";
                timeend=year+"-"+"01"+"-"+"01";
                int i = year-1;
                xueqi=(year-1)+"-"+year+"-"+1;
            }
        }
        String[] kechengbianhao = hetong.getKechengbianhao().split(",");
        String[] kechengmingcheng = hetong.getKechengmingcheng().split(",");
        String[] renkebanji = hetong.getRenkebanji().split(",");
        String[] zongxueshi = hetong.getZongxueshi().split(",");
        params.put("id",hetong.getId());
        params.put("jiaoshibianhao",hetong.getJiaoshibianhao());
        params.put("jiaoshixingming",hetong.getJiaoshixingming());
        params.put("zhuanzhanghuming",zhuanzhanghuming);
        params.put("shenfenzheng",hetong.getHetongleixing());
        params.put("yinhangkahao",hetong.getYinhangkahao());
        params.put("yinhang",hetong.getYinhang());
        params.put("kaihuhanghuhao",hetong.getKaihuhanghuhao());
        params.put("hetongleixing",hetong.getHetongleixing());
        params.put("pinyongxingshi",hetong.getPinyongxingshi());
        params.put("keshidanjia",hetong.getKeshidanjia());
        params.put("kechengbianhao1",kechengbianhao[0]);
        params.put("kechengmingcheng1",kechengmingcheng[0]);
        params.put("renkebanji1",renkebanji[0]);
        params.put("zongxueshi1",zongxueshi[0]);
        if (zongxueshi.length>1){
            for (int i = 1; i <zongxueshi.length ; i++) {
                String kechengb="kechengbianhao"+(i+1);
                String kechengm="kechengmingcheng"+(i+1);
                String renkeban="renkebanji"+(i+1);
                String zongxue="zongxueshi"+(i+1);
                params.put(kechengb,kechengbianhao[i]);
                params.put(kechengm,kechengmingcheng[i]);
                params.put(renkeban,renkebanji[i]);
                params.put(zongxue,zongxueshi[i]);
            }
        }

        params.put("zhuangzhangjine",hetong.getZhuangzhangjine());
        params.put("starttime",timestart);
        params.put("endtime",timeend);
        params.put("xueqi",xueqi);
        String filename=hetong.getJiaoshixingming()+"的";
        //这里是我说的一行代码
        if (hetong.getHetongleixing().equals("派遣制合同")){
            ExportWordUtils.exportWord("word/派遣合同.docx","D:/test",filename+"派遣合同.docx",params,request,response);
        }else {
            ExportWordUtils.exportWord("word/外聘合同.docx","D:/test",filename+"派遣合同.docx",params,request,response);
        }

    }
    //生成合同方法，获取合同数据
    @RequestMapping("shengcheng2")
    public void shengcheng2(HttpServletRequest request, HttpServletResponse response){
        String hetongid = request.getParameter("hetongid");
        Map<String,Object> params = new HashMap<>();
        Hetong hetong = guanliyuanService.selectByhetongid(Integer.parseInt(hetongid));
        List<Hetong> hetongList=new ArrayList<>();
        hetongList.add(hetong);
        String zhuanzhanghuming = hetong.getZhuanzhanghuming();
        Date chuangjianshijian = hetong.getChuangjianshijian();
        String nian=dateToStr(chuangjianshijian).substring(0,4);
        int year = Integer.parseInt(nian);
        String mouth=dateToStr(chuangjianshijian).substring(6,7);
        int yue = Integer.parseInt(mouth);
        String timestart="";
        String timeend="";
        String xueqi=hetong.getXueqi();
//        if (yue<=7&&yue>=3){
////            timestart=year+"-"+"03"+"-"+"01";
////            timeend=year+"-"+"07"+"-"+"31";
////            xueqi=(year-1)+"-"+year+"-"+2;
////        }else {
////            if (yue>7){
////                int y=year+1;
////                String s = Integer.toString(y);
////                timestart=year+"-"+"08"+"-"+"01";
////                timeend=s+"-"+"01"+"-"+"01";
////                xueqi=year+"-"+(year+1)+"-"+1;
////            }
////            if (yue<3){
////                int y=year-1;
////                String s = Integer.toString(y);
////                timestart=s+"-"+"08"+"-"+"01";
////                timeend=year+"-"+"01"+"-"+"01";
////                int i = year-1;
////                xueqi=(year-1)+"-"+year+"-"+1;
////            }
////        }

        List<hetonginfo> hetonginfoList=new ArrayList<hetonginfo> ();
        String zongxueshiitem[];
        String renkebanjiitem[];
        String kechengmingchengitem[];
        String kechengbianhaoitem[];
            String zongxueshi = hetong.getZongxueshi();
            String renkebanji = hetong.getRenkebanji();
            String kechengmingcheng = hetong.getKechengmingcheng();
            String kechengbianhao = hetong.getKechengbianhao();
            System.out.println("kechengmingcheng"+kechengmingcheng);
                zongxueshiitem=zongxueshi.split(",");
                renkebanjiitem=renkebanji.split(",");
                kechengmingchengitem=kechengmingcheng.split(",");
                kechengbianhaoitem=kechengbianhao.split(",");
            //System.out.println("kechengbianhaoitem的长度"+kechengbianhaoitem.length);
            for (int j = 0; j <kechengbianhaoitem.length ; j++) {
                hetonginfo hetonginfo=new hetonginfo();
                hetonginfo.setZongxueshi(zongxueshiitem[j]);
                hetonginfo.setRenkebanji(renkebanjiitem[j]);
                hetonginfo.setKechengmingcheng(kechengmingchengitem[j]);
                hetonginfo.setKechengbianhao(kechengbianhaoitem[j]);
                hetonginfo.setSize(kechengbianhaoitem.length);
                //System.out.println("hetonginfo"+hetonginfo);
                hetonginfoList.add(hetonginfo);
            }
        params.put("jiaoshibianhao",hetong.getJiaoshibianhao());
        params.put("jiaoshixingming",hetong.getJiaoshixingming());
        params.put("zhuanzhanghuming",zhuanzhanghuming);
        params.put("zhuangzhangjine",hetong.getZhuangzhangjine());
        params.put("shenfenzheng",hetong.getHetongleixing());
        params.put("yinhangkahao",hetong.getYinhangkahao());
        params.put("yinhang",hetong.getYinhang());
        params.put("kaihuhanghuhao",hetong.getKaihuhanghuhao());
        params.put("hetongleixing",hetong.getHetongleixing());
        params.put("pinyongxingshi",hetong.getPinyongxingshi());
        params.put("keshidanjia",hetong.getKeshidanjia());
        params.put("starttime",timestart);
        params.put("endtime",timeend);
        params.put("xueqi",hetong.getXueqi());
        params.put("hetongList",hetongList);
        params.put("hetonginfoList",hetonginfoList);
        String filename=hetong.getJiaoshixingming()+"的";
        //合同生成工具类，用来插入数据，生成word文档
        try {
            if (hetong.getPinyongxingshi().equals("派遣制")){
                WordUtils.exportMillCertificateWord(request,response,params,filename+"派遣合同","派遣合同.ftl");
            }
            else {
                WordUtils.exportMillCertificateWord(request,response,params,filename+"外聘合同","外聘合同.ftl");
            }
        } catch (IOException e) {
                             // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



}
