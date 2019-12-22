package fun.imcoder.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DdEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DdEurekaApplication.class, args);
    }

}
