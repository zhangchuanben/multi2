package com.ben.jdbc;

public class SqlOrder implements SqlCriteriaSpecification{
	private static final long serialVersionUID = -9006438567066600107L;
	private String property;
	private boolean isDesc;
	
	protected SqlOrder(String property, boolean isDesc) {
		this.property = property;
		this.isDesc = isDesc;
	}

	public String toSql() {
		return WHITE_SPACE+this.property.toLowerCase() +WHITE_SPACE+ (isDesc ? "desc" : "asc").toLowerCase();
	}
	
	public static SqlOrder desc (String property) {
		return new SqlOrder(property, true);
	}
	
	public static SqlOrder asc (String property) {
		return new SqlOrder(property, false);
	}
}
