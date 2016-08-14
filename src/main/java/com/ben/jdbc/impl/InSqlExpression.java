package com.ben.jdbc.impl;

import com.ben.jdbc.SqlCriteria;
import com.ben.jdbc.SqlCriterion;

public class InSqlExpression implements SqlCriterion{
	private static final long serialVersionUID = 2985308640555356674L;
	private final String propertyName;
	private final Object[] values;

	public InSqlExpression(String propertyName, Object[] values) {
		this.propertyName = propertyName;
		this.values = values;
		// TODO if values is empty throw exception
	}
	
	public String toSql(SqlCriteria criteria) {
		StringBuffer sb = new StringBuffer(WHITE_SPACE+"("+propertyName+WHITE_SPACE+"in(");
		boolean first = true;
		for (int i = 0;i < values.length ; i++) {
			if (first) {
				sb.append("?");
				first = false;
			}else {
				sb.append(",?");
			}
			
		}
		sb.append("))");
		return sb.toString().toLowerCase();
	}

	public Object[] getVale() {
		return values;
	}
}
