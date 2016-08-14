package com.ben.jdbc;

import java.util.Collection;

import com.ben.jdbc.impl.BetweenSqlExpression;
import com.ben.jdbc.impl.ExistsIOrNotSqlExpression;
import com.ben.jdbc.impl.InSqlExpression;
import com.ben.jdbc.impl.LogicalSqlExpression;
import com.ben.jdbc.impl.NotNullSqlExpression;
import com.ben.jdbc.impl.NullSqlExpression;
import com.ben.jdbc.impl.PropertySqlExpression;
import com.ben.jdbc.impl.SimpleSqlExpression;
import com.ben.jdbc.impl.SqlExpression;

public class SqlFactory {
	public static SimpleSqlExpression eq(String propertyName, Object value) {
		return new SimpleSqlExpression(propertyName, value, "=");
	}
	
	public static SimpleSqlExpression ne(String propertyName, Object value) {
		return new SimpleSqlExpression(propertyName, value, "<>");
	}
	
	public static SimpleSqlExpression gt(String propertyName, Object value) {
		return new SimpleSqlExpression(propertyName, value, ">");
	}
	
	public static SimpleSqlExpression lt(String propertyName, Object value) {
		return new SimpleSqlExpression(propertyName, value, "<");
	}
	
	public static SimpleSqlExpression le(String propertyName, Object value) {
		return new SimpleSqlExpression(propertyName, value, "<=");
	}
	
	public static SimpleSqlExpression ge(String propertyName, Object value) {
		return new SimpleSqlExpression(propertyName, value, ">=");
	}
	
	public static SimpleSqlExpression like(String propertyName, Object value) {
		return new SimpleSqlExpression(propertyName, value, " like ");
	}
	
	public static SimpleSqlExpression like(String propertyName, String value, JdbcMatchMode matchMode) {
		return new SimpleSqlExpression(propertyName, matchMode.toMatchString(value), " like ");
	}
	
	public static PropertySqlExpression eqProperty(String propertyName, String otherPropertyName) {
		return new PropertySqlExpression(propertyName, otherPropertyName, "=");
	}
	public static PropertySqlExpression neProperty(String propertyName, String otherPropertyName) {
		return new PropertySqlExpression(propertyName, otherPropertyName, "<>");
	}
	public static PropertySqlExpression ltProperty(String propertyName, String otherPropertyName) {
		return new PropertySqlExpression(propertyName, otherPropertyName, "<");
	}
	public static PropertySqlExpression leProperty(String propertyName, String otherPropertyName) {
		return new PropertySqlExpression(propertyName, otherPropertyName, "<=");
	}
	public static PropertySqlExpression gtProperty(String propertyName, String otherPropertyName) {
		return new PropertySqlExpression(propertyName, otherPropertyName, ">");
	}
	public static PropertySqlExpression geProperty(String propertyName, String otherPropertyName) {
		return new PropertySqlExpression(propertyName, otherPropertyName, ">=");
	}
	public static LogicalSqlExpression and(SqlCriterion... criterions) {
		return new LogicalSqlExpression("and", criterions);
	}
	
	public static LogicalSqlExpression or(SqlCriterion... criterions) {
		return new LogicalSqlExpression("or", criterions);
	}
	
	public static InSqlExpression inV (String propertyName , Object[] values) {
		return new InSqlExpression(propertyName,values);
	}
	
	public static InSqlExpression in (String propertyName , Collection<?> values) {
		return new InSqlExpression(propertyName,values.toArray());
	}
	
	public static InSqlExpression in (String propertyName , Object...values) {
		return new InSqlExpression(propertyName,values);
	}
	
	public static NullSqlExpression isNull (String propertyName) {
		return new NullSqlExpression(propertyName);
	}
	
	public static NotNullSqlExpression isNotNull (String propertyName) {
		return new NotNullSqlExpression(propertyName);
	}
	
	public static ExistsIOrNotSqlExpression not (SqlCriterion criterion) {
		return new ExistsIOrNotSqlExpression(criterion,"not");
	}
	
	public static ExistsIOrNotSqlExpression exists (SqlCriterion criterion) {
		return new ExistsIOrNotSqlExpression(criterion,"exists");
	}
	
	public static BetweenSqlExpression between (String propertyName, Object lo, Object hi) {
		return new BetweenSqlExpression(propertyName,lo,hi);
	}
	
	public static SqlExpression rawSql (String sql) {
		return new SqlExpression (SqlCriteriaSpecification.EMPTY_ARR,sql);
	}
	
	public static SqlExpression rawSqlV (String sql, Object[] params) {
		return new SqlExpression (params,sql);
	}
	
	public static SqlExpression rawSql (String sql, Collection<?> params) {
		return new SqlExpression (params.toArray(),sql);
	}
	
	public static SqlExpression rawSql (String sql, Object... params) {
		return new SqlExpression (sql, params);
	}
}
