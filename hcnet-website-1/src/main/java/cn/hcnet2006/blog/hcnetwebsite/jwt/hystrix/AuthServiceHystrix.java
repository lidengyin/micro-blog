package cn.hcnet2006.blog.hcnetwebsite.jwt.hystrix;

import cn.hcnet2006.blog.hcnetwebsite.jwt.JWT;
import cn.hcnet2006.blog.hcnetwebsite.jwt.fegin.AuthServiceClient;
import org.springframework.stereotype.Component;
@Component
public class AuthServiceHystrix implements AuthServiceClient {

    @Override
    public JWT getToken(String authorization, String type, String username, String password) {
        return null;
    }
}
