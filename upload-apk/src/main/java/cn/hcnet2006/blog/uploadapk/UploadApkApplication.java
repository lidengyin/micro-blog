package cn.hcnet2006.blog.uploadapk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//开启服务熔断
@EnableHystrix
//开启声明式服务消费客户端
@EnableFeignClients
//开启服务发现
@EnableDiscoveryClient
@SpringBootApplication
public class UploadApkApplication {

    public static void main(String[] args) {
        SpringApplication.run(UploadApkApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
