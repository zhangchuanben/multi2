package com.ben.jdbc.utils;

import java.util.Collection;

public class CollectionUtils {
	public static boolean isNotEmpty(Collection<?> cols) {
		return cols != null && !cols.isEmpty();
	}
	
	public static boolean isEmpty (Collection<?> cols) {
		return !isNotEmpty(cols);
	}
}
