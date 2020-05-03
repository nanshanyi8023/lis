package cn.hs.checkitem.pojo;

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

        public Criteria andItemNameIsNull() {
            addCriterion("item_name is null");
            return (Criteria) this;
        }

        public Criteria andItemNameIsNotNull() {
            addCriterion("item_name is not null");
            return (Criteria) this;
        }

        public Criteria andItemNameEqualTo(String value) {
            addCriterion("item_name =", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotEqualTo(String value) {
            addCriterion("item_name <>", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameGreaterThan(String value) {
            addCriterion("item_name >", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameGreaterThanOrEqualTo(String value) {
            addCriterion("item_name >=", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLessThan(String value) {
            addCriterion("item_name <", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLessThanOrEqualTo(String value) {
            addCriterion("item_name <=", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLike(String value) {
            addCriterion("item_name like", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotLike(String value) {
            addCriterion("item_name not like", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameIn(List<String> values) {
            addCriterion("item_name in", values, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotIn(List<String> values) {
            addCriterion("item_name not in", values, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameBetween(String value1, String value2) {
            addCriterion("item_name between", value1, value2, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotBetween(String value1, String value2) {
            addCriterion("item_name not between", value1, value2, "itemName");
            return (Criteria) this;
        }

        public Criteria andEnglishAbbreviationIsNull() {
            addCriterion("english_abbreviation is null");
            return (Criteria) this;
        }

        public Criteria andEnglishAbbreviationIsNotNull() {
            addCriterion("english_abbreviation is not null");
            return (Criteria) this;
        }

        public Criteria andEnglishAbbreviationEqualTo(String value) {
            addCriterion("english_abbreviation =", value, "englishAbbreviation");
            return (Criteria) this;
        }

        public Criteria andEnglishAbbreviationNotEqualTo(String value) {
            addCriterion("english_abbreviation <>", value, "englishAbbreviation");
            return (Criteria) this;
        }

        public Criteria andEnglishAbbreviationGreaterThan(String value) {
            addCriterion("english_abbreviation >", value, "englishAbbreviation");
            return (Criteria) this;
        }

        public Criteria andEnglishAbbreviationGreaterThanOrEqualTo(String value) {
            addCriterion("english_abbreviation >=", value, "englishAbbreviation");
            return (Criteria) this;
        }

        public Criteria andEnglishAbbreviationLessThan(String value) {
            addCriterion("english_abbreviation <", value, "englishAbbreviation");
            return (Criteria) this;
        }

        public Criteria andEnglishAbbreviationLessThanOrEqualTo(String value) {
            addCriterion("english_abbreviation <=", value, "englishAbbreviation");
            return (Criteria) this;
        }

        public Criteria andEnglishAbbreviationLike(String value) {
            addCriterion("english_abbreviation like", value, "englishAbbreviation");
            return (Criteria) this;
        }

        public Criteria andEnglishAbbreviationNotLike(String value) {
            addCriterion("english_abbreviation not like", value, "englishAbbreviation");
            return (Criteria) this;
        }

        public Criteria andEnglishAbbreviationIn(List<String> values) {
            addCriterion("english_abbreviation in", values, "englishAbbreviation");
            return (Criteria) this;
        }

        public Criteria andEnglishAbbreviationNotIn(List<String> values) {
            addCriterion("english_abbreviation not in", values, "englishAbbreviation");
            return (Criteria) this;
        }

        public Criteria andEnglishAbbreviationBetween(String value1, String value2) {
            addCriterion("english_abbreviation between", value1, value2, "englishAbbreviation");
            return (Criteria) this;
        }

        public Criteria andEnglishAbbreviationNotBetween(String value1, String value2) {
            addCriterion("english_abbreviation not between", value1, value2, "englishAbbreviation");
            return (Criteria) this;
        }

        public Criteria andUnitIsNull() {
            addCriterion("unit is null");
            return (Criteria) this;
        }

        public Criteria andUnitIsNotNull() {
            addCriterion("unit is not null");
            return (Criteria) this;
        }

        public Criteria andUnitEqualTo(String value) {
            addCriterion("unit =", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotEqualTo(String value) {
            addCriterion("unit <>", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThan(String value) {
            addCriterion("unit >", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThanOrEqualTo(String value) {
            addCriterion("unit >=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThan(String value) {
            addCriterion("unit <", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThanOrEqualTo(String value) {
            addCriterion("unit <=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLike(String value) {
            addCriterion("unit like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotLike(String value) {
            addCriterion("unit not like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitIn(List<String> values) {
            addCriterion("unit in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotIn(List<String> values) {
            addCriterion("unit not in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitBetween(String value1, String value2) {
            addCriterion("unit between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotBetween(String value1, String value2) {
            addCriterion("unit not between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andItemTypeIsNull() {
            addCriterion("item_type is null");
            return (Criteria) this;
        }

        public Criteria andItemTypeIsNotNull() {
            addCriterion("item_type is not null");
            return (Criteria) this;
        }

        public Criteria andItemTypeEqualTo(String value) {
            addCriterion("item_type =", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeNotEqualTo(String value) {
            addCriterion("item_type <>", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeGreaterThan(String value) {
            addCriterion("item_type >", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeGreaterThanOrEqualTo(String value) {
            addCriterion("item_type >=", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeLessThan(String value) {
            addCriterion("item_type <", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeLessThanOrEqualTo(String value) {
            addCriterion("item_type <=", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeLike(String value) {
            addCriterion("item_type like", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeNotLike(String value) {
            addCriterion("item_type not like", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeIn(List<String> values) {
            addCriterion("item_type in", values, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeNotIn(List<String> values) {
            addCriterion("item_type not in", values, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeBetween(String value1, String value2) {
            addCriterion("item_type between", value1, value2, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeNotBetween(String value1, String value2) {
            addCriterion("item_type not between", value1, value2, "itemType");
            return (Criteria) this;
        }

        public Criteria andLowerReferenceValueIsNull() {
            addCriterion("lower_reference_value is null");
            return (Criteria) this;
        }

        public Criteria andLowerReferenceValueIsNotNull() {
            addCriterion("lower_reference_value is not null");
            return (Criteria) this;
        }

        public Criteria andLowerReferenceValueEqualTo(Double value) {
            addCriterion("lower_reference_value =", value, "lowerReferenceValue");
            return (Criteria) this;
        }

        public Criteria andLowerReferenceValueNotEqualTo(Double value) {
            addCriterion("lower_reference_value <>", value, "lowerReferenceValue");
            return (Criteria) this;
        }

        public Criteria andLowerReferenceValueGreaterThan(Double value) {
            addCriterion("lower_reference_value >", value, "lowerReferenceValue");
            return (Criteria) this;
        }

        public Criteria andLowerReferenceValueGreaterThanOrEqualTo(Double value) {
            addCriterion("lower_reference_value >=", value, "lowerReferenceValue");
            return (Criteria) this;
        }

        public Criteria andLowerReferenceValueLessThan(Double value) {
            addCriterion("lower_reference_value <", value, "lowerReferenceValue");
            return (Criteria) this;
        }

        public Criteria andLowerReferenceValueLessThanOrEqualTo(Double value) {
            addCriterion("lower_reference_value <=", value, "lowerReferenceValue");
            return (Criteria) this;
        }

        public Criteria andLowerReferenceValueIn(List<Double> values) {
            addCriterion("lower_reference_value in", values, "lowerReferenceValue");
            return (Criteria) this;
        }

        public Criteria andLowerReferenceValueNotIn(List<Double> values) {
            addCriterion("lower_reference_value not in", values, "lowerReferenceValue");
            return (Criteria) this;
        }

        public Criteria andLowerReferenceValueBetween(Double value1, Double value2) {
            addCriterion("lower_reference_value between", value1, value2, "lowerReferenceValue");
            return (Criteria) this;
        }

        public Criteria andLowerReferenceValueNotBetween(Double value1, Double value2) {
            addCriterion("lower_reference_value not between", value1, value2, "lowerReferenceValue");
            return (Criteria) this;
        }

        public Criteria andUpperReferenceValueIsNull() {
            addCriterion("upper_reference_value is null");
            return (Criteria) this;
        }

        public Criteria andUpperReferenceValueIsNotNull() {
            addCriterion("upper_reference_value is not null");
            return (Criteria) this;
        }

        public Criteria andUpperReferenceValueEqualTo(Double value) {
            addCriterion("upper_reference_value =", value, "upperReferenceValue");
            return (Criteria) this;
        }

        public Criteria andUpperReferenceValueNotEqualTo(Double value) {
            addCriterion("upper_reference_value <>", value, "upperReferenceValue");
            return (Criteria) this;
        }

        public Criteria andUpperReferenceValueGreaterThan(Double value) {
            addCriterion("upper_reference_value >", value, "upperReferenceValue");
            return (Criteria) this;
        }

        public Criteria andUpperReferenceValueGreaterThanOrEqualTo(Double value) {
            addCriterion("upper_reference_value >=", value, "upperReferenceValue");
            return (Criteria) this;
        }

        public Criteria andUpperReferenceValueLessThan(Double value) {
            addCriterion("upper_reference_value <", value, "upperReferenceValue");
            return (Criteria) this;
        }

        public Criteria andUpperReferenceValueLessThanOrEqualTo(Double value) {
            addCriterion("upper_reference_value <=", value, "upperReferenceValue");
            return (Criteria) this;
        }

        public Criteria andUpperReferenceValueIn(List<Double> values) {
            addCriterion("upper_reference_value in", values, "upperReferenceValue");
            return (Criteria) this;
        }

        public Criteria andUpperReferenceValueNotIn(List<Double> values) {
            addCriterion("upper_reference_value not in", values, "upperReferenceValue");
            return (Criteria) this;
        }

        public Criteria andUpperReferenceValueBetween(Double value1, Double value2) {
            addCriterion("upper_reference_value between", value1, value2, "upperReferenceValue");
            return (Criteria) this;
        }

        public Criteria andUpperReferenceValueNotBetween(Double value1, Double value2) {
            addCriterion("upper_reference_value not between", value1, value2, "upperReferenceValue");
            return (Criteria) this;
        }

        public Criteria andReferenceValueIsNull() {
            addCriterion("reference_value is null");
            return (Criteria) this;
        }

        public Criteria andReferenceValueIsNotNull() {
            addCriterion("reference_value is not null");
            return (Criteria) this;
        }

        public Criteria andReferenceValueEqualTo(String value) {
            addCriterion("reference_value =", value, "referenceValue");
            return (Criteria) this;
        }

        public Criteria andReferenceValueNotEqualTo(String value) {
            addCriterion("reference_value <>", value, "referenceValue");
            return (Criteria) this;
        }

        public Criteria andReferenceValueGreaterThan(String value) {
            addCriterion("reference_value >", value, "referenceValue");
            return (Criteria) this;
        }

        public Criteria andReferenceValueGreaterThanOrEqualTo(String value) {
            addCriterion("reference_value >=", value, "referenceValue");
            return (Criteria) this;
        }

        public Criteria andReferenceValueLessThan(String value) {
            addCriterion("reference_value <", value, "referenceValue");
            return (Criteria) this;
        }

        public Criteria andReferenceValueLessThanOrEqualTo(String value) {
            addCriterion("reference_value <=", value, "referenceValue");
            return (Criteria) this;
        }

        public Criteria andReferenceValueLike(String value) {
            addCriterion("reference_value like", value, "referenceValue");
            return (Criteria) this;
        }

        public Criteria andReferenceValueNotLike(String value) {
            addCriterion("reference_value not like", value, "referenceValue");
            return (Criteria) this;
        }

        public Criteria andReferenceValueIn(List<String> values) {
            addCriterion("reference_value in", values, "referenceValue");
            return (Criteria) this;
        }

        public Criteria andReferenceValueNotIn(List<String> values) {
            addCriterion("reference_value not in", values, "referenceValue");
            return (Criteria) this;
        }

        public Criteria andReferenceValueBetween(String value1, String value2) {
            addCriterion("reference_value between", value1, value2, "referenceValue");
            return (Criteria) this;
        }

        public Criteria andReferenceValueNotBetween(String value1, String value2) {
            addCriterion("reference_value not between", value1, value2, "referenceValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueIsNull() {
            addCriterion("default_value is null");
            return (Criteria) this;
        }

        public Criteria andDefaultValueIsNotNull() {
            addCriterion("default_value is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultValueEqualTo(String value) {
            addCriterion("default_value =", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueNotEqualTo(String value) {
            addCriterion("default_value <>", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueGreaterThan(String value) {
            addCriterion("default_value >", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueGreaterThanOrEqualTo(String value) {
            addCriterion("default_value >=", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueLessThan(String value) {
            addCriterion("default_value <", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueLessThanOrEqualTo(String value) {
            addCriterion("default_value <=", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueLike(String value) {
            addCriterion("default_value like", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueNotLike(String value) {
            addCriterion("default_value not like", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueIn(List<String> values) {
            addCriterion("default_value in", values, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueNotIn(List<String> values) {
            addCriterion("default_value not in", values, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueBetween(String value1, String value2) {
            addCriterion("default_value between", value1, value2, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueNotBetween(String value1, String value2) {
            addCriterion("default_value not between", value1, value2, "defaultValue");
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