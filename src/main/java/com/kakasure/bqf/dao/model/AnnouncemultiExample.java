package com.kakasure.bqf.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AnnouncemultiExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yunear_announcemulti
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yunear_announcemulti
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yunear_announcemulti
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_announcemulti
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public AnnouncemultiExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_announcemulti
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_announcemulti
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_announcemulti
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_announcemulti
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_announcemulti
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_announcemulti
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_announcemulti
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
     * This method corresponds to the database table yunear_announcemulti
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
     * This method corresponds to the database table yunear_announcemulti
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_announcemulti
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
     * This class corresponds to the database table yunear_announcemulti
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

        public Criteria andAnnouncemultiIdIsNull() {
            addCriterion("ANNOUNCEMULTI_ID is null");
            return (Criteria) this;
        }

        public Criteria andAnnouncemultiIdIsNotNull() {
            addCriterion("ANNOUNCEMULTI_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAnnouncemultiIdEqualTo(String value) {
            addCriterion("ANNOUNCEMULTI_ID =", value, "announcemultiId");
            return (Criteria) this;
        }

        public Criteria andAnnouncemultiIdNotEqualTo(String value) {
            addCriterion("ANNOUNCEMULTI_ID <>", value, "announcemultiId");
            return (Criteria) this;
        }

        public Criteria andAnnouncemultiIdGreaterThan(String value) {
            addCriterion("ANNOUNCEMULTI_ID >", value, "announcemultiId");
            return (Criteria) this;
        }

        public Criteria andAnnouncemultiIdGreaterThanOrEqualTo(String value) {
            addCriterion("ANNOUNCEMULTI_ID >=", value, "announcemultiId");
            return (Criteria) this;
        }

        public Criteria andAnnouncemultiIdLessThan(String value) {
            addCriterion("ANNOUNCEMULTI_ID <", value, "announcemultiId");
            return (Criteria) this;
        }

        public Criteria andAnnouncemultiIdLessThanOrEqualTo(String value) {
            addCriterion("ANNOUNCEMULTI_ID <=", value, "announcemultiId");
            return (Criteria) this;
        }

        public Criteria andAnnouncemultiIdLike(String value) {
            addCriterion("ANNOUNCEMULTI_ID like", value, "announcemultiId");
            return (Criteria) this;
        }

        public Criteria andAnnouncemultiIdNotLike(String value) {
            addCriterion("ANNOUNCEMULTI_ID not like", value, "announcemultiId");
            return (Criteria) this;
        }

        public Criteria andAnnouncemultiIdIn(List<String> values) {
            addCriterion("ANNOUNCEMULTI_ID in", values, "announcemultiId");
            return (Criteria) this;
        }

        public Criteria andAnnouncemultiIdNotIn(List<String> values) {
            addCriterion("ANNOUNCEMULTI_ID not in", values, "announcemultiId");
            return (Criteria) this;
        }

        public Criteria andAnnouncemultiIdBetween(String value1, String value2) {
            addCriterion("ANNOUNCEMULTI_ID between", value1, value2, "announcemultiId");
            return (Criteria) this;
        }

        public Criteria andAnnouncemultiIdNotBetween(String value1, String value2) {
            addCriterion("ANNOUNCEMULTI_ID not between", value1, value2, "announcemultiId");
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

        public Criteria andCopyrightUserIdIsNull() {
            addCriterion("COPYRIGHT_USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andCopyrightUserIdIsNotNull() {
            addCriterion("COPYRIGHT_USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCopyrightUserIdEqualTo(String value) {
            addCriterion("COPYRIGHT_USER_ID =", value, "copyrightUserId");
            return (Criteria) this;
        }

        public Criteria andCopyrightUserIdNotEqualTo(String value) {
            addCriterion("COPYRIGHT_USER_ID <>", value, "copyrightUserId");
            return (Criteria) this;
        }

        public Criteria andCopyrightUserIdGreaterThan(String value) {
            addCriterion("COPYRIGHT_USER_ID >", value, "copyrightUserId");
            return (Criteria) this;
        }

        public Criteria andCopyrightUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("COPYRIGHT_USER_ID >=", value, "copyrightUserId");
            return (Criteria) this;
        }

        public Criteria andCopyrightUserIdLessThan(String value) {
            addCriterion("COPYRIGHT_USER_ID <", value, "copyrightUserId");
            return (Criteria) this;
        }

        public Criteria andCopyrightUserIdLessThanOrEqualTo(String value) {
            addCriterion("COPYRIGHT_USER_ID <=", value, "copyrightUserId");
            return (Criteria) this;
        }

        public Criteria andCopyrightUserIdLike(String value) {
            addCriterion("COPYRIGHT_USER_ID like", value, "copyrightUserId");
            return (Criteria) this;
        }

        public Criteria andCopyrightUserIdNotLike(String value) {
            addCriterion("COPYRIGHT_USER_ID not like", value, "copyrightUserId");
            return (Criteria) this;
        }

        public Criteria andCopyrightUserIdIn(List<String> values) {
            addCriterion("COPYRIGHT_USER_ID in", values, "copyrightUserId");
            return (Criteria) this;
        }

        public Criteria andCopyrightUserIdNotIn(List<String> values) {
            addCriterion("COPYRIGHT_USER_ID not in", values, "copyrightUserId");
            return (Criteria) this;
        }

        public Criteria andCopyrightUserIdBetween(String value1, String value2) {
            addCriterion("COPYRIGHT_USER_ID between", value1, value2, "copyrightUserId");
            return (Criteria) this;
        }

        public Criteria andCopyrightUserIdNotBetween(String value1, String value2) {
            addCriterion("COPYRIGHT_USER_ID not between", value1, value2, "copyrightUserId");
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

        public Criteria andCodeIdIsNull() {
            addCriterion("CODE_ID is null");
            return (Criteria) this;
        }

        public Criteria andCodeIdIsNotNull() {
            addCriterion("CODE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCodeIdEqualTo(String value) {
            addCriterion("CODE_ID =", value, "codeId");
            return (Criteria) this;
        }

        public Criteria andCodeIdNotEqualTo(String value) {
            addCriterion("CODE_ID <>", value, "codeId");
            return (Criteria) this;
        }

        public Criteria andCodeIdGreaterThan(String value) {
            addCriterion("CODE_ID >", value, "codeId");
            return (Criteria) this;
        }

        public Criteria andCodeIdGreaterThanOrEqualTo(String value) {
            addCriterion("CODE_ID >=", value, "codeId");
            return (Criteria) this;
        }

        public Criteria andCodeIdLessThan(String value) {
            addCriterion("CODE_ID <", value, "codeId");
            return (Criteria) this;
        }

        public Criteria andCodeIdLessThanOrEqualTo(String value) {
            addCriterion("CODE_ID <=", value, "codeId");
            return (Criteria) this;
        }

        public Criteria andCodeIdLike(String value) {
            addCriterion("CODE_ID like", value, "codeId");
            return (Criteria) this;
        }

        public Criteria andCodeIdNotLike(String value) {
            addCriterion("CODE_ID not like", value, "codeId");
            return (Criteria) this;
        }

        public Criteria andCodeIdIn(List<String> values) {
            addCriterion("CODE_ID in", values, "codeId");
            return (Criteria) this;
        }

        public Criteria andCodeIdNotIn(List<String> values) {
            addCriterion("CODE_ID not in", values, "codeId");
            return (Criteria) this;
        }

        public Criteria andCodeIdBetween(String value1, String value2) {
            addCriterion("CODE_ID between", value1, value2, "codeId");
            return (Criteria) this;
        }

        public Criteria andCodeIdNotBetween(String value1, String value2) {
            addCriterion("CODE_ID not between", value1, value2, "codeId");
            return (Criteria) this;
        }

        public Criteria andScanCodeLinkIsNull() {
            addCriterion("SCAN_CODE_LINK is null");
            return (Criteria) this;
        }

        public Criteria andScanCodeLinkIsNotNull() {
            addCriterion("SCAN_CODE_LINK is not null");
            return (Criteria) this;
        }

        public Criteria andScanCodeLinkEqualTo(String value) {
            addCriterion("SCAN_CODE_LINK =", value, "scanCodeLink");
            return (Criteria) this;
        }

        public Criteria andScanCodeLinkNotEqualTo(String value) {
            addCriterion("SCAN_CODE_LINK <>", value, "scanCodeLink");
            return (Criteria) this;
        }

        public Criteria andScanCodeLinkGreaterThan(String value) {
            addCriterion("SCAN_CODE_LINK >", value, "scanCodeLink");
            return (Criteria) this;
        }

        public Criteria andScanCodeLinkGreaterThanOrEqualTo(String value) {
            addCriterion("SCAN_CODE_LINK >=", value, "scanCodeLink");
            return (Criteria) this;
        }

        public Criteria andScanCodeLinkLessThan(String value) {
            addCriterion("SCAN_CODE_LINK <", value, "scanCodeLink");
            return (Criteria) this;
        }

        public Criteria andScanCodeLinkLessThanOrEqualTo(String value) {
            addCriterion("SCAN_CODE_LINK <=", value, "scanCodeLink");
            return (Criteria) this;
        }

        public Criteria andScanCodeLinkLike(String value) {
            addCriterion("SCAN_CODE_LINK like", value, "scanCodeLink");
            return (Criteria) this;
        }

        public Criteria andScanCodeLinkNotLike(String value) {
            addCriterion("SCAN_CODE_LINK not like", value, "scanCodeLink");
            return (Criteria) this;
        }

        public Criteria andScanCodeLinkIn(List<String> values) {
            addCriterion("SCAN_CODE_LINK in", values, "scanCodeLink");
            return (Criteria) this;
        }

        public Criteria andScanCodeLinkNotIn(List<String> values) {
            addCriterion("SCAN_CODE_LINK not in", values, "scanCodeLink");
            return (Criteria) this;
        }

        public Criteria andScanCodeLinkBetween(String value1, String value2) {
            addCriterion("SCAN_CODE_LINK between", value1, value2, "scanCodeLink");
            return (Criteria) this;
        }

        public Criteria andScanCodeLinkNotBetween(String value1, String value2) {
            addCriterion("SCAN_CODE_LINK not between", value1, value2, "scanCodeLink");
            return (Criteria) this;
        }

        public Criteria andScanCodeNumIsNull() {
            addCriterion("SCAN_CODE_NUM is null");
            return (Criteria) this;
        }

        public Criteria andScanCodeNumIsNotNull() {
            addCriterion("SCAN_CODE_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andScanCodeNumEqualTo(Integer value) {
            addCriterion("SCAN_CODE_NUM =", value, "scanCodeNum");
            return (Criteria) this;
        }

        public Criteria andScanCodeNumNotEqualTo(Integer value) {
            addCriterion("SCAN_CODE_NUM <>", value, "scanCodeNum");
            return (Criteria) this;
        }

        public Criteria andScanCodeNumGreaterThan(Integer value) {
            addCriterion("SCAN_CODE_NUM >", value, "scanCodeNum");
            return (Criteria) this;
        }

        public Criteria andScanCodeNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("SCAN_CODE_NUM >=", value, "scanCodeNum");
            return (Criteria) this;
        }

        public Criteria andScanCodeNumLessThan(Integer value) {
            addCriterion("SCAN_CODE_NUM <", value, "scanCodeNum");
            return (Criteria) this;
        }

        public Criteria andScanCodeNumLessThanOrEqualTo(Integer value) {
            addCriterion("SCAN_CODE_NUM <=", value, "scanCodeNum");
            return (Criteria) this;
        }

        public Criteria andScanCodeNumIn(List<Integer> values) {
            addCriterion("SCAN_CODE_NUM in", values, "scanCodeNum");
            return (Criteria) this;
        }

        public Criteria andScanCodeNumNotIn(List<Integer> values) {
            addCriterion("SCAN_CODE_NUM not in", values, "scanCodeNum");
            return (Criteria) this;
        }

        public Criteria andScanCodeNumBetween(Integer value1, Integer value2) {
            addCriterion("SCAN_CODE_NUM between", value1, value2, "scanCodeNum");
            return (Criteria) this;
        }

        public Criteria andScanCodeNumNotBetween(Integer value1, Integer value2) {
            addCriterion("SCAN_CODE_NUM not between", value1, value2, "scanCodeNum");
            return (Criteria) this;
        }

        public Criteria andImgLinkIsNull() {
            addCriterion("IMG_LINK is null");
            return (Criteria) this;
        }

        public Criteria andImgLinkIsNotNull() {
            addCriterion("IMG_LINK is not null");
            return (Criteria) this;
        }

        public Criteria andImgLinkEqualTo(String value) {
            addCriterion("IMG_LINK =", value, "imgLink");
            return (Criteria) this;
        }

        public Criteria andImgLinkNotEqualTo(String value) {
            addCriterion("IMG_LINK <>", value, "imgLink");
            return (Criteria) this;
        }

        public Criteria andImgLinkGreaterThan(String value) {
            addCriterion("IMG_LINK >", value, "imgLink");
            return (Criteria) this;
        }

        public Criteria andImgLinkGreaterThanOrEqualTo(String value) {
            addCriterion("IMG_LINK >=", value, "imgLink");
            return (Criteria) this;
        }

        public Criteria andImgLinkLessThan(String value) {
            addCriterion("IMG_LINK <", value, "imgLink");
            return (Criteria) this;
        }

        public Criteria andImgLinkLessThanOrEqualTo(String value) {
            addCriterion("IMG_LINK <=", value, "imgLink");
            return (Criteria) this;
        }

        public Criteria andImgLinkLike(String value) {
            addCriterion("IMG_LINK like", value, "imgLink");
            return (Criteria) this;
        }

        public Criteria andImgLinkNotLike(String value) {
            addCriterion("IMG_LINK not like", value, "imgLink");
            return (Criteria) this;
        }

        public Criteria andImgLinkIn(List<String> values) {
            addCriterion("IMG_LINK in", values, "imgLink");
            return (Criteria) this;
        }

        public Criteria andImgLinkNotIn(List<String> values) {
            addCriterion("IMG_LINK not in", values, "imgLink");
            return (Criteria) this;
        }

        public Criteria andImgLinkBetween(String value1, String value2) {
            addCriterion("IMG_LINK between", value1, value2, "imgLink");
            return (Criteria) this;
        }

        public Criteria andImgLinkNotBetween(String value1, String value2) {
            addCriterion("IMG_LINK not between", value1, value2, "imgLink");
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
     * This class corresponds to the database table yunear_announcemulti
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
     * This class corresponds to the database table yunear_announcemulti
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