package fun.imcoder.cloud.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("fun.imcoder.cloud.auth.mapper")
public class DdAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(DdAuthApplication.class, args);
    }

}
