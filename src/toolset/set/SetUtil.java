package toolset.set;

import static toolset.list.ListUtil.*;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * 工具类方法列表：
 * 1.泛型为Map<String, Object>的List转换为泛型为String的Set，listMap2SetString(List<Map<String, Object>> list, String key)
 */

public class SetUtil {
    /**
     * @Description 泛型为Map<String, Object>的List转换为泛型为String的Set
     * @Author .Mark
     * @Date 2018年12月3日
     * @Param key：要从Map中提取的数据的键
     */
    public static Set<String> listMap2SetString(List<Map<String, Object>> list, String key) {
        Set<String> result = new LinkedHashSet<>();
        if (notNullAndEmpty(list)) {
            for (Map<String, Object> tmp : list) {
                result.add((String)tmp.get(key));
            }
        }
        return result;
    }
}
