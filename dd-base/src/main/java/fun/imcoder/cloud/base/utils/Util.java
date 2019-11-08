package fun.imcoder.cloud.base.utils;
import java.util.Arrays;
import java.util.List;

public class Util {

	@SuppressWarnings("rawtypes")
	public static String listToString(List list, String separator) {
		StringBuilder sb = new StringBuilder();
		if(list.isEmpty() || list == null){
			return "";
		}
		list.forEach(o -> {
			sb.append(o).append(separator);
		});
		return sb.toString().substring(0, sb.toString().length() - 1);
	}

	public static boolean notEmpty(Object o) {
		if(o != null && o != "" && !"".equals(o)){
			return true;
		}
		return false;
	}

	public static boolean isEmpty(Object o) {
		return !notEmpty(o);
	}

	public static Integer[] toIntegerArr(String str,String regex){
		return Arrays.stream(str.split(regex)).map(Integer::valueOf).toArray(Integer[]::new);
	}

}
