package com.waipin.service;


import com.waipin.model.*;

import java.nio.file.attribute.UserPrincipal;
import java.util.Date;
import java.util.List;

public interface GuanliyuanService {

    Guanliyuan chazhaoid(int id);
    List<Guanliyuan> selectAllguanliyuan();
    boolean updateinfo(Guanliyuan guanliyuan);
    User selectuserid(String yonghuming,String mima);
    User selectuseryonghuming(String yonghuming);
    void updateuserinfo(User user);
    void saveuserinfo(User user);
    //***********教师管理模块*********************
    public PageBean<Jiaoshi> jiaoshifindBypage(int currentPage, int row,int id,String yonghuming,String xingming);
    Jiaoshi selectByteavherid(int id);
    boolean updateTeacherinfo(Jiaoshi jiaoshi);
    boolean insertTeacherinfo(Jiaoshi jiaoshi);
    boolean updateTeachershenfenzhenglujing(int id,String shenfenzhenglujing);
    boolean updateTeachercanjiagongzuoshijianlujing(int id,String canjiagongzuoshijianlujing);
    boolean updateTeacherxueweizhenglujing(int id,String xueweizhenglujing);
    boolean updateTeacherxuelizhenglujing(int id,String xuelizhenglujing);
    boolean updateTeacherzhichengzhenglujing(int id,String zhichenglujing);
    boolean updateTeacherzongheshuipingfenshulujing(int id,String zhongheshuipingfenshulujing);
    boolean jiaoshidel(int id);
    List<Jiaoshi> selectAlljiaoshi();
    //***********教学督导模块*********************
    public PageBean<Jiaoxuedudao> jiaoxuedudaofindBypage(int currentPage, int row,String xingming,String shenhezhuangtai,String fashengshijian);
    Jiaoxuedudao selectByjiaoxuedudaoid(int id);
//  Jiaoxuedudao selectByjiaoxuedudao
    boolean updateJiaoxuedudaoinfo(Jiaoxuedudao jiaoxuedudao);
    boolean insertJiaoxuedudaoinfo(Jiaoxuedudao jiaoxuedudao);
    boolean dudaodel(int id);

    //***********处罚等级管理模块*****************
    List<Chufadengji> selectAllchufadengji();
    Chufadengji selectBychufadengjiid(int id);
    boolean updateChufadengjiinfo(Chufadengji chufadengji);
    boolean insertChufadengjiinfo(Chufadengji chufadengji);
    boolean chufadengjidel(int id);
    //***********学生管理模块*********************
    public PageBean<Xuesheng> xueshengfindBypage(int currentPage, int row,int id,String yonghuming,String xingming);
    Xuesheng selectByxueshengid(int id);
    boolean updateXueshenginfo(Xuesheng xuesheng);
    boolean insertXueshenginfo(Xuesheng xuesheng);
    boolean xueshengdel(int id);
    //***********课程管理模块*********************
    public PageBean<Kecheng> kechengfindBypage(int currentPage, int row,int id,String kechenghao,String kechengmingcheng);
    Kecheng selectBykechengid(int id);
    boolean updateKechenginfo(Kecheng kecheng);
    boolean insertKechenginfo(Kecheng kecheng);
    boolean updateKechengjiaogxuedaganglujing(int id,String jiaoxuedaganglujing);
    boolean kechengdel(int id);
    List<Kecheng> selectAllkecheng();
    //***********岗前培训资料管理模块*********************
    public PageBean<Gangqianpeixunziliao> GangqianpeixunziliaoBypage(int currentPage, int row,int id,String ziliaomingcheng);
    Gangqianpeixunziliao selectByGangqianpeixunziliaoid(int id);
    boolean updateGangqianpeixunziliaoinfo(Gangqianpeixunziliao gangqianpeixunziliao);
    boolean insertGangqianpeixunziliaoinfo(Gangqianpeixunziliao gangqianpeixunziliao);
    boolean updateGangqianpeixunziliaolujing(int id,String ziliaolujing);
    boolean ziliaodel(int id);
    //***********班级管理模块*********************
    public PageBean<Banji> banjifindBypage(int currentPage, int row,int id,String banjimingcheng);
    Banji selectBybanjiid(int id);
    boolean updatebanjiinfo(Banji banji);
    boolean insertbanjiinfo(Banji banji);
    boolean banjidel(int id);
    List<Banji> selectAllbanji();
    //***********上课班级管理模块*********************
    public PageBean<Shangkebanji> shangkebanjifindBypage(int currentPage, int row ,String jiaoshixingming ,String kechengmingcheng ,String xueqi);
    Shangkebanji selectByshangkebanjiid(int id);
    boolean updateshangkebanjiinfo(Shangkebanji shangkebanji);
    boolean insertshangkebanjiinfo(Shangkebanji shangkebanji);
    boolean shangkebanjidel(int id);

    //***********学位管理模块*****************
    List<Xuewei> selectAllxuewei();
    Xuewei selectByxueweiid(int id);
    boolean updatexueweiinfo(Xuewei xuewei);
    boolean insertxueweiinfo(Xuewei xuewei);
    boolean xueweidel(int id);
    //***********职称管理模块*****************
    List<Zhicheng> selectAllzhicheng();
    Zhicheng selectByzhichengid(int id);
    boolean updatezhichenginfo(Zhicheng zhicheng);
    boolean insertzhichenginfo(Zhicheng zhicheng);
    boolean zhichengdel(int id);
    //***********工作年限管理模块*****************
    List<Gongzuonianxian> selectAllgongzuonianxian();
    Gongzuonianxian selectBygongzuonianxianid(int id);
    boolean updategongzuonianxianinfo(Gongzuonianxian gongzuonianxian);
    boolean insertgongzuonianxianinfo(Gongzuonianxian gongzuonianxian);
    boolean gongzuonianxiandel(int id);
    //***********工作年限管理模块*****************
    List<Zonghepingdingjieguo> selectAllzonghepingdingjieguo();
    Zonghepingdingjieguo selectByzonghepingdingjieguoid(int id);
    boolean updatezonghepingdingjieguoinfo(Zonghepingdingjieguo zonghepingdingjieguo);
    boolean insertzonghepingdingjieguoinfo(Zonghepingdingjieguo zonghepingdingjieguo);
    boolean zonghepingdingjieguodel(int id);
    //***********专家管理模块*********************
    public PageBean<Zhuanjia> zhuanjiafindBypage(int currentPage, int row,int id ,String xingming);
    Zhuanjia selectByzhuanjiaid(int id);
    boolean updateZhuanjiainfo(Zhuanjia zhuanjia);
    boolean insertZhuanjiainfo(Zhuanjia zhuanjia);
    boolean zhuanjiadel(int id);
    List<Zhuanjia> selectAllzhuanjia();
    //***********试讲管理模块*********************
    public  PageBean<Shijiangdangan> shijiangfindBypage(int currentPage, int row,int id,String jiaoshixingming,String shijiangkecheng);
    Shijiangdangan selectByshijiangid(int id);
    boolean updateshijiangdanganinfo(Shijiangdangan shijiangdangan);
    boolean insertshijiangdanganinfo(Shijiangdangan shijiangdangan);
    boolean updateShijiangneirongurl(int id,String shijiangneirongurl);
    boolean updateShijiangfenshuurl(int id,String shijiangfenshuurl);
    boolean shijiangdel(int id);
    int fenshuSelectbyxuewei(String xuewei);
    int fenshuSelectbygongniannianxian(int gongzuonianxian);
    int fenshuSelectbyzhicheng(String zhicheng);
    double fenshuSelectbyshijiang(int jiaoshiid);
    String dengjiSelectbyfenshu(double fenshu);
    //boolean updateZonghe(String pingdingdengji,String fenshu);
    //***********薪水管理模块*****************
    List<Xinshui> selectAllxinshui();
    Xinshui selectByxinshuiid(int id);
    boolean updatexinshuiinfo(Xinshui xinshui);
    boolean insertxinshuiinfo(Xinshui xinshui);
    boolean xinshuidel(int id);
    //***********合同管理模块*********************
    public PageBean<Hetong> hetongfindBypage(int currentPage, int row , String xueqi);
    Hetong selectByhetongid(int id);
    String selectxueshibyjiaoshibianhao(int id, String xueqi,String shangkexingshi) ;
    String selectkechengbianhaobyjiaoshibianhao(int id, String xueqi,String shangkexingshi) ;
    String selectkechengmingchengbyjiaoshibianhao(int id, String xueqi,String shangkexingshi) ;
    String selectrenkebanjibyjiaoshibianhao(int id, String xueqi,String shangkexingshi) ;
    boolean updatehetonginfo(Hetong Hetong);
    boolean inserthetonginfo(Hetong Hetong);
    boolean hetongdel(int id);
    List<Hetong> selectAllhetong(String xueqi);
    public PageBean<Hetong> hetongdanganfindBypage(int currentPage, int row ,int jiaoshibianhao,String jiaoshixingming , String xueqi);
    boolean updatehetongurl(int id,String hetongurl);
    /*LAST****************************************************/
    int selectjiaoxuedudaoLastid();
    int selecthetongLastid();
    int selectchufadengjiLastid();
    int selectgangqianpeixunziliaoLastid();
    int selectgongziLastid();
    int selectgongzuonianxianLastid();
    int selectshangkebanjiLastid();
    int selectshijiangdanganLastid();
    int selectxinshuiLastid();
    int selectkechengLastid();
    int selectbanjiLastid();
    int selectxueshengLastid();
    int selectxueweiLastid();
    int selectzhichengLastid();
    int selectzhuanjiaLastid();
    int selectzonghepingdingjieguoLastid();
    int selectjiaoshiLastid();
    /************************************工资**************************************/
    boolean updategongziinfo(Gongzi gongzi);
    boolean insertgongziinfo(Gongzi gongzi);
    public PageBean<Gongzi> gongzifindBypage(int currentPage, int row , String time,String jiaoshibianhao,String jiaoshixingming);
    Gongzi selectBygongziid(int id);
    boolean updategongzifakuaninfo(int jiaoshibianhao,double fakuanjine ,String shijian);
    List<Gongzi> selectByjiaoshibianhaoandcjsj(int jiaoshibiaohao,Date chuangjianshijian);
    public PageBean<Gongzi> gongzidanganfindBypage(int currentPage, int row ,int jiaoshibianhao,String jiaoshixingming, String time);
    public List<Gongzi> yijianshengchenggongzi(String fafangzhuangtai, String shijian);

}
