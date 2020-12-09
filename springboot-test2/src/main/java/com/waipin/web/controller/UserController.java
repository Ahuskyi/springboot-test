package com.waipin.web.controller;
import java.io.IOException;

import com.waipin.model.PageBean;
import com.waipin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller//Rest风格的控制器
@EnableAutoConfiguration//自动配置，相当于写了spring的配置文件
@RequestMapping("user")
public class UserController {
//    @RequestMapping("{id}")
//    @ResponseBody
//    public User userInfo(@PathVariable() Integer id ) {
//        User user=new User("waipin","123");
//        user.setId(id);
//        return user;
//    }
//    public int currentpage=0;
//    @Autowired
//    private UserService userService;
//    @RequestMapping("register")
//    @ResponseBody
//    public String register(String username,String password){
//        userService.register(username,password);
//        return "success";
//    }
//    @RequestMapping("find")
//    @ResponseBody
//    public User find(String username){
//        return userService.findByUsername(username);
//    }
//    @RequestMapping("login")
//    public String login(String username){
//        return "user/list2";
//    }
//    @RequestMapping("muban")
//    public String muban(String username){
//        return "user/index";
//    }
//    @RequestMapping("select")
//    public String select(Map<String,Object> data){
//        data.put("loginname","waipin");
//        data.put("age" , 20);
//        data.put("sex" , "男");
//        List<User> list =userService.findBypage(1,5).getList();
//        data.put("list",list);
//        return "user/usertest";
//    }
//    @RequestMapping("showlist2")
//    public String showlist2(Map<String,Object> data){
//        data.put("loginname","waipin");
//        data.put("age" , 20);
//        data.put("sex" , "男");
//        List<User> list =userService.findBypage(1,5).getList();
//
//        //int size = list.size();
//        //int a=size%10;
//        //int pageNum=5;
//        //data.put("pageNum",pageNum);
//        //System.out.println("pageNum*******"+pageNum);
////
//        data.put("list",list);
//        return "user/list2";
//    }
//
//    @RequestMapping("add")
//    public String add(){
//        return "user/AddPage";
//
//    }
//    @RequestMapping("miao")
//    public String miao(@ModelAttribute("select") User user  ){
////        System.out.println(data.get("id"));
////        System.out.println(data.get("username"));
//        return "user/miao";
//    }
//    @RequestMapping("add2")
//    public ModelAndView login(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        ModelAndView mv = new ModelAndView();
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        User user=new User();
//        user.setUsername(username);
//        mv.addObject("user",user);
//        mv.setViewName("adminPage");
//        return mv;
//    }
//    @RequestMapping("show")
//    public String show(){
//        return "user/usertest3";
//    }
//
//    @RequestMapping("add3")
//    public String add(@Valid User student, BindingResult bindingResult){
//        if(bindingResult.hasErrors()){
//            return bindingResult.getFieldError().getDefaultMessage();
//        }else{
//            System.out.println(student);
//            return "添加成功";
//        }
//    }
//    @RequestMapping("add4")
//    @ResponseBody
//    public Map<String,Object> add4(HttpServletRequest request,HttpServletResponse response){
//        Map<String, Object> map = new HashMap<String, Object>();
//        System.out.println("add4 ajax ");
//        String username=request.getParameter("username");
//        String password=request.getParameter("password");
//        System.out.println(username+password);
//        map.put("result","success");
//        return map;
//    }
//    @RequestMapping("usertest")
//    @ResponseBody
//    public Map<String,Object> usertest(HttpServletRequest request,HttpServletResponse response){
//        Map<String, Object> map = new HashMap<String, Object>();
//        System.out.println("usertest分页测试 ajax ");
//        //String username=request.getParameter("username");
//        //String password=request.getParameter("password");
//        String currentPageStr=request.getParameter("currentPage");
//        String rowStr = request.getParameter("row");
//        int currentPage = 0;//当前页码，默认为第一页
//        if (currentPageStr != null && currentPageStr.length() > 0) {
//            currentPage = Integer.parseInt(currentPageStr);
//        } else {
//            currentPage = 1;
//        }
//        request.getSession().removeAttribute("curr");
//        System.out.println("存入session 的 currentPage :"+currentPage);
//        request.getSession().setAttribute("curr",currentPage);
//        System.out.println("当前页码currentPage"+currentPage);
//        int row=0;//每一页显示的条数，默认为5
//        if(rowStr!=null&&rowStr.length()>0){
//            row=Integer.parseInt(rowStr);
//        }else {
//            row=5;
//        }
//        //System.out.println(username+password);
//        PageBean<User> pb = userService.findBypage(currentPage, row);
//
//        map.put("currentPage",pb.getCurrentPage());
//        map.put("totalPage",pb.getTotalPage());
//        map.put("totalCount",pb.getTotalCount());
//        map.put("list",pb.getList());
//        List<User> listAll =userService.SelectAll();
//        map.put("listAll",listAll);
//        return map;
//    }
//    @RequestMapping("usertest2")
//    public String usertest2(Map<String,Object> map,HttpServletRequest request,HttpServletResponse response){
//        System.out.println("usertest分页测试 ajax ");
//        String currentPageStr=request.getParameter("currentPage");
//        System.out.println("currentPageStr"+currentPageStr);
//        String rowStr = request.getParameter("row");
//        int currentPage = 0;//当前页码，默认为第一页
//        if (currentPageStr != null && currentPageStr.length() > 0) {
//            currentPage = Integer.parseInt(currentPageStr);
//        } else {
//            currentPage = 1;
//        }
//        request.getSession().removeAttribute("curr");
//        System.out.println("存入session 的 currentPage :"+currentPage);
//        request.getSession().setAttribute("curr",currentPage);
//        System.out.println("当前页码currentPage"+currentPage);
//        int row=0;//每一页显示的条数，默认为5
//        if(rowStr!=null&&rowStr.length()>0){
//            row=Integer.parseInt(rowStr);
//        }else {
//            row=5;
//        }
//        //System.out.println(username+password);
//        PageBean<User> pb = userService.findBypage(currentPage, row);
//        map.remove("currentPage");
//        map.remove("list");
//        map.put("currentPage",pb.getCurrentPage());
//        map.put("totalPage",pb.getTotalPage());
//        map.put("totalCount",pb.getTotalCount());
//        map.put("list",pb.getList());
//        System.out.println("存入map的list是"+map.get("list"));
//        List<User> listAll =userService.SelectAll();
//        map.put("listAll",listAll);
//        return "user/usertest";
//    }
//    @RequestMapping("usertest3")
//    public String usertest3(ModelMap modelMap, int currentPage, int row)throws Exception{
////        Map<String, Object> map = new HashMap<String, Object>();
//        //PageHelper.startPage(pageNum, pageSize);
//        System.out.println("usertest3分页测试 ajax ");
//        //System.out.println(username+password);
//        PageBean<User> pb = userService.findBypage(currentPage, row);
//        modelMap.addAttribute("pb", pb);
//        //List<User> listAll =userService.SelectAll();
//        return "user/index-table";
//    }





//    public static void main(String[] args) {
//        SpringApplication.run(UserController.class, args);
//    }
}
