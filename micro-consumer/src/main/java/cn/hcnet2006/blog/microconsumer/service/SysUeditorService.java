package cn.hcnet2006.blog.microconsumer.service;

import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@FeignClient(name = "hcnet-website-1",contextId = "h")
public interface SysUeditorService {
    @RequestMapping("/")
    public String showPage();
    @RequestMapping("/content")
    public String findContent(@RequestParam String content, @RequestParam String content1);

    @RequestMapping(value="/ueditor")
    public String ueditor(HttpServletRequest request);


    @RequestMapping(value = "/imgUpload", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, Object> images (@RequestBody MultipartFile upfile, @RequestParam HttpServletRequest request, @RequestParam HttpServletResponse response);


    @RequestMapping(value = "/videoUpload", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, Object> videos (@RequestBody MultipartFile upfile,@RequestParam HttpServletRequest request,@RequestParam HttpServletResponse response);


    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, Object> files (@RequestBody MultipartFile upfile, @RequestParam HttpServletRequest request,@RequestParam HttpServletResponse response);
}