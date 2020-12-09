package com.waipin.web.controller;

import com.waipin.model.*;
import com.waipin.service.GuanliyuanService;
import com.waipin.service.JiaoshiService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller//Rest风格的控制器
@EnableAutoConfiguration//自动配置，相当于写了spring的配置文件
@RequestMapping("jiaoshi")
public class JiaoshiController {
    @Autowired
    JiaoshiService jiaoshiService;
    @Autowired
    GuanliyuanService guanliyuanService;
    String denglurenid="";
    String denglurenxingming="";
    Jiaoshi jiaoshi=null;
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
        Jiaoshi denglu = jiaoshiService.chazhaoid(id);
        denglurenxingming=denglu.getXingming();
        jiaoshi=denglu;
//        Map<String, Object> map = new HashMap<String, Object>();
        map.put("denglurenid",denglurenid);
        return "jiaoshi/gerenzhongxin";
    }
    @RequestMapping("gerenxinxizhanshi")
    @ResponseBody
    public Map<String,Object> gerenxinxizhanshi(HttpServletRequest request, HttpServletResponse response)
    {
//        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println("gerenxinxizhanshi个人信息展示测试 ajax ");
        //String denglurenStr= (String) request.getSession().getAttribute("denglurenid");
         map.put("denglurenxingming",denglurenxingming);
        map.put("jiaoshi",jiaoshi);
        return map;
    }

    @RequestMapping ("gerenzhongxin")
    public String gerenzhongxin(){
        return "jiaoshi/gerenzhongxin";
    }
    /*个人信息修改*/
//    @RequestMapping("gerenxinxixiugai")
//    @ResponseBody
//    public String gerenxinxixiugai(@Valid Jiaoshi jiaoshi1, BindingResult bindingResult){
//        String result ="";
//        if(bindingResult.hasErrors()){
//            System.out.println("个人信息修改有问题");
//            return bindingResult.getFieldError().getDefaultMessage();
//        }else{
//            System.out.println(jiaoshi1);
//            User selectuserid=new User();
//            User selectuseryonghuming = guanliyuanService.selectuseryonghuming(jiaoshi1.getYonghuming());
//            selectuserid = guanliyuanService.selectuserid(jiaoshi1.getYonghuming(), jiaoshi1.getMima());
//            if (selectuseryonghuming==null||jiaoshi.getYonghuming()==jiaoshi1.getYonghuming()){
//                selectuserid.setYonghuming(jiaoshi1.getYonghuming());
//                selectuserid.setMima(jiaoshi1.getMima());
//                selectuserid.setShenfen("jiaoshi");
//                guanliyuanService.updateuserinfo(selectuserid);
//                boolean updateinfo = jiaoshiService.updateinfo(jiaoshi1);
//                if (updateinfo) {
//                    result="修改成功";
//                    jiaoshi=jiaoshi1;
//                    denglurenid=jiaoshi1.getId().toString();
//                }
//                else {
//                    result="修改失败";
//                }
//            }else {
//                result="用户名已存在";
//            }
//        }
//        System.out.println("result是"+result);
//        return result;
//    }

    @RequestMapping("gerenxinxixiugai")
    @ResponseBody
    public Map<String,Object> gerenxinxixiugai(HttpServletRequest request,HttpServletResponse response){
        String mima=request.getParameter("mima");
        String dianhua = request.getParameter("dianhua");
        User selectuserid=new User();
        selectuserid = guanliyuanService.selectuserid(jiaoshi.getYonghuming(), jiaoshi.getMima());
        guanliyuanService.updateuserinfo(selectuserid);
        selectuserid.setYonghuming(jiaoshi.getYonghuming());
        selectuserid.setShenfen("jiaoshi");
        jiaoshi.setMima(mima);
        jiaoshi.setDianhua(dianhua);
        selectuserid.setMima(mima);
        boolean b = guanliyuanService.updateTeacherinfo(jiaoshi);
        guanliyuanService.updateuserinfo(selectuserid);
        if (b){
            map.put("message","修改成功");

        }else {
            map.put("message","修改失败");
        }
        return map;
    }

    /*培训资料**************************************************************/
    /*培训资料管理模块*************************************************/
    @RequestMapping ("peixunziliaoguanli")
    public String peixunziliao(){
        return "jiaoshi/peixunziliaoguanli";
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
        map.put("denglurenxingming",jiaoshi.getXingming());
        return map;
    }

}
