package cn.hcnet2006.blog.hcnetwebsite.security;

import cn.hcnet2006.blog.hcnetwebsite.vo.JWT;
import org.springframework.stereotype.Component;

@Component
public class AuthServiceHystrix implements AuthServiceClient{

    @Override
    public JWT getToken(String authorization, String type, String username, String password) {
        return null;
    }
}
