package toolset.map;

import java.util.Map;
import java.util.Set;

public class MapUtil {
	/** 
	 * <p>方法名：nullToEmpty</p>
	 * <p>功能描述：map中value为null的值处理成空字符串</p>
	 * @Author .Mark
	 * @Date   2018年1月9日
	 * @Return Map<String,Object>
     * @Note 只有当确保map的所有value都可以是String类型才能使用此方法, 比如：日期类型的数据转为空字符串, requester会解析出错
	 */
	public static Map<String, Object> nullToEmpty(Map<String, Object> map) {
		Set<String> set = map.keySet();
		if(set != null && !set.isEmpty()) {
			for(String key : set) {
				if(map.get(key) == null) { map.put(key, ""); }
			}
		}
		return map;
	}
}
