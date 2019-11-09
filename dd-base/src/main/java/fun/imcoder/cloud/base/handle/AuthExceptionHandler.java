package fun.imcoder.cloud.base.handle;

import com.alibaba.fastjson.JSON;
import fun.imcoder.cloud.base.enums.ResponseEnum;
import fun.imcoder.cloud.base.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Component
@Slf4j
public class AuthExceptionHandler implements AuthenticationEntryPoint, AccessDeniedHandler {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Throwable cause = authException.getCause();
        response.setContentType("application/json;charset=UTF-8");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        if(cause instanceof InvalidTokenException) {
            response.getWriter().write(JSON.toJSONString(ResponseVO.error(ResponseEnum.ACCESS_TOKEN_INVALID)));
        }else{
            response.getWriter().write(JSON.toJSONString(ResponseVO.error(ResponseEnum.UNAUTHORIZED)));
        }
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(JSON.toJSONString(ResponseVO.error(ResponseEnum.UNAUTHORIZED)));
    }
}
