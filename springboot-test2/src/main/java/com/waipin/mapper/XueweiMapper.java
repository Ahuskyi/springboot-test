package com.waipin.mapper;

import com.waipin.model.Xuewei;
import com.waipin.model.XueweiExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XueweiMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xuewei
     *
     * @mbggenerated Mon Apr 20 17:34:14 CST 2020
     */
    int countByExample(XueweiExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xuewei
     *
     * @mbggenerated Mon Apr 20 17:34:14 CST 2020
     */
    int deleteByExample(XueweiExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xuewei
     *
     * @mbggenerated Mon Apr 20 17:34:14 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xuewei
     *
     * @mbggenerated Mon Apr 20 17:34:14 CST 2020
     */
    int insert(Xuewei record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xuewei
     *
     * @mbggenerated Mon Apr 20 17:34:14 CST 2020
     */
    int insertSelective(Xuewei record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xuewei
     *
     * @mbggenerated Mon Apr 20 17:34:14 CST 2020
     */
    List<Xuewei> selectByExample(XueweiExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xuewei
     *
     * @mbggenerated Mon Apr 20 17:34:14 CST 2020
     */
    Xuewei selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xuewei
     *
     * @mbggenerated Mon Apr 20 17:34:14 CST 2020
     */
    int updateByExampleSelective(@Param("record") Xuewei record, @Param("example") XueweiExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xuewei
     *
     * @mbggenerated Mon Apr 20 17:34:14 CST 2020
     */
    int updateByExample(@Param("record") Xuewei record, @Param("example") XueweiExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xuewei
     *
     * @mbggenerated Mon Apr 20 17:34:14 CST 2020
     */
    int updateByPrimaryKeySelective(Xuewei record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xuewei
     *
     * @mbggenerated Mon Apr 20 17:34:14 CST 2020
     */
    int updateByPrimaryKey(Xuewei record);
}