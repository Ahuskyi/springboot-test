package com.waipin.model;

import java.util.Date;

public class Gongzi {
    @Override
    public String toString() {
        return "Gongzi{" +
                "id=" + id +
                ", jiaoshibianhao=" + jiaoshibianhao +
                ", jiaoshixingming='" + jiaoshixingming + '\'' +
                ", yingfajine=" + yingfajine +
                ", fakuanjine=" + fakuanjine +
                ", shifajine=" + shifajine +
                ", shengyujine=" + shengyujine +
                ", shijian='" + shijian + '\'' +
                ", fafangzhuangtai='" + fafangzhuangtai + '\'' +
                ", biaoti='" + biaoti + '\'' +
                ", chuangjianshijian=" + chuangjianshijian +
                '}';
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gongzi.id
     *
     * @mbggenerated Sun Apr 26 12:39:50 CST 2020
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gongzi.jiaoshibianhao
     *
     * @mbggenerated Sun Apr 26 12:39:50 CST 2020
     */
    private Integer jiaoshibianhao;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gongzi.jiaoshixingming
     *
     * @mbggenerated Sun Apr 26 12:39:50 CST 2020
     */
    private String jiaoshixingming;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gongzi.yingfajine
     *
     * @mbggenerated Sun Apr 26 12:39:50 CST 2020
     */
    private Double yingfajine;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gongzi.fakuanjine
     *
     * @mbggenerated Sun Apr 26 12:39:50 CST 2020
     */
    private Double fakuanjine;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gongzi.shifajine
     *
     * @mbggenerated Sun Apr 26 12:39:50 CST 2020
     */
    private Double shifajine;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gongzi.shengyujine
     *
     * @mbggenerated Sun Apr 26 12:39:50 CST 2020
     */
    private Double shengyujine;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gongzi.shijian
     *
     * @mbggenerated Sun Apr 26 12:39:50 CST 2020
     */
    private String shijian;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gongzi.fafangzhuangtai
     *
     * @mbggenerated Sun Apr 26 12:39:50 CST 2020
     */
    private String fafangzhuangtai;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gongzi.biaoti
     *
     * @mbggenerated Sun Apr 26 12:39:50 CST 2020
     */
    private String biaoti;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gongzi.chuangjianshijian
     *
     * @mbggenerated Sun Apr 26 12:39:50 CST 2020
     */
    private Date chuangjianshijian;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gongzi.id
     *
     * @return the value of gongzi.id
     *
     * @mbggenerated Sun Apr 26 12:39:50 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gongzi.id
     *
     * @param id the value for gongzi.id
     *
     * @mbggenerated Sun Apr 26 12:39:50 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gongzi.jiaoshibianhao
     *
     * @return the value of gongzi.jiaoshibianhao
     *
     * @mbggenerated Sun Apr 26 12:39:50 CST 2020
     */
    public Integer getJiaoshibianhao() {
        return jiaoshibianhao;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gongzi.jiaoshibianhao
     *
     * @param jiaoshibianhao the value for gongzi.jiaoshibianhao
     *
     * @mbggenerated Sun Apr 26 12:39:50 CST 2020
     */
    public void setJiaoshibianhao(Integer jiaoshibianhao) {
        this.jiaoshibianhao = jiaoshibianhao;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gongzi.jiaoshixingming
     *
     * @return the value of gongzi.jiaoshixingming
     *
     * @mbggenerated Sun Apr 26 12:39:50 CST 2020
     */
    public String getJiaoshixingming() {
        return jiaoshixingming;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gongzi.jiaoshixingming
     *
     * @param jiaoshixingming the value for gongzi.jiaoshixingming
     *
     * @mbggenerated Sun Apr 26 12:39:50 CST 2020
     */
    public void setJiaoshixingming(String jiaoshixingming) {
        this.jiaoshixingming = jiaoshixingming == null ? null : jiaoshixingming.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gongzi.yingfajine
     *
     * @return the value of gongzi.yingfajine
     *
     * @mbggenerated Sun Apr 26 12:39:50 CST 2020
     */
    public Double getYingfajine() {
        return yingfajine;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gongzi.yingfajine
     *
     * @param yingfajine the value for gongzi.yingfajine
     *
     * @mbggenerated Sun Apr 26 12:39:50 CST 2020
     */
    public void setYingfajine(Double yingfajine) {
        this.yingfajine = yingfajine;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gongzi.fakuanjine
     *
     * @return the value of gongzi.fakuanjine
     *
     * @mbggenerated Sun Apr 26 12:39:50 CST 2020
     */
    public Double getFakuanjine() {
        return fakuanjine;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gongzi.fakuanjine
     *
     * @param fakuanjine the value for gongzi.fakuanjine
     *
     * @mbggenerated Sun Apr 26 12:39:50 CST 2020
     */
    public void setFakuanjine(Double fakuanjine) {
        this.fakuanjine = fakuanjine;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gongzi.shifajine
     *
     * @return the value of gongzi.shifajine
     *
     * @mbggenerated Sun Apr 26 12:39:50 CST 2020
     */
    public Double getShifajine() {
        return shifajine;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gongzi.shifajine
     *
     * @param shifajine the value for gongzi.shifajine
     *
     * @mbggenerated Sun Apr 26 12:39:50 CST 2020
     */
    public void setShifajine(Double shifajine) {
        this.shifajine = shifajine;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gongzi.shengyujine
     *
     * @return the value of gongzi.shengyujine
     *
     * @mbggenerated Sun Apr 26 12:39:50 CST 2020
     */
    public Double getShengyujine() {
        return shengyujine;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gongzi.shengyujine
     *
     * @param shengyujine the value for gongzi.shengyujine
     *
     * @mbggenerated Sun Apr 26 12:39:50 CST 2020
     */
    public void setShengyujine(Double shengyujine) {
        this.shengyujine = shengyujine;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gongzi.shijian
     *
     * @return the value of gongzi.shijian
     *
     * @mbggenerated Sun Apr 26 12:39:50 CST 2020
     */
    public String getShijian() {
        return shijian;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gongzi.shijian
     *
     * @param shijian the value for gongzi.shijian
     *
     * @mbggenerated Sun Apr 26 12:39:50 CST 2020
     */
    public void setShijian(String shijian) {
        this.shijian = shijian == null ? null : shijian.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gongzi.fafangzhuangtai
     *
     * @return the value of gongzi.fafangzhuangtai
     *
     * @mbggenerated Sun Apr 26 12:39:50 CST 2020
     */
    public String getFafangzhuangtai() {
        return fafangzhuangtai;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gongzi.fafangzhuangtai
     *
     * @param fafangzhuangtai the value for gongzi.fafangzhuangtai
     *
     * @mbggenerated Sun Apr 26 12:39:50 CST 2020
     */
    public void setFafangzhuangtai(String fafangzhuangtai) {
        this.fafangzhuangtai = fafangzhuangtai == null ? null : fafangzhuangtai.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gongzi.biaoti
     *
     * @return the value of gongzi.biaoti
     *
     * @mbggenerated Sun Apr 26 12:39:50 CST 2020
     */
    public String getBiaoti() {
        return biaoti;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gongzi.biaoti
     *
     * @param biaoti the value for gongzi.biaoti
     *
     * @mbggenerated Sun Apr 26 12:39:50 CST 2020
     */
    public void setBiaoti(String biaoti) {
        this.biaoti = biaoti == null ? null : biaoti.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gongzi.chuangjianshijian
     *
     * @return the value of gongzi.chuangjianshijian
     *
     * @mbggenerated Sun Apr 26 12:39:50 CST 2020
     */
    public Date getChuangjianshijian() {
        return chuangjianshijian;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gongzi.chuangjianshijian
     *
     * @param chuangjianshijian the value for gongzi.chuangjianshijian
     *
     * @mbggenerated Sun Apr 26 12:39:50 CST 2020
     */
    public void setChuangjianshijian(Date chuangjianshijian) {
        this.chuangjianshijian = chuangjianshijian;
    }
}