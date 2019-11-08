package fun.imcoder.cloud.base.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.LinkedList;
import java.util.List;

public class ValidateUtil {

	public static boolean validated(BindingResult result) {
		if(result.hasErrors()) {
			return false;
		}
		return true;
	}
	
	public static boolean notValidated(BindingResult result) {
		if(result.hasErrors()) {
			return true;
		}
		return false;
	}
	
	public static String getError(BindingResult result) {
		List<String> list = new LinkedList<>();
		List<FieldError> l = result.getFieldErrors();
		l.forEach((e)->{
			list.add(e.getDefaultMessage());
		});
		return Util.listToString(list,";");
	}
	
}
