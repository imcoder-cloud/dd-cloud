package fun.imcoder.cloud.auth.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Component
public class DdAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object obj, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        HttpServletRequest request = ((FilterInvocation) obj).getHttpRequest();

        for (GrantedAuthority ga : authentication.getAuthorities()) {
            String authority = ga.getAuthority();
            String url = StringUtils.substringAfter(authority," ");
            String method = StringUtils.substringBefore(authority," ");

            AntPathRequestMatcher matcher = new AntPathRequestMatcher(url,method);
            if (matcher.matches(request)) {
                return;
            }
        }
        throw new AccessDeniedException("Access Denied");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

}
