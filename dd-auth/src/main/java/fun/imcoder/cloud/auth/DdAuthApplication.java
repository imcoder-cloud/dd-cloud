package fun.imcoder.cloud.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("fun.imcoder.cloud.auth.mapper")
//@ComponentScan(basePackages = {"fun.imcoder.cloud"})
public class DdAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(DdAuthApplication.class, args);
    }

}
