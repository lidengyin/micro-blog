package cn.hcnet2006.blog.hcnetwebsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
//开启服务熔断
@EnableHystrix
//开启声明式服务消费客户端
@EnableFeignClients
//开启服务发现
@EnableDiscoveryClient
@SpringBootApplication
public class HcnetWebsiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(HcnetWebsiteApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
