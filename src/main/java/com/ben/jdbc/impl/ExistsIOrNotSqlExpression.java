package com.ben.jdbc.impl;

import com.ben.jdbc.SqlCriteria;
import com.ben.jdbc.SqlCriterion;

public class ExistsIOrNotSqlExpression implements SqlCriterion {

	private static final long serialVersionUID = -3268078166283376996L;
	private final SqlCriterion criterion;
	private final String op;
	public ExistsIOrNotSqlExpression(SqlCriterion criterion, String op) {
		this.criterion = criterion;
		this.op = op;
	}
	public String toSql(SqlCriteria criteria) {
		return (WHITE_SPACE+this.op+criterion.toSql(criteria)).toLowerCase();
	}

	public Object[] getVale() {
		return criterion.getVale();
	}

}
