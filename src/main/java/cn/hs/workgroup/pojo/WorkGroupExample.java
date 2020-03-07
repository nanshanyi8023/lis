package cn.hs.workgroup.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WorkGroupExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WorkGroupExample() {
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

        public Criteria andWorkGroupIdIsNull() {
            addCriterion("work_group_id is null");
            return (Criteria) this;
        }

        public Criteria andWorkGroupIdIsNotNull() {
            addCriterion("work_group_id is not null");
            return (Criteria) this;
        }

        public Criteria andWorkGroupIdEqualTo(String value) {
            addCriterion("work_group_id =", value, "workGroupId");
            return (Criteria) this;
        }

        public Criteria andWorkGroupIdNotEqualTo(String value) {
            addCriterion("work_group_id <>", value, "workGroupId");
            return (Criteria) this;
        }

        public Criteria andWorkGroupIdGreaterThan(String value) {
            addCriterion("work_group_id >", value, "workGroupId");
            return (Criteria) this;
        }

        public Criteria andWorkGroupIdGreaterThanOrEqualTo(String value) {
            addCriterion("work_group_id >=", value, "workGroupId");
            return (Criteria) this;
        }

        public Criteria andWorkGroupIdLessThan(String value) {
            addCriterion("work_group_id <", value, "workGroupId");
            return (Criteria) this;
        }

        public Criteria andWorkGroupIdLessThanOrEqualTo(String value) {
            addCriterion("work_group_id <=", value, "workGroupId");
            return (Criteria) this;
        }

        public Criteria andWorkGroupIdLike(String value) {
            addCriterion("work_group_id like", value, "workGroupId");
            return (Criteria) this;
        }

        public Criteria andWorkGroupIdNotLike(String value) {
            addCriterion("work_group_id not like", value, "workGroupId");
            return (Criteria) this;
        }

        public Criteria andWorkGroupIdIn(List<String> values) {
            addCriterion("work_group_id in", values, "workGroupId");
            return (Criteria) this;
        }

        public Criteria andWorkGroupIdNotIn(List<String> values) {
            addCriterion("work_group_id not in", values, "workGroupId");
            return (Criteria) this;
        }

        public Criteria andWorkGroupIdBetween(String value1, String value2) {
            addCriterion("work_group_id between", value1, value2, "workGroupId");
            return (Criteria) this;
        }

        public Criteria andWorkGroupIdNotBetween(String value1, String value2) {
            addCriterion("work_group_id not between", value1, value2, "workGroupId");
            return (Criteria) this;
        }

        public Criteria andWorkGroupNameIsNull() {
            addCriterion("work_group_name is null");
            return (Criteria) this;
        }

        public Criteria andWorkGroupNameIsNotNull() {
            addCriterion("work_group_name is not null");
            return (Criteria) this;
        }

        public Criteria andWorkGroupNameEqualTo(String value) {
            addCriterion("work_group_name =", value, "workGroupName");
            return (Criteria) this;
        }

        public Criteria andWorkGroupNameNotEqualTo(String value) {
            addCriterion("work_group_name <>", value, "workGroupName");
            return (Criteria) this;
        }

        public Criteria andWorkGroupNameGreaterThan(String value) {
            addCriterion("work_group_name >", value, "workGroupName");
            return (Criteria) this;
        }

        public Criteria andWorkGroupNameGreaterThanOrEqualTo(String value) {
            addCriterion("work_group_name >=", value, "workGroupName");
            return (Criteria) this;
        }

        public Criteria andWorkGroupNameLessThan(String value) {
            addCriterion("work_group_name <", value, "workGroupName");
            return (Criteria) this;
        }

        public Criteria andWorkGroupNameLessThanOrEqualTo(String value) {
            addCriterion("work_group_name <=", value, "workGroupName");
            return (Criteria) this;
        }

        public Criteria andWorkGroupNameLike(String value) {
            addCriterion("work_group_name like", value, "workGroupName");
            return (Criteria) this;
        }

        public Criteria andWorkGroupNameNotLike(String value) {
            addCriterion("work_group_name not like", value, "workGroupName");
            return (Criteria) this;
        }

        public Criteria andWorkGroupNameIn(List<String> values) {
            addCriterion("work_group_name in", values, "workGroupName");
            return (Criteria) this;
        }

        public Criteria andWorkGroupNameNotIn(List<String> values) {
            addCriterion("work_group_name not in", values, "workGroupName");
            return (Criteria) this;
        }

        public Criteria andWorkGroupNameBetween(String value1, String value2) {
            addCriterion("work_group_name between", value1, value2, "workGroupName");
            return (Criteria) this;
        }

        public Criteria andWorkGroupNameNotBetween(String value1, String value2) {
            addCriterion("work_group_name not between", value1, value2, "workGroupName");
            return (Criteria) this;
        }

        public Criteria andGroupCodeIsNull() {
            addCriterion("group_code is null");
            return (Criteria) this;
        }

        public Criteria andGroupCodeIsNotNull() {
            addCriterion("group_code is not null");
            return (Criteria) this;
        }

        public Criteria andGroupCodeEqualTo(String value) {
            addCriterion("group_code =", value, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeNotEqualTo(String value) {
            addCriterion("group_code <>", value, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeGreaterThan(String value) {
            addCriterion("group_code >", value, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeGreaterThanOrEqualTo(String value) {
            addCriterion("group_code >=", value, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeLessThan(String value) {
            addCriterion("group_code <", value, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeLessThanOrEqualTo(String value) {
            addCriterion("group_code <=", value, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeLike(String value) {
            addCriterion("group_code like", value, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeNotLike(String value) {
            addCriterion("group_code not like", value, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeIn(List<String> values) {
            addCriterion("group_code in", values, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeNotIn(List<String> values) {
            addCriterion("group_code not in", values, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeBetween(String value1, String value2) {
            addCriterion("group_code between", value1, value2, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeNotBetween(String value1, String value2) {
            addCriterion("group_code not between", value1, value2, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIsNull() {
            addCriterion("group_type is null");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIsNotNull() {
            addCriterion("group_type is not null");
            return (Criteria) this;
        }

        public Criteria andGroupTypeEqualTo(String value) {
            addCriterion("group_type =", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNotEqualTo(String value) {
            addCriterion("group_type <>", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeGreaterThan(String value) {
            addCriterion("group_type >", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeGreaterThanOrEqualTo(String value) {
            addCriterion("group_type >=", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeLessThan(String value) {
            addCriterion("group_type <", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeLessThanOrEqualTo(String value) {
            addCriterion("group_type <=", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeLike(String value) {
            addCriterion("group_type like", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNotLike(String value) {
            addCriterion("group_type not like", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIn(List<String> values) {
            addCriterion("group_type in", values, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNotIn(List<String> values) {
            addCriterion("group_type not in", values, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeBetween(String value1, String value2) {
            addCriterion("group_type between", value1, value2, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNotBetween(String value1, String value2) {
            addCriterion("group_type not between", value1, value2, "groupType");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameIsNull() {
            addCriterion("department_name is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameIsNotNull() {
            addCriterion("department_name is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameEqualTo(String value) {
            addCriterion("department_name =", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameNotEqualTo(String value) {
            addCriterion("department_name <>", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameGreaterThan(String value) {
            addCriterion("department_name >", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameGreaterThanOrEqualTo(String value) {
            addCriterion("department_name >=", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameLessThan(String value) {
            addCriterion("department_name <", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameLessThanOrEqualTo(String value) {
            addCriterion("department_name <=", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameLike(String value) {
            addCriterion("department_name like", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameNotLike(String value) {
            addCriterion("department_name not like", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameIn(List<String> values) {
            addCriterion("department_name in", values, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameNotIn(List<String> values) {
            addCriterion("department_name not in", values, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameBetween(String value1, String value2) {
            addCriterion("department_name between", value1, value2, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameNotBetween(String value1, String value2) {
            addCriterion("department_name not between", value1, value2, "departmentName");
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

        public Criteria andTraceLogIsNull() {
            addCriterion("trace_log is null");
            return (Criteria) this;
        }

        public Criteria andTraceLogIsNotNull() {
            addCriterion("trace_log is not null");
            return (Criteria) this;
        }

        public Criteria andTraceLogEqualTo(Date value) {
            addCriterion("trace_log =", value, "traceLog");
            return (Criteria) this;
        }

        public Criteria andTraceLogNotEqualTo(Date value) {
            addCriterion("trace_log <>", value, "traceLog");
            return (Criteria) this;
        }

        public Criteria andTraceLogGreaterThan(Date value) {
            addCriterion("trace_log >", value, "traceLog");
            return (Criteria) this;
        }

        public Criteria andTraceLogGreaterThanOrEqualTo(Date value) {
            addCriterion("trace_log >=", value, "traceLog");
            return (Criteria) this;
        }

        public Criteria andTraceLogLessThan(Date value) {
            addCriterion("trace_log <", value, "traceLog");
            return (Criteria) this;
        }

        public Criteria andTraceLogLessThanOrEqualTo(Date value) {
            addCriterion("trace_log <=", value, "traceLog");
            return (Criteria) this;
        }

        public Criteria andTraceLogIn(List<Date> values) {
            addCriterion("trace_log in", values, "traceLog");
            return (Criteria) this;
        }

        public Criteria andTraceLogNotIn(List<Date> values) {
            addCriterion("trace_log not in", values, "traceLog");
            return (Criteria) this;
        }

        public Criteria andTraceLogBetween(Date value1, Date value2) {
            addCriterion("trace_log between", value1, value2, "traceLog");
            return (Criteria) this;
        }

        public Criteria andTraceLogNotBetween(Date value1, Date value2) {
            addCriterion("trace_log not between", value1, value2, "traceLog");
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