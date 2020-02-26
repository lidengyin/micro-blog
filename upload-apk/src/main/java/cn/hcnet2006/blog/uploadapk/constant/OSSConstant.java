package cn.hcnet2006.blog.uploadapk.constant;

import com.google.gson.internal.$Gson$Preconditions;
import org.codehaus.jackson.annotate.JsonRawValue;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

import javax.xml.ws.Endpoint;

public class OSSConstant implements InitializingBean {
    @Value("endpoint")
    private String endpoint;
    public static String ENDPOINT ;
    @Value("accessKeyId")
    private String accessKeyId;
    public static String ACCESS_KEY_ID;
    @Value("accessKeySecret")
    private String accessKeySecret;
    public static String ACCESS_KEY_SECRET;
    @Value("bucketName")
    private String bucketName;
    public static String BUCKETNAME;
    @Value("aliyun-oss.firstKey")
    public static String FIRSTKEY;

    @Override
    public void afterPropertiesSet() throws Exception {
        ENDPOINT = endpoint;
        ACCESS_KEY_ID = accessKeyId;
        ACCESS_KEY_SECRET = accessKeySecret;
        BUCKETNAME = bucketName;

    }
}
