package cn.hcnet2006.blog.uaaserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域配置
 */
@Configuration
public class CorsConfig  implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
            registry
                    //允许访问的路径
                    .addMapping("/**")
                    //允许访问的源
                    .allowedOrigins("*")
                    //允许访问的方法
                    .allowedMethods("POST","GET","OPTION","DELETE","PUT")
                    //允许访问的头部
                    .allowedHeaders("*")
                    //允许携带cookie
                    .allowCredentials(true)
                    //预检间隔时间
                    .maxAge(168000);
    }
}
