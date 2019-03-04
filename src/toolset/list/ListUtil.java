package toolset.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description 链表工具类
 * @Author .Mark
 * @Date 2019年3月4日
 */
public class ListUtil {
	/**
	 * @Description	判断list是否非空非null
	 * @Author .Mark
	 * @Date 2018年6月26日
	 */
	public static boolean notNullAndEmpty(List<Map<String, Object>> list) {
		if(list != null && !list.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * @Description 判断list是否是空或null
	 * @Author .Mark
	 * @Date 2019年1月9日
	 */
	public static boolean nullOrEmpty(List<Map<String, Object>> list) {
        if(list == null || list.isEmpty()) {
            return true;
        }else {
            return false;
        }
    }
	
	/** 
	 * <p>方法名：nullToEmpty</p>
	 * <p>功能描述：map的value如果是null处理成空字符串</p>
	 * @Author .Mark
	 * @Date   2017年12月21日
	 * @Return List
     * @Note 只有当确保map的所有value都可以是String类型才能使用此方法, 比如：日期类型的数据转为空字符串, requester会解析出错
	 */
	public static List<Map<String, Object>> nullToEmpty(List<Map<String, Object>> list) {
		if(list !=null && !list.isEmpty()) {
			for(Map<String, Object> map : list) {
				Set<String> set = map.keySet();
				if(set != null && !set.isEmpty()) {
					for(String key : set) {
						if(map.get(key) == null || "null".equals(map.get(key))) {
							map.put(key, "");
						}
					}
				}
			}
		}
		return list;
	}
	
	/**
     * @Description Map<String, Object>数据类型的List转为String数据类型的List
     * @Author .Mark
     * @Date 2018年12月3日
     */
    public static List<String> listMap2ListString(List<Map<String, Object>> list, String key) {
        List<String> result = new ArrayList<>();
        if (notNullAndEmpty(list)) {
            for (Map<String, Object> map : list) {
                String value = (String)map.get(key);
                if (value != null && !"".equals(value.trim())) {
                    result.add(value);
                }
            }
        }
        return result;
    }
}
