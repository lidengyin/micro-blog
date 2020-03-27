package cn.hcnet2006.blog.microconsumer.controller;

import cn.hcnet2006.blog.microconsumer.http.HttpResult;
import cn.hcnet2006.blog.microconsumer.service.SysUserService;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
@Api(tags = "用户信息接口")
@RestController
@RequestMapping("/feign/user")
public class UserController {
    @Autowired
    private SysUserService sysUserService;
    @ApiOperation(value = "用户登录",notes = "用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "name", value = "用户名",required = true),
            @ApiImplicitParam(type = "query", name = "password", value = "密码",required = true)
    })
    @PostMapping("/login")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult login(String name, String password) {
        //获取用户信息
        return sysUserService.login(name,password);
    }

    @ApiOperation(value = "用户注册",notes = "用户注册")

    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "name",value = "用户名",required = true),
            @ApiImplicitParam(type = "query", name = "password",value = "密码",required = true),
            @ApiImplicitParam(type = "query", name = "deptId",value = "所属方向ID",required = true),
            @ApiImplicitParam(type = "query", name = "grade",value = "年级，比如2018",required = true),
            @ApiImplicitParam(type = "query", name = "email",value = "邮箱，确保格式正确",required = true),
            @ApiImplicitParam(type = "query", name = "mobile",value = "手机，确保格式正确",required = true),
    })
    @PostMapping("/register")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult register(String name, String password, Long deptId, String grade, String email, String mobile, @RequestParam List<Long> roleList, MultipartFile uploadFile  ) throws FileNotFoundException {
        System.out.println("112121");
        for(Long a : roleList){
            System.out.println("role："+a);
        }
        return   sysUserService.register(name, password, deptId, grade, email, mobile, roleList, uploadFile);

    }
    @ApiOperation(value = "用户修改",notes = "用户修改,这里必须说的是，roleList用户角色ID列表的修改，每次都是删除以前的，然后加上新增的")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name="id",value = "用户编号",required = true),
            @ApiImplicitParam(type = "query", name = "name",value = "用户名"),
            @ApiImplicitParam(type = "query", name = "password",value = "密码"),
            @ApiImplicitParam(type = "query", name = "deptId",value = "所属方向ID"),
            @ApiImplicitParam(type = "query", name = "grade",value = "年级，比如2018"),
            @ApiImplicitParam(type = "query", name = "email",value = "邮箱，确保格式正确"),
            @ApiImplicitParam(type = "query", name = "mobile",value = "手机，确保格式正确"),
            @ApiImplicitParam(type = "query", name = "delFlag",value = "删除标志，-1删除，0正常")
    })
    @PutMapping("/update")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult update(Long id, String name, String password, Long deptId, String grade, String email, String mobile,
                             @RequestParam(required = false) List<Long> roleList,  MultipartFile uploadFile) throws IOException,NullPointerException {

        return sysUserService.update(id,name,password,deptId,grade,email,mobile,roleList,uploadFile);
    }
    @ApiOperation(value = "分页查看用户列表",notes = "分页查看用户列表:可选参数列表，以and的形式，随机组合，不加参数就是全选")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "当前页码",required = true),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页行数",required = true),
            @ApiImplicitParam(type = "query", name="id",value = "用户编号"),
            @ApiImplicitParam(type = "query", name = "name",value = "用户名"),
            @ApiImplicitParam(type = "query", name = "deptId",value = "所属方向ID"),
            @ApiImplicitParam(type = "query", name = "grade",value = "年级，比如2018"),
            @ApiImplicitParam(type = "query", name = "delFlag",value = "删除标志，-1删除，0正常")
    })
   // @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/find/page")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult find(int pageNum, int pageSize, Long id, String name, String grade, Long deptId, Byte delFlag){

        return sysUserService.find(pageNum,pageSize,id,name,grade,deptId,delFlag);
    }
    @ApiOperation(value = "具体查看某一个用户信息",notes = "具体查看某一个用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "id", value = "用户编号", required = true)
    })
    @PostMapping("/find/id")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult findById(Long id){
        return sysUserService.findById(id);
    }
}
