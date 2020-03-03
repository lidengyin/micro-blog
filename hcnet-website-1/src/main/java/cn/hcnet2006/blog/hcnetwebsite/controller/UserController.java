package cn.hcnet2006.blog.hcnetwebsite.controller;

import cn.hcnet2006.blog.hcnetwebsite.bean.SysUser;
import cn.hcnet2006.blog.hcnetwebsite.http.HttpResult;
import cn.hcnet2006.blog.hcnetwebsite.service.SysUserService;
import cn.hcnet2006.blog.hcnetwebsite.util.OSSUtils;
import io.swagger.annotations.*;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Api(tags = "用户信息接口")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private SysUserService sysUserService;
    //private String url = "/usr/local/spring";
    @ApiOperation(value = "用户注册",notes = "用户注册" +
            "参数包括：" +
            "1.")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "name",value = "用户名",required = true),
            @ApiImplicitParam(type = "query", name = "password",value = "密码",required = true),
            @ApiImplicitParam(type = "query", name = "deptId",value = "所属方向ID",required = true),
            @ApiImplicitParam(type = "query", name = "grade",value = "年级，比如2018",required = true),
            @ApiImplicitParam(type = "query", name = "email",value = "邮箱，确保格式正确",required = true),
            @ApiImplicitParam(type = "query", name = "mobile",value = "手机，确保格式正确",required = true),
            @ApiImplicitParam(type = "query", name = "createBy",value = "创建者",required = true),
            //@ApiImplicitParam(type = "query", name = "createTime",value = "创建时间",required = true)
    })
    @PostMapping("/save")
    public HttpResult register(SysUser sysUser, @ApiParam(value = "uploadFile", required = true) MultipartFile uploadFile,
                               HttpServletRequest request) throws FileNotFoundException {

        //新建暂时缓存目录,该目录一定存在
        String url = ResourceUtils.getURL("").getPath()+uploadFile.getOriginalFilename();
        System.out.println(url);
        File folder = new File(url);
        try{
            //转义文件到服务器
            uploadFile.transferTo(folder);
            //从服务器获取文件传递到阿里云OSS.返回下载链接地址
            String avator_url = OSSUtils.upload(folder,sysUser.getName()+".jpg");
            //删除服务器缓存文件
            folder.delete();
            //设置属性
            //设置用户头像
            sysUser.setAvator(avator_url);
            //设置创建时间
            sysUser.setCreateTime(new Date());
            //设置更新时间
            sysUser.setLastUpdateTime(new Date());
            //设置创建者
            sysUser.setLastUpdateBy(sysUser.getCreateBy());
            //删除标志
            sysUser.setDelFlag((byte)0);
            //保存
            sysUserService.save(sysUser);
            return HttpResult.ok(sysUser);
        }catch (DuplicateKeyException e){
            return HttpResult.error("重复注册");
        }catch (IOException e){
            e.printStackTrace();
            return HttpResult.error("注册失败");
        }

    }
    @ApiOperation(value = "用户修改",notes = "用户修改")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name="id",value = "用户编号",required = true),
            @ApiImplicitParam(type = "query", name = "name",value = "用户名"),
            @ApiImplicitParam(type = "query", name = "password",value = "密码"),
            @ApiImplicitParam(type = "query", name = "deptId",value = "所属方向ID"),
            @ApiImplicitParam(type = "query", name = "grade",value = "年级，比如2018"),
            @ApiImplicitParam(type = "query", name = "email",value = "邮箱，确保格式正确"),
            @ApiImplicitParam(type = "query", name = "mobile",value = "手机，确保格式正确"),
            @ApiImplicitParam(type = "query", name = "lastUpdateBy",value = "修改者"),
            @ApiImplicitParam(type = "query", name = "delFlag",value = "删除标志，-1删除，0正常")
            //@ApiImplicitParam(type = "query", name = "createTime",value = "创建时间",required = true)
    })
    @PostMapping("/update")
    public HttpResult update(SysUser sysUser, @ApiParam(value = "uploadFile",required = false) MultipartFile uploadFile) throws IOException,NullPointerException {
        try{
            if (uploadFile !=null){
                String url = ResourceUtils.getURL("").getPath()+uploadFile.getOriginalFilename();
                File folder = new File(url);
                uploadFile.transferTo(folder);
                String avator_url = OSSUtils.upload(folder, UUID.randomUUID().toString() +".jpg");
                folder.delete();
                sysUser.setAvator(avator_url);
            }
            sysUser.setLastUpdateTime(new Date());
            sysUserService.update(sysUser);
            return HttpResult.ok(sysUser);
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error("用户修改失败");
        }

    }
}
