package com.kakasure.bqf.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FolderExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yunear_folder
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yunear_folder
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yunear_folder
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_folder
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public FolderExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_folder
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_folder
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_folder
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_folder
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_folder
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_folder
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_folder
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
     * This method corresponds to the database table yunear_folder
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
     * This method corresponds to the database table yunear_folder
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_folder
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
     * This class corresponds to the database table yunear_folder
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

        public Criteria andFolderIdIsNull() {
            addCriterion("FOLDER_ID is null");
            return (Criteria) this;
        }

        public Criteria andFolderIdIsNotNull() {
            addCriterion("FOLDER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFolderIdEqualTo(String value) {
            addCriterion("FOLDER_ID =", value, "folderId");
            return (Criteria) this;
        }

        public Criteria andFolderIdNotEqualTo(String value) {
            addCriterion("FOLDER_ID <>", value, "folderId");
            return (Criteria) this;
        }

        public Criteria andFolderIdGreaterThan(String value) {
            addCriterion("FOLDER_ID >", value, "folderId");
            return (Criteria) this;
        }

        public Criteria andFolderIdGreaterThanOrEqualTo(String value) {
            addCriterion("FOLDER_ID >=", value, "folderId");
            return (Criteria) this;
        }

        public Criteria andFolderIdLessThan(String value) {
            addCriterion("FOLDER_ID <", value, "folderId");
            return (Criteria) this;
        }

        public Criteria andFolderIdLessThanOrEqualTo(String value) {
            addCriterion("FOLDER_ID <=", value, "folderId");
            return (Criteria) this;
        }

        public Criteria andFolderIdLike(String value) {
            addCriterion("FOLDER_ID like", value, "folderId");
            return (Criteria) this;
        }

        public Criteria andFolderIdNotLike(String value) {
            addCriterion("FOLDER_ID not like", value, "folderId");
            return (Criteria) this;
        }

        public Criteria andFolderIdIn(List<String> values) {
            addCriterion("FOLDER_ID in", values, "folderId");
            return (Criteria) this;
        }

        public Criteria andFolderIdNotIn(List<String> values) {
            addCriterion("FOLDER_ID not in", values, "folderId");
            return (Criteria) this;
        }

        public Criteria andFolderIdBetween(String value1, String value2) {
            addCriterion("FOLDER_ID between", value1, value2, "folderId");
            return (Criteria) this;
        }

        public Criteria andFolderIdNotBetween(String value1, String value2) {
            addCriterion("FOLDER_ID not between", value1, value2, "folderId");
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

        public Criteria andFolderNameIsNull() {
            addCriterion("FOLDER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFolderNameIsNotNull() {
            addCriterion("FOLDER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFolderNameEqualTo(String value) {
            addCriterion("FOLDER_NAME =", value, "folderName");
            return (Criteria) this;
        }

        public Criteria andFolderNameNotEqualTo(String value) {
            addCriterion("FOLDER_NAME <>", value, "folderName");
            return (Criteria) this;
        }

        public Criteria andFolderNameGreaterThan(String value) {
            addCriterion("FOLDER_NAME >", value, "folderName");
            return (Criteria) this;
        }

        public Criteria andFolderNameGreaterThanOrEqualTo(String value) {
            addCriterion("FOLDER_NAME >=", value, "folderName");
            return (Criteria) this;
        }

        public Criteria andFolderNameLessThan(String value) {
            addCriterion("FOLDER_NAME <", value, "folderName");
            return (Criteria) this;
        }

        public Criteria andFolderNameLessThanOrEqualTo(String value) {
            addCriterion("FOLDER_NAME <=", value, "folderName");
            return (Criteria) this;
        }

        public Criteria andFolderNameLike(String value) {
            addCriterion("FOLDER_NAME like", value, "folderName");
            return (Criteria) this;
        }

        public Criteria andFolderNameNotLike(String value) {
            addCriterion("FOLDER_NAME not like", value, "folderName");
            return (Criteria) this;
        }

        public Criteria andFolderNameIn(List<String> values) {
            addCriterion("FOLDER_NAME in", values, "folderName");
            return (Criteria) this;
        }

        public Criteria andFolderNameNotIn(List<String> values) {
            addCriterion("FOLDER_NAME not in", values, "folderName");
            return (Criteria) this;
        }

        public Criteria andFolderNameBetween(String value1, String value2) {
            addCriterion("FOLDER_NAME between", value1, value2, "folderName");
            return (Criteria) this;
        }

        public Criteria andFolderNameNotBetween(String value1, String value2) {
            addCriterion("FOLDER_NAME not between", value1, value2, "folderName");
            return (Criteria) this;
        }

        public Criteria andFolderTeamIsNull() {
            addCriterion("FOLDER_TEAM is null");
            return (Criteria) this;
        }

        public Criteria andFolderTeamIsNotNull() {
            addCriterion("FOLDER_TEAM is not null");
            return (Criteria) this;
        }

        public Criteria andFolderTeamEqualTo(String value) {
            addCriterion("FOLDER_TEAM =", value, "folderTeam");
            return (Criteria) this;
        }

        public Criteria andFolderTeamNotEqualTo(String value) {
            addCriterion("FOLDER_TEAM <>", value, "folderTeam");
            return (Criteria) this;
        }

        public Criteria andFolderTeamGreaterThan(String value) {
            addCriterion("FOLDER_TEAM >", value, "folderTeam");
            return (Criteria) this;
        }

        public Criteria andFolderTeamGreaterThanOrEqualTo(String value) {
            addCriterion("FOLDER_TEAM >=", value, "folderTeam");
            return (Criteria) this;
        }

        public Criteria andFolderTeamLessThan(String value) {
            addCriterion("FOLDER_TEAM <", value, "folderTeam");
            return (Criteria) this;
        }

        public Criteria andFolderTeamLessThanOrEqualTo(String value) {
            addCriterion("FOLDER_TEAM <=", value, "folderTeam");
            return (Criteria) this;
        }

        public Criteria andFolderTeamLike(String value) {
            addCriterion("FOLDER_TEAM like", value, "folderTeam");
            return (Criteria) this;
        }

        public Criteria andFolderTeamNotLike(String value) {
            addCriterion("FOLDER_TEAM not like", value, "folderTeam");
            return (Criteria) this;
        }

        public Criteria andFolderTeamIn(List<String> values) {
            addCriterion("FOLDER_TEAM in", values, "folderTeam");
            return (Criteria) this;
        }

        public Criteria andFolderTeamNotIn(List<String> values) {
            addCriterion("FOLDER_TEAM not in", values, "folderTeam");
            return (Criteria) this;
        }

        public Criteria andFolderTeamBetween(String value1, String value2) {
            addCriterion("FOLDER_TEAM between", value1, value2, "folderTeam");
            return (Criteria) this;
        }

        public Criteria andFolderTeamNotBetween(String value1, String value2) {
            addCriterion("FOLDER_TEAM not between", value1, value2, "folderTeam");
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
     * This class corresponds to the database table yunear_folder
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
     * This class corresponds to the database table yunear_folder
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