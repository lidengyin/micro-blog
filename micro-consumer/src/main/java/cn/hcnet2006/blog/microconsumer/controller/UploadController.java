package cn.hcnet2006.blog.microconsumer.controller;

import cn.hcnet2006.blog.microconsumer.http.HttpResult;
import cn.hcnet2006.blog.microconsumer.service.SysApkService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "APK安装包上传接口")
@RestController
@RequestMapping("/feign/upload")
public class UploadController {
    @Autowired
    private SysApkService sysApkService;
    @ApiOperation(value = "文件上传",notes = "文件上传")
    @PostMapping(value = "/apk")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult upload(MultipartFile uploadFile) {
        System.out.println("123");
        System.out.println(uploadFile.getOriginalFilename());
        return sysApkService.upload(uploadFile);
    }
    @ApiOperation(value = "根据删除标志，分页查看上传文件",notes = "根据删除标志，分页查看上传文件")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "pageNum", value = "页码",required = true),
            @ApiImplicitParam(type = "query", name = "pageSize", value = "行数",required = true),
            @ApiImplicitParam(type = "query",name = "delFlag", value = "删除标志，-1为删除，0为正常",required = true)
    })
    @PostMapping(value = "/select/delFlag",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult selectByDelFlag(int pageNum, int pageSize, Byte delFlag){
       return sysApkService.selectByDelFlag(pageNum,pageSize,delFlag);
    }
}
