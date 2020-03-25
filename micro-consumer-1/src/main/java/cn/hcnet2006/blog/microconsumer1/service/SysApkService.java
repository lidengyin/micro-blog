package cn.hcnet2006.blog.microconsumer1.service;
import cn.hcnet2006.blog.microconsumer1.http.HttpResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
@Service
@FeignClient(value = "hcnet-website-1")
@RequestMapping(value = "/upload")
public interface SysApkService {
    @RequestMapping(value = "/apk",method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public HttpResult upload(@RequestBody MultipartFile uploadFile);

}
