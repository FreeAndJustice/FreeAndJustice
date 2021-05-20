package top.cxh.chat.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FriendsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FriendsExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMyAccountIsNull() {
            addCriterion("my_account is null");
            return (Criteria) this;
        }

        public Criteria andMyAccountIsNotNull() {
            addCriterion("my_account is not null");
            return (Criteria) this;
        }

        public Criteria andMyAccountEqualTo(String value) {
            addCriterion("my_account =", value, "myAccount");
            return (Criteria) this;
        }

        public Criteria andMyAccountNotEqualTo(String value) {
            addCriterion("my_account <>", value, "myAccount");
            return (Criteria) this;
        }

        public Criteria andMyAccountGreaterThan(String value) {
            addCriterion("my_account >", value, "myAccount");
            return (Criteria) this;
        }

        public Criteria andMyAccountGreaterThanOrEqualTo(String value) {
            addCriterion("my_account >=", value, "myAccount");
            return (Criteria) this;
        }

        public Criteria andMyAccountLessThan(String value) {
            addCriterion("my_account <", value, "myAccount");
            return (Criteria) this;
        }

        public Criteria andMyAccountLessThanOrEqualTo(String value) {
            addCriterion("my_account <=", value, "myAccount");
            return (Criteria) this;
        }

        public Criteria andMyAccountLike(String value) {
            addCriterion("my_account like", value, "myAccount");
            return (Criteria) this;
        }

        public Criteria andMyAccountNotLike(String value) {
            addCriterion("my_account not like", value, "myAccount");
            return (Criteria) this;
        }

        public Criteria andMyAccountIn(List<String> values) {
            addCriterion("my_account in", values, "myAccount");
            return (Criteria) this;
        }

        public Criteria andMyAccountNotIn(List<String> values) {
            addCriterion("my_account not in", values, "myAccount");
            return (Criteria) this;
        }

        public Criteria andMyAccountBetween(String value1, String value2) {
            addCriterion("my_account between", value1, value2, "myAccount");
            return (Criteria) this;
        }

        public Criteria andMyAccountNotBetween(String value1, String value2) {
            addCriterion("my_account not between", value1, value2, "myAccount");
            return (Criteria) this;
        }

        public Criteria andFriendAccountIsNull() {
            addCriterion("friend_account is null");
            return (Criteria) this;
        }

        public Criteria andFriendAccountIsNotNull() {
            addCriterion("friend_account is not null");
            return (Criteria) this;
        }

        public Criteria andFriendAccountEqualTo(String value) {
            addCriterion("friend_account =", value, "friendAccount");
            return (Criteria) this;
        }

        public Criteria andFriendAccountNotEqualTo(String value) {
            addCriterion("friend_account <>", value, "friendAccount");
            return (Criteria) this;
        }

        public Criteria andFriendAccountGreaterThan(String value) {
            addCriterion("friend_account >", value, "friendAccount");
            return (Criteria) this;
        }

        public Criteria andFriendAccountGreaterThanOrEqualTo(String value) {
            addCriterion("friend_account >=", value, "friendAccount");
            return (Criteria) this;
        }

        public Criteria andFriendAccountLessThan(String value) {
            addCriterion("friend_account <", value, "friendAccount");
            return (Criteria) this;
        }

        public Criteria andFriendAccountLessThanOrEqualTo(String value) {
            addCriterion("friend_account <=", value, "friendAccount");
            return (Criteria) this;
        }

        public Criteria andFriendAccountLike(String value) {
            addCriterion("friend_account like", value, "friendAccount");
            return (Criteria) this;
        }

        public Criteria andFriendAccountNotLike(String value) {
            addCriterion("friend_account not like", value, "friendAccount");
            return (Criteria) this;
        }

        public Criteria andFriendAccountIn(List<String> values) {
            addCriterion("friend_account in", values, "friendAccount");
            return (Criteria) this;
        }

        public Criteria andFriendAccountNotIn(List<String> values) {
            addCriterion("friend_account not in", values, "friendAccount");
            return (Criteria) this;
        }

        public Criteria andFriendAccountBetween(String value1, String value2) {
            addCriterion("friend_account between", value1, value2, "friendAccount");
            return (Criteria) this;
        }

        public Criteria andFriendAccountNotBetween(String value1, String value2) {
            addCriterion("friend_account not between", value1, value2, "friendAccount");
            return (Criteria) this;
        }

        public Criteria andFriendNameIsNull() {
            addCriterion("friend_name is null");
            return (Criteria) this;
        }

        public Criteria andFriendNameIsNotNull() {
            addCriterion("friend_name is not null");
            return (Criteria) this;
        }

        public Criteria andFriendNameEqualTo(String value) {
            addCriterion("friend_name =", value, "friendName");
            return (Criteria) this;
        }

        public Criteria andFriendNameNotEqualTo(String value) {
            addCriterion("friend_name <>", value, "friendName");
            return (Criteria) this;
        }

        public Criteria andFriendNameGreaterThan(String value) {
            addCriterion("friend_name >", value, "friendName");
            return (Criteria) this;
        }

        public Criteria andFriendNameGreaterThanOrEqualTo(String value) {
            addCriterion("friend_name >=", value, "friendName");
            return (Criteria) this;
        }

        public Criteria andFriendNameLessThan(String value) {
            addCriterion("friend_name <", value, "friendName");
            return (Criteria) this;
        }

        public Criteria andFriendNameLessThanOrEqualTo(String value) {
            addCriterion("friend_name <=", value, "friendName");
            return (Criteria) this;
        }

        public Criteria andFriendNameLike(String value) {
            addCriterion("friend_name like", value, "friendName");
            return (Criteria) this;
        }

        public Criteria andFriendNameNotLike(String value) {
            addCriterion("friend_name not like", value, "friendName");
            return (Criteria) this;
        }

        public Criteria andFriendNameIn(List<String> values) {
            addCriterion("friend_name in", values, "friendName");
            return (Criteria) this;
        }

        public Criteria andFriendNameNotIn(List<String> values) {
            addCriterion("friend_name not in", values, "friendName");
            return (Criteria) this;
        }

        public Criteria andFriendNameBetween(String value1, String value2) {
            addCriterion("friend_name between", value1, value2, "friendName");
            return (Criteria) this;
        }

        public Criteria andFriendNameNotBetween(String value1, String value2) {
            addCriterion("friend_name not between", value1, value2, "friendName");
            return (Criteria) this;
        }

        public Criteria andFriendGroupIsNull() {
            addCriterion("friend_group is null");
            return (Criteria) this;
        }

        public Criteria andFriendGroupIsNotNull() {
            addCriterion("friend_group is not null");
            return (Criteria) this;
        }

        public Criteria andFriendGroupEqualTo(Integer value) {
            addCriterion("friend_group =", value, "friendGroup");
            return (Criteria) this;
        }

        public Criteria andFriendGroupNotEqualTo(Integer value) {
            addCriterion("friend_group <>", value, "friendGroup");
            return (Criteria) this;
        }

        public Criteria andFriendGroupGreaterThan(Integer value) {
            addCriterion("friend_group >", value, "friendGroup");
            return (Criteria) this;
        }

        public Criteria andFriendGroupGreaterThanOrEqualTo(Integer value) {
            addCriterion("friend_group >=", value, "friendGroup");
            return (Criteria) this;
        }

        public Criteria andFriendGroupLessThan(Integer value) {
            addCriterion("friend_group <", value, "friendGroup");
            return (Criteria) this;
        }

        public Criteria andFriendGroupLessThanOrEqualTo(Integer value) {
            addCriterion("friend_group <=", value, "friendGroup");
            return (Criteria) this;
        }

        public Criteria andFriendGroupIn(List<Integer> values) {
            addCriterion("friend_group in", values, "friendGroup");
            return (Criteria) this;
        }

        public Criteria andFriendGroupNotIn(List<Integer> values) {
            addCriterion("friend_group not in", values, "friendGroup");
            return (Criteria) this;
        }

        public Criteria andFriendGroupBetween(Integer value1, Integer value2) {
            addCriterion("friend_group between", value1, value2, "friendGroup");
            return (Criteria) this;
        }

        public Criteria andFriendGroupNotBetween(Integer value1, Integer value2) {
            addCriterion("friend_group not between", value1, value2, "friendGroup");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
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