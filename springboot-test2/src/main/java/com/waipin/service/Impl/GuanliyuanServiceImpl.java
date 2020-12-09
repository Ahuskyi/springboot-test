package com.waipin.service.Impl;

import com.waipin.mapper.*;
import com.waipin.model.*;
import com.waipin.service.GuanliyuanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GuanliyuanServiceImpl implements GuanliyuanService {
    @Autowired
    GuanliyuanMapper guanliyuanMapper;
    @Autowired
    JiaoshiMapper jiaoshiMapper;
    @Autowired
    JiaoxuedudaoMapper jiaoxuedudaoMapper;
    @Autowired
    ChufadengjiMapper chufadengjiMapper;
    @Autowired
    XueshengMapper xueshengMapper;
    @Autowired
    KechengMapper kechengMapper;
    @Autowired
    GangqianpeixunziliaoMapper gangqianpeixunziliaoMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    BanjiMapper banjiMapper;
    @Autowired
    ShangkebanjiMapper shangkebanjiMapper;
    @Autowired
    XueweiMapper xueweiMapper;
    @Autowired
    ZhichengMapper zhichengMapper;
    @Autowired
    GongzuonianxianMapper gongzuonianxianMapper;
    @Autowired
    ZonghepingdingjieguoMapper zonghepingdingjieguoMapper;
    @Autowired
    ZhuanjiaMapper zhuanjiaMapper;
    @Autowired
    ShijiangdanganMapper shijiangdanganMapper;
    @Autowired
    XinshuiMapper xinshuiMapper;
    @Autowired
    HetongMapper hetongMapper;
    @Autowired
    GongziMapper gongziMapper;

    Map<String,Object> map=new HashMap<String,Object>();
    @Override
    public Guanliyuan chazhaoid(int id) {
        Guanliyuan guanliyuan = guanliyuanMapper.selectByPrimaryKey(id);
        return guanliyuan;
    }

    @Override
    public List<Guanliyuan> selectAllguanliyuan() {
        return guanliyuanMapper.findall();
    }

    @Override
    public boolean updateinfo(Guanliyuan guanliyuan) {
        if (guanliyuan!=null){
            guanliyuanMapper.updateByPrimaryKey(guanliyuan);
            return true;
        }
        return false;
    }

    @Override
    public User selectuserid(String yonghuming, String mima) {
        map.put("yonghuming",yonghuming);
        map.put("mima",mima);
        User selectuserid = guanliyuanMapper.selectuserid(map);
        return selectuserid;
    }
    @Override
    public User selectuseryonghuming(String yonghuming) {
        map.put("yonghuming",yonghuming);
        User selectuserid = guanliyuanMapper.selectuseryonghuming(map);
       // System.out.println("selectuserid mima"+selectuserid.getMima());
        return selectuserid;
    }

    @Override
    public void updateuserinfo(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public void saveuserinfo(User user) {
        userMapper.insert(user);
    }

    @Override
    public PageBean<Jiaoshi> jiaoshifindBypage(int currentPage, int row,int id,String yonghuming,String xingming) {
        PageBean<Jiaoshi> pb = new PageBean<Jiaoshi>();
        //List<User> byRid = new ArrayList<User>();
        map.put("id",id);
        map.put("yonghuming",yonghuming);
        map.put("xingming",xingming);
        pb.setCurrentPage(currentPage);
        pb.setRows(row);
        List<Jiaoshi> all = guanliyuanMapper.selectAllteacher(map);
        int totalCount = all.size();
        System.out.println("教师分页的总条数totalCount 是" + totalCount);
        pb.setTotalCount(totalCount);
        int start = (currentPage - 1) * row;
//        map.put("uid", uid);
        map.put("start", start);
        map.put("row", row);
        List<Jiaoshi> bypage = guanliyuanMapper.selectTeacherBypage(map);
        pb.setList(bypage);
        System.out.println("bypage"+bypage);
        int totalPage = totalCount % row == 0 ? totalCount / row : (totalCount / row) + 1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public Jiaoshi selectByteavherid(int id) {
        Jiaoshi jiaoshi = jiaoshiMapper.selectByPrimaryKey(id);
        return jiaoshi;
    }

    @Override
    public boolean updateTeacherinfo(Jiaoshi jiaoshi) {
        if (jiaoshi!=null){
            jiaoshiMapper.updateByPrimaryKey(jiaoshi);
            return true;
        }
        return false;
    }

    @Override
    public boolean insertTeacherinfo(Jiaoshi jiaoshi) {
        if (jiaoshi!=null){
            jiaoshiMapper.insert(jiaoshi);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateTeachershenfenzhenglujing(int id, String shenfenzhenglujing) {
        map.put("shenfenzhenglujing",shenfenzhenglujing);
        map.put("id",id);
        boolean b = guanliyuanMapper.updateTeachershenfenzhenglujing(map);
        if (b){
            System.out.println("教师身份证路径修改成功");
        }
        return b;
    }

    @Override
    public boolean updateTeachercanjiagongzuoshijianlujing(int id, String canjiagongzuoshijianlujing) {
        map.put("canjiagongzuoshijianlujing",canjiagongzuoshijianlujing);
        map.put("id",id);
        boolean b = guanliyuanMapper.updateTeachercanjiagongzuoshijianlujing(map);
        if (b){
            System.out.println("简历路径修改成功");
        }
        return b;
    }

    @Override
    public boolean updateTeacherxueweizhenglujing(int id, String xueweizhenglujing) {
        map.put("xueweizhenglujing",xueweizhenglujing);
        map.put("id",id);
        boolean b = guanliyuanMapper.updateTeacherxueweizhenglujing(map);
        if (b){
            System.out.println("教师学位证路径修改成功");
        }
        return b;
    }

    @Override
    public boolean updateTeacherxuelizhenglujing(int id, String xuelizhenglujing) {
        map.put("xuelizhenglujing",xuelizhenglujing);
        map.put("id",id);
        boolean b = guanliyuanMapper.updateTeacherxuelizhenglujing(map);
        if (b){
            System.out.println("教师学历证路径修改成功");
        }
        return b;
    }

    @Override
    public boolean updateTeacherzhichengzhenglujing(int id, String zhichengzhenglujing) {
        map.put("zhichengzhenglujing",zhichengzhenglujing);
        map.put("id",id);
        boolean b = guanliyuanMapper.updateTeacherzhichengzhenglujing(map);
        if (b){
            System.out.println("教师职称证路径修改成功");
        }
        return b;
    }

    @Override
    public boolean updateTeacherzongheshuipingfenshulujing(int id, String zongheshuipingfenshulujing) {
        map.put("zongheshuipingfenshulujing",zongheshuipingfenshulujing);
        map.put("id",id);
        boolean b = guanliyuanMapper.updateTeacherzongheshuipingfenshulujing(map);
        if (b){
            System.out.println("教师综合水平分数路径修改成功");
        }
        return b;
    }

    @Override
    public boolean jiaoshidel(int id) {
        int i = jiaoshiMapper.deleteByPrimaryKey(id);
        if (i!=0){
            return true;
        }
        return false;
    }

    @Override
    public List<Jiaoshi> selectAlljiaoshi() {
        map.clear();
        return guanliyuanMapper.selectAllteacher(map);
    }

    /*教学督导模块*********************************************************/
    @Override
    public PageBean<Jiaoxuedudao> jiaoxuedudaofindBypage(int currentPage, int row,String xingming,String shenhezhuangtai,String fashengshijian) {
        PageBean<Jiaoxuedudao> pb = new PageBean<Jiaoxuedudao>();
        map.put("xingming",xingming);
        map.put("shenhezhuangtai",shenhezhuangtai);
        map.put("fashengshjian",fashengshijian);
        pb.setCurrentPage(currentPage);
        pb.setRows(row);
        List<Jiaoxuedudao> all = guanliyuanMapper.selectAlljiaoxuedudao(map);
        int totalCount = all.size();
        System.out.println("督导记录的总条数totalCount 是" + totalCount);
        pb.setTotalCount(totalCount);
        int start = (currentPage - 1) * row;
//        map.put("uid", uid);
        map.put("start", start);
        map.put("row", row);
        List<Jiaoxuedudao> bypage = guanliyuanMapper.selectJxuedudaoBypage(map);
        pb.setList(bypage);
        System.out.println("bypage"+bypage);
        int totalPage = totalCount % row == 0 ? totalCount / row : (totalCount / row) + 1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public Jiaoxuedudao selectByjiaoxuedudaoid(int id) {
        return jiaoxuedudaoMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateJiaoxuedudaoinfo(Jiaoxuedudao jiaoxuedudao) {
        if (jiaoxuedudao!=null){
            jiaoxuedudaoMapper.updateByPrimaryKey(jiaoxuedudao);
            return true;
        }
        return false;
    }

    @Override
    public boolean insertJiaoxuedudaoinfo(Jiaoxuedudao jiaoxuedudao) {
        if (jiaoxuedudao!=null){
            jiaoxuedudaoMapper.insert(jiaoxuedudao);
            return true;
        }
        return false;
    }

    @Override
    public boolean dudaodel(int id) {
        if (id!=0){
            jiaoxuedudaoMapper.deleteByPrimaryKey(id);
            return true;
        }
        return false;
    }

    @Override
    public int selectjiaoxuedudaoLastid() {
        int i = guanliyuanMapper.selectjiaoxuedudaoLastid();
        return i+1;
    }
        /***********************LAST**************************************/
    @Override
    public int selecthetongLastid() {
        return guanliyuanMapper.selecthetongLastid()+1;
    }

    @Override
    public int selectchufadengjiLastid() {
        return guanliyuanMapper.selectchufadengjiLastid()+1;
    }

    @Override
    public int selectgangqianpeixunziliaoLastid() {
        return guanliyuanMapper.selectgangqianpeixunziliaoLastid()+1;
    }

    @Override
    public int selectgongziLastid() {
        return guanliyuanMapper.selectgongziLastid()+1;
    }

    @Override
    public int selectgongzuonianxianLastid() {
        return guanliyuanMapper.selectgongzuonianxianLastid()+1;
    }

    @Override
    public int selectshangkebanjiLastid() {
        return guanliyuanMapper.selectshangkebanjiLastid()+1;
    }

    @Override
    public int selectshijiangdanganLastid() {
        return guanliyuanMapper.selectshijiangdanganLastid()+1;
    }

    @Override
    public int selectxinshuiLastid() {
        return guanliyuanMapper.selectxinshuiLastid()+1;
    }

    @Override
    public int selectxueshengLastid() {
        return guanliyuanMapper.selectxueshengLastid()+1;
    }

    @Override
    public int selectxueweiLastid() {
        return guanliyuanMapper.selectxueweiLastid()+1;
    }

    @Override
    public int selectzhichengLastid() {
        return guanliyuanMapper.selectzhichengLastid()+1;
    }

    @Override
    public int selectzhuanjiaLastid() {
        return guanliyuanMapper.selectzhuanjiaLastid()+1;
    }

    @Override
    public int selectzonghepingdingjieguoLastid() {
        return guanliyuanMapper.selectzonghepingdingjieguoLastid()+1;
    }

    @Override
    public int selectjiaoshiLastid() {
        return guanliyuanMapper.selectjiaoshiLastid()+1;
    }
    @Override
    public int selectkechengLastid() {
        return guanliyuanMapper.selectkechengLastid()+1;
    }
    @Override
    public int selectbanjiLastid() {
        return guanliyuanMapper.selectbanjiLastid()+1;
    }


    /*学生管理********************************************************************************/
    @Override
    public PageBean<Xuesheng> xueshengfindBypage(int currentPage, int row,int id,String yonghuming,String xingming) {
        PageBean<Xuesheng> pb = new PageBean<Xuesheng>();
        map.put("xuehao",id);
        map.put("yonghuming",yonghuming);
        map.put("xingming",xingming);
        pb.setCurrentPage(currentPage);
        pb.setRows(row);
        List<Xuesheng> all = guanliyuanMapper.selectAllxuesheng(map);
        int totalCount = all.size();
        System.out.println("督导记录的总条数totalCount 是" + totalCount);
        pb.setTotalCount(totalCount);
        int start = (currentPage - 1) * row;
//        map.put("uid", uid);
        map.put("start", start);
        map.put("row", row);

        List<Xuesheng> bypage = guanliyuanMapper.selectxueshengBypage(map);
        pb.setList(bypage);
        System.out.println("bypage"+bypage);
        int totalPage = totalCount % row == 0 ? totalCount / row : (totalCount / row) + 1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public Xuesheng selectByxueshengid(int id) {
        return xueshengMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateXueshenginfo(Xuesheng xuesheng) {
        if (xuesheng!=null){
            xueshengMapper.updateByPrimaryKey(xuesheng);
            return true;
        }
        return false;
    }

    @Override
    public boolean insertXueshenginfo(Xuesheng xuesheng) {
        if (xuesheng!=null){
            xueshengMapper.insert(xuesheng);
            return true;
        }
        return false;
    }

    @Override
    public boolean xueshengdel(int id) {
        if (id!=0){
            xueshengMapper.deleteByPrimaryKey(id);
            return true;
        }
        return false;
    }

    /*课程管理********************************************************************************/
    @Override
    public PageBean<Kecheng> kechengfindBypage(int currentPage, int row,int id,String kechenghao,String kechengmingcheng) {
        PageBean<Kecheng> pb = new PageBean<Kecheng>();
        map.put("id",id);
        map.put("kechenghao",kechenghao);
        map.put("kechengmingcheng",kechengmingcheng);
        pb.setCurrentPage(currentPage);
        pb.setRows(row);
        List<Kecheng> all = guanliyuanMapper.selectAllkecheng(map);
        int totalCount = all.size();
        System.out.println("督导记录的总条数totalCount 是" + totalCount);
        pb.setTotalCount(totalCount);
        int start = (currentPage - 1) * row;
//        map.put("uid", uid);
        map.put("start", start);
        map.put("row", row);
        List<Kecheng> bypage = guanliyuanMapper.selectkechengBypage(map);
        pb.setList(bypage);
        System.out.println("bypage"+bypage);
        int totalPage = totalCount % row == 0 ? totalCount / row : (totalCount / row) + 1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public Kecheng selectBykechengid(int id) {
        return kechengMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateKechenginfo(Kecheng kecheng) {
        if (kecheng!=null){
            kechengMapper.updateByPrimaryKey(kecheng);
            return true;
        }
        return false;
    }

    @Override
    public boolean insertKechenginfo(Kecheng kecheng) {
        if (kecheng!=null){
            kechengMapper.insert(kecheng);
            return true;
        }
        return false;
    }
    @Override
    public boolean updateKechengjiaogxuedaganglujing(int id,String jiaoxuedaganglujing) {
        map.put("jiaoxuedaganglujing",jiaoxuedaganglujing);
        map.put("id",id);
        boolean b = guanliyuanMapper.updateKechengjiaoxuedaganglujing(map);
        if (b){
            System.out.println("教学大纲路径修改成功");
        }
        return b;
    }

    @Override
    public boolean kechengdel(int id) {
        if (id!=0){
            kechengMapper.deleteByPrimaryKey(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Kecheng> selectAllkecheng() {
        map.clear();
        return guanliyuanMapper.selectAllkecheng(map);
    }

    /*岗前培训资料管理********************************************************************************/
    @Override
    public PageBean<Gangqianpeixunziliao> GangqianpeixunziliaoBypage(int currentPage, int row,int id,String ziliaomingcheng) {
        PageBean<Gangqianpeixunziliao> pb = new PageBean<Gangqianpeixunziliao>();
        map.put("ziliaomingcheng",ziliaomingcheng);
        map.put("id",id);
        pb.setCurrentPage(currentPage);
        pb.setRows(row);
        List<Gangqianpeixunziliao> all = guanliyuanMapper.selectAllgangqianpeixunziliao(map);
        int totalCount = all.size();
        System.out.println("岗前培训资料的总条数totalCount 是" + totalCount);
        pb.setTotalCount(totalCount);
        int start = (currentPage - 1) * row;
//        map.put("uid", uid);
        map.put("start", start);
        map.put("row", row);
        List<Gangqianpeixunziliao> bypage = guanliyuanMapper.selectgangqianpeixunziliaoBypage(map);
        pb.setList(bypage);
        System.out.println("list集合"+bypage);
        int totalPage = totalCount % row == 0 ? totalCount / row : (totalCount / row) + 1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public Gangqianpeixunziliao selectByGangqianpeixunziliaoid(int id) {
        return gangqianpeixunziliaoMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateGangqianpeixunziliaoinfo(Gangqianpeixunziliao gangqianpeixunziliao) {
        if (gangqianpeixunziliao!=null){
            gangqianpeixunziliaoMapper.updateByPrimaryKey(gangqianpeixunziliao);
            return true;
        }
        return false;
    }

    @Override
    public boolean insertGangqianpeixunziliaoinfo(Gangqianpeixunziliao gangqianpeixunziliao) {
        if (gangqianpeixunziliao!=null){
            gangqianpeixunziliaoMapper.insert(gangqianpeixunziliao);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateGangqianpeixunziliaolujing(int id, String ziliaolujing) {
        map.put("ziliaolujing",ziliaolujing);
        map.put("id",id);
        boolean b = guanliyuanMapper.updateGangqianpeixunziliaolujing(map);
        if (b){
            System.out.println("资料路径修改成功");
        }
        return b;
    }

    @Override
    public boolean ziliaodel(int id) {
        if (id!=0){
            gangqianpeixunziliaoMapper.deleteByPrimaryKey(id);
            return true;
        }
        return false;
    }

    /*班级管理********************************************************************************/
    @Override
    public PageBean<Banji> banjifindBypage(int currentPage, int row,int id,String banjimingcheng) {
        PageBean<Banji> pb = new PageBean<Banji>();//创建一个pagebean对象
        map.put("id",id);
        map.put("banjimingcheng",banjimingcheng);
        pb.setCurrentPage(currentPage);
        pb.setRows(row);
        List<Banji> all = guanliyuanMapper.selectAllbanji(map);
        int totalCount = all.size();
        System.out.println("班级的总条数totalCount 是" + totalCount);
        pb.setTotalCount(totalCount);
        int start = (currentPage - 1) * row;
//        map.put("uid", uid);
        map.put("start", start);
        map.put("row", row);
        List<Banji> bypage = guanliyuanMapper.selectbanjiBypage(map);
        pb.setList(bypage);
        System.out.println("bypage"+bypage);
        int totalPage = totalCount % row == 0 ? totalCount / row : (totalCount / row) + 1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public Banji selectBybanjiid(int id) {
        return banjiMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updatebanjiinfo(Banji banji) {
        if (banji!=null){
            banjiMapper.updateByPrimaryKey(banji);
            return true;
        }
        return false;
    }

    @Override
    public boolean insertbanjiinfo(Banji banji) {
        if (banji!=null){
            banjiMapper.insert(banji);
            return true;
        }
        return false;
    }

    @Override
    public boolean banjidel(int id) {
        if (id!=0){
            banjiMapper.deleteByPrimaryKey(id);
            return true;
        }
        return false;
    }

    /*上课班级管理********************************************************************************/
    @Override
    public PageBean<Shangkebanji> shangkebanjifindBypage(int currentPage, int row ,String jiaoshixingming ,String kechengmingcheng ,String xueqi) {
        PageBean<Shangkebanji> pb = new PageBean<Shangkebanji>();
        pb.setCurrentPage(currentPage);
        pb.setRows(row);
        int start = (currentPage - 1) * row;
//        map.put("uid", uid);
        map.put("start", start);
        map.put("row", row);
        map.put("jiaoshixingming",jiaoshixingming);
        map.put("kechengmingcheng",kechengmingcheng);
        map.put("xueqi",xueqi);
        List<Shangkebanji> bypage = guanliyuanMapper.selectshangkebanjiBypage(map);
        pb.setList(bypage);
        System.out.println("bypage"+bypage);
        List<Shangkebanji> all = guanliyuanMapper.selectAllshangkebanji(map);
        int totalCount = all.size();
        System.out.println("学期"+xueqi+"课程安排的总条数totalCount 是" + totalCount);
        pb.setTotalCount(totalCount);
        int totalPage = totalCount % row == 0 ? totalCount / row : (totalCount / row) + 1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public Shangkebanji selectByshangkebanjiid(int id) {
        return shangkebanjiMapper.selectByPrimaryKey(id);
    }
    @Override
    public boolean updateshangkebanjiinfo(Shangkebanji shangkebanji) {
        if (shangkebanji!=null){
            shangkebanjiMapper.updateByPrimaryKey(shangkebanji);
            return true;
        }
        return false;
    }

    @Override
    public boolean insertshangkebanjiinfo(Shangkebanji shangkebanji) {
        if (shangkebanji!=null){
            shangkebanjiMapper.insert(shangkebanji);
            return true;
        }
        return false;
    }

    @Override
    public boolean shangkebanjidel(int id) {
        if (id!=0){
            shangkebanjiMapper.deleteByPrimaryKey(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Banji> selectAllbanji() {
        map.clear();
        return guanliyuanMapper.selectAllbanji(map);
    }

    //处罚等级管理模块*****************************************************
    @Override
    public List<Chufadengji> selectAllchufadengji() {
        List<Chufadengji> chufadengjis = guanliyuanMapper.selectAllchufadengji();
        return chufadengjis;
    }

    @Override
    public Chufadengji selectBychufadengjiid(int id) {
        Chufadengji chufadengji = chufadengjiMapper.selectByPrimaryKey(id);
        return chufadengji;
    }

    @Override
    public boolean updateChufadengjiinfo(Chufadengji chufadengji) {
        if (chufadengji!=null){
            chufadengjiMapper.updateByPrimaryKey(chufadengji);
            return true;
        }
        return false;
    }

    @Override
    public boolean insertChufadengjiinfo(Chufadengji chufadengji) {
        if (chufadengji!=null){
            chufadengjiMapper.insert(chufadengji);
            return true;
        }
        return false;
    }

    @Override
    public boolean chufadengjidel(int id) {
        if (id!=0){
            chufadengjiMapper.deleteByPrimaryKey(id);
            return true;
        }
        return false;
    }
    //***********学位管理模块***********************************************
    @Override
    public List<Xuewei> selectAllxuewei() {
        List<Xuewei> xuewei = guanliyuanMapper.selectAllxuewei();
        return xuewei;
    }

    @Override
    public Xuewei selectByxueweiid(int id) {
        return xueweiMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updatexueweiinfo(Xuewei xuewei) {
        if (xuewei!=null){
            xueweiMapper.updateByPrimaryKey(xuewei);
            return true;
        }
        return false;
    }

    @Override
    public boolean insertxueweiinfo(Xuewei xuewei) {
        if (xuewei!=null){
            xueweiMapper.insert(xuewei);
            return true;
        }
        return false;
    }

    @Override
    public boolean xueweidel(int id) {
        if (id!=0){
            xueweiMapper.deleteByPrimaryKey(id);
            return true;
        }
        return false;
    }
    //***********职称管理模块*****************
    @Override
    public List<Zhicheng> selectAllzhicheng() {
        return guanliyuanMapper.selectAllzhicheng();
    }

    @Override
    public Zhicheng selectByzhichengid(int id) {
        return zhichengMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updatezhichenginfo(Zhicheng zhicheng) {
        if (zhicheng!=null){
            zhichengMapper.updateByPrimaryKey(zhicheng);
            return true;
        }
        return false;
    }

    @Override
    public boolean insertzhichenginfo(Zhicheng zhicheng) {
        if (zhicheng!=null){
            zhichengMapper.insert(zhicheng);
            return true;
        }
        return false;
    }

    @Override
    public boolean zhichengdel(int id) {
        if (id!=0){
            zhichengMapper.deleteByPrimaryKey(id);
            return true;
        }
        return false;
    }
    //***********工作年限管理模块*****************
    @Override
    public List<Gongzuonianxian> selectAllgongzuonianxian() {
        return guanliyuanMapper.selectAllgongzuonianxian();
    }

    @Override
    public Gongzuonianxian selectBygongzuonianxianid(int id) {
        return gongzuonianxianMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updategongzuonianxianinfo(Gongzuonianxian gongzuonianxian) {
        if (gongzuonianxian!=null){
            gongzuonianxianMapper.updateByPrimaryKey(gongzuonianxian);
            return true;
        }
        return false;
    }

    @Override
    public boolean insertgongzuonianxianinfo(Gongzuonianxian gongzuonianxian) {
        if (gongzuonianxian!=null){
            gongzuonianxianMapper.insert(gongzuonianxian);
            return true;
        }
        return false;
    }

    @Override
    public boolean gongzuonianxiandel(int id) {
        if (id!=0){
            gongzuonianxianMapper.deleteByPrimaryKey(id);
            return true;
        }
        return false;
    }
    //***********工作年限管理模块*****************
    @Override
    public List<Zonghepingdingjieguo> selectAllzonghepingdingjieguo() {
        return guanliyuanMapper.selectAllzonghepingdingjieguo();
    }

    @Override
    public Zonghepingdingjieguo selectByzonghepingdingjieguoid(int id) {
        return zonghepingdingjieguoMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updatezonghepingdingjieguoinfo(Zonghepingdingjieguo zonghepingdingjieguo) {
        if (zonghepingdingjieguo!=null){
            zonghepingdingjieguoMapper.updateByPrimaryKey(zonghepingdingjieguo);
            return true;
        }
        return false;
    }

    @Override
    public boolean insertzonghepingdingjieguoinfo(Zonghepingdingjieguo zonghepingdingjieguo) {
        if (zonghepingdingjieguo!=null){
            zonghepingdingjieguoMapper.insert(zonghepingdingjieguo);
            return true;
        }
        return false;
    }

    @Override
    public boolean zonghepingdingjieguodel(int id) {
        if (id!=0){
            zonghepingdingjieguoMapper.deleteByPrimaryKey(id);
            return true;
        }
        return false;
    }
    /*专家*************************************************************/
    @Override
    public PageBean<Zhuanjia> zhuanjiafindBypage(int currentPage, int row,int id ,String xingming) {
        PageBean<Zhuanjia> pb = new PageBean<Zhuanjia>();
        pb.setCurrentPage(currentPage);
        pb.setRows(row);
        map.put("id", id);
        map.put("xingming", xingming);
        List<Zhuanjia> all = guanliyuanMapper.selectAllzhuanjia();
        int totalCount = all.size();
        System.out.println("专家总条数totalCount 是" + totalCount);
        pb.setTotalCount(totalCount);
        int start = (currentPage - 1) * row;
        map.put("start", start);
        map.put("row", row);
        List<Zhuanjia> bypage = guanliyuanMapper.selectzhuanjiaBypage(map);
        pb.setList(bypage);
        System.out.println("bypage"+bypage);
        int totalPage = totalCount % row == 0 ? totalCount / row : (totalCount / row) + 1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public Zhuanjia selectByzhuanjiaid(int id) {
        return zhuanjiaMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateZhuanjiainfo(Zhuanjia zhuanjia) {

        int i = zhuanjiaMapper.updateByPrimaryKey(zhuanjia);
        if (i!=0){
            return true;
        }
        return false;
    }

    @Override
    public boolean insertZhuanjiainfo(Zhuanjia zhuanjia) {
        if (zhuanjia!=null){
            zhuanjiaMapper.insert(zhuanjia);
            return true;
        }
        return false;
    }

    @Override
    public boolean zhuanjiadel(int id) {
        if (id!=0){
            zhuanjiaMapper.deleteByPrimaryKey(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Zhuanjia> selectAllzhuanjia() {
        return zhuanjiaMapper.selectAllzhuanjia();
    }
    /*试讲档案管理******************************************************************/
    @Override
    public PageBean<Shijiangdangan> shijiangfindBypage(int currentPage, int row,int id,String jiaoshixingming,String shijiangkecheng) {
        PageBean<Shijiangdangan> pb = new PageBean<Shijiangdangan>();
        map.put("id",id);
        map.put("jiaoshixingming",jiaoshixingming);
        map.put("shijiangkecheng",shijiangkecheng);
        pb.setCurrentPage(currentPage);
        pb.setRows(row);
        List<Shijiangdangan> all = guanliyuanMapper.selectAllshijiang(map);
        int totalCount = all.size();
        System.out.println("试讲的总条数totalCount 是" + totalCount);
        pb.setTotalCount(totalCount);
        int start = (currentPage - 1) * row;
        map.put("start", start);
        map.put("row", row);
        List<Shijiangdangan> bypage = guanliyuanMapper.selectshijiangBypage(map);
        pb.setList(bypage);
        System.out.println("bypage"+bypage);
        int totalPage = totalCount % row == 0 ? totalCount / row : (totalCount / row) + 1;
        pb.setTotalPage(totalPage);
        return pb;
    }
    @Override
    public Shijiangdangan selectByshijiangid(int id) {
        return shijiangdanganMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateshijiangdanganinfo(Shijiangdangan shijiangdangan) {

        int i = shijiangdanganMapper.updateByPrimaryKey(shijiangdangan);

        if (i!=0){
            return true;
        }
        return false;

    }

    @Override
    public boolean insertshijiangdanganinfo(Shijiangdangan shijiangdangan) {
        int insert = shijiangdanganMapper.insert(shijiangdangan);
        if (insert!=0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean updateShijiangneirongurl(int id, String shijiangneirongurl) {
        map.put("shijiangneirongurl",shijiangneirongurl);
        map.put("id",id);
        boolean b = guanliyuanMapper.updateShijiangneirongurl(map);
        return b;
    }

    @Override
    public boolean updateShijiangfenshuurl(int id, String shijiangfenshuurl) {
        map.put("shijiangfenshuurl",shijiangfenshuurl);
        map.put("id",id);
        boolean b = guanliyuanMapper.updateShijiangfenshuurl(map);
        return b;
    }

    @Override
    public boolean shijiangdel(int id) {
        int i = shijiangdanganMapper.deleteByPrimaryKey(id);
        if (i!=0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public int fenshuSelectbyxuewei(String xuewei) {
        map.put("xuewei",xuewei);
        Xuewei xuewei1 = guanliyuanMapper.fenshuselectByxuewei(map);
        return xuewei1.getFenshu();
    }

    @Override
    public int fenshuSelectbygongniannianxian(int gongzuonianxian) {
        List<Gongzuonianxian> gongzuonianxians = guanliyuanMapper.selectAllgongzuonianxian();
        Integer nianxianstart=0;
        Integer nianxianend=0;
        int fenshu=0;
        for (int i = 0; i < gongzuonianxians.size(); i++) {
            nianxianstart = gongzuonianxians.get(i).getNianxianstart();
            nianxianend = gongzuonianxians.get(i).getNianxianend();
            if (gongzuonianxian>=nianxianstart&&gongzuonianxian<nianxianend){
                fenshu=gongzuonianxians.get(i).getFenshu();
            }
        }
        return fenshu;
    }

    @Override
    public int fenshuSelectbyzhicheng(String zhicheng) {
        map.put("zhicheng",zhicheng);
        Zhicheng zhicheng1 = guanliyuanMapper.fenshuselectByzhicheng(map);
        return zhicheng1.getFenshu();
    }
    @Override
    public double fenshuSelectbyshijiang(int jiaoshiid) {
        map.put("jiaoshiid",jiaoshiid);
        List<Shijiangdangan> shijiangdangans = guanliyuanMapper.fenshuselectByshijiang(map);
        double pingjunfen=0;
        for (int i = 0; i <shijiangdangans.size() ; i++) {
            pingjunfen+=shijiangdangans.get(i).getShijiangfenshu();
        }
        pingjunfen=pingjunfen/shijiangdangans.size();
        System.out.println("平均分"+pingjunfen);
        return pingjunfen;
    }

    @Override
    public String dengjiSelectbyfenshu(double fenshu) {
        List<Zonghepingdingjieguo> zonghepingdingjieguos = guanliyuanMapper.selectAllzonghepingdingjieguo();
        Integer zuidifen=0;
        Integer zuigaofen=0;
        String dengji=null;
        for (int i = 0; i <zonghepingdingjieguos.size() ; i++) {
            zuidifen=zonghepingdingjieguos.get(i).getZuidifen();
            zuigaofen=zonghepingdingjieguos.get(i).getZuigaofen();
            if (fenshu>=zuidifen&&fenshu<zuigaofen){
                dengji=zonghepingdingjieguos.get(i).getDengji();
            }
        }
        return dengji;
    }
    //***********薪水管理模块***********************************************
    @Override
    public List<Xinshui> selectAllxinshui() {
        List<Xinshui> xinshui = guanliyuanMapper.selectAllxinshui();
        return xinshui;
    }

    @Override
    public Xinshui selectByxinshuiid(int id) {
        return xinshuiMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updatexinshuiinfo(Xinshui xinshui) {
        if (xinshui!=null){
            xinshuiMapper.updateByPrimaryKey(xinshui);
            return true;
        }
        return false;
    }

    @Override
    public boolean insertxinshuiinfo(Xinshui xinshui) {
        if (xinshui!=null){
            xinshuiMapper.insert(xinshui);
            return true;
        }
        return false;
    }

    @Override
    public boolean xinshuidel(int id) {
        if (id!=0){
            xinshuiMapper.deleteByPrimaryKey(id);
            return true;
        }
        return false;
    }
    /*合同管理*********************************************************************/
    @Override
    public PageBean<Hetong> hetongfindBypage(int currentPage, int row, String xueqi) {
        PageBean<Hetong> pb = new PageBean<Hetong>();
        //List<User> byRid = new ArrayList<User>();
        pb.setCurrentPage(currentPage);
        pb.setRows(row);
        int start = (currentPage - 1) * row;
//        map.put("uid", uid);
        map.put("start", start);
        map.put("row", row);
        map.put("xueqi",xueqi);
        List<Hetong> bypage = guanliyuanMapper.selectHetongdanganBypage(map);
        List<Hetong> all = guanliyuanMapper.selectAllhetong(map);
        int totalCount = all.size();
        System.out.println("本学期合同分页的总条数totalCount 是" + totalCount);
        pb.setTotalCount(totalCount);
        pb.setList(bypage);
        System.out.println("bypage"+bypage);
        int totalPage = totalCount % row == 0 ? totalCount / row : (totalCount / row) + 1;
        pb.setTotalPage(totalPage);
        return pb;
    }
    @Override
    public PageBean<Hetong> hetongdanganfindBypage(int currentPage, int row, int jiaoshibianhao,String jiaoshixingming,String xueqi) {
        PageBean<Hetong> pb = new PageBean<Hetong>();
        //List<User> byRid = new ArrayList<User>();
        pb.setCurrentPage(currentPage);
        pb.setRows(row);
        int start = (currentPage - 1) * row;
//        map.put("uid", uid);
        map.put("start", start);
        map.put("row", row);
        map.put("jiaoshibianhao",jiaoshibianhao);
        map.put("jiaoshixingming",jiaoshixingming);
        map.put("xueqi",xueqi);
        System.out.println("map"+map);
        List<Hetong> bypage = guanliyuanMapper.selectHetongdanganBypage(map);
        List<Hetong> all = guanliyuanMapper.selectAllhetong(map);
        int totalCount = all.size();
        System.out.println("总条数totalCount 是" + totalCount);
        pb.setTotalCount(totalCount);
        pb.setList(bypage);
        System.out.println("bypage"+bypage);
        int totalPage = totalCount % row == 0 ? totalCount / row : (totalCount / row) + 1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public boolean updatehetongurl(int id, String zhichenglujing) {

        return false;
    }

    @Override
    public List<Gongzi> yijianshengchenggongzi(String fafangzhuangtai,String shijian) {
        map.put("fafangzhuangtai",fafangzhuangtai);
        map.put("shijian",shijian);
        List<Gongzi> yijianshengchenggongzi = guanliyuanMapper.yijianshengchenggongzi(map);
        return yijianshengchenggongzi;
    }

    @Override
    public Hetong selectByhetongid(int id) {
        Hetong hetong = hetongMapper.selectByPrimaryKey(id);
        return hetong;
    }

    @Override
    public String selectxueshibyjiaoshibianhao(int id, String xueqi,String shangkexingshi) {
        map.put("jiaoshiid",id);
        map.put("xueqi",xueqi);
        map.put("shangkexingshi",shangkexingshi);
        List<Shangkebanji> shangkebanjis = guanliyuanMapper.selectshangkebanjiByjiaoshiid(map);
        int size = shangkebanjis.size();
        //List<Shangkebanji> shangkebanjis = guanliyuanMapper.selectAllshangkebanji(map);
        String xueshi="";
        for (int i = 0; i <shangkebanjis.size() ; i++) {
                map.remove("kechengmingcheng");
                map.put("kechengmingcheng",shangkebanjis.get(i).getKechengmingcheng());
                String s = guanliyuanMapper.selectxueshiBykechengming(map);
                xueshi+=s+",";
        }
        return xueshi;
    }

    @Override
    public String selectkechengbianhaobyjiaoshibianhao(int id, String xueqi,String shangkexingshi) {
        map.put("jiaoshiid",id);
        map.put("xueqi",xueqi);
        map.put("shangkexingshi",shangkexingshi);
        List<Shangkebanji> shangkebanjis = guanliyuanMapper.selectshangkebanjiByjiaoshiid(map);
        System.out.println("通过教师编号搜索的课程编号"+shangkebanjis);
        int size=shangkebanjis.size();
        String kechengbianhao="";
        for (int i = 0; i <shangkebanjis.size() ; i++) {
            if (shangkebanjis.get(i).getJiaoshiid()==id){
                    kechengbianhao+=shangkebanjis.get(i).getKechengid().toString()+",";
            }
        }
        return kechengbianhao;
    }

    @Override
    public String selectkechengmingchengbyjiaoshibianhao(int id, String xueqi,String shangkexingshi) {
        map.put("jiaoshiid",id);
        map.put("xueqi",xueqi);
        map.put("shangkexingshi",shangkexingshi);
        List<Shangkebanji> shangkebanjis = guanliyuanMapper.selectshangkebanjiByjiaoshiid(map);
        int size = shangkebanjis.size();
        String kechengmingcheng="";
        for (int i = 0; i <shangkebanjis.size() ; i++) {
            if (shangkebanjis.get(i).getJiaoshiid()==id){
//                if (i==(size-1)){
////                    kechengmingcheng+=shangkebanjis.get(i).getKechengmingcheng() ;
////                }else {
                    kechengmingcheng+=shangkebanjis.get(i).getKechengmingcheng()+",";
//                }
            }
        }
        return kechengmingcheng;
    }
    @Override
    public String selectrenkebanjibyjiaoshibianhao(int id,String xueqi,String shangkexingshi) {
        map.put("jiaoshiid",id);
        map.put("xueqi",xueqi);
        map.put("shangkexingshi",shangkexingshi);
        List<Shangkebanji> shangkebanjis = guanliyuanMapper.selectshangkebanjiByjiaoshiid(map);
        int size = shangkebanjis.size();
        System.out.println("size"+size);
        String kechengmingcheng="";
        for (int i = 0; i <shangkebanjis.size() ; i++) {
            if (shangkebanjis.get(i).getJiaoshiid()==id){
//                if (i==(size-1)){
//                    kechengmingcheng+=shangkebanjis.get(i).getBanjimingcheng() ;
//                }else {
                    kechengmingcheng+=shangkebanjis.get(i).getBanjimingcheng()+",";
//                }
            }
        }
        return kechengmingcheng;
    }

    @Override
    public boolean updatehetonginfo(Hetong hetong) {
        if (hetong!=null){
            hetongMapper.updateByPrimaryKey(hetong);
            return true;
        }
        return false;
    }

    @Override
    public boolean inserthetonginfo(Hetong hetong) {
        if (hetong!=null){
            hetongMapper.insert(hetong);
            return true;
        }
        return false;
    }

    @Override
    public boolean hetongdel(int id) {
        if (id!=0){
            hetongMapper.deleteByPrimaryKey(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Hetong> selectAllhetong(String xueqi) {
        map.put("xueqi",xueqi);
        List<Hetong> hetongs = guanliyuanMapper.selectAllhetong(map);
        return hetongs;
    }

    /**********************工资管理***************************************/
    @Override
    public boolean updategongziinfo(Gongzi gongzi) {
        if (gongzi!=null){
            gongziMapper.updateByPrimaryKey(gongzi);
            return true;
        }
        return false;
    }

    @Override
    public boolean insertgongziinfo(Gongzi gongzi) {
        if (gongzi!=null){
            gongziMapper.insert(gongzi);
            return true;
        }
        return false;
    }

    @Override
    public PageBean<Gongzi> gongzifindBypage(int currentPage, int row, String time,String jiaoshibianhao,String jiaoshixingming) {
        PageBean<Gongzi> pb = new PageBean<Gongzi>();
        map.put("jiaoshibianhao",jiaoshibianhao);
        map.put("jiaoshixingming",jiaoshixingming);
        //List<User> byRid = new ArrayList<User>();
        pb.setCurrentPage(currentPage);
        pb.setRows(row);
        int start = (currentPage - 1) * row;
        map.put("start", start);
        map.put("row", row);
        map.put("shijian",time);
        List<Gongzi> bypage = guanliyuanMapper.selectGongzidanganBypage(map);
        List<Gongzi> all = guanliyuanMapper.selectAllgongzidangan(map);
        int totalCount = all.size();
        System.out.println("本学期合同分页的总条数totalCount 是" + totalCount);
        pb.setTotalCount(totalCount);
        pb.setList(bypage);
        System.out.println("bypage"+bypage);
        int totalPage = totalCount % row == 0 ? totalCount / row : (totalCount / row) + 1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public Gongzi selectBygongziid(int id) {
        Gongzi gongzi = gongziMapper.selectByPrimaryKey(id);
        return gongzi;
    }

    @Override
    public boolean updategongzifakuaninfo(int jiaoshibianhao, double fakuanjine,String shijian) {
        map.put("jiaoshibianhao",jiaoshibianhao);
        map.put("shijian",shijian);
        List<Gongzi> gongziList = guanliyuanMapper.selectByjiaoshibianhaoandshijian(map);
        Gongzi gongzi=null;
        gongzi=gongziList.get(0);
        Double fakuanjine1 = gongzi.getFakuanjine();
        double yingfajine=gongzi.getYingfajine();
        double shifajine=yingfajine-(fakuanjine+fakuanjine1);
        System.out.println("shifajine"+shifajine);
        map.put("id",gongzi.getId());
        map.put("fakuanjine",(fakuanjine+fakuanjine1));
        map.put("shifajine",shifajine);
        boolean updategongzifakuaninfo = guanliyuanMapper.updategongzifakuaninfo(map);
        return updategongzifakuaninfo;
    }
    @Override
    public List<Gongzi> selectByjiaoshibianhaoandcjsj(int jiaoshibianhao, Date chuangjianshijian) {
        map.put("jiaoshibianhao", jiaoshibianhao);
        map.put("chuangjianshijian", chuangjianshijian);
        List<Gongzi> gongzis = guanliyuanMapper.selectByjiaoshibianhaoandcjsj(map);
        return gongzis;
    }
    @Override
    public PageBean<Gongzi> gongzidanganfindBypage(int currentPage, int row,int jiaoshibianhao,String jiaoshixingming ,String time) {
        PageBean<Gongzi> pb = new PageBean<Gongzi>();
        //List<User> byRid = new ArrayList<User>();
        pb.setCurrentPage(currentPage);
        pb.setRows(row);

        int start = (currentPage - 1) * row;
//        map.put("uid", uid);
        map.put("start", start);
        map.put("row", row);
        map.put("jiaoshibianhao",jiaoshibianhao);
        map.put("jiaoshixingming",jiaoshixingming);
        map.put("shijian",time);
        List<Gongzi> bypage = guanliyuanMapper.selectGongzidanganBypage(map);
        List<Gongzi> all = guanliyuanMapper.selectAllgongzidangan(map);
        int totalCount = all.size();
        System.out.println("本学期合同分页的总条数totalCount 是" + totalCount);
        pb.setTotalCount(totalCount);
        pb.setList(bypage);
        System.out.println("bypage"+bypage);
        int totalPage = totalCount % row == 0 ? totalCount / row : (totalCount / row) + 1;
        pb.setTotalPage(totalPage);
        return pb;
    }
}
