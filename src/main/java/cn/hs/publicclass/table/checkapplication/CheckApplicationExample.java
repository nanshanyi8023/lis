package cn.hs.publicclass.table.checkapplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CheckApplicationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CheckApplicationExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        public Criteria andHosnumIsNull() {
            addCriterion("hosnum is null");
            return (Criteria) this;
        }

        public Criteria andHosnumIsNotNull() {
            addCriterion("hosnum is not null");
            return (Criteria) this;
        }

        public Criteria andHosnumEqualTo(String value) {
            addCriterion("hosnum =", value, "hosnum");
            return (Criteria) this;
        }

        public Criteria andHosnumNotEqualTo(String value) {
            addCriterion("hosnum <>", value, "hosnum");
            return (Criteria) this;
        }

        public Criteria andHosnumGreaterThan(String value) {
            addCriterion("hosnum >", value, "hosnum");
            return (Criteria) this;
        }

        public Criteria andHosnumGreaterThanOrEqualTo(String value) {
            addCriterion("hosnum >=", value, "hosnum");
            return (Criteria) this;
        }

        public Criteria andHosnumLessThan(String value) {
            addCriterion("hosnum <", value, "hosnum");
            return (Criteria) this;
        }

        public Criteria andHosnumLessThanOrEqualTo(String value) {
            addCriterion("hosnum <=", value, "hosnum");
            return (Criteria) this;
        }

        public Criteria andHosnumLike(String value) {
            addCriterion("hosnum like", value, "hosnum");
            return (Criteria) this;
        }

        public Criteria andHosnumNotLike(String value) {
            addCriterion("hosnum not like", value, "hosnum");
            return (Criteria) this;
        }

        public Criteria andHosnumIn(List<String> values) {
            addCriterion("hosnum in", values, "hosnum");
            return (Criteria) this;
        }

        public Criteria andHosnumNotIn(List<String> values) {
            addCriterion("hosnum not in", values, "hosnum");
            return (Criteria) this;
        }

        public Criteria andHosnumBetween(String value1, String value2) {
            addCriterion("hosnum between", value1, value2, "hosnum");
            return (Criteria) this;
        }

        public Criteria andHosnumNotBetween(String value1, String value2) {
            addCriterion("hosnum not between", value1, value2, "hosnum");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNull() {
            addCriterion("item_id is null");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNotNull() {
            addCriterion("item_id is not null");
            return (Criteria) this;
        }

        public Criteria andItemIdEqualTo(String value) {
            addCriterion("item_id =", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotEqualTo(String value) {
            addCriterion("item_id <>", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThan(String value) {
            addCriterion("item_id >", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThanOrEqualTo(String value) {
            addCriterion("item_id >=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThan(String value) {
            addCriterion("item_id <", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThanOrEqualTo(String value) {
            addCriterion("item_id <=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLike(String value) {
            addCriterion("item_id like", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotLike(String value) {
            addCriterion("item_id not like", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdIn(List<String> values) {
            addCriterion("item_id in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotIn(List<String> values) {
            addCriterion("item_id not in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdBetween(String value1, String value2) {
            addCriterion("item_id between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotBetween(String value1, String value2) {
            addCriterion("item_id not between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andPatientIdIsNull() {
            addCriterion("patient_id is null");
            return (Criteria) this;
        }

        public Criteria andPatientIdIsNotNull() {
            addCriterion("patient_id is not null");
            return (Criteria) this;
        }

        public Criteria andPatientIdEqualTo(String value) {
            addCriterion("patient_id =", value, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdNotEqualTo(String value) {
            addCriterion("patient_id <>", value, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdGreaterThan(String value) {
            addCriterion("patient_id >", value, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdGreaterThanOrEqualTo(String value) {
            addCriterion("patient_id >=", value, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdLessThan(String value) {
            addCriterion("patient_id <", value, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdLessThanOrEqualTo(String value) {
            addCriterion("patient_id <=", value, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdLike(String value) {
            addCriterion("patient_id like", value, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdNotLike(String value) {
            addCriterion("patient_id not like", value, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdIn(List<String> values) {
            addCriterion("patient_id in", values, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdNotIn(List<String> values) {
            addCriterion("patient_id not in", values, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdBetween(String value1, String value2) {
            addCriterion("patient_id between", value1, value2, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdNotBetween(String value1, String value2) {
            addCriterion("patient_id not between", value1, value2, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientNameIsNull() {
            addCriterion("patient_name is null");
            return (Criteria) this;
        }

        public Criteria andPatientNameIsNotNull() {
            addCriterion("patient_name is not null");
            return (Criteria) this;
        }

        public Criteria andPatientNameEqualTo(String value) {
            addCriterion("patient_name =", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameNotEqualTo(String value) {
            addCriterion("patient_name <>", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameGreaterThan(String value) {
            addCriterion("patient_name >", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameGreaterThanOrEqualTo(String value) {
            addCriterion("patient_name >=", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameLessThan(String value) {
            addCriterion("patient_name <", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameLessThanOrEqualTo(String value) {
            addCriterion("patient_name <=", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameLike(String value) {
            addCriterion("patient_name like", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameNotLike(String value) {
            addCriterion("patient_name not like", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameIn(List<String> values) {
            addCriterion("patient_name in", values, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameNotIn(List<String> values) {
            addCriterion("patient_name not in", values, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameBetween(String value1, String value2) {
            addCriterion("patient_name between", value1, value2, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameNotBetween(String value1, String value2) {
            addCriterion("patient_name not between", value1, value2, "patientName");
            return (Criteria) this;
        }

        public Criteria andCollectionContainerIsNull() {
            addCriterion("collection_container is null");
            return (Criteria) this;
        }

        public Criteria andCollectionContainerIsNotNull() {
            addCriterion("collection_container is not null");
            return (Criteria) this;
        }

        public Criteria andCollectionContainerEqualTo(String value) {
            addCriterion("collection_container =", value, "collectionContainer");
            return (Criteria) this;
        }

        public Criteria andCollectionContainerNotEqualTo(String value) {
            addCriterion("collection_container <>", value, "collectionContainer");
            return (Criteria) this;
        }

        public Criteria andCollectionContainerGreaterThan(String value) {
            addCriterion("collection_container >", value, "collectionContainer");
            return (Criteria) this;
        }

        public Criteria andCollectionContainerGreaterThanOrEqualTo(String value) {
            addCriterion("collection_container >=", value, "collectionContainer");
            return (Criteria) this;
        }

        public Criteria andCollectionContainerLessThan(String value) {
            addCriterion("collection_container <", value, "collectionContainer");
            return (Criteria) this;
        }

        public Criteria andCollectionContainerLessThanOrEqualTo(String value) {
            addCriterion("collection_container <=", value, "collectionContainer");
            return (Criteria) this;
        }

        public Criteria andCollectionContainerLike(String value) {
            addCriterion("collection_container like", value, "collectionContainer");
            return (Criteria) this;
        }

        public Criteria andCollectionContainerNotLike(String value) {
            addCriterion("collection_container not like", value, "collectionContainer");
            return (Criteria) this;
        }

        public Criteria andCollectionContainerIn(List<String> values) {
            addCriterion("collection_container in", values, "collectionContainer");
            return (Criteria) this;
        }

        public Criteria andCollectionContainerNotIn(List<String> values) {
            addCriterion("collection_container not in", values, "collectionContainer");
            return (Criteria) this;
        }

        public Criteria andCollectionContainerBetween(String value1, String value2) {
            addCriterion("collection_container between", value1, value2, "collectionContainer");
            return (Criteria) this;
        }

        public Criteria andCollectionContainerNotBetween(String value1, String value2) {
            addCriterion("collection_container not between", value1, value2, "collectionContainer");
            return (Criteria) this;
        }

        public Criteria andSubmitDepartmentIsNull() {
            addCriterion("submit_department is null");
            return (Criteria) this;
        }

        public Criteria andSubmitDepartmentIsNotNull() {
            addCriterion("submit_department is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitDepartmentEqualTo(String value) {
            addCriterion("submit_department =", value, "submitDepartment");
            return (Criteria) this;
        }

        public Criteria andSubmitDepartmentNotEqualTo(String value) {
            addCriterion("submit_department <>", value, "submitDepartment");
            return (Criteria) this;
        }

        public Criteria andSubmitDepartmentGreaterThan(String value) {
            addCriterion("submit_department >", value, "submitDepartment");
            return (Criteria) this;
        }

        public Criteria andSubmitDepartmentGreaterThanOrEqualTo(String value) {
            addCriterion("submit_department >=", value, "submitDepartment");
            return (Criteria) this;
        }

        public Criteria andSubmitDepartmentLessThan(String value) {
            addCriterion("submit_department <", value, "submitDepartment");
            return (Criteria) this;
        }

        public Criteria andSubmitDepartmentLessThanOrEqualTo(String value) {
            addCriterion("submit_department <=", value, "submitDepartment");
            return (Criteria) this;
        }

        public Criteria andSubmitDepartmentLike(String value) {
            addCriterion("submit_department like", value, "submitDepartment");
            return (Criteria) this;
        }

        public Criteria andSubmitDepartmentNotLike(String value) {
            addCriterion("submit_department not like", value, "submitDepartment");
            return (Criteria) this;
        }

        public Criteria andSubmitDepartmentIn(List<String> values) {
            addCriterion("submit_department in", values, "submitDepartment");
            return (Criteria) this;
        }

        public Criteria andSubmitDepartmentNotIn(List<String> values) {
            addCriterion("submit_department not in", values, "submitDepartment");
            return (Criteria) this;
        }

        public Criteria andSubmitDepartmentBetween(String value1, String value2) {
            addCriterion("submit_department between", value1, value2, "submitDepartment");
            return (Criteria) this;
        }

        public Criteria andSubmitDepartmentNotBetween(String value1, String value2) {
            addCriterion("submit_department not between", value1, value2, "submitDepartment");
            return (Criteria) this;
        }

        public Criteria andIsEmergencyIsNull() {
            addCriterion("is_emergency is null");
            return (Criteria) this;
        }

        public Criteria andIsEmergencyIsNotNull() {
            addCriterion("is_emergency is not null");
            return (Criteria) this;
        }

        public Criteria andIsEmergencyEqualTo(String value) {
            addCriterion("is_emergency =", value, "isEmergency");
            return (Criteria) this;
        }

        public Criteria andIsEmergencyNotEqualTo(String value) {
            addCriterion("is_emergency <>", value, "isEmergency");
            return (Criteria) this;
        }

        public Criteria andIsEmergencyGreaterThan(String value) {
            addCriterion("is_emergency >", value, "isEmergency");
            return (Criteria) this;
        }

        public Criteria andIsEmergencyGreaterThanOrEqualTo(String value) {
            addCriterion("is_emergency >=", value, "isEmergency");
            return (Criteria) this;
        }

        public Criteria andIsEmergencyLessThan(String value) {
            addCriterion("is_emergency <", value, "isEmergency");
            return (Criteria) this;
        }

        public Criteria andIsEmergencyLessThanOrEqualTo(String value) {
            addCriterion("is_emergency <=", value, "isEmergency");
            return (Criteria) this;
        }

        public Criteria andIsEmergencyLike(String value) {
            addCriterion("is_emergency like", value, "isEmergency");
            return (Criteria) this;
        }

        public Criteria andIsEmergencyNotLike(String value) {
            addCriterion("is_emergency not like", value, "isEmergency");
            return (Criteria) this;
        }

        public Criteria andIsEmergencyIn(List<String> values) {
            addCriterion("is_emergency in", values, "isEmergency");
            return (Criteria) this;
        }

        public Criteria andIsEmergencyNotIn(List<String> values) {
            addCriterion("is_emergency not in", values, "isEmergency");
            return (Criteria) this;
        }

        public Criteria andIsEmergencyBetween(String value1, String value2) {
            addCriterion("is_emergency between", value1, value2, "isEmergency");
            return (Criteria) this;
        }

        public Criteria andIsEmergencyNotBetween(String value1, String value2) {
            addCriterion("is_emergency not between", value1, value2, "isEmergency");
            return (Criteria) this;
        }

        public Criteria andSampleTypeIsNull() {
            addCriterion("sample_type is null");
            return (Criteria) this;
        }

        public Criteria andSampleTypeIsNotNull() {
            addCriterion("sample_type is not null");
            return (Criteria) this;
        }

        public Criteria andSampleTypeEqualTo(String value) {
            addCriterion("sample_type =", value, "sampleType");
            return (Criteria) this;
        }

        public Criteria andSampleTypeNotEqualTo(String value) {
            addCriterion("sample_type <>", value, "sampleType");
            return (Criteria) this;
        }

        public Criteria andSampleTypeGreaterThan(String value) {
            addCriterion("sample_type >", value, "sampleType");
            return (Criteria) this;
        }

        public Criteria andSampleTypeGreaterThanOrEqualTo(String value) {
            addCriterion("sample_type >=", value, "sampleType");
            return (Criteria) this;
        }

        public Criteria andSampleTypeLessThan(String value) {
            addCriterion("sample_type <", value, "sampleType");
            return (Criteria) this;
        }

        public Criteria andSampleTypeLessThanOrEqualTo(String value) {
            addCriterion("sample_type <=", value, "sampleType");
            return (Criteria) this;
        }

        public Criteria andSampleTypeLike(String value) {
            addCriterion("sample_type like", value, "sampleType");
            return (Criteria) this;
        }

        public Criteria andSampleTypeNotLike(String value) {
            addCriterion("sample_type not like", value, "sampleType");
            return (Criteria) this;
        }

        public Criteria andSampleTypeIn(List<String> values) {
            addCriterion("sample_type in", values, "sampleType");
            return (Criteria) this;
        }

        public Criteria andSampleTypeNotIn(List<String> values) {
            addCriterion("sample_type not in", values, "sampleType");
            return (Criteria) this;
        }

        public Criteria andSampleTypeBetween(String value1, String value2) {
            addCriterion("sample_type between", value1, value2, "sampleType");
            return (Criteria) this;
        }

        public Criteria andSampleTypeNotBetween(String value1, String value2) {
            addCriterion("sample_type not between", value1, value2, "sampleType");
            return (Criteria) this;
        }

        public Criteria andBillingDoctorIsNull() {
            addCriterion("billing_doctor is null");
            return (Criteria) this;
        }

        public Criteria andBillingDoctorIsNotNull() {
            addCriterion("billing_doctor is not null");
            return (Criteria) this;
        }

        public Criteria andBillingDoctorEqualTo(String value) {
            addCriterion("billing_doctor =", value, "billingDoctor");
            return (Criteria) this;
        }

        public Criteria andBillingDoctorNotEqualTo(String value) {
            addCriterion("billing_doctor <>", value, "billingDoctor");
            return (Criteria) this;
        }

        public Criteria andBillingDoctorGreaterThan(String value) {
            addCriterion("billing_doctor >", value, "billingDoctor");
            return (Criteria) this;
        }

        public Criteria andBillingDoctorGreaterThanOrEqualTo(String value) {
            addCriterion("billing_doctor >=", value, "billingDoctor");
            return (Criteria) this;
        }

        public Criteria andBillingDoctorLessThan(String value) {
            addCriterion("billing_doctor <", value, "billingDoctor");
            return (Criteria) this;
        }

        public Criteria andBillingDoctorLessThanOrEqualTo(String value) {
            addCriterion("billing_doctor <=", value, "billingDoctor");
            return (Criteria) this;
        }

        public Criteria andBillingDoctorLike(String value) {
            addCriterion("billing_doctor like", value, "billingDoctor");
            return (Criteria) this;
        }

        public Criteria andBillingDoctorNotLike(String value) {
            addCriterion("billing_doctor not like", value, "billingDoctor");
            return (Criteria) this;
        }

        public Criteria andBillingDoctorIn(List<String> values) {
            addCriterion("billing_doctor in", values, "billingDoctor");
            return (Criteria) this;
        }

        public Criteria andBillingDoctorNotIn(List<String> values) {
            addCriterion("billing_doctor not in", values, "billingDoctor");
            return (Criteria) this;
        }

        public Criteria andBillingDoctorBetween(String value1, String value2) {
            addCriterion("billing_doctor between", value1, value2, "billingDoctor");
            return (Criteria) this;
        }

        public Criteria andBillingDoctorNotBetween(String value1, String value2) {
            addCriterion("billing_doctor not between", value1, value2, "billingDoctor");
            return (Criteria) this;
        }

        public Criteria andBillingTimeIsNull() {
            addCriterion("billing_time is null");
            return (Criteria) this;
        }

        public Criteria andBillingTimeIsNotNull() {
            addCriterion("billing_time is not null");
            return (Criteria) this;
        }

        public Criteria andBillingTimeEqualTo(Date value) {
            addCriterion("billing_time =", value, "billingTime");
            return (Criteria) this;
        }

        public Criteria andBillingTimeNotEqualTo(Date value) {
            addCriterion("billing_time <>", value, "billingTime");
            return (Criteria) this;
        }

        public Criteria andBillingTimeGreaterThan(Date value) {
            addCriterion("billing_time >", value, "billingTime");
            return (Criteria) this;
        }

        public Criteria andBillingTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("billing_time >=", value, "billingTime");
            return (Criteria) this;
        }

        public Criteria andBillingTimeLessThan(Date value) {
            addCriterion("billing_time <", value, "billingTime");
            return (Criteria) this;
        }

        public Criteria andBillingTimeLessThanOrEqualTo(Date value) {
            addCriterion("billing_time <=", value, "billingTime");
            return (Criteria) this;
        }

        public Criteria andBillingTimeIn(List<Date> values) {
            addCriterion("billing_time in", values, "billingTime");
            return (Criteria) this;
        }

        public Criteria andBillingTimeNotIn(List<Date> values) {
            addCriterion("billing_time not in", values, "billingTime");
            return (Criteria) this;
        }

        public Criteria andBillingTimeBetween(Date value1, Date value2) {
            addCriterion("billing_time between", value1, value2, "billingTime");
            return (Criteria) this;
        }

        public Criteria andBillingTimeNotBetween(Date value1, Date value2) {
            addCriterion("billing_time not between", value1, value2, "billingTime");
            return (Criteria) this;
        }

        public Criteria andBarcodeNumberIsNull() {
            addCriterion("barcode_number is null");
            return (Criteria) this;
        }

        public Criteria andBarcodeNumberIsNotNull() {
            addCriterion("barcode_number is not null");
            return (Criteria) this;
        }

        public Criteria andBarcodeNumberEqualTo(String value) {
            addCriterion("barcode_number =", value, "barcodeNumber");
            return (Criteria) this;
        }

        public Criteria andBarcodeNumberNotEqualTo(String value) {
            addCriterion("barcode_number <>", value, "barcodeNumber");
            return (Criteria) this;
        }

        public Criteria andBarcodeNumberGreaterThan(String value) {
            addCriterion("barcode_number >", value, "barcodeNumber");
            return (Criteria) this;
        }

        public Criteria andBarcodeNumberGreaterThanOrEqualTo(String value) {
            addCriterion("barcode_number >=", value, "barcodeNumber");
            return (Criteria) this;
        }

        public Criteria andBarcodeNumberLessThan(String value) {
            addCriterion("barcode_number <", value, "barcodeNumber");
            return (Criteria) this;
        }

        public Criteria andBarcodeNumberLessThanOrEqualTo(String value) {
            addCriterion("barcode_number <=", value, "barcodeNumber");
            return (Criteria) this;
        }

        public Criteria andBarcodeNumberLike(String value) {
            addCriterion("barcode_number like", value, "barcodeNumber");
            return (Criteria) this;
        }

        public Criteria andBarcodeNumberNotLike(String value) {
            addCriterion("barcode_number not like", value, "barcodeNumber");
            return (Criteria) this;
        }

        public Criteria andBarcodeNumberIn(List<String> values) {
            addCriterion("barcode_number in", values, "barcodeNumber");
            return (Criteria) this;
        }

        public Criteria andBarcodeNumberNotIn(List<String> values) {
            addCriterion("barcode_number not in", values, "barcodeNumber");
            return (Criteria) this;
        }

        public Criteria andBarcodeNumberBetween(String value1, String value2) {
            addCriterion("barcode_number between", value1, value2, "barcodeNumber");
            return (Criteria) this;
        }

        public Criteria andBarcodeNumberNotBetween(String value1, String value2) {
            addCriterion("barcode_number not between", value1, value2, "barcodeNumber");
            return (Criteria) this;
        }

        public Criteria andPrintStatuIsNull() {
            addCriterion("print_statu is null");
            return (Criteria) this;
        }

        public Criteria andPrintStatuIsNotNull() {
            addCriterion("print_statu is not null");
            return (Criteria) this;
        }

        public Criteria andPrintStatuEqualTo(String value) {
            addCriterion("print_statu =", value, "printStatu");
            return (Criteria) this;
        }

        public Criteria andPrintStatuNotEqualTo(String value) {
            addCriterion("print_statu <>", value, "printStatu");
            return (Criteria) this;
        }

        public Criteria andPrintStatuGreaterThan(String value) {
            addCriterion("print_statu >", value, "printStatu");
            return (Criteria) this;
        }

        public Criteria andPrintStatuGreaterThanOrEqualTo(String value) {
            addCriterion("print_statu >=", value, "printStatu");
            return (Criteria) this;
        }

        public Criteria andPrintStatuLessThan(String value) {
            addCriterion("print_statu <", value, "printStatu");
            return (Criteria) this;
        }

        public Criteria andPrintStatuLessThanOrEqualTo(String value) {
            addCriterion("print_statu <=", value, "printStatu");
            return (Criteria) this;
        }

        public Criteria andPrintStatuLike(String value) {
            addCriterion("print_statu like", value, "printStatu");
            return (Criteria) this;
        }

        public Criteria andPrintStatuNotLike(String value) {
            addCriterion("print_statu not like", value, "printStatu");
            return (Criteria) this;
        }

        public Criteria andPrintStatuIn(List<String> values) {
            addCriterion("print_statu in", values, "printStatu");
            return (Criteria) this;
        }

        public Criteria andPrintStatuNotIn(List<String> values) {
            addCriterion("print_statu not in", values, "printStatu");
            return (Criteria) this;
        }

        public Criteria andPrintStatuBetween(String value1, String value2) {
            addCriterion("print_statu between", value1, value2, "printStatu");
            return (Criteria) this;
        }

        public Criteria andPrintStatuNotBetween(String value1, String value2) {
            addCriterion("print_statu not between", value1, value2, "printStatu");
            return (Criteria) this;
        }

        public Criteria andSampleReceptionStatuIsNull() {
            addCriterion("sample_reception_statu is null");
            return (Criteria) this;
        }

        public Criteria andSampleReceptionStatuIsNotNull() {
            addCriterion("sample_reception_statu is not null");
            return (Criteria) this;
        }

        public Criteria andSampleReceptionStatuEqualTo(String value) {
            addCriterion("sample_reception_statu =", value, "sampleReceptionStatu");
            return (Criteria) this;
        }

        public Criteria andSampleReceptionStatuNotEqualTo(String value) {
            addCriterion("sample_reception_statu <>", value, "sampleReceptionStatu");
            return (Criteria) this;
        }

        public Criteria andSampleReceptionStatuGreaterThan(String value) {
            addCriterion("sample_reception_statu >", value, "sampleReceptionStatu");
            return (Criteria) this;
        }

        public Criteria andSampleReceptionStatuGreaterThanOrEqualTo(String value) {
            addCriterion("sample_reception_statu >=", value, "sampleReceptionStatu");
            return (Criteria) this;
        }

        public Criteria andSampleReceptionStatuLessThan(String value) {
            addCriterion("sample_reception_statu <", value, "sampleReceptionStatu");
            return (Criteria) this;
        }

        public Criteria andSampleReceptionStatuLessThanOrEqualTo(String value) {
            addCriterion("sample_reception_statu <=", value, "sampleReceptionStatu");
            return (Criteria) this;
        }

        public Criteria andSampleReceptionStatuLike(String value) {
            addCriterion("sample_reception_statu like", value, "sampleReceptionStatu");
            return (Criteria) this;
        }

        public Criteria andSampleReceptionStatuNotLike(String value) {
            addCriterion("sample_reception_statu not like", value, "sampleReceptionStatu");
            return (Criteria) this;
        }

        public Criteria andSampleReceptionStatuIn(List<String> values) {
            addCriterion("sample_reception_statu in", values, "sampleReceptionStatu");
            return (Criteria) this;
        }

        public Criteria andSampleReceptionStatuNotIn(List<String> values) {
            addCriterion("sample_reception_statu not in", values, "sampleReceptionStatu");
            return (Criteria) this;
        }

        public Criteria andSampleReceptionStatuBetween(String value1, String value2) {
            addCriterion("sample_reception_statu between", value1, value2, "sampleReceptionStatu");
            return (Criteria) this;
        }

        public Criteria andSampleReceptionStatuNotBetween(String value1, String value2) {
            addCriterion("sample_reception_statu not between", value1, value2, "sampleReceptionStatu");
            return (Criteria) this;
        }

        public Criteria andOrderNumberIsNull() {
            addCriterion("order_number is null");
            return (Criteria) this;
        }

        public Criteria andOrderNumberIsNotNull() {
            addCriterion("order_number is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNumberEqualTo(Integer value) {
            addCriterion("order_number =", value, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberNotEqualTo(Integer value) {
            addCriterion("order_number <>", value, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberGreaterThan(Integer value) {
            addCriterion("order_number >", value, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_number >=", value, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberLessThan(Integer value) {
            addCriterion("order_number <", value, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberLessThanOrEqualTo(Integer value) {
            addCriterion("order_number <=", value, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberIn(List<Integer> values) {
            addCriterion("order_number in", values, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberNotIn(List<Integer> values) {
            addCriterion("order_number not in", values, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberBetween(Integer value1, Integer value2) {
            addCriterion("order_number between", value1, value2, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("order_number not between", value1, value2, "orderNumber");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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