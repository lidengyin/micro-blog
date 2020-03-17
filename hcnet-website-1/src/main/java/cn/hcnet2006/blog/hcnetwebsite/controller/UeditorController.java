package cn.hcnet2006.blog.hcnetwebsite.controller;

import cn.hcnet2006.blog.hcnetwebsite.ueditor.PublicMsg;
import cn.hcnet2006.blog.hcnetwebsite.util.OSSUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@Api(tags = "富文本独立上传接口")
@Controller
public class UeditorController {
    @RequestMapping("/")
    private String showPage(){
        return "index";
    }
   @ApiOperation(value = "返回富文本整体内容",notes = "返回富文本整体内容")
    @RequestMapping("/content")
    @ResponseBody
    public String findContent(@ApiParam("") String content, String content1) throws IOException {
        System.out.println("content1:"+content1);
        String url = ResourceUtils.getURL("").getPath()+UUID.randomUUID().toString()+".html";
        File folder = new File(url);
        FileWriter fileWriter = new FileWriter(folder);
        fileWriter.write(content1);
        fileWriter.flush();
        fileWriter.close();
        url = OSSUtils.upload(folder,UUID.randomUUID().toString()+".html");

        System.out.println("content:"+url);
        return url;
    }
    @ApiOperation(value = "设置富文本本地配置",notes = "设置富文本本地配置")
    @RequestMapping(value="/ueditor")
    @ResponseBody
    public String ueditor(HttpServletRequest request) {

        return PublicMsg.UEDITOR_CONFIG;
    }
    @ApiOperation(value = "富文本中单独用于图片上传的接口")
    @RequestMapping(value="/imgUpload")
    @ResponseBody
    public Map<String, Object> images (MultipartFile upfile, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> params = new HashMap<String, Object>();
        System.out.println("1111111111111");
        try{
            String url = ResourceUtils.getURL("").getPath()+upfile.getOriginalFilename();
            File folder = new File(url);
            upfile.transferTo(folder);
            url = OSSUtils.upload(folder, UUID.randomUUID().toString()+upfile.getOriginalFilename().substring(upfile.getOriginalFilename().lastIndexOf(".")));
            System.out.println(UUID.randomUUID().toString()+upfile.getOriginalFilename().substring(upfile.getOriginalFilename().lastIndexOf(".")));
            folder.delete();
            String fileName = upfile.getOriginalFilename();
            params.put("state", "SUCCESS");
            params.put("url", url);
            params.put("size", upfile.getSize());
            params.put("original", fileName);
            params.put("type", upfile.getContentType());

        } catch (Exception e){
            params.put("state", "ERROR");
        }
        return params;
    }
@ApiOperation(value = "富文本单独用于视频上传的接口")
    @RequestMapping(value="/videoUpload")
    @ResponseBody
    public Map<String, Object> videos (MultipartFile upfile, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> params = new HashMap<String, Object>();
        System.out.println("1111111111111");
        try{
            String url = ResourceUtils.getURL("").getPath()+upfile.getOriginalFilename();
            File folder = new File(url);
            upfile.transferTo(folder);
            url = OSSUtils.upload(folder, UUID.randomUUID().toString()+upfile.getOriginalFilename().substring(upfile.getOriginalFilename().lastIndexOf(".")));
            System.out.println(UUID.randomUUID().toString()+upfile.getOriginalFilename().substring(upfile.getOriginalFilename().lastIndexOf(".")));
            folder.delete();
            String fileName = upfile.getOriginalFilename();
            params.put("state", "SUCCESS");
            params.put("url", url);
            params.put("size", upfile.getSize());
            params.put("original", fileName);
            params.put("type", upfile.getContentType());

        } catch (Exception e){
            params.put("state", "ERROR");
        }
        return params;
    }
@ApiOperation(value = "富文本单独用于附件上传的方法")
    @RequestMapping(value="/fileUpload")
    @ResponseBody
    public Map<String, Object> files (MultipartFile upfile, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> params = new HashMap<String, Object>();
        System.out.println("1111111111111");
        try{
            String url = ResourceUtils.getURL("").getPath()+upfile.getOriginalFilename();
            File folder = new File(url);
            upfile.transferTo(folder);
            url = OSSUtils.upload(folder, UUID.randomUUID().toString()+upfile.getOriginalFilename().substring(upfile.getOriginalFilename().lastIndexOf(".")));
            System.out.println(UUID.randomUUID().toString()+upfile.getOriginalFilename().substring(upfile.getOriginalFilename().lastIndexOf(".")));
            folder.delete();
            String fileName = upfile.getOriginalFilename();
            params.put("state", "SUCCESS");
            params.put("url", url);
            params.put("size", upfile.getSize());
            params.put("original", fileName);
            params.put("type", upfile.getContentType());
        } catch (Exception e){
            params.put("state", "ERROR");
        }
        return params;
    }
}