package cn.hcnet2006.blog.microconsumer1.controller;

import cn.hcnet2006.blog.microconsumer1.http.HttpResult;
import cn.hcnet2006.blog.microconsumer1.service.SysApkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/feign/upload")
public class UploadController {
    @Autowired
    private SysApkService sysApkService;
    @PostMapping(value = "/apk")
    public HttpResult upload(MultipartFile uploadFile) {
        System.out.println("123");
        System.out.println(uploadFile.getOriginalFilename());
        return sysApkService.upload(uploadFile);
    }
//    @ApiOperation(value = "根据删除标志，分页查看上传文件",notes = "根据删除标志，分页查看上传文件")
//    @ApiImplicitParams({
//            @ApiImplicitParam(type = "query", name = "pageNum", value = "页码",required = true),
//            @ApiImplicitParam(type = "query", name = "pageSize", value = "行数",required = true),
//            @ApiImplicitParam(type = "query",name = "delFlag", value = "删除标志，-1为删除，0为正常",required = true)
//    })
//    @PostMapping(value = "/select/delFlag",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
//    public HttpResult selectByDelFlag(int pageNum, int pageSize, Byte delFlag){
//       return sysApkService.selectByDelFlag(pageNum,pageSize,delFlag);
//    }
}
