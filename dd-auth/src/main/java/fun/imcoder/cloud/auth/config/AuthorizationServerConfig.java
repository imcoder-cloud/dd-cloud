package fun.imcoder.cloud.auth.config;

import fun.imcoder.cloud.auth.handle.AuthExceptionTranslator;
import fun.imcoder.cloud.auth.handle.DdUserAuthenticationConverter;
import fun.imcoder.cloud.auth.component.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;


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
    private DataSource dataSource;

    @Autowired
    private DdUserAuthenticationConverter ddUserAuthenticationConverter;

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

//    @Bean
//    public TokenEnhancer tokenEnhancer() {
//        return (accessToken, authentication) -> {
//            if (accessToken instanceof DefaultOAuth2AccessToken) {
//                DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
//                Map<String, Object> additionalInformation = new LinkedHashMap<>();
//                additionalInformation.put("expiration", accessToken.getExpiration().getTime());
//
//                User user = (User) authentication.getPrincipal();
//                additionalInformation.put("userName", user.getUsername());
//                token.setAdditionalInformation(additionalInformation);
//            }
//            return accessToken;
//        };
//    }

    @Bean
    public ClientDetailsService clientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        DefaultAccessTokenConverter defaultAccessTokenConverter = new DefaultAccessTokenConverter();
        defaultAccessTokenConverter.setUserTokenConverter(ddUserAuthenticationConverter);

        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());

        endpoints
//                .tokenEnhancer(tokenEnhancer())
                .exceptionTranslator(authExceptionTranslator)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .accessTokenConverter(defaultAccessTokenConverter)
                .tokenServices(tokenServices)
                .tokenStore(getTokenStore());

        tokenServices.setTokenStore(endpoints.getTokenStore());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

}
