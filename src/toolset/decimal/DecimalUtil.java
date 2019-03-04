package toolset.decimal;

import java.text.DecimalFormat;

/**
 * @Description 数字工具类
 * @Author .Mark
 * @Date 2019年3月4日
 */
public class DecimalUtil {
	/** 
	 * <p>方法名：formatDecimal</p>
	 * <p>功能描述：处理数字格式，输入参数是整数，返回整数；输入参数是小数，则返回数字保留小数点后两位</p>
	 * @Author .Mark
	 * @Date   2017年9月16日
	 * @Return String
	 * @param Double
	 * <p>小数点后第二位根据第三位四舍五入取值</p>
	 */
	public static String formatDecimal(Double param){
		if(param == null) return null;
		
		DecimalFormat dt = new DecimalFormat("0.00");
		if(param.intValue() == Double.valueOf(dt.format(param))){
			return String.valueOf(param.intValue());
		}else{
			return dt.format(param);
		}
	}
}
