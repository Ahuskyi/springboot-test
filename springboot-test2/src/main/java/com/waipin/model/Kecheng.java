package com.waipin.model;

public class Kecheng {
    @Override
    public String toString() {
        return "Kecheng{" +
                "id=" + id +
                ", kechenghao='" + kechenghao + '\'' +
                ", kechengmingcheng='" + kechengmingcheng + '\'' +
                ", xuefen='" + xuefen + '\'' +
                ", xueshi='" + xueshi + '\'' +
                ", jiaoxuedagang='" + jiaoxuedagang + '\'' +
                ", jiaoxuedaganglujing='" + jiaoxuedaganglujing + '\'' +
                ", chuangjianshijian='" + chuangjianshijian + '\'' +
                '}';
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kecheng.id
     *
     * @mbggenerated Sat Apr 18 10:00:24 CST 2020
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kecheng.kechenghao
     *
     * @mbggenerated Sat Apr 18 10:00:24 CST 2020
     */
    private String kechenghao;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kecheng.kechengmingcheng
     *
     * @mbggenerated Sat Apr 18 10:00:24 CST 2020
     */
    private String kechengmingcheng;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kecheng.xuefen
     *
     * @mbggenerated Sat Apr 18 10:00:24 CST 2020
     */
    private String xuefen;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kecheng.xueshi
     *
     * @mbggenerated Sat Apr 18 10:00:24 CST 2020
     */
    private String xueshi;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kecheng.jiaoxuedagang
     *
     * @mbggenerated Sat Apr 18 10:00:24 CST 2020
     */
    private String jiaoxuedagang;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kecheng.jiaoxuedaganglujing
     *
     * @mbggenerated Sat Apr 18 10:00:24 CST 2020
     */
    private String jiaoxuedaganglujing;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kecheng.chuangjianshijian
     *
     * @mbggenerated Sat Apr 18 10:00:24 CST 2020
     */
    private String chuangjianshijian;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kecheng.id
     *
     * @return the value of kecheng.id
     *
     * @mbggenerated Sat Apr 18 10:00:24 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kecheng.id
     *
     * @param id the value for kecheng.id
     *
     * @mbggenerated Sat Apr 18 10:00:24 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kecheng.kechenghao
     *
     * @return the value of kecheng.kechenghao
     *
     * @mbggenerated Sat Apr 18 10:00:24 CST 2020
     */
    public String getKechenghao() {
        return kechenghao;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kecheng.kechenghao
     *
     * @param kechenghao the value for kecheng.kechenghao
     *
     * @mbggenerated Sat Apr 18 10:00:24 CST 2020
     */
    public void setKechenghao(String kechenghao) {
        this.kechenghao = kechenghao == null ? null : kechenghao.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kecheng.kechengmingcheng
     *
     * @return the value of kecheng.kechengmingcheng
     *
     * @mbggenerated Sat Apr 18 10:00:24 CST 2020
     */
    public String getKechengmingcheng() {
        return kechengmingcheng;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kecheng.kechengmingcheng
     *
     * @param kechengmingcheng the value for kecheng.kechengmingcheng
     *
     * @mbggenerated Sat Apr 18 10:00:24 CST 2020
     */
    public void setKechengmingcheng(String kechengmingcheng) {
        this.kechengmingcheng = kechengmingcheng == null ? null : kechengmingcheng.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kecheng.xuefen
     *
     * @return the value of kecheng.xuefen
     *
     * @mbggenerated Sat Apr 18 10:00:24 CST 2020
     */
    public String getXuefen() {
        return xuefen;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kecheng.xuefen
     *
     * @param xuefen the value for kecheng.xuefen
     *
     * @mbggenerated Sat Apr 18 10:00:24 CST 2020
     */
    public void setXuefen(String xuefen) {
        this.xuefen = xuefen == null ? null : xuefen.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kecheng.xueshi
     *
     * @return the value of kecheng.xueshi
     *
     * @mbggenerated Sat Apr 18 10:00:24 CST 2020
     */
    public String getXueshi() {
        return xueshi;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kecheng.xueshi
     *
     * @param xueshi the value for kecheng.xueshi
     *
     * @mbggenerated Sat Apr 18 10:00:24 CST 2020
     */
    public void setXueshi(String xueshi) {
        this.xueshi = xueshi == null ? null : xueshi.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kecheng.jiaoxuedagang
     *
     * @return the value of kecheng.jiaoxuedagang
     *
     * @mbggenerated Sat Apr 18 10:00:24 CST 2020
     */
    public String getJiaoxuedagang() {
        return jiaoxuedagang;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kecheng.jiaoxuedagang
     *
     * @param jiaoxuedagang the value for kecheng.jiaoxuedagang
     *
     * @mbggenerated Sat Apr 18 10:00:24 CST 2020
     */
    public void setJiaoxuedagang(String jiaoxuedagang) {
        this.jiaoxuedagang = jiaoxuedagang == null ? null : jiaoxuedagang.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kecheng.jiaoxuedaganglujing
     *
     * @return the value of kecheng.jiaoxuedaganglujing
     *
     * @mbggenerated Sat Apr 18 10:00:24 CST 2020
     */
    public String getJiaoxuedaganglujing() {
        return jiaoxuedaganglujing;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kecheng.jiaoxuedaganglujing
     *
     * @param jiaoxuedaganglujing the value for kecheng.jiaoxuedaganglujing
     *
     * @mbggenerated Sat Apr 18 10:00:24 CST 2020
     */
    public void setJiaoxuedaganglujing(String jiaoxuedaganglujing) {
        this.jiaoxuedaganglujing = jiaoxuedaganglujing == null ? null : jiaoxuedaganglujing.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kecheng.chuangjianshijian
     *
     * @return the value of kecheng.chuangjianshijian
     *
     * @mbggenerated Sat Apr 18 10:00:24 CST 2020
     */
    public String getChuangjianshijian() {
        return chuangjianshijian;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kecheng.chuangjianshijian
     *
     * @param chuangjianshijian the value for kecheng.chuangjianshijian
     *
     * @mbggenerated Sat Apr 18 10:00:24 CST 2020
     */
    public void setChuangjianshijian(String chuangjianshijian) {
        this.chuangjianshijian = chuangjianshijian == null ? null : chuangjianshijian.trim();
    }
}