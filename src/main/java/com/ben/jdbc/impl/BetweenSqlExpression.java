package com.ben.jdbc.impl;

import com.ben.jdbc.SqlCriteria;
import com.ben.jdbc.SqlCriterion;

public class BetweenSqlExpression implements SqlCriterion {

	private static final long serialVersionUID = -3268078166283376996L;
	private final String propertyName;
	private final Object lo;
	private final Object hi;
	public BetweenSqlExpression(String propertyName, Object lo, Object hi) {
		this.propertyName = propertyName;
		this.hi = hi;
		this.lo = lo;
	}
	public String toSql(SqlCriteria criteria) {
		return (WHITE_SPACE+"("+propertyName+" between ? and ?)").toLowerCase();
	}

	public Object[] getVale() {
		return new Object[]{lo,hi};
	}

}
