package fun.imcoder.cloud.base.config;

import fun.imcoder.cloud.base.handle.GetArgumentHandle;
import fun.imcoder.cloud.base.handle.PageArgumentHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    GetArgumentHandle getArgumentHandle;
    @Autowired
    PageArgumentHandle pageArgumentHandle;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(getArgumentHandle);
        argumentResolvers.add(pageArgumentHandle);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(pageArgumentHandle);
    }

    @Bean
    public GetArgumentHandle getArgumentHandle(){
        return new GetArgumentHandle();
    }

    @Bean
    public PageArgumentHandle pageArgumentHandle(){
        return new PageArgumentHandle();
    }

}
