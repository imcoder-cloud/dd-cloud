package fun.imcoder.cloud.security.handle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import fun.imcoder.cloud.base.common.ResponseData;
import fun.imcoder.cloud.base.enums.ResponseEnum;
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
            response.getWriter().write(JSON.toJSONString(new ResponseData(ResponseEnum.ACCESS_TOKEN_INVALID)));
        } else if (e instanceof InsufficientAuthenticationException) {
            response.getWriter().write(JSON.toJSONString(new ResponseData(ResponseEnum.UNAUTHORIZED)));
        } else {
            response.getWriter().write(JSON.toJSONString(new ResponseData(ResponseEnum.BAD_REQUEST)));
        }
    }
}
