package cn.hcnet2006.blog.hcnetwebsite.controller;

import cn.hcnet2006.blog.hcnetwebsite.bean.SysRole;
import cn.hcnet2006.blog.hcnetwebsite.http.HttpResult;
import cn.hcnet2006.blog.hcnetwebsite.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.bytedeco.javacpp.freenect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.scheduling.SchedulingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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

}
