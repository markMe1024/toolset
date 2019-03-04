package toolset.string;

/**
 * @Description 字符串工具类
 * @Author .Mark
 * @Date 2019年3月4日
 */
public class StringUtil {
	/**
	 * @Description 判断String类型入参是否是null或者空字符串
	 * @Author .Mark
	 * @Date: 2018年3月29日
	 * @Return true：null或者空字符串；false：既不是null也不是空字符串
	 */
	public static boolean nullOrEmpty(String str) {
		if (str == null || "".equals(str.trim()) || "null".equalsIgnoreCase(str)) { 
		    return true; 
		}
		return false;
	}
	
	/**
	 * @Description 判断String类型入参是否不是null也不是空字符串
	 * @Author .Mark
	 * @Date 2018年12月13日
	 * @Return true：既不是null也不是空字符串；false：null或者空字符串
	 */
	public static boolean notNullAndEmpty(String str) {
	    if (str != null && !"".equals(str.trim()) && !"null".equalsIgnoreCase(str)) {
	        return true;
	    }
	    return false;
	}
}
