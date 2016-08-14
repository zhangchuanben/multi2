package com.ben.jdbc.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ben.jdbc.SqlCriteria;
import com.ben.jdbc.SqlCriterion;
import com.ben.jdbc.SqlOrder;
import com.ben.jdbc.utils.CollectionUtils;

public class SqlCriteriaImpl implements SqlCriteria {
	private static final long serialVersionUID = -5042256601995151063L;
	private List<SqlCriterion> expressionEntries = new ArrayList<SqlCriterion>();
	private List<SqlOrder> sqlOrderEntries = new ArrayList<SqlOrder>();
	
	private final StringBuffer baseSql;
	
	
	public SqlCriteriaImpl(StringBuffer baseSql) {
		this.baseSql = new StringBuffer(baseSql);
	}

	public SqlCriteria add(SqlCriterion expression) {
		expressionEntries.add(expression);
		return this;
	}

	public SqlCriteria addOrder(SqlOrder order) {
		sqlOrderEntries.add(order);
		return this;
	}


	public StringBuffer getSql() {
		return new SqlBulder(this).getSql();
	}

	public Object[] getParams() {
		List<Object> params = new ArrayList<Object>();
		if (CollectionUtils.isEmpty(expressionEntries)) 
			return EMPTY_ARR;
		for (SqlCriterion expression : expressionEntries) {
			// 有些表达式没有参数
			if (expression.getVale().length > 0)
				params.addAll(Arrays.asList(expression.getVale()));
		}
		return params.toArray();
	}

	public StringBuffer getBaseSql() {
		return this.baseSql;
	}
	
	private class SqlBulder{
		private SqlCriteriaImpl criteria;
		
		public SqlBulder(SqlCriteriaImpl criteria) {
			this.criteria = criteria;
		}

		public StringBuffer getSql() {
			StringBuffer sb = new StringBuffer();
			sb.append(criteria.getBaseSql());
			buildWheres(sb);
			buildOrders(sb);
			return sb;
		}

		private void buildWheres(StringBuffer sb) {
			sb.append(WHITE_SPACE+"where 1=1");
			for (SqlCriterion expression : criteria.expressionEntries) {
				sb.append(WHITE_SPACE+"and"+expression.toSql(criteria));
			}
		}
		
		private void buildOrders(StringBuffer sb) {
			if (CollectionUtils.isNotEmpty(criteria.sqlOrderEntries)) {
				boolean isFirst = true;
				sb.append(WHITE_SPACE+"order by");
				for (SqlOrder order : criteria.sqlOrderEntries) {
					if (isFirst) {
						sb.append(order.toSql());
						isFirst = false;
					} else {
						sb.append(","+order.toSql());
					}
				}
			}
		}
	}
}
