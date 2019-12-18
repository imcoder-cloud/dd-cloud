package fun.imcoder.cloud.auth.exception;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @Description: 异常处理类
 * @Date: 2019/7/17 15:29
 * @Version: 1.0
 */
@JsonSerialize(using = DdOAuth2ExceptionSerializer.class)
public class DdOAuth2Exception extends OAuth2Exception {
    private Integer code = 400;

    public DdOAuth2Exception(String message, Throwable t) {
        super(message, t);
        code = ((OAuth2Exception)t).getHttpErrorCode();
    }

    public DdOAuth2Exception(String message) {
        super(message);
    }
    @Override
    public int getHttpErrorCode() {
        return code;
    }

}

