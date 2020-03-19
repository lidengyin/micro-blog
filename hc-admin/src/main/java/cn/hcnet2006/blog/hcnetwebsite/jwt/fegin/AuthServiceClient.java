package cn.hcnet2006.blog.hcnetwebsite.jwt.fegin;


import cn.hcnet2006.blog.hcnetwebsite.jwt.JWT;
import cn.hcnet2006.blog.hcnetwebsite.jwt.hystrix.AuthServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 声明式服务消费
 */
@FeignClient(value = "uaa-server",fallback = AuthServiceHystrix.class)
public interface AuthServiceClient {
    @PostMapping(value = "/oauth/token")
    public JWT getToken(@RequestHeader("Authorization") String authorization,
                        @RequestParam("grant_type") String type,
                        @RequestParam("username") String username,
                        @RequestParam("password") String password);

}