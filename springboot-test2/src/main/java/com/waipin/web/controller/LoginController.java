package com.waipin.web.controller;

import com.waipin.model.*;
import com.waipin.service.GuanliyuanService;
import com.waipin.service.LoginService;
import com.waipin.service.Impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller//控制器
@EnableAutoConfiguration//自动配置，相当于写了spring的配置文件
@RequestMapping("login")
public class LoginController {

    @Autowired
    private LoginService loginService=new LoginServiceImpl();
    @Autowired
    GuanliyuanService guanliyuanService;
    //login/showlogin
    @RequestMapping("showlogin")
    public String showlogin(){
        return "denglu/showlogin";
    }

    @RequestMapping("login")//login/login
    @ResponseBody//回传信息，格式是json格式
    public Map<String,Object> login(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println("login ajax ");
        String yonghuming=request.getParameter("yonghuming");
        String mima=request.getParameter("mima");
        System.out.println("用户名"+yonghuming+"密码"+mima);
        boolean is =false;
        boolean login1 =false;
        User selectuseryonghuming = guanliyuanService.selectuseryonghuming(yonghuming);
        if (selectuseryonghuming!=null){
            if (!selectuseryonghuming.getMima().equals(mima)){
                map.put("errormsg","密码错误");
            }else {

                String shenfen=selectuseryonghuming.getShenfen();
                System.out.println("shenfen is"+shenfen);
                if (shenfen.equals("guanliyuan")){
                    Guanliyuan guanliyuan=loginService.guanliyuandenglu(yonghuming,mima);
                    System.out.println("guanliyuan"+guanliyuan.getId());
                    map.put("dengluren",guanliyuan.getId());
                }
                if (shenfen.equals("jiaoshi")){
                    Jiaoshi jiaoshidenglu = loginService.jiaoshidenglu(yonghuming, mima);
                    map.put("dengluren",jiaoshidenglu.getId());
                }
                if (shenfen.equals("xuesheng")){
                    Xuesheng xueshengdenglu = loginService.xueshengdenglu(yonghuming, mima);
                    map.put("dengluren",xueshengdenglu.getXuehao());
                }
                if (shenfen.equals("zhuanjia")){
                    Zhuanjia zhuanjiadenglu = loginService.zhuanjiadenglu(yonghuming,mima);
                    map.put("dengluren",zhuanjiadenglu.getId());
                }
                map.put("shenfen",shenfen);
            }
        }else {
            map.put("errormsg","用户不存在");
        }
        return map;
    }

}