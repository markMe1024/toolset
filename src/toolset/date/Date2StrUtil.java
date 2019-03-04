package toolset.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description 日期转字符串工具类
 * @Author .Mark
 * @Date 2019年3月4日
 */
public class Date2StrUtil {
    /**
     * yyyy-MM-dd格式SimpleDateFormat
     */
    private static SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd"); 
    
    /**
     * yyyy-MM格式SimpleDateFormat
     */
    private static SimpleDateFormat yyyyMM = new SimpleDateFormat("yyyy-MM");
    
    /**
     * @Description Date转yyyyMMdd格式字符串
     * @Author .Mark
     * @Date 2019年3月4日
     */
    public static String date2yyyyMMdd(Date date) {
        if (date == null ) { return ""; }
        return yyyyMMdd.format(date);
    }
    
    /**
     * @Description Date转yyyyMM格式字符串
     * @Author .Mark
     * @Date 2019年3月4日
     */
    public static String date2yyyyMM(Date date) {
        if (date == null ) { return ""; }
        return yyyyMM.format(date);
    }
}
