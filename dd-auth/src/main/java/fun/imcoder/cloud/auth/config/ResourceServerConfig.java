package fun.imcoder.cloud.auth.config;

import fun.imcoder.cloud.security.handle.AuthExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private AuthExceptionHandler authExceptionHandler;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources
                .accessDeniedHandler(authExceptionHandler)
                .authenticationEntryPoint(authExceptionHandler);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**")
                .permitAll()
                .anyRequest().authenticated();
    }

}
