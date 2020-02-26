package cn.hcnet2006.blog.uploadapk.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



public class OSSConfig {
    @Value("${endpoint}")
    private static String endpoint;//访问OSS的域名
    @Value("${accessKeyId}")
    private static String accessKeyId;//访问密钥
    @Value("${accessKeySecret}")
    private static String accessKeySecret;//访问密钥
    @Value("${bucketName}")
    private static String bucketName;//存储空间
    private static String urlPrefix;//前缀

    public static OSS ossClient(){
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

}
