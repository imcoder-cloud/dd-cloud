package fun.imcoder.cloud.security.utils;

import fun.imcoder.cloud.base.utils.BeanUtil;
import fun.imcoder.cloud.security.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class SecurityUtil {

    /**
     * 获取用户信息
     *
     * @return
     */
    public static User getUser() throws InvocationTargetException, IllegalAccessException {
        OAuth2Authentication authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if(principal instanceof Map){
            User user = new User();
            return BeanUtil.mapToBean((Map<String, Object>) principal,user);
        }
        return (User) authentication.getPrincipal();
    }

    /**
     * 获取clientId
     *
     * @return
     */
    public static String getClientId() {
        OAuth2Authentication authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        return authentication.getOAuth2Request().getClientId();
    }

}
