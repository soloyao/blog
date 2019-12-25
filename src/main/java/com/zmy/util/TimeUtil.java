package com.zmy.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
	
	/**
	 * 格式化日期
	 * @return
	 */
	public static String getFormatDateForFive() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return now.format(format);
	}
}
