package com.ben.jdbc.impl;

import com.ben.jdbc.SqlCriteria;
import com.ben.jdbc.SqlCriterion;

public class NullSqlExpression implements SqlCriterion {

	private static final long serialVersionUID = -3268078166283376996L;
	private final String propertyName;
	public NullSqlExpression(String propertyName) {
		this.propertyName = propertyName;
	}
	public String toSql(SqlCriteria criteria) {
		return (WHITE_SPACE+"("+propertyName+" is null)").toLowerCase();
	}

	public Object[] getVale() {
		return EMPTY_ARR;
	}

}
