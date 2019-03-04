package toolset.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description 字符串转日期工具类
 * @Author .Mark
 * @Date 2019年1月30日
 */
public class Str2DateUtil {
    
    /**
     * yyyy-MM-dd格式SimpleDateFormat
     */
    private static SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd"); 
    
    /**
     * yyyy-MM格式SimpleDateFormat
     */
    private static SimpleDateFormat yyyyMM = new SimpleDateFormat("yyyy-MM");
    
    /**
     * @Description yyyy-MM-dd格式字符串转Date 
     * @Author .Mark
     * @Date 2019年1月30日
     */
    public static Date yyyyMMdd2Date(String str) {
        try {
            Date date = yyyyMMdd.parse(str);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null; 
    }
    
    /**
     * @Description yyyy-MM格式字符串转Date
     * @Author .Mark
     * @Date 2019年2月16日
     */
    public static Date yyyyMM2Date(String str) {
        try {
            Date date = yyyyMM.parse(str);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null; 
    }
}
