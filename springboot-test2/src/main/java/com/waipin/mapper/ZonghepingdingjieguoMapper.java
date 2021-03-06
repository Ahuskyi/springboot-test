package com.waipin.mapper;

import com.waipin.model.Zonghepingdingjieguo;
import com.waipin.model.ZonghepingdingjieguoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZonghepingdingjieguoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zonghepingdingjieguo
     *
     * @mbggenerated Mon Apr 20 17:34:14 CST 2020
     */
    int countByExample(ZonghepingdingjieguoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zonghepingdingjieguo
     *
     * @mbggenerated Mon Apr 20 17:34:14 CST 2020
     */
    int deleteByExample(ZonghepingdingjieguoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zonghepingdingjieguo
     *
     * @mbggenerated Mon Apr 20 17:34:14 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zonghepingdingjieguo
     *
     * @mbggenerated Mon Apr 20 17:34:14 CST 2020
     */
    int insert(Zonghepingdingjieguo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zonghepingdingjieguo
     *
     * @mbggenerated Mon Apr 20 17:34:14 CST 2020
     */
    int insertSelective(Zonghepingdingjieguo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zonghepingdingjieguo
     *
     * @mbggenerated Mon Apr 20 17:34:14 CST 2020
     */
    List<Zonghepingdingjieguo> selectByExample(ZonghepingdingjieguoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zonghepingdingjieguo
     *
     * @mbggenerated Mon Apr 20 17:34:14 CST 2020
     */
    Zonghepingdingjieguo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zonghepingdingjieguo
     *
     * @mbggenerated Mon Apr 20 17:34:14 CST 2020
     */
    int updateByExampleSelective(@Param("record") Zonghepingdingjieguo record, @Param("example") ZonghepingdingjieguoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zonghepingdingjieguo
     *
     * @mbggenerated Mon Apr 20 17:34:14 CST 2020
     */
    int updateByExample(@Param("record") Zonghepingdingjieguo record, @Param("example") ZonghepingdingjieguoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zonghepingdingjieguo
     *
     * @mbggenerated Mon Apr 20 17:34:14 CST 2020
     */
    int updateByPrimaryKeySelective(Zonghepingdingjieguo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zonghepingdingjieguo
     *
     * @mbggenerated Mon Apr 20 17:34:14 CST 2020
     */
    int updateByPrimaryKey(Zonghepingdingjieguo record);
}