package com.kakasure.bqf.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CodenumExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yunear_codenum
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yunear_codenum
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yunear_codenum
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_codenum
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public CodenumExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_codenum
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_codenum
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_codenum
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_codenum
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_codenum
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_codenum
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_codenum
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_codenum
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
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
     * This method corresponds to the database table yunear_codenum
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_codenum
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table yunear_codenum
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
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

        public Criteria andCodenumIdIsNull() {
            addCriterion("CODENUM_ID is null");
            return (Criteria) this;
        }

        public Criteria andCodenumIdIsNotNull() {
            addCriterion("CODENUM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCodenumIdEqualTo(String value) {
            addCriterion("CODENUM_ID =", value, "codenumId");
            return (Criteria) this;
        }

        public Criteria andCodenumIdNotEqualTo(String value) {
            addCriterion("CODENUM_ID <>", value, "codenumId");
            return (Criteria) this;
        }

        public Criteria andCodenumIdGreaterThan(String value) {
            addCriterion("CODENUM_ID >", value, "codenumId");
            return (Criteria) this;
        }

        public Criteria andCodenumIdGreaterThanOrEqualTo(String value) {
            addCriterion("CODENUM_ID >=", value, "codenumId");
            return (Criteria) this;
        }

        public Criteria andCodenumIdLessThan(String value) {
            addCriterion("CODENUM_ID <", value, "codenumId");
            return (Criteria) this;
        }

        public Criteria andCodenumIdLessThanOrEqualTo(String value) {
            addCriterion("CODENUM_ID <=", value, "codenumId");
            return (Criteria) this;
        }

        public Criteria andCodenumIdLike(String value) {
            addCriterion("CODENUM_ID like", value, "codenumId");
            return (Criteria) this;
        }

        public Criteria andCodenumIdNotLike(String value) {
            addCriterion("CODENUM_ID not like", value, "codenumId");
            return (Criteria) this;
        }

        public Criteria andCodenumIdIn(List<String> values) {
            addCriterion("CODENUM_ID in", values, "codenumId");
            return (Criteria) this;
        }

        public Criteria andCodenumIdNotIn(List<String> values) {
            addCriterion("CODENUM_ID not in", values, "codenumId");
            return (Criteria) this;
        }

        public Criteria andCodenumIdBetween(String value1, String value2) {
            addCriterion("CODENUM_ID between", value1, value2, "codenumId");
            return (Criteria) this;
        }

        public Criteria andCodenumIdNotBetween(String value1, String value2) {
            addCriterion("CODENUM_ID not between", value1, value2, "codenumId");
            return (Criteria) this;
        }

        public Criteria andCopyrightmultiIdIsNull() {
            addCriterion("COPYRIGHTMULTI_ID is null");
            return (Criteria) this;
        }

        public Criteria andCopyrightmultiIdIsNotNull() {
            addCriterion("COPYRIGHTMULTI_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCopyrightmultiIdEqualTo(String value) {
            addCriterion("COPYRIGHTMULTI_ID =", value, "copyrightmultiId");
            return (Criteria) this;
        }

        public Criteria andCopyrightmultiIdNotEqualTo(String value) {
            addCriterion("COPYRIGHTMULTI_ID <>", value, "copyrightmultiId");
            return (Criteria) this;
        }

        public Criteria andCopyrightmultiIdGreaterThan(String value) {
            addCriterion("COPYRIGHTMULTI_ID >", value, "copyrightmultiId");
            return (Criteria) this;
        }

        public Criteria andCopyrightmultiIdGreaterThanOrEqualTo(String value) {
            addCriterion("COPYRIGHTMULTI_ID >=", value, "copyrightmultiId");
            return (Criteria) this;
        }

        public Criteria andCopyrightmultiIdLessThan(String value) {
            addCriterion("COPYRIGHTMULTI_ID <", value, "copyrightmultiId");
            return (Criteria) this;
        }

        public Criteria andCopyrightmultiIdLessThanOrEqualTo(String value) {
            addCriterion("COPYRIGHTMULTI_ID <=", value, "copyrightmultiId");
            return (Criteria) this;
        }

        public Criteria andCopyrightmultiIdLike(String value) {
            addCriterion("COPYRIGHTMULTI_ID like", value, "copyrightmultiId");
            return (Criteria) this;
        }

        public Criteria andCopyrightmultiIdNotLike(String value) {
            addCriterion("COPYRIGHTMULTI_ID not like", value, "copyrightmultiId");
            return (Criteria) this;
        }

        public Criteria andCopyrightmultiIdIn(List<String> values) {
            addCriterion("COPYRIGHTMULTI_ID in", values, "copyrightmultiId");
            return (Criteria) this;
        }

        public Criteria andCopyrightmultiIdNotIn(List<String> values) {
            addCriterion("COPYRIGHTMULTI_ID not in", values, "copyrightmultiId");
            return (Criteria) this;
        }

        public Criteria andCopyrightmultiIdBetween(String value1, String value2) {
            addCriterion("COPYRIGHTMULTI_ID between", value1, value2, "copyrightmultiId");
            return (Criteria) this;
        }

        public Criteria andCopyrightmultiIdNotBetween(String value1, String value2) {
            addCriterion("COPYRIGHTMULTI_ID not between", value1, value2, "copyrightmultiId");
            return (Criteria) this;
        }

        public Criteria andBqfUserIdIsNull() {
            addCriterion("BQF_USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andBqfUserIdIsNotNull() {
            addCriterion("BQF_USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBqfUserIdEqualTo(String value) {
            addCriterion("BQF_USER_ID =", value, "bqfUserId");
            return (Criteria) this;
        }

        public Criteria andBqfUserIdNotEqualTo(String value) {
            addCriterion("BQF_USER_ID <>", value, "bqfUserId");
            return (Criteria) this;
        }

        public Criteria andBqfUserIdGreaterThan(String value) {
            addCriterion("BQF_USER_ID >", value, "bqfUserId");
            return (Criteria) this;
        }

        public Criteria andBqfUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("BQF_USER_ID >=", value, "bqfUserId");
            return (Criteria) this;
        }

        public Criteria andBqfUserIdLessThan(String value) {
            addCriterion("BQF_USER_ID <", value, "bqfUserId");
            return (Criteria) this;
        }

        public Criteria andBqfUserIdLessThanOrEqualTo(String value) {
            addCriterion("BQF_USER_ID <=", value, "bqfUserId");
            return (Criteria) this;
        }

        public Criteria andBqfUserIdLike(String value) {
            addCriterion("BQF_USER_ID like", value, "bqfUserId");
            return (Criteria) this;
        }

        public Criteria andBqfUserIdNotLike(String value) {
            addCriterion("BQF_USER_ID not like", value, "bqfUserId");
            return (Criteria) this;
        }

        public Criteria andBqfUserIdIn(List<String> values) {
            addCriterion("BQF_USER_ID in", values, "bqfUserId");
            return (Criteria) this;
        }

        public Criteria andBqfUserIdNotIn(List<String> values) {
            addCriterion("BQF_USER_ID not in", values, "bqfUserId");
            return (Criteria) this;
        }

        public Criteria andBqfUserIdBetween(String value1, String value2) {
            addCriterion("BQF_USER_ID between", value1, value2, "bqfUserId");
            return (Criteria) this;
        }

        public Criteria andBqfUserIdNotBetween(String value1, String value2) {
            addCriterion("BQF_USER_ID not between", value1, value2, "bqfUserId");
            return (Criteria) this;
        }

        public Criteria andFbfUserIdIsNull() {
            addCriterion("FBF_USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andFbfUserIdIsNotNull() {
            addCriterion("FBF_USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFbfUserIdEqualTo(String value) {
            addCriterion("FBF_USER_ID =", value, "fbfUserId");
            return (Criteria) this;
        }

        public Criteria andFbfUserIdNotEqualTo(String value) {
            addCriterion("FBF_USER_ID <>", value, "fbfUserId");
            return (Criteria) this;
        }

        public Criteria andFbfUserIdGreaterThan(String value) {
            addCriterion("FBF_USER_ID >", value, "fbfUserId");
            return (Criteria) this;
        }

        public Criteria andFbfUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("FBF_USER_ID >=", value, "fbfUserId");
            return (Criteria) this;
        }

        public Criteria andFbfUserIdLessThan(String value) {
            addCriterion("FBF_USER_ID <", value, "fbfUserId");
            return (Criteria) this;
        }

        public Criteria andFbfUserIdLessThanOrEqualTo(String value) {
            addCriterion("FBF_USER_ID <=", value, "fbfUserId");
            return (Criteria) this;
        }

        public Criteria andFbfUserIdLike(String value) {
            addCriterion("FBF_USER_ID like", value, "fbfUserId");
            return (Criteria) this;
        }

        public Criteria andFbfUserIdNotLike(String value) {
            addCriterion("FBF_USER_ID not like", value, "fbfUserId");
            return (Criteria) this;
        }

        public Criteria andFbfUserIdIn(List<String> values) {
            addCriterion("FBF_USER_ID in", values, "fbfUserId");
            return (Criteria) this;
        }

        public Criteria andFbfUserIdNotIn(List<String> values) {
            addCriterion("FBF_USER_ID not in", values, "fbfUserId");
            return (Criteria) this;
        }

        public Criteria andFbfUserIdBetween(String value1, String value2) {
            addCriterion("FBF_USER_ID between", value1, value2, "fbfUserId");
            return (Criteria) this;
        }

        public Criteria andFbfUserIdNotBetween(String value1, String value2) {
            addCriterion("FBF_USER_ID not between", value1, value2, "fbfUserId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("USER_ID =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("USER_ID <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("USER_ID >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("USER_ID >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("USER_ID <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("USER_ID <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("USER_ID like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("USER_ID not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("USER_ID in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("USER_ID not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("USER_ID between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("USER_ID not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andIpIsNull() {
            addCriterion("IP is null");
            return (Criteria) this;
        }

        public Criteria andIpIsNotNull() {
            addCriterion("IP is not null");
            return (Criteria) this;
        }

        public Criteria andIpEqualTo(String value) {
            addCriterion("IP =", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotEqualTo(String value) {
            addCriterion("IP <>", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThan(String value) {
            addCriterion("IP >", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThanOrEqualTo(String value) {
            addCriterion("IP >=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThan(String value) {
            addCriterion("IP <", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThanOrEqualTo(String value) {
            addCriterion("IP <=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLike(String value) {
            addCriterion("IP like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotLike(String value) {
            addCriterion("IP not like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpIn(List<String> values) {
            addCriterion("IP in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotIn(List<String> values) {
            addCriterion("IP not in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpBetween(String value1, String value2) {
            addCriterion("IP between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotBetween(String value1, String value2) {
            addCriterion("IP not between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andDateCreateIsNull() {
            addCriterion("DATE_CREATE is null");
            return (Criteria) this;
        }

        public Criteria andDateCreateIsNotNull() {
            addCriterion("DATE_CREATE is not null");
            return (Criteria) this;
        }

        public Criteria andDateCreateEqualTo(Date value) {
            addCriterion("DATE_CREATE =", value, "dateCreate");
            return (Criteria) this;
        }

        public Criteria andDateCreateNotEqualTo(Date value) {
            addCriterion("DATE_CREATE <>", value, "dateCreate");
            return (Criteria) this;
        }

        public Criteria andDateCreateGreaterThan(Date value) {
            addCriterion("DATE_CREATE >", value, "dateCreate");
            return (Criteria) this;
        }

        public Criteria andDateCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("DATE_CREATE >=", value, "dateCreate");
            return (Criteria) this;
        }

        public Criteria andDateCreateLessThan(Date value) {
            addCriterion("DATE_CREATE <", value, "dateCreate");
            return (Criteria) this;
        }

        public Criteria andDateCreateLessThanOrEqualTo(Date value) {
            addCriterion("DATE_CREATE <=", value, "dateCreate");
            return (Criteria) this;
        }

        public Criteria andDateCreateIn(List<Date> values) {
            addCriterion("DATE_CREATE in", values, "dateCreate");
            return (Criteria) this;
        }

        public Criteria andDateCreateNotIn(List<Date> values) {
            addCriterion("DATE_CREATE not in", values, "dateCreate");
            return (Criteria) this;
        }

        public Criteria andDateCreateBetween(Date value1, Date value2) {
            addCriterion("DATE_CREATE between", value1, value2, "dateCreate");
            return (Criteria) this;
        }

        public Criteria andDateCreateNotBetween(Date value1, Date value2) {
            addCriterion("DATE_CREATE not between", value1, value2, "dateCreate");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table yunear_codenum
     *
     * @mbggenerated do_not_delete_during_merge Thu Sep 22 10:02:32 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table yunear_codenum
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
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