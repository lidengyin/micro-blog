package cn.hcnet2006.blog.microconsumer.service;

import cn.hcnet2006.blog.microconsumer.fallback.FeginFooHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "upload-apk",fallback = FeginFooHystrix.class)
public interface FeignFooService {
    @RequestMapping("/user/foo")
    public String foo();
}
