package cn.hcnet2006.blog.microconsumer.service;


import cn.hcnet2006.blog.microconsumer.http.HttpResult;
import io.swagger.annotations.*;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@FeignClient(name = "hcnet-website-1")
@RestController
@RequestMapping("/upload")
public interface SysUploadService {
    @PostMapping("/apk")
    public HttpResult upload(@ApiParam(value = "uploadFile",required = true) MultipartFile uploadFile);

    @PostMapping("/select/delFlag")
    public HttpResult selectByDelFlag(int pageNum, int pageSize, Byte delFlag);
}
