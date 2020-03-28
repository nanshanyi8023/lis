package cn.hs.publicclass.table.checkapplicationdetail;

import java.util.ArrayList;
import java.util.List;

public class CheckApplicationDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CheckApplicationDetailExample() {
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

        public Criteria andCheckItemGroupIdIsNull() {
            addCriterion("check_item_group_id is null");
            return (Criteria) this;
        }

        public Criteria andCheckItemGroupIdIsNotNull() {
            addCriterion("check_item_group_id is not null");
            return (Criteria) this;
        }

        public Criteria andCheckItemGroupIdEqualTo(String value) {
            addCriterion("check_item_group_id =", value, "checkItemGroupId");
            return (Criteria) this;
        }

        public Criteria andCheckItemGroupIdNotEqualTo(String value) {
            addCriterion("check_item_group_id <>", value, "checkItemGroupId");
            return (Criteria) this;
        }

        public Criteria andCheckItemGroupIdGreaterThan(String value) {
            addCriterion("check_item_group_id >", value, "checkItemGroupId");
            return (Criteria) this;
        }

        public Criteria andCheckItemGroupIdGreaterThanOrEqualTo(String value) {
            addCriterion("check_item_group_id >=", value, "checkItemGroupId");
            return (Criteria) this;
        }

        public Criteria andCheckItemGroupIdLessThan(String value) {
            addCriterion("check_item_group_id <", value, "checkItemGroupId");
            return (Criteria) this;
        }

        public Criteria andCheckItemGroupIdLessThanOrEqualTo(String value) {
            addCriterion("check_item_group_id <=", value, "checkItemGroupId");
            return (Criteria) this;
        }

        public Criteria andCheckItemGroupIdLike(String value) {
            addCriterion("check_item_group_id like", value, "checkItemGroupId");
            return (Criteria) this;
        }

        public Criteria andCheckItemGroupIdNotLike(String value) {
            addCriterion("check_item_group_id not like", value, "checkItemGroupId");
            return (Criteria) this;
        }

        public Criteria andCheckItemGroupIdIn(List<String> values) {
            addCriterion("check_item_group_id in", values, "checkItemGroupId");
            return (Criteria) this;
        }

        public Criteria andCheckItemGroupIdNotIn(List<String> values) {
            addCriterion("check_item_group_id not in", values, "checkItemGroupId");
            return (Criteria) this;
        }

        public Criteria andCheckItemGroupIdBetween(String value1, String value2) {
            addCriterion("check_item_group_id between", value1, value2, "checkItemGroupId");
            return (Criteria) this;
        }

        public Criteria andCheckItemGroupIdNotBetween(String value1, String value2) {
            addCriterion("check_item_group_id not between", value1, value2, "checkItemGroupId");
            return (Criteria) this;
        }

        public Criteria andCheckItemGroupNameIsNull() {
            addCriterion("check_item_group_name is null");
            return (Criteria) this;
        }

        public Criteria andCheckItemGroupNameIsNotNull() {
            addCriterion("check_item_group_name is not null");
            return (Criteria) this;
        }

        public Criteria andCheckItemGroupNameEqualTo(String value) {
            addCriterion("check_item_group_name =", value, "checkItemGroupName");
            return (Criteria) this;
        }

        public Criteria andCheckItemGroupNameNotEqualTo(String value) {
            addCriterion("check_item_group_name <>", value, "checkItemGroupName");
            return (Criteria) this;
        }

        public Criteria andCheckItemGroupNameGreaterThan(String value) {
            addCriterion("check_item_group_name >", value, "checkItemGroupName");
            return (Criteria) this;
        }

        public Criteria andCheckItemGroupNameGreaterThanOrEqualTo(String value) {
            addCriterion("check_item_group_name >=", value, "checkItemGroupName");
            return (Criteria) this;
        }

        public Criteria andCheckItemGroupNameLessThan(String value) {
            addCriterion("check_item_group_name <", value, "checkItemGroupName");
            return (Criteria) this;
        }

        public Criteria andCheckItemGroupNameLessThanOrEqualTo(String value) {
            addCriterion("check_item_group_name <=", value, "checkItemGroupName");
            return (Criteria) this;
        }

        public Criteria andCheckItemGroupNameLike(String value) {
            addCriterion("check_item_group_name like", value, "checkItemGroupName");
            return (Criteria) this;
        }

        public Criteria andCheckItemGroupNameNotLike(String value) {
            addCriterion("check_item_group_name not like", value, "checkItemGroupName");
            return (Criteria) this;
        }

        public Criteria andCheckItemGroupNameIn(List<String> values) {
            addCriterion("check_item_group_name in", values, "checkItemGroupName");
            return (Criteria) this;
        }

        public Criteria andCheckItemGroupNameNotIn(List<String> values) {
            addCriterion("check_item_group_name not in", values, "checkItemGroupName");
            return (Criteria) this;
        }

        public Criteria andCheckItemGroupNameBetween(String value1, String value2) {
            addCriterion("check_item_group_name between", value1, value2, "checkItemGroupName");
            return (Criteria) this;
        }

        public Criteria andCheckItemGroupNameNotBetween(String value1, String value2) {
            addCriterion("check_item_group_name not between", value1, value2, "checkItemGroupName");
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