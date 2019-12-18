package fun.imcoder.cloud.security.handle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InsufficientScopeException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class DdAuthExceptionEntryPoint extends OAuth2AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        JSONObject json = new JSONObject();
        if (e.getCause() instanceof InvalidTokenException) {
            json.put("code",HttpStatus.BAD_REQUEST.value());
            json.put("message","access_token 无效");
        }else if(e instanceof InsufficientAuthenticationException){
            json.put("code", HttpStatus.UNAUTHORIZED.value());
            json.put("message","访问此资源需要完全的身份验证");
        }else{
            json.put("code", HttpStatus.BAD_REQUEST.value());
            json.put("message","Bad Request");
        }
        response.getWriter().write(JSON.toJSONString(json));
    }
}
