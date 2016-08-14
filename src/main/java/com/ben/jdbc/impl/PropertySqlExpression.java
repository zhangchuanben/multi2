package com.ben.jdbc.impl;

import com.ben.jdbc.SqlCriteria;
import com.ben.jdbc.SqlCriterion;

public class PropertySqlExpression implements SqlCriterion {
	private static final long serialVersionUID = 1883369127762598248L;
	private final String propertyName;
	private final String otherPropertyName;
	private final String op;
	
	public PropertySqlExpression(String propertyName, String otherPropertyName,
			String op) {
		this.propertyName = propertyName;
		this.otherPropertyName = otherPropertyName;
		this.op = op;
	}

	public String toSql(SqlCriteria criteria) {
		return (WHITE_SPACE+"("+propertyName+getOp()+otherPropertyName+")").toLowerCase();
	}

	public Object[] getVale() {
		return EMPTY_ARR;
	}
	
	public String toString() {
		return propertyName + getOp() + otherPropertyName;
	}

	public String getOp() {
		return op;
	}

}
