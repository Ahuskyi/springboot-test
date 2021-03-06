package com.waipin.mapper;

import com.waipin.model.Xinshui;
import com.waipin.model.XinshuiExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XinshuiMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xinshui
     *
     * @mbggenerated Fri Apr 24 23:45:21 CST 2020
     */
    int countByExample(XinshuiExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xinshui
     *
     * @mbggenerated Fri Apr 24 23:45:21 CST 2020
     */
    int deleteByExample(XinshuiExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xinshui
     *
     * @mbggenerated Fri Apr 24 23:45:21 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xinshui
     *
     * @mbggenerated Fri Apr 24 23:45:21 CST 2020
     */
    int insert(Xinshui record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xinshui
     *
     * @mbggenerated Fri Apr 24 23:45:21 CST 2020
     */
    int insertSelective(Xinshui record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xinshui
     *
     * @mbggenerated Fri Apr 24 23:45:21 CST 2020
     */
    List<Xinshui> selectByExample(XinshuiExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xinshui
     *
     * @mbggenerated Fri Apr 24 23:45:21 CST 2020
     */
    Xinshui selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xinshui
     *
     * @mbggenerated Fri Apr 24 23:45:21 CST 2020
     */
    int updateByExampleSelective(@Param("record") Xinshui record, @Param("example") XinshuiExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xinshui
     *
     * @mbggenerated Fri Apr 24 23:45:21 CST 2020
     */
    int updateByExample(@Param("record") Xinshui record, @Param("example") XinshuiExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xinshui
     *
     * @mbggenerated Fri Apr 24 23:45:21 CST 2020
     */
    int updateByPrimaryKeySelective(Xinshui record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xinshui
     *
     * @mbggenerated Fri Apr 24 23:45:21 CST 2020
     */
    int updateByPrimaryKey(Xinshui record);
}