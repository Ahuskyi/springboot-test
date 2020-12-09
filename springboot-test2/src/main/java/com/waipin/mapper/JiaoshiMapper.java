package com.waipin.mapper;

import com.waipin.model.Jiaoshi;
import com.waipin.model.JiaoshiExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JiaoshiMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jiaoshi
     *
     * @mbggenerated Sun Apr 12 01:52:53 CST 2020
     */
     Jiaoshi login(String yonghuming, String  mima);





    int countByExample(JiaoshiExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jiaoshi
     *
     * @mbggenerated Sun Apr 12 01:52:53 CST 2020
     */
    int deleteByExample(JiaoshiExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jiaoshi
     *
     * @mbggenerated Sun Apr 12 01:52:53 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jiaoshi
     *
     * @mbggenerated Sun Apr 12 01:52:53 CST 2020
     */
    int insert(Jiaoshi record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jiaoshi
     *
     * @mbggenerated Sun Apr 12 01:52:53 CST 2020
     */
    int insertSelective(Jiaoshi record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jiaoshi
     *
     * @mbggenerated Sun Apr 12 01:52:53 CST 2020
     */
    List<Jiaoshi> selectByExample(JiaoshiExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jiaoshi
     *
     * @mbggenerated Sun Apr 12 01:52:53 CST 2020
     */
    Jiaoshi selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jiaoshi
     *
     * @mbggenerated Sun Apr 12 01:52:53 CST 2020
     */
    int updateByExampleSelective(@Param("record") Jiaoshi record, @Param("example") JiaoshiExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jiaoshi
     *
     * @mbggenerated Sun Apr 12 01:52:53 CST 2020
     */
    int updateByExample(@Param("record") Jiaoshi record, @Param("example") JiaoshiExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jiaoshi
     *
     * @mbggenerated Sun Apr 12 01:52:53 CST 2020
     */
    int updateByPrimaryKeySelective(Jiaoshi record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jiaoshi
     *
     * @mbggenerated Sun Apr 12 01:52:53 CST 2020
     */
    int updateByPrimaryKey(Jiaoshi record);
}