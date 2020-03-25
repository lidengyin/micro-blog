package cn.hcnet2006.blog.microconsumer.controller;

import cn.hcnet2006.blog.microconsumer.service.SysUeditorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Api(tags = "富文本独立上传接口")
@RequestMapping("/feign")
@Controller
public class UeditorController {
    @Autowired
    private SysUeditorService sysUeditorService;
    @RequestMapping("/")
    private String showPage(){
        return sysUeditorService.showPage();
    }
   @ApiOperation(value = "返回富文本整体内容",notes = "返回富文本整体内容")
    @RequestMapping("/content")
    @ResponseBody
   @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public String findContent(@ApiParam("") String content, String content1) throws IOException {
       return sysUeditorService.findContent(content, content1);
    }
    @ApiOperation(value = "设置富文本本地配置",notes = "设置富文本本地配置")
    @RequestMapping(value="/ueditor")
    @ResponseBody
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public String ueditor(HttpServletRequest request) {
        return sysUeditorService.ueditor(request);
    }
    @ApiOperation(value = "富文本中单独用于图片上传的接口")
    @RequestMapping(value="/imgUpload")
    @ResponseBody
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public Map<String, Object> images (MultipartFile upfile, HttpServletRequest request, HttpServletResponse response){
       return sysUeditorService.images(upfile, request, response);
    }
@ApiOperation(value = "富文本单独用于视频上传的接口")
    @RequestMapping(value="/videoUpload")
    @ResponseBody
@CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public Map<String, Object> videos (MultipartFile upfile, HttpServletRequest request, HttpServletResponse response){
        return sysUeditorService.videos(upfile, request, response);
    }
@ApiOperation(value = "富文本单独用于附件上传的方法")
    @RequestMapping(value="/fileUpload")
    @ResponseBody
@CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public Map<String, Object> files (MultipartFile upfile, HttpServletRequest request, HttpServletResponse response){
       return sysUeditorService.files(upfile, request, response);
    }
}