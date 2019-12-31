package fun.imcoder.cloud.security.utils;

import fun.imcoder.cloud.security.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

public class SecurityUtil {

    /**
     * 获取用户信息
     *
     * @return
     */
    public static User getUser() {
        OAuth2Authentication authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
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
