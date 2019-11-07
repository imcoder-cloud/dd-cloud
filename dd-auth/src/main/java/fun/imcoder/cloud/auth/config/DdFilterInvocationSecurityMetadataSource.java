package fun.imcoder.cloud.auth.config;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class DdFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Collection<ConfigAttribute> co=new ArrayList<>();
        co.add(new SecurityConfig("null"));
        return co;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
