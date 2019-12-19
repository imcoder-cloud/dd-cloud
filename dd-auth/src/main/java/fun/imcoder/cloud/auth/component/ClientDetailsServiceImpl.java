package fun.imcoder.cloud.auth.component;

import fun.imcoder.cloud.auth.exception.DdOAuth2Exception;
import fun.imcoder.cloud.auth.model.OauthClientDetails;
import fun.imcoder.cloud.auth.service.OauthClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {

    @Autowired
    private OauthClientDetailsService oauthClientDetailsService;

    @Override
    public BaseClientDetails loadClientByClientId(String clientId) {
        OauthClientDetails client = oauthClientDetailsService.getById(clientId);
        if (client == null) {
            throw new DdOAuth2Exception(clientId + " 无效");
        }
        BaseClientDetails baseClientDetails = new BaseClientDetails();
        baseClientDetails.setClientId(client.getClientId());
        baseClientDetails.setClientSecret(new BCryptPasswordEncoder().encode(client.getClientSecret()));
        baseClientDetails.setAccessTokenValiditySeconds(client.getAccessTokenValidity());
        baseClientDetails.setRefreshTokenValiditySeconds(client.getRefreshTokenValidity());
        baseClientDetails.setAuthorizedGrantTypes(Arrays.asList(client.getAuthorizedGrantTypes().split(",")));
        Collection<SimpleGrantedAuthority> collection = new HashSet<>();
        collection.add(new SimpleGrantedAuthority("ROLE_APP"));
        baseClientDetails.setAuthorities(collection);
        Set<String> scope = new TreeSet<>();
        scope.addAll(Arrays.asList(client.getScope().split(",")));
        baseClientDetails.setScope(scope);
        baseClientDetails.setAutoApproveScopes(scope);
        return baseClientDetails;
    }
}
