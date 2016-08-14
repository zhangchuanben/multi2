package com.ben.jdbc.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ben.jdbc.SqlCriteria;
import com.ben.jdbc.SqlCriterion;

public class LogicalSqlExpression implements SqlCriterion {
	private static final long serialVersionUID = -2742278632419282816L;
	private final List<SqlCriterion> sqlCretions = new ArrayList<SqlCriterion>();
	
	private final String op;
	
	public LogicalSqlExpression(String op, SqlCriterion...criterionsArr) {
		sqlCretions.addAll(Arrays.asList(criterionsArr));
		this.op = op;
		//TODO throw exception if the criterionsArr is empty
	}
	
	// TODO 这里可能会出现多余的空格
	public String toSql(SqlCriteria criteria) {
		StringBuffer sb = new StringBuffer(WHITE_SPACE+"(");
		boolean start = true;
		for (SqlCriterion sqlCretion : sqlCretions) {
			if (start) {
				sb.append(sqlCretion.toSql(criteria));
				start = false;
			} else {
				sb.append(WHITE_SPACE+getOp()+sqlCretion.toSql(criteria));
			}
		}
		sb.append(")");
		return sb.toString();
	}

	// (name = ? or name1=? or (name3=? and name4=?))
	public Object[] getVale() {
		List<Object> params = new ArrayList<Object>();
		for (SqlCriterion sqlCretion : sqlCretions) {
			if (sqlCretion.getVale().length > 0) {
				params.addAll(Arrays.asList(sqlCretion.getVale()));
			}
		}
		return params.toArray();
	}
	
	public String getOp() {
		return op;
	}

}
 