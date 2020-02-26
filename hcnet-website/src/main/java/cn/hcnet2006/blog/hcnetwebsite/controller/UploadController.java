package cn.hcnet2006.blog.hcnetwebsite.controller;

import cn.hcnet2006.blog.hcnetwebsite.bean.SysApk;
import cn.hcnet2006.blog.hcnetwebsite.http.HttpResult;
import cn.hcnet2006.blog.hcnetwebsite.page.PageRequest;
import cn.hcnet2006.blog.hcnetwebsite.page.PageResult;
import cn.hcnet2006.blog.hcnetwebsite.service.SysApkService;
import cn.hcnet2006.blog.hcnetwebsite.utils.ApkInfoUtil;
import cn.hcnet2006.blog.hcnetwebsite.utils.HttpUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Api(tags = "上传信息接口")
@RestController
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private SysApkService sysApkService;
    //文件按时间结构存储
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    @ApiOperation(value = "文件上传",notes = "文件上传")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query",name = "createBy",dataType = "String",required = true)
    })
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/apk")
    public HttpResult upload(@ApiParam(value = "uploadFile",required = true) MultipartFile uploadFile,String createBy, HttpServletRequest req) throws NullPointerException{
        //获取文件大小
        String apkSize = uploadFile.getSize()+"";
        //应用类型
        String cType = uploadFile.getContentType();
        System.out.println("应用类型："+cType);
        //规划文件上传路径
        String realPath = "/home/lidengyin/Documents/uploadFile/apk/";
        //文件目录格式，时期划分
        String format = sdf.format(new Date());
        //指定上传文件目录
        File folder = new File(realPath + format);
        if(!folder.isDirectory()){
            //生成路径下所有目录
            folder.mkdirs();
        }

        //文件上传名
        String apkOldName = uploadFile.getOriginalFilename();
        System.out.println("fileName:"+apkOldName);
        //文件存储名:UUID+文件类型后缀
        String apkStoName = UUID.randomUUID().toString()+
                apkOldName.substring(apkOldName.lastIndexOf("."),apkOldName.length());
        //
        try{
            //将上传文件以指定名称上传到指定文件夹
            uploadFile.transferTo(new File(folder, apkStoName));
            //获取文件在服务器存储路径
            String filePath = folder.getPath()+"/"+apkStoName;

            System.out.println("filePath:"+filePath);

            File convFile = new File(filePath);
            Map<String,Object> apkInfo = ApkInfoUtil.readAPK(convFile);
            //应用名
            String apkName = (String) apkInfo.get("name");
            //应用包
            String pkName = (String) apkInfo.get("pkName");
            //版本名
            String vName = (String) apkInfo.get("vName");
            //版本号
            long vCode = (long) apkInfo.get("vCode");
            System.out.println("名称:"+apkName);
            System.out.println("包名:"+pkName);
            System.out.println("版本名:"+vName);
            System.out.println("版本号:"+vCode);
            //设置SysApk
            SysApk sysApk = new SysApk();
            sysApk.setApkName(apkName);
            sysApk.setApkPk(pkName);
            sysApk.setApkVc(vCode);
            sysApk.setApkVn(vName);
            sysApk.setCreateBy(createBy);
            sysApk.setCreateTime(new Date());
            sysApk.setLastUpdateBy(createBy);
            sysApk.setLastUpdateTime(new Date());
            sysApk.setApkSize(apkSize);
            sysApk.setApkUrl(filePath);
            sysApk.setDelFlag((byte)0);
            sysApkService.save(sysApk);
            return HttpResult.ok(filePath);
        }catch (IOException e){
            e.printStackTrace();

        }
        return HttpResult.error("上传失败");
    }

    @ApiOperation(value = "下载信息接口",notes = "下载信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query",name = "id",value = "资源序号",required = true)
    })
    @PostMapping("/download")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*",allowCredentials = "true")
    public HttpResult download(String id, HttpServletResponse resp,HttpServletRequest req){
        try{
            System.out.println("id:"+id);
            //根据主键获取对应文件
            SysApk sysApk = sysApkService.findById(Long.parseLong(id));
            System.out.println("name:"+sysApk.getApkName());
            System.out.println("url:"+sysApk.getApkUrl());

            //文件大小
            String content_length = sysApk.getApkSize();
            //文件下载路径
            String apk_url = sysApk.getApkUrl();
            //生成文件
            File file = new File(apk_url);
            System.out.println("fileName:"+file.getName());
            System.out.println("fileIsDirectory:"+file.isDirectory());
//            if(!file.isDirectory()){
//                return HttpResult.error("文件已被破坏，无法下载");
//            }
            //设置文件内容
            resp.setContentType("application/octet-stream");
            //下载显示的文件名
            String apk_name = sysApk.getApkName();
            //下载显示文件名解决中文乱码问题
            //在下载之前首先判断是否是微软浏览器，如果是，用UTF-8编码,
            //如果不是，用万能解码
            //这样就可以在IE8-11，EDGE,FIXBO一级Chrome浏览器下载文件时，不会中文乱码了
            boolean isMSIE = HttpUtils.isMSBrowser(req);
            //如果是IE浏览器
            if(isMSIE){
                apk_name = URLEncoder.encode(apk_name,"UTF-8");
            }else{
                //其他浏览器
                apk_name = new String(apk_name.getBytes("UTF-8"),"ISO-8859-1");
            }
            //设置response的Content-disposition时，apk_name的值要加上双引号如果不佳双引号
            //在火狐下载数据时，如果文件名是英文加中文的组合，那么火狐在下载时，文件名只有英文部分
            //只有加了双引号之后，文件名和代码设置的文件名一致，因为这个双引号是在字符串中的，所以需
            //要加反斜杠来转义

            resp.setHeader("Content-disposition","attachment;filename:"+apk_name);
            //设置文件大小，这样就可以在下载时，显示文件的大小
            resp.setContentLength(Integer.parseInt(content_length));

            //读取要下载的文件保存到文件输入流
            InputStream in = new FileInputStream(apk_url);
            //创建文件输出流
            OutputStream out = resp.getOutputStream();
            //创建缓冲区
            byte buffer[] = new byte[1024];
            int len = 0;
            while((len = in.read(buffer)) > 0){
                out.write(buffer,0,len);
            }
            //关闭输入流
            in.close();
            //关闭输出流
            out.close();
            return HttpResult.ok("下载成功");
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error("下载失败");
        }
    }
    @ApiOperation(value = "根据删除标志，分页查看上传文件",notes = "根据删除标志，分页查看上传文件")
    @ApiImplicitParams({
//            @ApiImplicitParam(type = "query", name = "pageNum", value = "页码",required = true),
//            @ApiImplicitParam(type = "query", name = "pageSize", value = "行数",required = true),
//            @ApiImplicitParam(type = "query",name = "delFlag", value = "删除标志，-1为删除，0为正常",required = true)
    })
    @PostMapping("/select/delFlag")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @CrossOrigin(maxAge = 3600, origins = "*", allowedHeaders = "*",allowCredentials = "true")
    public HttpResult selectByDelFlag(@RequestBody PageRequest pageRequest){
        Byte delFlag = Byte.parseByte( pageRequest.getParam("delFlag")+"") ;
        Map<String ,Object> map = new HashMap<>();
        map.put("delFlag", delFlag);
        pageRequest.setParams(map);
        System.out.println(pageRequest.getParam("delFlag"));
        PageResult pageResult = sysApkService.findPage(pageRequest);
        return HttpResult.ok(pageResult);
    }

    @ApiOperation(value = "修改可用性",notes = "修改可用性")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query",name = "id",value = "ID",required = true),
            @ApiImplicitParam(type = "query",name = "delFlag",value = "删除标志，-1删除，0正常",required = true)
    })
    @PostMapping("/update/id")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public HttpResult updateDelFlagById(String id, String delFlag){
        try{
            SysApk sysApk = new SysApk();
            sysApk.setId(Long.parseLong(id));
            sysApk.setDelFlag(Byte.parseByte(delFlag));
            int result = sysApkService.delete(sysApk);
            return HttpResult.ok(sysApk);
        }catch (Exception e){
            return HttpResult.error("修改失败");
        }
    }
}
