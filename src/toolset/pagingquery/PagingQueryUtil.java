package toolset.pagingquery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PagingQueryUtil {
	
	/** 
	 * @Description 从完整列表中截取出符合分页条件的列表
	 * @Author .Mark
	 * @Date   2018年3月27日
	 */
	public static List<Map<String, Object>> subList(List<Map<String, Object>> entireList, int pageNo, int pageSize) {
		// 限制入参
		if(entireList == null || entireList.isEmpty()) { return new ArrayList<>(); }
		if(pageNo <= 0 || pageSize <= 0) { return new ArrayList<>(); }
		// 完整列表容量
		int totals = entireList.size();
		// 如果请求数据起始范围超过了总记录数，返回空列表
		if((pageNo-1)*pageSize+1 > totals) {
			return new ArrayList<>();
		}
		// 如果请求数据范围的起始范围小于等于总记录数，而终止范围大于等于总记录数，则截取到entireList末尾
		else if((pageNo-1)*pageSize + 1 <= totals && totals <= pageNo*pageSize) {
			return entireList.subList((pageNo-1)*pageSize, totals);
		}
		// 请求数据起始终止范围都在总记录范围内，正常截取
		else {
			return entireList.subList((pageNo-1)*pageSize, pageNo*pageSize);
		}
	}
}
