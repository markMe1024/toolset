package toolset.pagingquery;

import java.util.ArrayList;
import java.util.List;

public class PagingQueryUtil {
	
	/** 
	 * @Description 从全部数据中截取出符合分页条件的数据
	 * @Author .Mark
	 * @Date   2018年3月27日
	 */
	public static List<Object> subList(List<Object> entireList, int currentPage, int pageSize) {
		// 1.如果全部数据为空，返回空
		if (entireList == null || entireList.isEmpty()) { 
			return new ArrayList<>(); 
		}

		// 2.计算截取的起始、终止位置，计算总数据量
		int startIndex = (currentPage - 1) * pageSize;
		int endIndex = currentPage * pageSize - 1;
		int totals = entireList.size();
		
		// 3.如果startIndex小于0，返回全部数据
		if (startIndex < 0) { 
			return entireList; 
		}
		
		// 4.如果startIndex超出总数据范围，返回空列表
		if (startIndex > totals - 1) {
			return new ArrayList<>();
		}
		
		// 5.如果endIndex超出总数据范围，则截取到末尾
		if (endIndex > totals - 1) {
			return entireList.subList(startIndex, totals);
		}
		
		// 6.如果endIndex没有超出总数据范围，则截取到endIndex
		else {
			return entireList.subList(startIndex, endIndex + 1);
		}
	}
}
