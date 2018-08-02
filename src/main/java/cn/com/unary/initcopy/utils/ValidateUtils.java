package cn.com.unary.initcopy.utils;

import java.util.Collection;

/**
 * 对象验证工具
 * @author shark
 *
 */
public class ValidateUtils {

	private ValidateUtils () {}
	
	/**
	 * 判断是否为字符空串
	 * @param value
	 * @return
	 */
	public static boolean isEmpty (String value) {
		return value == null || value.isEmpty();
	}
	/**
	 * 判断是否为空集合
	 * @param c
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty (Collection c) {
		return c==null || c.isEmpty();
	}
}
