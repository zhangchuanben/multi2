package com.ben.jdbc.impl;

import com.ben.jdbc.SqlCriteria;
import com.ben.jdbc.SqlCriterion;

public class SqlExpression implements SqlCriterion {
	private static final long serialVersionUID = 7634269822015490884L;
	private final String sql;
	private final Object[] params;
	
	public SqlExpression(String sql, Object... params) {
		this.sql = sql;
		this.params = params;
	}
	
	public SqlExpression(Object[] params, String sql) {
		this.sql = sql;
		this.params = params;
	}

	public String toSql(SqlCriteria criteria) {
		return (WHITE_SPACE+"("+sql+")").toLowerCase();
	}

	public Object[] getVale() {
		return this.params;
	}

}
