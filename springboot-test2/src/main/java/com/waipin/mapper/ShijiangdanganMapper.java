package com.waipin.mapper;

import com.waipin.model.Shijiangdangan;
import com.waipin.model.ShijiangdanganExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShijiangdanganMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shijiangdangan
     *
     * @mbggenerated Mon Apr 20 22:07:35 CST 2020
     */
    int countByExample(ShijiangdanganExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shijiangdangan
     *
     * @mbggenerated Mon Apr 20 22:07:35 CST 2020
     */
    int deleteByExample(ShijiangdanganExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shijiangdangan
     *
     * @mbggenerated Mon Apr 20 22:07:35 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shijiangdangan
     *
     * @mbggenerated Mon Apr 20 22:07:35 CST 2020
     */
    int insert(Shijiangdangan record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shijiangdangan
     *
     * @mbggenerated Mon Apr 20 22:07:35 CST 2020
     */
    int insertSelective(Shijiangdangan record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shijiangdangan
     *
     * @mbggenerated Mon Apr 20 22:07:35 CST 2020
     */
    List<Shijiangdangan> selectByExample(ShijiangdanganExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shijiangdangan
     *
     * @mbggenerated Mon Apr 20 22:07:35 CST 2020
     */
    Shijiangdangan selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shijiangdangan
     *
     * @mbggenerated Mon Apr 20 22:07:35 CST 2020
     */
    int updateByExampleSelective(@Param("record") Shijiangdangan record, @Param("example") ShijiangdanganExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shijiangdangan
     *
     * @mbggenerated Mon Apr 20 22:07:35 CST 2020
     */
    int updateByExample(@Param("record") Shijiangdangan record, @Param("example") ShijiangdanganExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shijiangdangan
     *
     * @mbggenerated Mon Apr 20 22:07:35 CST 2020
     */
    int updateByPrimaryKeySelective(Shijiangdangan record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shijiangdangan
     *
     * @mbggenerated Mon Apr 20 22:07:35 CST 2020
     */
    int updateByPrimaryKey(Shijiangdangan record);
}