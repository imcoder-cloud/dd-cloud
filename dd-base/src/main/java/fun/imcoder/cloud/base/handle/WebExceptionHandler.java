package fun.imcoder.cloud.base.handle;

import fun.imcoder.cloud.base.enums.ResponseEnum;
import fun.imcoder.cloud.base.common.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.common.exceptions.UnsupportedGrantTypeException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class WebExceptionHandler {

    //处理Get请求中 使用@Valid 验证路径中请求实体校验失败后抛出的异常，详情继续往下看代码
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResponseData<String> BindExceptionHandler(BindException e) {
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(";"));
        return ResponseData.error(ResponseEnum.INCORRECT_PARAMS);
    }

    //处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是javax.validation.ConstraintViolationException
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResponseData<String> ConstraintViolationExceptionHandler(ConstraintViolationException e) {
        String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(";"));
        return ResponseData.error(ResponseEnum.INCORRECT_PARAMS);
    }

    //处理请求参数格式错误 @RequestBody上validate失败后抛出的异常是MethodArgumentNotValidException异常。
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseData<String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(";"));
        return ResponseData.error(ResponseEnum.INCORRECT_PARAMS);
    }

    //处理请求参数格式错误  HttpMessageNotReadableException
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseData<String> HttpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
        return ResponseData.error(ResponseEnum.INCORRECT_PARAMS);
    }

    //处理请求参数格式错误  HttpMediaTypeNotSupportedException
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public ResponseData<String> HttpMediaTypeNotSupportedExceptionHandler(HttpMediaTypeNotSupportedException e) {
        return ResponseData.error(ResponseEnum.INCORRECT_PARAMS);
    }

    // 认证异常
    @ExceptionHandler(OAuth2Exception.class)
    @ResponseBody
    public ResponseData<String> OAuth2ExceptionHandler(OAuth2Exception e) {
        return ResponseData.error(ResponseEnum.AUTH_ERROR, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseData<String> ExceptionHandler(Exception e) {
        log.error("错误信息:" + e.getMessage());
        e.printStackTrace();
        return ResponseData.error(ResponseEnum.SERVER_ERROR, e.getMessage());
    }

}
