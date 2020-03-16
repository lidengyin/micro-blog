package cn.hcnet2006.blog.hcnetwebsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

@Controller
public class UeditorController extends cn.hcnet2006.core.controller.UeditorController {
    @Override
    public String findContent(String content, String content1) throws IOException {
        return super.findContent(content, content1);
    }

    @Override
    public String ueditor(HttpServletRequest request) {
        return super.ueditor(request);
    }

    @Override
    public Map<String, Object> images(MultipartFile upfile, HttpServletRequest request, HttpServletResponse response) {
        return super.images(upfile, request, response);
    }

    @Override
    public Map<String, Object> videos(MultipartFile upfile, HttpServletRequest request, HttpServletResponse response) {
        return super.videos(upfile, request, response);
    }

    @Override
    public Map<String, Object> files(MultipartFile upfile, HttpServletRequest request, HttpServletResponse response) {
        return super.files(upfile, request, response);
    }
    //    @RequestMapping("/")
//    private String showPage(){
//        return "index";
//    }
//    @RequestMapping("/content")
//    @ResponseBody
//    public String findContent(String content, String content1) throws IOException {
//        System.out.println("content1:"+content1);
//        String url = ResourceUtils.getURL("").getPath()+UUID.randomUUID().toString()+".html";
//        File folder = new File(url);
//        FileWriter fileWriter = new FileWriter(folder);
//        fileWriter.write(content1);
//        fileWriter.flush();
//        fileWriter.close();
//        url = OSSUtils.upload(folder,UUID.randomUUID().toString()+".html");
//
//        System.out.println("content:"+url);
//        return url;
//    }
//    @RequestMapping(value="/ueditor")
//    @ResponseBody
//    public String ueditor(HttpServletRequest request) {
//
//        return PublicMsg.UEDITOR_CONFIG;
//    }
//
//    @RequestMapping(value="/imgUpload")
//    @ResponseBody
//    public Map<String, Object> images (MultipartFile upfile, HttpServletRequest request, HttpServletResponse response){
//        Map<String, Object> params = new HashMap<String, Object>();
//        System.out.println("1111111111111");
//        try{
//            String url = ResourceUtils.getURL("").getPath()+upfile.getOriginalFilename();
//            File folder = new File(url);
//            upfile.transferTo(folder);
//            url = OSSUtils.upload(folder, UUID.randomUUID().toString()+upfile.getOriginalFilename().substring(upfile.getOriginalFilename().lastIndexOf(".")));
//            System.out.println(UUID.randomUUID().toString()+upfile.getOriginalFilename().substring(upfile.getOriginalFilename().lastIndexOf(".")));
//            folder.delete();
//            String fileName = upfile.getOriginalFilename();
//            params.put("state", "SUCCESS");
//            params.put("url", url);
//            params.put("size", upfile.getSize());
//            params.put("original", fileName);
//            params.put("type", upfile.getContentType());
//
//        } catch (Exception e){
//            params.put("state", "ERROR");
//        }
//        return params;
//    }
//
//    @RequestMapping(value="/videoUpload")
//    @ResponseBody
//    public Map<String, Object> videos (MultipartFile upfile, HttpServletRequest request, HttpServletResponse response){
//        Map<String, Object> params = new HashMap<String, Object>();
//        System.out.println("1111111111111");
//        try{
//            String url = ResourceUtils.getURL("").getPath()+upfile.getOriginalFilename();
//            File folder = new File(url);
//            upfile.transferTo(folder);
//            url = OSSUtils.upload(folder, UUID.randomUUID().toString()+upfile.getOriginalFilename().substring(upfile.getOriginalFilename().lastIndexOf(".")));
//            System.out.println(UUID.randomUUID().toString()+upfile.getOriginalFilename().substring(upfile.getOriginalFilename().lastIndexOf(".")));
//            folder.delete();
//            String fileName = upfile.getOriginalFilename();
//            params.put("state", "SUCCESS");
//            params.put("url", url);
//            params.put("size", upfile.getSize());
//            params.put("original", fileName);
//            params.put("type", upfile.getContentType());
//
//        } catch (Exception e){
//            params.put("state", "ERROR");
//        }
//        return params;
//    }
//
//    @RequestMapping(value="/fileUpload")
//    @ResponseBody
//    public Map<String, Object> files (MultipartFile upfile, HttpServletRequest request, HttpServletResponse response){
//        Map<String, Object> params = new HashMap<String, Object>();
//        System.out.println("1111111111111");
//        try{
//            String url = ResourceUtils.getURL("").getPath()+upfile.getOriginalFilename();
//            File folder = new File(url);
//            upfile.transferTo(folder);
//            url = OSSUtils.upload(folder, UUID.randomUUID().toString()+upfile.getOriginalFilename().substring(upfile.getOriginalFilename().lastIndexOf(".")));
//            System.out.println(UUID.randomUUID().toString()+upfile.getOriginalFilename().substring(upfile.getOriginalFilename().lastIndexOf(".")));
//            folder.delete();
//            String fileName = upfile.getOriginalFilename();
//            params.put("state", "SUCCESS");
//            params.put("url", url);
//            params.put("size", upfile.getSize());
//            params.put("original", fileName);
//            params.put("type", upfile.getContentType());
//
//        } catch (Exception e){
//            params.put("state", "ERROR");
//        }
//        return params;
//    }
}