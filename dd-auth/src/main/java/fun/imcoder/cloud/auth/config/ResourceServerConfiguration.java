package fun.imcoder.cloud.auth.config;

import fun.imcoder.cloud.auth.handle.AuthExceptionHandler;
import fun.imcoder.cloud.auth.handle.AuthFailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Autowired
    private AuthExceptionHandler authExceptionHandler;

    @Autowired
    private AuthFailureHandler authFailureHandler;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources
                .accessDeniedHandler(authExceptionHandler)
                .authenticationEntryPoint(authExceptionHandler);
    }

    @Bean
    public FilterSecurityInterceptor filterSecurityInterceptor() {
        return new FilterSecurityInterceptor();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
//                .successHandler(authSuccessHandler)
                .failureHandler(authFailureHandler);
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
