package com.ben.jdbc;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"serial","rawtypes", "unchecked"})
public abstract class JdbcMatchMode implements Serializable{
	private final String name;
	private static final Map INSTANCES = new HashMap();
	protected JdbcMatchMode(String name) {
		this.name=name;
	}
	public String toString() {
		return name;
	}
	
	/**
	 * Match the entire string to the pattern
	 */
	public static final JdbcMatchMode EXACT = new JdbcMatchMode("EXACT") {
		public String toMatchString(String pattern) {
			return pattern;
		}
	};
	
	/**
	 * Match the start of the string to the pattern
	 */
	public static final JdbcMatchMode START = new JdbcMatchMode("START") {
		public String toMatchString(String pattern) {
			return pattern + '%';
		}
	};
	
	/**
	 * Match the end of the string to the pattern
	 */
	public static final JdbcMatchMode END = new JdbcMatchMode("END") {
		public String toMatchString(String pattern) {
			return '%' + pattern;
		}
	};

	/**
	 * Match the pattern anywhere in the string
	 */
	public static final JdbcMatchMode ANYWHERE = new JdbcMatchMode("ANYWHERE") {
		public String toMatchString(String pattern) {
			return '%' + pattern + '%';
		}
	};

	static {
		INSTANCES.put( EXACT.name, EXACT );
		INSTANCES.put( END.name, END );
		INSTANCES.put( START.name, START );
		INSTANCES.put( ANYWHERE.name, ANYWHERE );
	}

	private Object readResolve() {
		return INSTANCES.get(name);
	}

	/**
	 * convert the pattern, by appending/prepending "%"
	 */
	public abstract String toMatchString(String pattern);
}
