package com.waipin.service;


import com.waipin.model.Guanliyuan;
import com.waipin.model.Jiaoshi;
import com.waipin.model.Xuesheng;
import com.waipin.model.Zhuanjia;

import java.util.List;

public interface LoginService {

    Guanliyuan guanliyuandenglu(String yonghuming,String mima);
    Xuesheng xueshengdenglu(String yonghuming,String mima);
    Zhuanjia zhuanjiadenglu(String yonghuming, String mima);
    Jiaoshi jiaoshidenglu(String yonghuming,String mima);
    boolean guanliyuandengluyanzheng(String yonghuming,String mima);
    boolean xueshengdengluyanzheng(String yonghuming,String mima);
    boolean zhuanjiadengluyanzheng(String yonghuming, String mima);
    boolean jiaoshidengluyanzheng(String yonghuming,String mima);
    List<Guanliyuan> findall();
    void save(String yonghuming,String mima);

}
