package fun.imcoder.cloud.base.utils;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import fun.imcoder.cloud.base.support.BaseModel;

import java.util.Map;

/**
    *   分页封装
 * @author chendongdong
 *
 */
public class PageUtil extends PageHelper {

	public static <T> PageInfo<T> listPage(BaseModel bm, ISelect select) {
		PageInfo<T> pageInfo = startPage(bm.getPageNum(), bm.getPageSize()).doSelectPageInfo(select);
		return pageInfo;
	}

	public static <T> PageInfo<T> listPage(Map<String,Object> m, ISelect select) {
		Integer pageNum = (Integer) m.get("pageNum");
		Integer pageSize = (Integer) m.get("pageSize");
		PageInfo<T> pageInfo = startPage(pageNum, pageSize).doSelectPageInfo(select);
		return pageInfo;
	}
}
