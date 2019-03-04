package toolset.date;

import java.util.Calendar;
import java.util.Date;

/**
 * @Description 日期转日期工具类
 * @Author .Mark
 * @Date 2019年2月27日
 */
public class Date2DateUtil {
    
    /**
     * @Description 清空日期时分秒，只保留年月日
     * @Author .Mark
     * @Date 2019年2月27日
     */
    public static Date clearHms(Date date) {
        // 1.限制入参
        if (date == null) { return null; }
        
        // 2.获得date的calendar
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        
        // 3.清空时分秒
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        
        // 4.
        return calendar.getTime();
    }
}
