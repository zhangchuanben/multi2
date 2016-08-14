package com.ben.jdbc;


public interface SqlCriterion extends SqlCriteriaSpecification{
	String toSql(SqlCriteria criteria);
	Object[] getVale();
}
