package cn.hcnet2006.blog.uploadapk.service.impl;

import cn.hcnet2006.blog.uploadapk.config.OSSConfig;
import com.aliyun.oss.OSS;
import org.springframework.beans.factory.annotation.Autowired;

public class OSSService {

    private OSS ossClient = OSSConfig.ossClient();


}
