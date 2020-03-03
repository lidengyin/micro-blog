package cn.hcnet2006.blog.hcnetwebsite.controller;

import cn.hcnet2006.blog.hcnetwebsite.bean.SysMenu;
import cn.hcnet2006.blog.hcnetwebsite.bean.SysRole;
import cn.hcnet2006.blog.hcnetwebsite.http.HttpResult;
import cn.hcnet2006.blog.hcnetwebsite.service.SysRoleService;
import io.swagger.annotations.*;
import org.bytedeco.javacpp.freenect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.scheduling.SchedulingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Api(tags = "角色信息接口")
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private SysRoleService sysRoleService;
    @ApiOperation(value = "角色注册",notes = "角色注册")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query",name = "name",value = "角色名",required = true),
            @ApiImplicitParam(type = "query", name = "remark",value = "角色备注",required = true),
            @ApiImplicitParam(type = "query", name = "createBy",value = "创建者",required = true),
    })
    @PostMapping("/register")
    public HttpResult upload(SysRole sysRole){
        try{
            sysRole.setCreateTime(new Date());
            sysRole.setLastUpdateTime(new Date());
            sysRole.setLastUpdateBy(sysRole.getCreateBy());
            sysRole.setDelFlag((byte)0);
            sysRoleService.save(sysRole);
            return HttpResult.ok(sysRole);
        }catch (DuplicateKeyException e){
            return HttpResult.error("角色注册重复");
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error("角色注册失败");
        }
    }
    @ApiOperation(value = "角色列表修改",notes = "角色列表修改:\n" +
            " @ApiImplicitParam(type = \"query\", name = \"id\", value = \"编号\",required = true),\n" +
            "            @ApiImplicitParam(type = \"query\",name = \"name\",value = \"角色名\"),\n" +
            "            @ApiImplicitParam(type = \"query\", name = \"remark\",value = \"角色备注\"),\n" +
            "            @ApiImplicitParam(type = \"query\", name = \"lastUpdateBy\",value = \"修改者\"),\n" +
            "            @ApiImplicitParam(type = \"query\", name = \"delFlag\",value = \"删除标志，-1删除，0正常\")")
    @ApiImplicitParams({
    })
    @PostMapping("/update/list")
    public HttpResult update( @RequestBody List<SysRole> sysRoles){
        try{
            for(SysRole sysRole: sysRoles){

                sysRole.setLastUpdateTime(new Date());
                sysRoleService.update(sysRole);
            }
            return HttpResult.ok(sysRoles);
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error("角色修改失败");
        }
    }
}
