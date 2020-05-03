package cn.hs.publicclass.table.checkresult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CheckResultExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CheckResultExample() {
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

        public Criteria andCheckApplicationIdIsNull() {
            addCriterion("check_application_id is null");
            return (Criteria) this;
        }

        public Criteria andCheckApplicationIdIsNotNull() {
            addCriterion("check_application_id is not null");
            return (Criteria) this;
        }

        public Criteria andCheckApplicationIdEqualTo(String value) {
            addCriterion("check_application_id =", value, "checkApplicationId");
            return (Criteria) this;
        }

        public Criteria andCheckApplicationIdNotEqualTo(String value) {
            addCriterion("check_application_id <>", value, "checkApplicationId");
            return (Criteria) this;
        }

        public Criteria andCheckApplicationIdGreaterThan(String value) {
            addCriterion("check_application_id >", value, "checkApplicationId");
            return (Criteria) this;
        }

        public Criteria andCheckApplicationIdGreaterThanOrEqualTo(String value) {
            addCriterion("check_application_id >=", value, "checkApplicationId");
            return (Criteria) this;
        }

        public Criteria andCheckApplicationIdLessThan(String value) {
            addCriterion("check_application_id <", value, "checkApplicationId");
            return (Criteria) this;
        }

        public Criteria andCheckApplicationIdLessThanOrEqualTo(String value) {
            addCriterion("check_application_id <=", value, "checkApplicationId");
            return (Criteria) this;
        }

        public Criteria andCheckApplicationIdLike(String value) {
            addCriterion("check_application_id like", value, "checkApplicationId");
            return (Criteria) this;
        }

        public Criteria andCheckApplicationIdNotLike(String value) {
            addCriterion("check_application_id not like", value, "checkApplicationId");
            return (Criteria) this;
        }

        public Criteria andCheckApplicationIdIn(List<String> values) {
            addCriterion("check_application_id in", values, "checkApplicationId");
            return (Criteria) this;
        }

        public Criteria andCheckApplicationIdNotIn(List<String> values) {
            addCriterion("check_application_id not in", values, "checkApplicationId");
            return (Criteria) this;
        }

        public Criteria andCheckApplicationIdBetween(String value1, String value2) {
            addCriterion("check_application_id between", value1, value2, "checkApplicationId");
            return (Criteria) this;
        }

        public Criteria andCheckApplicationIdNotBetween(String value1, String value2) {
            addCriterion("check_application_id not between", value1, value2, "checkApplicationId");
            return (Criteria) this;
        }

        public Criteria andCheckItemIdIsNull() {
            addCriterion("check_item_id is null");
            return (Criteria) this;
        }

        public Criteria andCheckItemIdIsNotNull() {
            addCriterion("check_item_id is not null");
            return (Criteria) this;
        }

        public Criteria andCheckItemIdEqualTo(String value) {
            addCriterion("check_item_id =", value, "checkItemId");
            return (Criteria) this;
        }

        public Criteria andCheckItemIdNotEqualTo(String value) {
            addCriterion("check_item_id <>", value, "checkItemId");
            return (Criteria) this;
        }

        public Criteria andCheckItemIdGreaterThan(String value) {
            addCriterion("check_item_id >", value, "checkItemId");
            return (Criteria) this;
        }

        public Criteria andCheckItemIdGreaterThanOrEqualTo(String value) {
            addCriterion("check_item_id >=", value, "checkItemId");
            return (Criteria) this;
        }

        public Criteria andCheckItemIdLessThan(String value) {
            addCriterion("check_item_id <", value, "checkItemId");
            return (Criteria) this;
        }

        public Criteria andCheckItemIdLessThanOrEqualTo(String value) {
            addCriterion("check_item_id <=", value, "checkItemId");
            return (Criteria) this;
        }

        public Criteria andCheckItemIdLike(String value) {
            addCriterion("check_item_id like", value, "checkItemId");
            return (Criteria) this;
        }

        public Criteria andCheckItemIdNotLike(String value) {
            addCriterion("check_item_id not like", value, "checkItemId");
            return (Criteria) this;
        }

        public Criteria andCheckItemIdIn(List<String> values) {
            addCriterion("check_item_id in", values, "checkItemId");
            return (Criteria) this;
        }

        public Criteria andCheckItemIdNotIn(List<String> values) {
            addCriterion("check_item_id not in", values, "checkItemId");
            return (Criteria) this;
        }

        public Criteria andCheckItemIdBetween(String value1, String value2) {
            addCriterion("check_item_id between", value1, value2, "checkItemId");
            return (Criteria) this;
        }

        public Criteria andCheckItemIdNotBetween(String value1, String value2) {
            addCriterion("check_item_id not between", value1, value2, "checkItemId");
            return (Criteria) this;
        }

        public Criteria andResultIsNull() {
            addCriterion("result is null");
            return (Criteria) this;
        }

        public Criteria andResultIsNotNull() {
            addCriterion("result is not null");
            return (Criteria) this;
        }

        public Criteria andResultEqualTo(String value) {
            addCriterion("result =", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotEqualTo(String value) {
            addCriterion("result <>", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultGreaterThan(String value) {
            addCriterion("result >", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultGreaterThanOrEqualTo(String value) {
            addCriterion("result >=", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLessThan(String value) {
            addCriterion("result <", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLessThanOrEqualTo(String value) {
            addCriterion("result <=", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLike(String value) {
            addCriterion("result like", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotLike(String value) {
            addCriterion("result not like", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultIn(List<String> values) {
            addCriterion("result in", values, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotIn(List<String> values) {
            addCriterion("result not in", values, "result");
            return (Criteria) this;
        }

        public Criteria andResultBetween(String value1, String value2) {
            addCriterion("result between", value1, value2, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotBetween(String value1, String value2) {
            addCriterion("result not between", value1, value2, "result");
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