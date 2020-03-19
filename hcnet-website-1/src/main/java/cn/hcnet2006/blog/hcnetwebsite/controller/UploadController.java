package cn.hcnet2006.blog.hcnetwebsite.controller;

import cn.hcnet2006.blog.hcnetwebsite.bean.SysApk;
import cn.hcnet2006.blog.hcnetwebsite.http.HttpResult;
import cn.hcnet2006.blog.hcnetwebsite.page.PageRequest;
import cn.hcnet2006.blog.hcnetwebsite.page.PageResult;
import cn.hcnet2006.blog.hcnetwebsite.service.SysApkService;
import cn.hcnet2006.blog.hcnetwebsite.util.ApkInfoUtil;
import cn.hcnet2006.blog.hcnetwebsite.util.OSSUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Api(tags = "APK安装包上传接口")
@RestController
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private SysApkService sysApkService;
    //文件按时间结构存储
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    @ApiOperation(value = "文件上传",notes = "文件上传")
    @ApiImplicitParams({
            //@ApiImplicitParam(type = "query",name = "createBy",dataType = "String",required = true)
    })
    //@PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/apk")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult upload(@ApiParam(value = "uploadFile",required = true) MultipartFile uploadFile) throws NullPointerException, FileNotFoundException {

        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            //获取文件大小
            String apkSize = uploadFile.getSize()+"";
            String url = ResourceUtils.getURL("").getPath()+uploadFile.getOriginalFilename();
            File folder = new File(url);
            //文件上传名
            String apkOldName = uploadFile.getOriginalFilename();
            //文件存储名:UUID+文件类型后缀
            String apkStoName = UUID.randomUUID().toString()+
                    apkOldName.substring(apkOldName.lastIndexOf("."),apkOldName.length());
            //
            //将上传文件以指定名称上传到指定文件夹
            uploadFile.transferTo(folder);
            Map<String,Object> apkInfo = ApkInfoUtil.readAPK(folder);
            //应用名
            String apkName = (String) apkInfo.get("name");
            //应用包
            String pkName = (String) apkInfo.get("pkName");
            //版本名
            String vName = (String) apkInfo.get("vName");
            //版本号
            long vCode = (long) apkInfo.get("vCode");
            url = OSSUtils.upload(folder, UUID.randomUUID().toString()+".apk");
            folder.delete();
            //设置SysApk
            SysApk sysApk = new SysApk();
            sysApk.setApkName(apkName);
            sysApk.setApkPk(pkName);
            sysApk.setApkVc(vCode);
            sysApk.setApkVn(vName);
            sysApk.setCreateBy(authentication.getName());
            sysApk.setCreateTime(new Date());
            sysApk.setLastUpdateBy(authentication.getName());
            sysApk.setLastUpdateTime(new Date());
            sysApk.setApkSize(apkSize);
            sysApk.setApkUrl(url);
            sysApk.setDelFlag((byte)0);
            sysApkService.save(sysApk);
            return HttpResult.ok(sysApk);
        }catch (IOException e){
            e.printStackTrace();

        }
        return HttpResult.error("上传失败");
    }
    @ApiOperation(value = "根据删除标志，分页查看上传文件",notes = "根据删除标志，分页查看上传文件")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "pageNum", value = "页码",required = true),
            @ApiImplicitParam(type = "query", name = "pageSize", value = "行数",required = true),
            @ApiImplicitParam(type = "query",name = "delFlag", value = "删除标志，-1为删除，0为正常",required = true)
    })
    @PostMapping("/select/delFlag")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult selectByDelFlag(int pageNum, int pageSize, Byte delFlag){
        Map<String, Object> map = new HashMap<>();
        map.put("delFlag", delFlag);
        PageRequest pageRequest = new PageRequest(pageNum,pageSize,map);
        PageResult pageResult = sysApkService.findPage(pageRequest);
        return HttpResult.ok(pageResult);
    }
}
