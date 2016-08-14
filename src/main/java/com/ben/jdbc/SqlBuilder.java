package com.ben.jdbc;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.ben.jdbc.impl.SqlCriteriaImpl;

public class SqlBuilder implements SqlCriteriaSpecification{
	private static final long serialVersionUID = 8511596764680577715L;
	private final SqlCriteria sqlCriteria;
	private final SqlCriteriaImpl sqlCriteriaImpl;
	public void test() {
		DetachedCriteria dc = DetachedCriteria.forClass(SqlBuilder.class);
		dc.add(Restrictions.eq("", ""));
	}
	private final StringBuffer baseSql; 
	
	protected SqlBuilder(String baseSql) {
		this.baseSql = new StringBuffer(baseSql);
		this.sqlCriteriaImpl = new SqlCriteriaImpl(this.baseSql);
		this.sqlCriteria = sqlCriteriaImpl;
		
	}
	
	protected SqlBuilder(StringBuffer baseSql) {
		this.baseSql = new StringBuffer(baseSql);
		this.sqlCriteriaImpl = new SqlCriteriaImpl(this.baseSql);
		this.sqlCriteria = sqlCriteriaImpl;
	}
	
	public static SqlBuilder baseSql(String baseSql) {
		return new SqlBuilder(baseSql);
	}
	
	public static SqlBuilder baseSql(StringBuffer baseSql) {
		return new SqlBuilder(baseSql);
	}
	
	public SqlBuilder add(SqlCriterion expression) {
		sqlCriteria.add(expression);
		return this;
	}
	
	public SqlBuilder addOrder(SqlOrder order) {
		sqlCriteria.addOrder(order);
		return this;
	}
	
	public String getSql() {
		return sqlCriteria.getSql().toString();
	}
	
	public Object[] getParams () {
		return sqlCriteria.getParams();
	}
}
