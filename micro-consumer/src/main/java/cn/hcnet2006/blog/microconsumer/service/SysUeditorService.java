package cn.hcnet2006.blog.microconsumer.service;

import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping(value="/imgUpload")
    public Map<String, Object> images (@RequestParam MultipartFile upfile,  @RequestParam HttpServletRequest request, @RequestParam HttpServletResponse response);

    @RequestMapping(value="/videoUpload")
    public Map<String, Object> videos (@RequestParam MultipartFile upfile,@RequestParam HttpServletRequest request,@RequestParam HttpServletResponse response);

    @RequestMapping(value="/fileUpload")
    public Map<String, Object> files (@RequestParam MultipartFile upfile, @RequestParam HttpServletRequest request,@RequestParam HttpServletResponse response);
}