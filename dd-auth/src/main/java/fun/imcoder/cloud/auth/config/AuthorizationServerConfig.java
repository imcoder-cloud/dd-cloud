package fun.imcoder.cloud.auth.config;

import fun.imcoder.cloud.auth.component.ClientDetailsServiceImpl;
import fun.imcoder.cloud.auth.handle.AuthExceptionTranslator;
import fun.imcoder.cloud.security.model.User;
import fun.imcoder.cloud.auth.component.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.util.LinkedHashMap;
import java.util.Map;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Autowired
    public UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthExceptionTranslator authExceptionTranslator;

    @Autowired
    private ClientDetailsServiceImpl clientDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public TokenStore getTokenStore() {
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        tokenStore.setPrefix("dd-oauth:");
        return tokenStore;
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            if (accessToken instanceof DefaultOAuth2AccessToken) {
                DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
                Map<String, Object> additionalInformation = new LinkedHashMap<>();
                additionalInformation.put("expiration", accessToken.getExpiration().getTime());

                User user = (User) authentication.getPrincipal();
                additionalInformation.put("userName", user.getUsername());
                token.setAdditionalInformation(additionalInformation);
            }
            return accessToken;
        };
    }

//    @Bean("clientDetailsService1")
//    public ClientDetailsService clientDetailsService() {
//        return new JdbcClientDetailsService(dataSource);
//    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .tokenEnhancer(tokenEnhancer())
                .exceptionTranslator(authExceptionTranslator)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .tokenStore(getTokenStore());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService).clients(clientDetailsService);
//        clients.inMemory()
//                .withClient("client_1")
//                .authorizedGrantTypes("password","authorization_code", "refresh_token")
//                .scopes("all")
//                .authorities("ROLE_APP")
//                .secret(passwordEncoder().encode("123456"))
//                .accessTokenValiditySeconds(60 * 30)
//                .refreshTokenValiditySeconds(60 * 60);
    }
}
