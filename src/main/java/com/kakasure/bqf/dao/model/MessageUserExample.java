package com.kakasure.bqf.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageUserExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yunear_message_user
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yunear_message_user
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yunear_message_user
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_message_user
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public MessageUserExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_message_user
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_message_user
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_message_user
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_message_user
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_message_user
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_message_user
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_message_user
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
     * This method corresponds to the database table yunear_message_user
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
     * This method corresponds to the database table yunear_message_user
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_message_user
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
     * This class corresponds to the database table yunear_message_user
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

        public Criteria andMessageuserIdIsNull() {
            addCriterion("MESSAGEUSER_ID is null");
            return (Criteria) this;
        }

        public Criteria andMessageuserIdIsNotNull() {
            addCriterion("MESSAGEUSER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMessageuserIdEqualTo(String value) {
            addCriterion("MESSAGEUSER_ID =", value, "messageuserId");
            return (Criteria) this;
        }

        public Criteria andMessageuserIdNotEqualTo(String value) {
            addCriterion("MESSAGEUSER_ID <>", value, "messageuserId");
            return (Criteria) this;
        }

        public Criteria andMessageuserIdGreaterThan(String value) {
            addCriterion("MESSAGEUSER_ID >", value, "messageuserId");
            return (Criteria) this;
        }

        public Criteria andMessageuserIdGreaterThanOrEqualTo(String value) {
            addCriterion("MESSAGEUSER_ID >=", value, "messageuserId");
            return (Criteria) this;
        }

        public Criteria andMessageuserIdLessThan(String value) {
            addCriterion("MESSAGEUSER_ID <", value, "messageuserId");
            return (Criteria) this;
        }

        public Criteria andMessageuserIdLessThanOrEqualTo(String value) {
            addCriterion("MESSAGEUSER_ID <=", value, "messageuserId");
            return (Criteria) this;
        }

        public Criteria andMessageuserIdLike(String value) {
            addCriterion("MESSAGEUSER_ID like", value, "messageuserId");
            return (Criteria) this;
        }

        public Criteria andMessageuserIdNotLike(String value) {
            addCriterion("MESSAGEUSER_ID not like", value, "messageuserId");
            return (Criteria) this;
        }

        public Criteria andMessageuserIdIn(List<String> values) {
            addCriterion("MESSAGEUSER_ID in", values, "messageuserId");
            return (Criteria) this;
        }

        public Criteria andMessageuserIdNotIn(List<String> values) {
            addCriterion("MESSAGEUSER_ID not in", values, "messageuserId");
            return (Criteria) this;
        }

        public Criteria andMessageuserIdBetween(String value1, String value2) {
            addCriterion("MESSAGEUSER_ID between", value1, value2, "messageuserId");
            return (Criteria) this;
        }

        public Criteria andMessageuserIdNotBetween(String value1, String value2) {
            addCriterion("MESSAGEUSER_ID not between", value1, value2, "messageuserId");
            return (Criteria) this;
        }

        public Criteria andMessageIdIsNull() {
            addCriterion("MESSAGE_ID is null");
            return (Criteria) this;
        }

        public Criteria andMessageIdIsNotNull() {
            addCriterion("MESSAGE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMessageIdEqualTo(String value) {
            addCriterion("MESSAGE_ID =", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdNotEqualTo(String value) {
            addCriterion("MESSAGE_ID <>", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdGreaterThan(String value) {
            addCriterion("MESSAGE_ID >", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdGreaterThanOrEqualTo(String value) {
            addCriterion("MESSAGE_ID >=", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdLessThan(String value) {
            addCriterion("MESSAGE_ID <", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdLessThanOrEqualTo(String value) {
            addCriterion("MESSAGE_ID <=", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdLike(String value) {
            addCriterion("MESSAGE_ID like", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdNotLike(String value) {
            addCriterion("MESSAGE_ID not like", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdIn(List<String> values) {
            addCriterion("MESSAGE_ID in", values, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdNotIn(List<String> values) {
            addCriterion("MESSAGE_ID not in", values, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdBetween(String value1, String value2) {
            addCriterion("MESSAGE_ID between", value1, value2, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdNotBetween(String value1, String value2) {
            addCriterion("MESSAGE_ID not between", value1, value2, "messageId");
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

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
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

        public Criteria andDateModifyIsNull() {
            addCriterion("DATE_MODIFY is null");
            return (Criteria) this;
        }

        public Criteria andDateModifyIsNotNull() {
            addCriterion("DATE_MODIFY is not null");
            return (Criteria) this;
        }

        public Criteria andDateModifyEqualTo(Date value) {
            addCriterion("DATE_MODIFY =", value, "dateModify");
            return (Criteria) this;
        }

        public Criteria andDateModifyNotEqualTo(Date value) {
            addCriterion("DATE_MODIFY <>", value, "dateModify");
            return (Criteria) this;
        }

        public Criteria andDateModifyGreaterThan(Date value) {
            addCriterion("DATE_MODIFY >", value, "dateModify");
            return (Criteria) this;
        }

        public Criteria andDateModifyGreaterThanOrEqualTo(Date value) {
            addCriterion("DATE_MODIFY >=", value, "dateModify");
            return (Criteria) this;
        }

        public Criteria andDateModifyLessThan(Date value) {
            addCriterion("DATE_MODIFY <", value, "dateModify");
            return (Criteria) this;
        }

        public Criteria andDateModifyLessThanOrEqualTo(Date value) {
            addCriterion("DATE_MODIFY <=", value, "dateModify");
            return (Criteria) this;
        }

        public Criteria andDateModifyIn(List<Date> values) {
            addCriterion("DATE_MODIFY in", values, "dateModify");
            return (Criteria) this;
        }

        public Criteria andDateModifyNotIn(List<Date> values) {
            addCriterion("DATE_MODIFY not in", values, "dateModify");
            return (Criteria) this;
        }

        public Criteria andDateModifyBetween(Date value1, Date value2) {
            addCriterion("DATE_MODIFY between", value1, value2, "dateModify");
            return (Criteria) this;
        }

        public Criteria andDateModifyNotBetween(Date value1, Date value2) {
            addCriterion("DATE_MODIFY not between", value1, value2, "dateModify");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNull() {
            addCriterion("IS_DELETE is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("IS_DELETE is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(String value) {
            addCriterion("IS_DELETE =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(String value) {
            addCriterion("IS_DELETE <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(String value) {
            addCriterion("IS_DELETE >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(String value) {
            addCriterion("IS_DELETE >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(String value) {
            addCriterion("IS_DELETE <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(String value) {
            addCriterion("IS_DELETE <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLike(String value) {
            addCriterion("IS_DELETE like", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotLike(String value) {
            addCriterion("IS_DELETE not like", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<String> values) {
            addCriterion("IS_DELETE in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<String> values) {
            addCriterion("IS_DELETE not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(String value1, String value2) {
            addCriterion("IS_DELETE between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(String value1, String value2) {
            addCriterion("IS_DELETE not between", value1, value2, "isDelete");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table yunear_message_user
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
     * This class corresponds to the database table yunear_message_user
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