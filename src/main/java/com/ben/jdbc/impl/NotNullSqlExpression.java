package com.ben.jdbc.impl;

import com.ben.jdbc.SqlCriteria;
import com.ben.jdbc.SqlCriterion;

public class NotNullSqlExpression implements SqlCriterion {

	private static final long serialVersionUID = -3268078166283376996L;
	private final String propertyName;
	public NotNullSqlExpression(String propertyName) {
		this.propertyName = propertyName;
	}
	public String toSql(SqlCriteria criteria) {
		return (WHITE_SPACE+"("+propertyName+" is not null)").toLowerCase();
	}

	public Object[] getVale() {
		return EMPTY_ARR;
	}

}
