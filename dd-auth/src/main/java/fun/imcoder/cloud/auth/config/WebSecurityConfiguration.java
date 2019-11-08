package fun.imcoder.cloud.auth.config;

import fun.imcoder.cloud.auth.handle.AuthFailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthFailureHandler authFailureHandler;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
//                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(authFailureHandler);

        http.authorizeRequests()
                .antMatchers("/login", "/authentication/form").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }

}
