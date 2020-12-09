package com.waipin.mapper;

import com.waipin.model.Zhicheng;
import com.waipin.model.ZhichengExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZhichengMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zhicheng
     *
     * @mbggenerated Mon Apr 20 17:34:14 CST 2020
     */
    int countByExample(ZhichengExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zhicheng
     *
     * @mbggenerated Mon Apr 20 17:34:14 CST 2020
     */
    int deleteByExample(ZhichengExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zhicheng
     *
     * @mbggenerated Mon Apr 20 17:34:14 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zhicheng
     *
     * @mbggenerated Mon Apr 20 17:34:14 CST 2020
     */
    int insert(Zhicheng record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zhicheng
     *
     * @mbggenerated Mon Apr 20 17:34:14 CST 2020
     */
    int insertSelective(Zhicheng record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zhicheng
     *
     * @mbggenerated Mon Apr 20 17:34:14 CST 2020
     */
    List<Zhicheng> selectByExample(ZhichengExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zhicheng
     *
     * @mbggenerated Mon Apr 20 17:34:14 CST 2020
     */
    Zhicheng selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zhicheng
     *
     * @mbggenerated Mon Apr 20 17:34:14 CST 2020
     */
    int updateByExampleSelective(@Param("record") Zhicheng record, @Param("example") ZhichengExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zhicheng
     *
     * @mbggenerated Mon Apr 20 17:34:14 CST 2020
     */
    int updateByExample(@Param("record") Zhicheng record, @Param("example") ZhichengExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zhicheng
     *
     * @mbggenerated Mon Apr 20 17:34:14 CST 2020
     */
    int updateByPrimaryKeySelective(Zhicheng record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zhicheng
     *
     * @mbggenerated Mon Apr 20 17:34:14 CST 2020
     */
    int updateByPrimaryKey(Zhicheng record);
}