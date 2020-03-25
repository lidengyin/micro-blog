package cn.hcnet2006.blog.microconsumer1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MicroConsumer1Application {

    public static void main(String[] args) {
        SpringApplication.run(MicroConsumer1Application.class, args);
    }

}
