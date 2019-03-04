package toolset.string;

import static toolset.string.StringUtil.*;

/**
 * @Description 判断字符串的数据类型
 * @Author .Mark
 * @Date 2019年1月14日
 */
public class DataTypeUtil {
    
    /**
     * @Description 判断字符串是否实际上是整数
     * @Author .Mark
     * @Date 2019年1月14日
     */
    public static Boolean isInteger(String str) {
        // 1.限制入参
        if (nullOrEmpty(str)) {
            return false;
        }
        
        // 2.判断
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        
        return true;
    }
}
