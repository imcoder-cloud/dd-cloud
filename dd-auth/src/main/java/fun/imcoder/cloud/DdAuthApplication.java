package fun.imcoder.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("fun.imcoder.cloud.auth.mapper")
public class DdAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(DdAuthApplication.class, args);
    }

}
