package cn.hcnet2006.blog.microconsumer.service;


import cn.hcnet2006.blog.microconsumer.http.HttpResult;
import feign.form.spring.SpringFormEncoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//@Headers("Content-Type: multipart/form-data")
@FeignClient(contextId = "a", name = "hcnet-website-1")
@RequestMapping(value = "/upload")
//@RequestMapping(value = "/upload")
public interface SysApkService {
    @RequestMapping(value = "/apk",method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpResult upload(@RequestBody MultipartFile uploadFile);

    @PostMapping("/select/delFlag")
    public HttpResult selectByDelFlag(@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam Byte delFlag);
//    class MyConfig {
//        @Bean
//        public SpringFormEncoder feignFormEncoder() {
//            return new SpringFormEncoder();
//        }
//    }

}
