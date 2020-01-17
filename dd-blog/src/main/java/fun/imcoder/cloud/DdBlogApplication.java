package fun.imcoder.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

@SpringBootApplication
@EnableOAuth2Sso
@MapperScan("fun.imcoder.cloud.blog.mapper")
public class DdBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(DdBlogApplication.class, args);
    }

}
