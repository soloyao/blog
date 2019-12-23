package com.zmy.util;

public class TimeUtil {
	/**
	 * 事件中的年转换为横杠
	 * @param str
	 * @return
	 */
	public static String timeYearToWhippletree(String str) {
		StringBuilder s = new StringBuilder();
		s.append(str.substring(0, 4));
		s.append("-");
		s.append(str.substring(5, 7));
		return String.valueOf(s);
	}
}
