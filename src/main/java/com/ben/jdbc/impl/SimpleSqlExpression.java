package com.ben.jdbc.impl;

import com.ben.jdbc.SqlCriteria;
import com.ben.jdbc.SqlCriterion;

public class SimpleSqlExpression implements SqlCriterion {
	private static final long serialVersionUID = -922840476585048234L;
	private final String propertyName;
	private final Object value;
	private final String op;
	
	
	public SimpleSqlExpression(String propertyName, Object value, String op) {
		this.propertyName = propertyName;
		this.value = value;
		this.op = op;
	}


	public String toSql(SqlCriteria criteria) {
		return (WHITE_SPACE+"("+propertyName+getOp()+"?)").toLowerCase();
	}


	@Override
	public String toString() {
		return " "+this.propertyName+getOp()+value;
	}
	
	protected final String getOp() {
		return op;
	}


	public Object[] getVale() {
		return new Object[] {value};
	}
	
	

}
