package com.waipin.model;

import java.util.ArrayList;
import java.util.List;

public class JiaoxuedudaoExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table jiaoxuedudao
     *
     * @mbggenerated Sun Apr 12 01:52:53 CST 2020
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table jiaoxuedudao
     *
     * @mbggenerated Sun Apr 12 01:52:53 CST 2020
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table jiaoxuedudao
     *
     * @mbggenerated Sun Apr 12 01:52:53 CST 2020
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jiaoxuedudao
     *
     * @mbggenerated Sun Apr 12 01:52:53 CST 2020
     */
    public JiaoxuedudaoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jiaoxuedudao
     *
     * @mbggenerated Sun Apr 12 01:52:53 CST 2020
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jiaoxuedudao
     *
     * @mbggenerated Sun Apr 12 01:52:53 CST 2020
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jiaoxuedudao
     *
     * @mbggenerated Sun Apr 12 01:52:53 CST 2020
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jiaoxuedudao
     *
     * @mbggenerated Sun Apr 12 01:52:53 CST 2020
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jiaoxuedudao
     *
     * @mbggenerated Sun Apr 12 01:52:53 CST 2020
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jiaoxuedudao
     *
     * @mbggenerated Sun Apr 12 01:52:53 CST 2020
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jiaoxuedudao
     *
     * @mbggenerated Sun Apr 12 01:52:53 CST 2020
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jiaoxuedudao
     *
     * @mbggenerated Sun Apr 12 01:52:53 CST 2020
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jiaoxuedudao
     *
     * @mbggenerated Sun Apr 12 01:52:53 CST 2020
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jiaoxuedudao
     *
     * @mbggenerated Sun Apr 12 01:52:53 CST 2020
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table jiaoxuedudao
     *
     * @mbggenerated Sun Apr 12 01:52:53 CST 2020
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andJiaoshibianhaoIsNull() {
            addCriterion("jiaoshibianhao is null");
            return (Criteria) this;
        }

        public Criteria andJiaoshibianhaoIsNotNull() {
            addCriterion("jiaoshibianhao is not null");
            return (Criteria) this;
        }

        public Criteria andJiaoshibianhaoEqualTo(Integer value) {
            addCriterion("jiaoshibianhao =", value, "jiaoshibianhao");
            return (Criteria) this;
        }

        public Criteria andJiaoshibianhaoNotEqualTo(Integer value) {
            addCriterion("jiaoshibianhao <>", value, "jiaoshibianhao");
            return (Criteria) this;
        }

        public Criteria andJiaoshibianhaoGreaterThan(Integer value) {
            addCriterion("jiaoshibianhao >", value, "jiaoshibianhao");
            return (Criteria) this;
        }

        public Criteria andJiaoshibianhaoGreaterThanOrEqualTo(Integer value) {
            addCriterion("jiaoshibianhao >=", value, "jiaoshibianhao");
            return (Criteria) this;
        }

        public Criteria andJiaoshibianhaoLessThan(Integer value) {
            addCriterion("jiaoshibianhao <", value, "jiaoshibianhao");
            return (Criteria) this;
        }

        public Criteria andJiaoshibianhaoLessThanOrEqualTo(Integer value) {
            addCriterion("jiaoshibianhao <=", value, "jiaoshibianhao");
            return (Criteria) this;
        }

        public Criteria andJiaoshibianhaoIn(List<Integer> values) {
            addCriterion("jiaoshibianhao in", values, "jiaoshibianhao");
            return (Criteria) this;
        }

        public Criteria andJiaoshibianhaoNotIn(List<Integer> values) {
            addCriterion("jiaoshibianhao not in", values, "jiaoshibianhao");
            return (Criteria) this;
        }

        public Criteria andJiaoshibianhaoBetween(Integer value1, Integer value2) {
            addCriterion("jiaoshibianhao between", value1, value2, "jiaoshibianhao");
            return (Criteria) this;
        }

        public Criteria andJiaoshibianhaoNotBetween(Integer value1, Integer value2) {
            addCriterion("jiaoshibianhao not between", value1, value2, "jiaoshibianhao");
            return (Criteria) this;
        }

        public Criteria andXingmingIsNull() {
            addCriterion("xingming is null");
            return (Criteria) this;
        }

        public Criteria andXingmingIsNotNull() {
            addCriterion("xingming is not null");
            return (Criteria) this;
        }

        public Criteria andXingmingEqualTo(String value) {
            addCriterion("xingming =", value, "xingming");
            return (Criteria) this;
        }

        public Criteria andXingmingNotEqualTo(String value) {
            addCriterion("xingming <>", value, "xingming");
            return (Criteria) this;
        }

        public Criteria andXingmingGreaterThan(String value) {
            addCriterion("xingming >", value, "xingming");
            return (Criteria) this;
        }

        public Criteria andXingmingGreaterThanOrEqualTo(String value) {
            addCriterion("xingming >=", value, "xingming");
            return (Criteria) this;
        }

        public Criteria andXingmingLessThan(String value) {
            addCriterion("xingming <", value, "xingming");
            return (Criteria) this;
        }

        public Criteria andXingmingLessThanOrEqualTo(String value) {
            addCriterion("xingming <=", value, "xingming");
            return (Criteria) this;
        }

        public Criteria andXingmingLike(String value) {
            addCriterion("xingming like", value, "xingming");
            return (Criteria) this;
        }

        public Criteria andXingmingNotLike(String value) {
            addCriterion("xingming not like", value, "xingming");
            return (Criteria) this;
        }

        public Criteria andXingmingIn(List<String> values) {
            addCriterion("xingming in", values, "xingming");
            return (Criteria) this;
        }

        public Criteria andXingmingNotIn(List<String> values) {
            addCriterion("xingming not in", values, "xingming");
            return (Criteria) this;
        }

        public Criteria andXingmingBetween(String value1, String value2) {
            addCriterion("xingming between", value1, value2, "xingming");
            return (Criteria) this;
        }

        public Criteria andXingmingNotBetween(String value1, String value2) {
            addCriterion("xingming not between", value1, value2, "xingming");
            return (Criteria) this;
        }

        public Criteria andTijiaorenbianhaoIsNull() {
            addCriterion("tijiaorenbianhao is null");
            return (Criteria) this;
        }

        public Criteria andTijiaorenbianhaoIsNotNull() {
            addCriterion("tijiaorenbianhao is not null");
            return (Criteria) this;
        }

        public Criteria andTijiaorenbianhaoEqualTo(String value) {
            addCriterion("tijiaorenbianhao =", value, "tijiaorenbianhao");
            return (Criteria) this;
        }

        public Criteria andTijiaorenbianhaoNotEqualTo(String value) {
            addCriterion("tijiaorenbianhao <>", value, "tijiaorenbianhao");
            return (Criteria) this;
        }

        public Criteria andTijiaorenbianhaoGreaterThan(String value) {
            addCriterion("tijiaorenbianhao >", value, "tijiaorenbianhao");
            return (Criteria) this;
        }

        public Criteria andTijiaorenbianhaoGreaterThanOrEqualTo(String value) {
            addCriterion("tijiaorenbianhao >=", value, "tijiaorenbianhao");
            return (Criteria) this;
        }

        public Criteria andTijiaorenbianhaoLessThan(String value) {
            addCriterion("tijiaorenbianhao <", value, "tijiaorenbianhao");
            return (Criteria) this;
        }

        public Criteria andTijiaorenbianhaoLessThanOrEqualTo(String value) {
            addCriterion("tijiaorenbianhao <=", value, "tijiaorenbianhao");
            return (Criteria) this;
        }

        public Criteria andTijiaorenbianhaoLike(String value) {
            addCriterion("tijiaorenbianhao like", value, "tijiaorenbianhao");
            return (Criteria) this;
        }

        public Criteria andTijiaorenbianhaoNotLike(String value) {
            addCriterion("tijiaorenbianhao not like", value, "tijiaorenbianhao");
            return (Criteria) this;
        }

        public Criteria andTijiaorenbianhaoIn(List<String> values) {
            addCriterion("tijiaorenbianhao in", values, "tijiaorenbianhao");
            return (Criteria) this;
        }

        public Criteria andTijiaorenbianhaoNotIn(List<String> values) {
            addCriterion("tijiaorenbianhao not in", values, "tijiaorenbianhao");
            return (Criteria) this;
        }

        public Criteria andTijiaorenbianhaoBetween(String value1, String value2) {
            addCriterion("tijiaorenbianhao between", value1, value2, "tijiaorenbianhao");
            return (Criteria) this;
        }

        public Criteria andTijiaorenbianhaoNotBetween(String value1, String value2) {
            addCriterion("tijiaorenbianhao not between", value1, value2, "tijiaorenbianhao");
            return (Criteria) this;
        }

        public Criteria andChufadengjiIsNull() {
            addCriterion("chufadengji is null");
            return (Criteria) this;
        }

        public Criteria andChufadengjiIsNotNull() {
            addCriterion("chufadengji is not null");
            return (Criteria) this;
        }

        public Criteria andChufadengjiEqualTo(String value) {
            addCriterion("chufadengji =", value, "chufadengji");
            return (Criteria) this;
        }

        public Criteria andChufadengjiNotEqualTo(String value) {
            addCriterion("chufadengji <>", value, "chufadengji");
            return (Criteria) this;
        }

        public Criteria andChufadengjiGreaterThan(String value) {
            addCriterion("chufadengji >", value, "chufadengji");
            return (Criteria) this;
        }

        public Criteria andChufadengjiGreaterThanOrEqualTo(String value) {
            addCriterion("chufadengji >=", value, "chufadengji");
            return (Criteria) this;
        }

        public Criteria andChufadengjiLessThan(String value) {
            addCriterion("chufadengji <", value, "chufadengji");
            return (Criteria) this;
        }

        public Criteria andChufadengjiLessThanOrEqualTo(String value) {
            addCriterion("chufadengji <=", value, "chufadengji");
            return (Criteria) this;
        }

        public Criteria andChufadengjiLike(String value) {
            addCriterion("chufadengji like", value, "chufadengji");
            return (Criteria) this;
        }

        public Criteria andChufadengjiNotLike(String value) {
            addCriterion("chufadengji not like", value, "chufadengji");
            return (Criteria) this;
        }

        public Criteria andChufadengjiIn(List<String> values) {
            addCriterion("chufadengji in", values, "chufadengji");
            return (Criteria) this;
        }

        public Criteria andChufadengjiNotIn(List<String> values) {
            addCriterion("chufadengji not in", values, "chufadengji");
            return (Criteria) this;
        }

        public Criteria andChufadengjiBetween(String value1, String value2) {
            addCriterion("chufadengji between", value1, value2, "chufadengji");
            return (Criteria) this;
        }

        public Criteria andChufadengjiNotBetween(String value1, String value2) {
            addCriterion("chufadengji not between", value1, value2, "chufadengji");
            return (Criteria) this;
        }

        public Criteria andFashengshjianIsNull() {
            addCriterion("fashengshjian is null");
            return (Criteria) this;
        }

        public Criteria andFashengshjianIsNotNull() {
            addCriterion("fashengshjian is not null");
            return (Criteria) this;
        }

        public Criteria andFashengshjianEqualTo(String value) {
            addCriterion("fashengshjian =", value, "fashengshjian");
            return (Criteria) this;
        }

        public Criteria andFashengshjianNotEqualTo(String value) {
            addCriterion("fashengshjian <>", value, "fashengshjian");
            return (Criteria) this;
        }

        public Criteria andFashengshjianGreaterThan(String value) {
            addCriterion("fashengshjian >", value, "fashengshjian");
            return (Criteria) this;
        }

        public Criteria andFashengshjianGreaterThanOrEqualTo(String value) {
            addCriterion("fashengshjian >=", value, "fashengshjian");
            return (Criteria) this;
        }

        public Criteria andFashengshjianLessThan(String value) {
            addCriterion("fashengshjian <", value, "fashengshjian");
            return (Criteria) this;
        }

        public Criteria andFashengshjianLessThanOrEqualTo(String value) {
            addCriterion("fashengshjian <=", value, "fashengshjian");
            return (Criteria) this;
        }

        public Criteria andFashengshjianLike(String value) {
            addCriterion("fashengshjian like", value, "fashengshjian");
            return (Criteria) this;
        }

        public Criteria andFashengshjianNotLike(String value) {
            addCriterion("fashengshjian not like", value, "fashengshjian");
            return (Criteria) this;
        }

        public Criteria andFashengshjianIn(List<String> values) {
            addCriterion("fashengshjian in", values, "fashengshjian");
            return (Criteria) this;
        }

        public Criteria andFashengshjianNotIn(List<String> values) {
            addCriterion("fashengshjian not in", values, "fashengshjian");
            return (Criteria) this;
        }

        public Criteria andFashengshjianBetween(String value1, String value2) {
            addCriterion("fashengshjian between", value1, value2, "fashengshjian");
            return (Criteria) this;
        }

        public Criteria andFashengshjianNotBetween(String value1, String value2) {
            addCriterion("fashengshjian not between", value1, value2, "fashengshjian");
            return (Criteria) this;
        }

        public Criteria andChufayuanyinIsNull() {
            addCriterion("chufayuanyin is null");
            return (Criteria) this;
        }

        public Criteria andChufayuanyinIsNotNull() {
            addCriterion("chufayuanyin is not null");
            return (Criteria) this;
        }

        public Criteria andChufayuanyinEqualTo(String value) {
            addCriterion("chufayuanyin =", value, "chufayuanyin");
            return (Criteria) this;
        }

        public Criteria andChufayuanyinNotEqualTo(String value) {
            addCriterion("chufayuanyin <>", value, "chufayuanyin");
            return (Criteria) this;
        }

        public Criteria andChufayuanyinGreaterThan(String value) {
            addCriterion("chufayuanyin >", value, "chufayuanyin");
            return (Criteria) this;
        }

        public Criteria andChufayuanyinGreaterThanOrEqualTo(String value) {
            addCriterion("chufayuanyin >=", value, "chufayuanyin");
            return (Criteria) this;
        }

        public Criteria andChufayuanyinLessThan(String value) {
            addCriterion("chufayuanyin <", value, "chufayuanyin");
            return (Criteria) this;
        }

        public Criteria andChufayuanyinLessThanOrEqualTo(String value) {
            addCriterion("chufayuanyin <=", value, "chufayuanyin");
            return (Criteria) this;
        }

        public Criteria andChufayuanyinLike(String value) {
            addCriterion("chufayuanyin like", value, "chufayuanyin");
            return (Criteria) this;
        }

        public Criteria andChufayuanyinNotLike(String value) {
            addCriterion("chufayuanyin not like", value, "chufayuanyin");
            return (Criteria) this;
        }

        public Criteria andChufayuanyinIn(List<String> values) {
            addCriterion("chufayuanyin in", values, "chufayuanyin");
            return (Criteria) this;
        }

        public Criteria andChufayuanyinNotIn(List<String> values) {
            addCriterion("chufayuanyin not in", values, "chufayuanyin");
            return (Criteria) this;
        }

        public Criteria andChufayuanyinBetween(String value1, String value2) {
            addCriterion("chufayuanyin between", value1, value2, "chufayuanyin");
            return (Criteria) this;
        }

        public Criteria andChufayuanyinNotBetween(String value1, String value2) {
            addCriterion("chufayuanyin not between", value1, value2, "chufayuanyin");
            return (Criteria) this;
        }

        public Criteria andChuangjianshijianIsNull() {
            addCriterion("chuangjianshijian is null");
            return (Criteria) this;
        }

        public Criteria andChuangjianshijianIsNotNull() {
            addCriterion("chuangjianshijian is not null");
            return (Criteria) this;
        }

        public Criteria andChuangjianshijianEqualTo(String value) {
            addCriterion("chuangjianshijian =", value, "chuangjianshijian");
            return (Criteria) this;
        }

        public Criteria andChuangjianshijianNotEqualTo(String value) {
            addCriterion("chuangjianshijian <>", value, "chuangjianshijian");
            return (Criteria) this;
        }

        public Criteria andChuangjianshijianGreaterThan(String value) {
            addCriterion("chuangjianshijian >", value, "chuangjianshijian");
            return (Criteria) this;
        }

        public Criteria andChuangjianshijianGreaterThanOrEqualTo(String value) {
            addCriterion("chuangjianshijian >=", value, "chuangjianshijian");
            return (Criteria) this;
        }

        public Criteria andChuangjianshijianLessThan(String value) {
            addCriterion("chuangjianshijian <", value, "chuangjianshijian");
            return (Criteria) this;
        }

        public Criteria andChuangjianshijianLessThanOrEqualTo(String value) {
            addCriterion("chuangjianshijian <=", value, "chuangjianshijian");
            return (Criteria) this;
        }

        public Criteria andChuangjianshijianLike(String value) {
            addCriterion("chuangjianshijian like", value, "chuangjianshijian");
            return (Criteria) this;
        }

        public Criteria andChuangjianshijianNotLike(String value) {
            addCriterion("chuangjianshijian not like", value, "chuangjianshijian");
            return (Criteria) this;
        }

        public Criteria andChuangjianshijianIn(List<String> values) {
            addCriterion("chuangjianshijian in", values, "chuangjianshijian");
            return (Criteria) this;
        }

        public Criteria andChuangjianshijianNotIn(List<String> values) {
            addCriterion("chuangjianshijian not in", values, "chuangjianshijian");
            return (Criteria) this;
        }

        public Criteria andChuangjianshijianBetween(String value1, String value2) {
            addCriterion("chuangjianshijian between", value1, value2, "chuangjianshijian");
            return (Criteria) this;
        }

        public Criteria andChuangjianshijianNotBetween(String value1, String value2) {
            addCriterion("chuangjianshijian not between", value1, value2, "chuangjianshijian");
            return (Criteria) this;
        }

        public Criteria andShenhezhuangtaiIsNull() {
            addCriterion("shenhezhuangtai is null");
            return (Criteria) this;
        }

        public Criteria andShenhezhuangtaiIsNotNull() {
            addCriterion("shenhezhuangtai is not null");
            return (Criteria) this;
        }

        public Criteria andShenhezhuangtaiEqualTo(String value) {
            addCriterion("shenhezhuangtai =", value, "shenhezhuangtai");
            return (Criteria) this;
        }

        public Criteria andShenhezhuangtaiNotEqualTo(String value) {
            addCriterion("shenhezhuangtai <>", value, "shenhezhuangtai");
            return (Criteria) this;
        }

        public Criteria andShenhezhuangtaiGreaterThan(String value) {
            addCriterion("shenhezhuangtai >", value, "shenhezhuangtai");
            return (Criteria) this;
        }

        public Criteria andShenhezhuangtaiGreaterThanOrEqualTo(String value) {
            addCriterion("shenhezhuangtai >=", value, "shenhezhuangtai");
            return (Criteria) this;
        }

        public Criteria andShenhezhuangtaiLessThan(String value) {
            addCriterion("shenhezhuangtai <", value, "shenhezhuangtai");
            return (Criteria) this;
        }

        public Criteria andShenhezhuangtaiLessThanOrEqualTo(String value) {
            addCriterion("shenhezhuangtai <=", value, "shenhezhuangtai");
            return (Criteria) this;
        }

        public Criteria andShenhezhuangtaiLike(String value) {
            addCriterion("shenhezhuangtai like", value, "shenhezhuangtai");
            return (Criteria) this;
        }

        public Criteria andShenhezhuangtaiNotLike(String value) {
            addCriterion("shenhezhuangtai not like", value, "shenhezhuangtai");
            return (Criteria) this;
        }

        public Criteria andShenhezhuangtaiIn(List<String> values) {
            addCriterion("shenhezhuangtai in", values, "shenhezhuangtai");
            return (Criteria) this;
        }

        public Criteria andShenhezhuangtaiNotIn(List<String> values) {
            addCriterion("shenhezhuangtai not in", values, "shenhezhuangtai");
            return (Criteria) this;
        }

        public Criteria andShenhezhuangtaiBetween(String value1, String value2) {
            addCriterion("shenhezhuangtai between", value1, value2, "shenhezhuangtai");
            return (Criteria) this;
        }

        public Criteria andShenhezhuangtaiNotBetween(String value1, String value2) {
            addCriterion("shenhezhuangtai not between", value1, value2, "shenhezhuangtai");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table jiaoxuedudao
     *
     * @mbggenerated do_not_delete_during_merge Sun Apr 12 01:52:53 CST 2020
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table jiaoxuedudao
     *
     * @mbggenerated Sun Apr 12 01:52:53 CST 2020
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}