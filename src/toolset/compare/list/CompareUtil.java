package toolset.compare.list;

import static toolset.list.ListUtil.*;
import static toolset.string.StringUtil.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 和List相关的比较
 * @Author .Mark
 * @Date 2019年1月10日
 */
public class CompareUtil {
    
    /**
     * @Description 比较两个List的数据差异
     * @Author .Mark
     * @Date 2019年1月10日
     * @Note newList和oldList比较，返回新增的、已删除的、已存在的数据；返回数据列表中只包含compareKey对应的value
     * @Param compareKey：根据这个字段判断两个List中的两个元素是否相同；必传
     * @Return add：newList相对于oldList新增的
     * @Return delete：newList相对于oldList已删除的
     * @Return exist：newList相对于oldList已存在的
     */
    public static Map<String, List<String>> listVsList(List<Map<String, Object>> oldList, 
            List<Map<String, Object>> newList, String compareKey) {
        // 1.预定义返回数据
        Map<String, List<String>> result = new HashMap<>();
        result.put("add", new ArrayList<String>());
        result.put("delete", new ArrayList<String>());
        result.put("exist", new ArrayList<String>());
        
        // 2.限制入参
        if (nullOrEmpty(compareKey) || (nullOrEmpty(oldList) && nullOrEmpty(newList))) {
            return result;
        }
        
        // 3.提取出只包含compareKey的value的List
        List<String> oldList2 = listMap2ListString(oldList, compareKey);
        List<String> newList2 = listMap2ListString(newList, compareKey);
        
        // 4.如果oldList为空，说明都是新增的
        if (nullOrEmpty(oldList)) {
            result.put("add", newList2);
            return result;
        }
        
        // 5.如果newList为空，说明都是删除的
        else if (nullOrEmpty(newList)) {
            result.put("delete", oldList2);
            return result;
        }
        
        // 6.都存在，判断
        else {
            // 6.1.找到新增的
            List<String> add = new ArrayList<>();
            for (String tmp : newList2) {
                if (!oldList2.contains(tmp)) {
                    add.add(tmp);
                }
            }
            result.put("add", add);
            
            // 6.2.找到删除的
            List<String> delete = new ArrayList<>();
            for (String tmp : oldList2) {
                if (!newList2.contains(tmp)) {
                    delete.add(tmp);
                }
            }
            result.put("delete", delete);
            
            // 6.3.找到已存在的
            List<String> exist = new ArrayList<>();
            for (String tmp : oldList2) {
                if (newList2.contains(tmp)) {
                    exist.add(tmp);
                }
            }
            result.put("exist", exist);
        }
        
        return result;
    }
}
