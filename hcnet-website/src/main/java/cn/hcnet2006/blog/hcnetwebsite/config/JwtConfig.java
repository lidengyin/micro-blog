package cn.hcnet2006.blog.hcnetwebsite.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

@Configuration
public class JwtConfig {
    @Autowired
    JwtAccessTokenConverter jwtAccessTokenConverter;

    @Bean
    @Qualifier("tokenStore")
    public TokenStore tokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    /**
     * JWT类型转换器
     * @return
     */
    @Bean
    protected JwtAccessTokenConverter jwtAccessTokenEnhancer(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        Resource resource = new ClassPathResource("public.cert");
        String publicKey;
        try{
            publicKey = new String(FileCopyUtils.copyToByteArray(
                    resource.getInputStream()));
        }catch (IOException e){
           throw new RuntimeException(e);
        }
        converter.setVerifierKey(publicKey);
        return converter;
    }
}
