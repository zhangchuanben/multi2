package com.ben.jdbc;

public interface SqlCriteria extends SqlCriteriaSpecification{
	SqlCriteria add(SqlCriterion expression);
	SqlCriteria addOrder(SqlOrder order);
	StringBuffer getSql();
	Object[] getParams();
	StringBuffer getBaseSql();
}
