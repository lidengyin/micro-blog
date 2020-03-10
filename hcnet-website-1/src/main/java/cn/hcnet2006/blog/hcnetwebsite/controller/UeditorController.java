package cn.hcnet2006.blog.hcnetwebsite.controller;
import cn.hcnet2006.blog.hcnetwebsite.ueditor.PublicMsg;
import cn.hcnet2006.blog.hcnetwebsite.util.OSSUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Controller
public class UeditorController {
    @RequestMapping("/")
    private String showPage(){
        return "index";
    }
    @RequestMapping("/content")
    @ResponseBody
    public String findContent(String content, String content1) throws IOException {
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
    @RequestMapping(value="/ueditor")
    @ResponseBody
    public String ueditor(HttpServletRequest request) {

        return PublicMsg.UEDITOR_CONFIG;
    }

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
}