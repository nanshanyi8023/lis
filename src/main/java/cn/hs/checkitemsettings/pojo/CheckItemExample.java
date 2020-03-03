package cn.hs.checkitemsettings.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CheckItemExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CheckItemExample() {
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

        public Criteria andItemIdIsNull() {
            addCriterion("ITEM_ID is null");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNotNull() {
            addCriterion("ITEM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andItemIdEqualTo(String value) {
            addCriterion("ITEM_ID =", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotEqualTo(String value) {
            addCriterion("ITEM_ID <>", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThan(String value) {
            addCriterion("ITEM_ID >", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThanOrEqualTo(String value) {
            addCriterion("ITEM_ID >=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThan(String value) {
            addCriterion("ITEM_ID <", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThanOrEqualTo(String value) {
            addCriterion("ITEM_ID <=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLike(String value) {
            addCriterion("ITEM_ID like", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotLike(String value) {
            addCriterion("ITEM_ID not like", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdIn(List<String> values) {
            addCriterion("ITEM_ID in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotIn(List<String> values) {
            addCriterion("ITEM_ID not in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdBetween(String value1, String value2) {
            addCriterion("ITEM_ID between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotBetween(String value1, String value2) {
            addCriterion("ITEM_ID not between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andHosnumIsNull() {
            addCriterion("HOSNUM is null");
            return (Criteria) this;
        }

        public Criteria andHosnumIsNotNull() {
            addCriterion("HOSNUM is not null");
            return (Criteria) this;
        }

        public Criteria andHosnumEqualTo(String value) {
            addCriterion("HOSNUM =", value, "hosnum");
            return (Criteria) this;
        }

        public Criteria andHosnumNotEqualTo(String value) {
            addCriterion("HOSNUM <>", value, "hosnum");
            return (Criteria) this;
        }

        public Criteria andHosnumGreaterThan(String value) {
            addCriterion("HOSNUM >", value, "hosnum");
            return (Criteria) this;
        }

        public Criteria andHosnumGreaterThanOrEqualTo(String value) {
            addCriterion("HOSNUM >=", value, "hosnum");
            return (Criteria) this;
        }

        public Criteria andHosnumLessThan(String value) {
            addCriterion("HOSNUM <", value, "hosnum");
            return (Criteria) this;
        }

        public Criteria andHosnumLessThanOrEqualTo(String value) {
            addCriterion("HOSNUM <=", value, "hosnum");
            return (Criteria) this;
        }

        public Criteria andHosnumLike(String value) {
            addCriterion("HOSNUM like", value, "hosnum");
            return (Criteria) this;
        }

        public Criteria andHosnumNotLike(String value) {
            addCriterion("HOSNUM not like", value, "hosnum");
            return (Criteria) this;
        }

        public Criteria andHosnumIn(List<String> values) {
            addCriterion("HOSNUM in", values, "hosnum");
            return (Criteria) this;
        }

        public Criteria andHosnumNotIn(List<String> values) {
            addCriterion("HOSNUM not in", values, "hosnum");
            return (Criteria) this;
        }

        public Criteria andHosnumBetween(String value1, String value2) {
            addCriterion("HOSNUM between", value1, value2, "hosnum");
            return (Criteria) this;
        }

        public Criteria andHosnumNotBetween(String value1, String value2) {
            addCriterion("HOSNUM not between", value1, value2, "hosnum");
            return (Criteria) this;
        }

        public Criteria andItemNameIsNull() {
            addCriterion("ITEM_NAME is null");
            return (Criteria) this;
        }

        public Criteria andItemNameIsNotNull() {
            addCriterion("ITEM_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andItemNameEqualTo(String value) {
            addCriterion("ITEM_NAME =", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotEqualTo(String value) {
            addCriterion("ITEM_NAME <>", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameGreaterThan(String value) {
            addCriterion("ITEM_NAME >", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameGreaterThanOrEqualTo(String value) {
            addCriterion("ITEM_NAME >=", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLessThan(String value) {
            addCriterion("ITEM_NAME <", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLessThanOrEqualTo(String value) {
            addCriterion("ITEM_NAME <=", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLike(String value) {
            addCriterion("ITEM_NAME like", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotLike(String value) {
            addCriterion("ITEM_NAME not like", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameIn(List<String> values) {
            addCriterion("ITEM_NAME in", values, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotIn(List<String> values) {
            addCriterion("ITEM_NAME not in", values, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameBetween(String value1, String value2) {
            addCriterion("ITEM_NAME between", value1, value2, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotBetween(String value1, String value2) {
            addCriterion("ITEM_NAME not between", value1, value2, "itemName");
            return (Criteria) this;
        }

        public Criteria andEnglishAbbreviationsIsNull() {
            addCriterion("ENGLISH_ABBREVIATIONS is null");
            return (Criteria) this;
        }

        public Criteria andEnglishAbbreviationsIsNotNull() {
            addCriterion("ENGLISH_ABBREVIATIONS is not null");
            return (Criteria) this;
        }

        public Criteria andEnglishAbbreviationsEqualTo(String value) {
            addCriterion("ENGLISH_ABBREVIATIONS =", value, "englishAbbreviations");
            return (Criteria) this;
        }

        public Criteria andEnglishAbbreviationsNotEqualTo(String value) {
            addCriterion("ENGLISH_ABBREVIATIONS <>", value, "englishAbbreviations");
            return (Criteria) this;
        }

        public Criteria andEnglishAbbreviationsGreaterThan(String value) {
            addCriterion("ENGLISH_ABBREVIATIONS >", value, "englishAbbreviations");
            return (Criteria) this;
        }

        public Criteria andEnglishAbbreviationsGreaterThanOrEqualTo(String value) {
            addCriterion("ENGLISH_ABBREVIATIONS >=", value, "englishAbbreviations");
            return (Criteria) this;
        }

        public Criteria andEnglishAbbreviationsLessThan(String value) {
            addCriterion("ENGLISH_ABBREVIATIONS <", value, "englishAbbreviations");
            return (Criteria) this;
        }

        public Criteria andEnglishAbbreviationsLessThanOrEqualTo(String value) {
            addCriterion("ENGLISH_ABBREVIATIONS <=", value, "englishAbbreviations");
            return (Criteria) this;
        }

        public Criteria andEnglishAbbreviationsLike(String value) {
            addCriterion("ENGLISH_ABBREVIATIONS like", value, "englishAbbreviations");
            return (Criteria) this;
        }

        public Criteria andEnglishAbbreviationsNotLike(String value) {
            addCriterion("ENGLISH_ABBREVIATIONS not like", value, "englishAbbreviations");
            return (Criteria) this;
        }

        public Criteria andEnglishAbbreviationsIn(List<String> values) {
            addCriterion("ENGLISH_ABBREVIATIONS in", values, "englishAbbreviations");
            return (Criteria) this;
        }

        public Criteria andEnglishAbbreviationsNotIn(List<String> values) {
            addCriterion("ENGLISH_ABBREVIATIONS not in", values, "englishAbbreviations");
            return (Criteria) this;
        }

        public Criteria andEnglishAbbreviationsBetween(String value1, String value2) {
            addCriterion("ENGLISH_ABBREVIATIONS between", value1, value2, "englishAbbreviations");
            return (Criteria) this;
        }

        public Criteria andEnglishAbbreviationsNotBetween(String value1, String value2) {
            addCriterion("ENGLISH_ABBREVIATIONS not between", value1, value2, "englishAbbreviations");
            return (Criteria) this;
        }

        public Criteria andUnitIsNull() {
            addCriterion("UNIT is null");
            return (Criteria) this;
        }

        public Criteria andUnitIsNotNull() {
            addCriterion("UNIT is not null");
            return (Criteria) this;
        }

        public Criteria andUnitEqualTo(String value) {
            addCriterion("UNIT =", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotEqualTo(String value) {
            addCriterion("UNIT <>", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThan(String value) {
            addCriterion("UNIT >", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThanOrEqualTo(String value) {
            addCriterion("UNIT >=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThan(String value) {
            addCriterion("UNIT <", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThanOrEqualTo(String value) {
            addCriterion("UNIT <=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLike(String value) {
            addCriterion("UNIT like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotLike(String value) {
            addCriterion("UNIT not like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitIn(List<String> values) {
            addCriterion("UNIT in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotIn(List<String> values) {
            addCriterion("UNIT not in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitBetween(String value1, String value2) {
            addCriterion("UNIT between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotBetween(String value1, String value2) {
            addCriterion("UNIT not between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andItemTypeIsNull() {
            addCriterion("ITEM_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andItemTypeIsNotNull() {
            addCriterion("ITEM_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andItemTypeEqualTo(String value) {
            addCriterion("ITEM_TYPE =", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeNotEqualTo(String value) {
            addCriterion("ITEM_TYPE <>", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeGreaterThan(String value) {
            addCriterion("ITEM_TYPE >", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ITEM_TYPE >=", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeLessThan(String value) {
            addCriterion("ITEM_TYPE <", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeLessThanOrEqualTo(String value) {
            addCriterion("ITEM_TYPE <=", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeLike(String value) {
            addCriterion("ITEM_TYPE like", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeNotLike(String value) {
            addCriterion("ITEM_TYPE not like", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeIn(List<String> values) {
            addCriterion("ITEM_TYPE in", values, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeNotIn(List<String> values) {
            addCriterion("ITEM_TYPE not in", values, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeBetween(String value1, String value2) {
            addCriterion("ITEM_TYPE between", value1, value2, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeNotBetween(String value1, String value2) {
            addCriterion("ITEM_TYPE not between", value1, value2, "itemType");
            return (Criteria) this;
        }

        public Criteria andPySpellIsNull() {
            addCriterion("PY_SPELL is null");
            return (Criteria) this;
        }

        public Criteria andPySpellIsNotNull() {
            addCriterion("PY_SPELL is not null");
            return (Criteria) this;
        }

        public Criteria andPySpellEqualTo(String value) {
            addCriterion("PY_SPELL =", value, "pySpell");
            return (Criteria) this;
        }

        public Criteria andPySpellNotEqualTo(String value) {
            addCriterion("PY_SPELL <>", value, "pySpell");
            return (Criteria) this;
        }

        public Criteria andPySpellGreaterThan(String value) {
            addCriterion("PY_SPELL >", value, "pySpell");
            return (Criteria) this;
        }

        public Criteria andPySpellGreaterThanOrEqualTo(String value) {
            addCriterion("PY_SPELL >=", value, "pySpell");
            return (Criteria) this;
        }

        public Criteria andPySpellLessThan(String value) {
            addCriterion("PY_SPELL <", value, "pySpell");
            return (Criteria) this;
        }

        public Criteria andPySpellLessThanOrEqualTo(String value) {
            addCriterion("PY_SPELL <=", value, "pySpell");
            return (Criteria) this;
        }

        public Criteria andPySpellLike(String value) {
            addCriterion("PY_SPELL like", value, "pySpell");
            return (Criteria) this;
        }

        public Criteria andPySpellNotLike(String value) {
            addCriterion("PY_SPELL not like", value, "pySpell");
            return (Criteria) this;
        }

        public Criteria andPySpellIn(List<String> values) {
            addCriterion("PY_SPELL in", values, "pySpell");
            return (Criteria) this;
        }

        public Criteria andPySpellNotIn(List<String> values) {
            addCriterion("PY_SPELL not in", values, "pySpell");
            return (Criteria) this;
        }

        public Criteria andPySpellBetween(String value1, String value2) {
            addCriterion("PY_SPELL between", value1, value2, "pySpell");
            return (Criteria) this;
        }

        public Criteria andPySpellNotBetween(String value1, String value2) {
            addCriterion("PY_SPELL not between", value1, value2, "pySpell");
            return (Criteria) this;
        }

        public Criteria andWbSpellIsNull() {
            addCriterion("WB_SPELL is null");
            return (Criteria) this;
        }

        public Criteria andWbSpellIsNotNull() {
            addCriterion("WB_SPELL is not null");
            return (Criteria) this;
        }

        public Criteria andWbSpellEqualTo(String value) {
            addCriterion("WB_SPELL =", value, "wbSpell");
            return (Criteria) this;
        }

        public Criteria andWbSpellNotEqualTo(String value) {
            addCriterion("WB_SPELL <>", value, "wbSpell");
            return (Criteria) this;
        }

        public Criteria andWbSpellGreaterThan(String value) {
            addCriterion("WB_SPELL >", value, "wbSpell");
            return (Criteria) this;
        }

        public Criteria andWbSpellGreaterThanOrEqualTo(String value) {
            addCriterion("WB_SPELL >=", value, "wbSpell");
            return (Criteria) this;
        }

        public Criteria andWbSpellLessThan(String value) {
            addCriterion("WB_SPELL <", value, "wbSpell");
            return (Criteria) this;
        }

        public Criteria andWbSpellLessThanOrEqualTo(String value) {
            addCriterion("WB_SPELL <=", value, "wbSpell");
            return (Criteria) this;
        }

        public Criteria andWbSpellLike(String value) {
            addCriterion("WB_SPELL like", value, "wbSpell");
            return (Criteria) this;
        }

        public Criteria andWbSpellNotLike(String value) {
            addCriterion("WB_SPELL not like", value, "wbSpell");
            return (Criteria) this;
        }

        public Criteria andWbSpellIn(List<String> values) {
            addCriterion("WB_SPELL in", values, "wbSpell");
            return (Criteria) this;
        }

        public Criteria andWbSpellNotIn(List<String> values) {
            addCriterion("WB_SPELL not in", values, "wbSpell");
            return (Criteria) this;
        }

        public Criteria andWbSpellBetween(String value1, String value2) {
            addCriterion("WB_SPELL between", value1, value2, "wbSpell");
            return (Criteria) this;
        }

        public Criteria andWbSpellNotBetween(String value1, String value2) {
            addCriterion("WB_SPELL not between", value1, value2, "wbSpell");
            return (Criteria) this;
        }

        public Criteria andTracelogIsNull() {
            addCriterion("TRACELOG is null");
            return (Criteria) this;
        }

        public Criteria andTracelogIsNotNull() {
            addCriterion("TRACELOG is not null");
            return (Criteria) this;
        }

        public Criteria andTracelogEqualTo(Date value) {
            addCriterion("TRACELOG =", value, "tracelog");
            return (Criteria) this;
        }

        public Criteria andTracelogNotEqualTo(Date value) {
            addCriterion("TRACELOG <>", value, "tracelog");
            return (Criteria) this;
        }

        public Criteria andTracelogGreaterThan(Date value) {
            addCriterion("TRACELOG >", value, "tracelog");
            return (Criteria) this;
        }

        public Criteria andTracelogGreaterThanOrEqualTo(Date value) {
            addCriterion("TRACELOG >=", value, "tracelog");
            return (Criteria) this;
        }

        public Criteria andTracelogLessThan(Date value) {
            addCriterion("TRACELOG <", value, "tracelog");
            return (Criteria) this;
        }

        public Criteria andTracelogLessThanOrEqualTo(Date value) {
            addCriterion("TRACELOG <=", value, "tracelog");
            return (Criteria) this;
        }

        public Criteria andTracelogIn(List<Date> values) {
            addCriterion("TRACELOG in", values, "tracelog");
            return (Criteria) this;
        }

        public Criteria andTracelogNotIn(List<Date> values) {
            addCriterion("TRACELOG not in", values, "tracelog");
            return (Criteria) this;
        }

        public Criteria andTracelogBetween(Date value1, Date value2) {
            addCriterion("TRACELOG between", value1, value2, "tracelog");
            return (Criteria) this;
        }

        public Criteria andTracelogNotBetween(Date value1, Date value2) {
            addCriterion("TRACELOG not between", value1, value2, "tracelog");
            return (Criteria) this;
        }

        public Criteria andOrderNumberIsNull() {
            addCriterion("ORDER_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andOrderNumberIsNotNull() {
            addCriterion("ORDER_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNumberEqualTo(Integer value) {
            addCriterion("ORDER_NUMBER =", value, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberNotEqualTo(Integer value) {
            addCriterion("ORDER_NUMBER <>", value, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberGreaterThan(Integer value) {
            addCriterion("ORDER_NUMBER >", value, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("ORDER_NUMBER >=", value, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberLessThan(Integer value) {
            addCriterion("ORDER_NUMBER <", value, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberLessThanOrEqualTo(Integer value) {
            addCriterion("ORDER_NUMBER <=", value, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberIn(List<Integer> values) {
            addCriterion("ORDER_NUMBER in", values, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberNotIn(List<Integer> values) {
            addCriterion("ORDER_NUMBER not in", values, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberBetween(Integer value1, Integer value2) {
            addCriterion("ORDER_NUMBER between", value1, value2, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("ORDER_NUMBER not between", value1, value2, "orderNumber");
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