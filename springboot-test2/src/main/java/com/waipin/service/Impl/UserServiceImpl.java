package com.waipin.service.Impl;

import com.waipin.model.PageBean;
import com.waipin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
//    Map<String,Object> map=new HashMap<String,Object>();
//    @Autowired
//    //private JdbcTemplate jdbcTemplate;
//    private UserMapper userMapper;
//    @Override
//    public void register(String username, String password) {
//        //String sql ="insert into user( username , address ) values (?,?)";
////        jdbcTemplate.update(sql,username, password);
//        userMapper.save(username,password);
//    }
//
//    @Override
//    public User findByUsername(String username) {
//
//        return userMapper.findByUsername(username);
//    }
//
//    @Override
//    public List<User> SelectAll() {
//        return userMapper.SelectAll();
//    }
//
//    @Override
//    public PageBean<User> findBypage(int currentPage, int row) {
//        PageBean<User> pb = new PageBean<User>();
//        //List<User> byRid = new ArrayList<User>();
//        pb.setCurrentPage(currentPage);
//        pb.setRows(row);
//        List<User> all = userMapper.SelectAll();
//        int totalCount = all.size();
//        System.out.println("UserServiceImpl/findAll 中 totalCount 是" + totalCount);
//        pb.setTotalCount(totalCount);
//        int start = (currentPage - 1) * row;
////        map.put("uid", uid);
//        map.put("start", start);
//        map.put("row", row);
//        List<User> bypage = userMapper.findBypage(map);
//        pb.setList(bypage);
//        System.out.println("bypage"+bypage);
//        int totalPage = totalCount % row == 0 ? totalCount / row : (totalCount / row) + 1;
//        pb.setTotalPage(totalPage);
//        return pb;
//    }

}
