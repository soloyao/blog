package com.zmy.util;

/**
 * @ClassName:StringAndArray
 * @Description:字符串与字符串数组之间的转换
 * @author:zmy(343722243@qq.com)
 * @date:2019年12月22日 下午12:18:25
 */
public class StringAndArray {
	/**
	 * 字符串转换成字符串数组
	 */
	public static String[] stringToArray(String str) {
		String[] array = str.split(",");
		for (int i = 0; i < array.length; i++) {
			array[i] = array[i].trim();
		}
		return array;
	}
	
	/**
     * 字符串数组拼接成字符串
     * @param articleTags 字符串数组
     * @return 拼接后的字符串
     */
    public static String arrayToString(String[] articleTags){
        StringBuilder sb = new StringBuilder();
        for(String s : articleTags){
            if(sb.length() == 0){
                sb.append(s.trim());
            } else {
                sb.append(",").append(s.trim());
            }
        }
        return sb.toString();
    }
}
