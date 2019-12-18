package fun.imcoder.cloud.base.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import fun.imcoder.cloud.base.enums.ResponseEnum;
import lombok.Data;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseData<T> implements Serializable {
    /**
     * 异常码
     */
    private Integer code;

    /**
     * 描述
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    public ResponseData() {}

    public ResponseData(Integer code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public ResponseData(Integer code, String msg, T data) {
        this.code = code;
        this.message = msg;
        this.data = data;
    }

    public ResponseData(ResponseEnum responseEnum) {
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
    }

    public ResponseData(ResponseEnum responseEnum, T data) {
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
        this.data = data;
    }

    public static ResponseData success(){
        return new ResponseData(ResponseEnum.SUCCESS);
    }

    public static <T> ResponseData<T> success(T data){
        return new ResponseData<T>(ResponseEnum.SUCCESS, data);
    }

    public static <T> ResponseData<T> success(int code, String msg){
        return new ResponseData<T>(code, msg);
    }

    public static ResponseData error(int code, String msg){
        return new ResponseData(code,msg);
    }

    public static ResponseData error(ResponseEnum responseEnum){
        return new ResponseData(responseEnum);
    }

    public static ResponseData error(ResponseEnum responseEnum, Object data){
        return new ResponseData<Object>(responseEnum, data);
    }

    public static ResponseData errorParams(String msg){
        return new ResponseData(ResponseEnum.INCORRECT_PARAMS.getCode(), msg);
    }

    public static ResponseData error(BindingResult result, MessageSource messageSource) {
        StringBuffer msg = new StringBuffer();
        //获取错误字段集合
        List<FieldError> fieldErrors = result.getFieldErrors();
        //获取本地locale,zh_CN
        Locale currentLocale = LocaleContextHolder.getLocale();
        //遍历错误字段获取错误消息
        for (FieldError fieldError : fieldErrors) {
            //获取错误信息
            String errorMessage = messageSource.getMessage(fieldError, currentLocale);
            //添加到错误消息集合内
            msg.append(fieldError.getField() + "：" + errorMessage + " , ");
        }
        return ResponseData.error(ResponseEnum.INCORRECT_PARAMS, msg.toString());
    }
}
