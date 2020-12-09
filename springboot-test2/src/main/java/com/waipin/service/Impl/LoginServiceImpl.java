package com.waipin.service.Impl;

import com.waipin.mapper.GuanliyuanMapper;
import com.waipin.mapper.JiaoshiMapper;
import com.waipin.mapper.XueshengMapper;
import com.waipin.mapper.ZhuanjiaMapper;
import com.waipin.model.Guanliyuan;
import com.waipin.model.Jiaoshi;
import com.waipin.model.Xuesheng;
import com.waipin.model.Zhuanjia;
import com.waipin.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    GuanliyuanMapper guanliyuanMapper;
    @Autowired
    XueshengMapper xueshengMapper;
    @Autowired
    ZhuanjiaMapper zhuanjiaMapper;
    @Autowired
    JiaoshiMapper jiaoshiMapper;
    Map<String,Object> map=new HashMap<String,Object>();
    @Override
    public Guanliyuan guanliyuandenglu(String yonghuming, String mima) {
        map.put("yonghuming",yonghuming);
        map.put("mima",mima);
        Guanliyuan login = guanliyuanMapper.login(map);
//        Guanliyuan login = guanliyuanMapper.login(yonghuming,mima);
        if (login!=null){
            System.out.println("guanliyuandenglu login="+login);
            return login;
        }
        return null;
    }

    @Override
    public Xuesheng xueshengdenglu(String yonghuming, String mima) {
        Xuesheng login = xueshengMapper.login(yonghuming,mima);
        if (login!=null){
            return login;
        }
        return null;
    }
    @Override
    public Zhuanjia zhuanjiadenglu(String yonghuming, String mima) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("yonghuming",yonghuming);

        Zhuanjia login = zhuanjiaMapper.login(yonghuming,mima);
        if (login!=null){
            return login;
        }
        return null;
    }
    @Override
    public Jiaoshi jiaoshidenglu(String yonghuming, String mima) {
        Jiaoshi login = jiaoshiMapper.login(yonghuming, mima);
        if (login!=null){
            return login;
        }
        return null;
    }

    @Override
    public boolean guanliyuandengluyanzheng(String yonghuming, String mima) {
        Guanliyuan login =new Guanliyuan();
          login=guanliyuanMapper.fingbyyonghuming(yonghuming);
        if (login!=null){
            System.out.println("guanliyuandenglu login="+login);
            return true;
        }
        return false;
    }

    @Override
    public boolean xueshengdengluyanzheng(String yonghuming, String mima) {
        Xuesheng login = xueshengMapper.login(yonghuming,mima);
        if (login!=null){
            return true;
        }
        return false;
    }

    @Override
    public boolean zhuanjiadengluyanzheng(String yonghuming, String mima) {
        Zhuanjia login = zhuanjiaMapper.login(yonghuming, mima);
        if (login!=null){
            return true;
        }
        return false;
    }

    @Override
    public boolean jiaoshidengluyanzheng(String yonghuming, String mima) {
        Jiaoshi login = jiaoshiMapper.login(yonghuming, mima);
        if (login!=null){
            return true;
        }
        return false;
    }

    @Override
    public List<Guanliyuan> findall() {
        List<Guanliyuan> findall=null;
        findall = guanliyuanMapper.findall();
        return findall;
    }

    @Override
    public void save(String yonghuming, String mima) {
        guanliyuanMapper.save(yonghuming, mima);
    }
}
