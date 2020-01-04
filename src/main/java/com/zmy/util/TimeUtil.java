package com.zmy.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
     * 使用线程安全的DateTimeFormatter
     * @return “年-月-日”字符串
     */
    public static String getFormatDateForThree(){

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return now.format(format);
    }
    
    /**
     * 获得当前时间的时间戳
     * @return 时间戳
     */
    public static long getLongTime(){
        Date now = new Date();
        return now.getTime()/1000;
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
	
	/**
     * 时间中横杆转换为年
     * @param str 2018-08
     * @return 2018年8月
     */
    public static String timeWhippletreeToYear(String str){
        StringBuilder s = new StringBuilder();
        s.append(str.substring(0,4));
        s.append("年");
        s.append(str.substring(5,7));
        s.append("月");
        return String.valueOf(s);
    }
}
