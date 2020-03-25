package cn.hcnet2006.blog.microconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

//开启feigin
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class MicroConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroConsumerApplication.class, args);
    }
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //允许上传的文件最大值
        factory.setMaxFileSize(DataSize.parse("150MB")); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize(DataSize.parse("150MB"));
        return factory.createMultipartConfig();
    }

}
