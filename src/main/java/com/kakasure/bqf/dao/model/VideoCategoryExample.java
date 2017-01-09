package com.kakasure.bqf.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VideoCategoryExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yunear_video_category
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yunear_video_category
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yunear_video_category
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_video_category
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public VideoCategoryExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_video_category
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_video_category
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_video_category
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_video_category
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_video_category
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_video_category
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_video_category
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
     * This method corresponds to the database table yunear_video_category
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
     * This method corresponds to the database table yunear_video_category
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_video_category
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
     * This class corresponds to the database table yunear_video_category
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

        public Criteria andVideoCategoryIdIsNull() {
            addCriterion("VIDEO_CATEGORY_ID is null");
            return (Criteria) this;
        }

        public Criteria andVideoCategoryIdIsNotNull() {
            addCriterion("VIDEO_CATEGORY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andVideoCategoryIdEqualTo(String value) {
            addCriterion("VIDEO_CATEGORY_ID =", value, "videoCategoryId");
            return (Criteria) this;
        }

        public Criteria andVideoCategoryIdNotEqualTo(String value) {
            addCriterion("VIDEO_CATEGORY_ID <>", value, "videoCategoryId");
            return (Criteria) this;
        }

        public Criteria andVideoCategoryIdGreaterThan(String value) {
            addCriterion("VIDEO_CATEGORY_ID >", value, "videoCategoryId");
            return (Criteria) this;
        }

        public Criteria andVideoCategoryIdGreaterThanOrEqualTo(String value) {
            addCriterion("VIDEO_CATEGORY_ID >=", value, "videoCategoryId");
            return (Criteria) this;
        }

        public Criteria andVideoCategoryIdLessThan(String value) {
            addCriterion("VIDEO_CATEGORY_ID <", value, "videoCategoryId");
            return (Criteria) this;
        }

        public Criteria andVideoCategoryIdLessThanOrEqualTo(String value) {
            addCriterion("VIDEO_CATEGORY_ID <=", value, "videoCategoryId");
            return (Criteria) this;
        }

        public Criteria andVideoCategoryIdLike(String value) {
            addCriterion("VIDEO_CATEGORY_ID like", value, "videoCategoryId");
            return (Criteria) this;
        }

        public Criteria andVideoCategoryIdNotLike(String value) {
            addCriterion("VIDEO_CATEGORY_ID not like", value, "videoCategoryId");
            return (Criteria) this;
        }

        public Criteria andVideoCategoryIdIn(List<String> values) {
            addCriterion("VIDEO_CATEGORY_ID in", values, "videoCategoryId");
            return (Criteria) this;
        }

        public Criteria andVideoCategoryIdNotIn(List<String> values) {
            addCriterion("VIDEO_CATEGORY_ID not in", values, "videoCategoryId");
            return (Criteria) this;
        }

        public Criteria andVideoCategoryIdBetween(String value1, String value2) {
            addCriterion("VIDEO_CATEGORY_ID between", value1, value2, "videoCategoryId");
            return (Criteria) this;
        }

        public Criteria andVideoCategoryIdNotBetween(String value1, String value2) {
            addCriterion("VIDEO_CATEGORY_ID not between", value1, value2, "videoCategoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryNameIsNull() {
            addCriterion("CATEGORY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCategoryNameIsNotNull() {
            addCriterion("CATEGORY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryNameEqualTo(String value) {
            addCriterion("CATEGORY_NAME =", value, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameNotEqualTo(String value) {
            addCriterion("CATEGORY_NAME <>", value, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameGreaterThan(String value) {
            addCriterion("CATEGORY_NAME >", value, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameGreaterThanOrEqualTo(String value) {
            addCriterion("CATEGORY_NAME >=", value, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameLessThan(String value) {
            addCriterion("CATEGORY_NAME <", value, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameLessThanOrEqualTo(String value) {
            addCriterion("CATEGORY_NAME <=", value, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameLike(String value) {
            addCriterion("CATEGORY_NAME like", value, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameNotLike(String value) {
            addCriterion("CATEGORY_NAME not like", value, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameIn(List<String> values) {
            addCriterion("CATEGORY_NAME in", values, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameNotIn(List<String> values) {
            addCriterion("CATEGORY_NAME not in", values, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameBetween(String value1, String value2) {
            addCriterion("CATEGORY_NAME between", value1, value2, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameNotBetween(String value1, String value2) {
            addCriterion("CATEGORY_NAME not between", value1, value2, "categoryName");
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
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table yunear_video_category
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
     * This class corresponds to the database table yunear_video_category
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