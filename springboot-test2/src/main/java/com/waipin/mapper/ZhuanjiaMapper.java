package com.waipin.mapper;

import com.waipin.model.Zhuanjia;
import com.waipin.model.ZhuanjiaExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZhuanjiaMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zhuanjia
     *
     * @mbggenerated Sun Apr 12 01:52:53 CST 2020
     */

    Zhuanjia login(String yonghuming, String  mima);
    boolean  loginyanzheng(String yonghuming, String  mima);
    int countByExample(ZhuanjiaExample example);
    List<Zhuanjia> selectAllzhuanjia();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zhuanjia
     *
     * @mbggenerated Sun Apr 12 01:52:53 CST 2020
     */
    int deleteByExample(ZhuanjiaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zhuanjia
     *
     * @mbggenerated Sun Apr 12 01:52:53 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zhuanjia
     *
     * @mbggenerated Sun Apr 12 01:52:53 CST 2020
     */
    int insert(Zhuanjia record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zhuanjia
     *
     * @mbggenerated Sun Apr 12 01:52:53 CST 2020
     */
    int insertSelective(Zhuanjia record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zhuanjia
     *
     * @mbggenerated Sun Apr 12 01:52:53 CST 2020
     */
    List<Zhuanjia> selectByExample(ZhuanjiaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zhuanjia
     *
     * @mbggenerated Sun Apr 12 01:52:53 CST 2020
     */
    Zhuanjia selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zhuanjia
     *
     * @mbggenerated Sun Apr 12 01:52:53 CST 2020
     */
    int updateByExampleSelective(@Param("record") Zhuanjia record, @Param("example") ZhuanjiaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zhuanjia
     *
     * @mbggenerated Sun Apr 12 01:52:53 CST 2020
     */
    int updateByExample(@Param("record") Zhuanjia record, @Param("example") ZhuanjiaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zhuanjia
     *
     * @mbggenerated Sun Apr 12 01:52:53 CST 2020
     */
    int updateByPrimaryKeySelective(Zhuanjia record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zhuanjia
     *
     * @mbggenerated Sun Apr 12 01:52:53 CST 2020
     */
    int updateByPrimaryKey(Zhuanjia record);
}