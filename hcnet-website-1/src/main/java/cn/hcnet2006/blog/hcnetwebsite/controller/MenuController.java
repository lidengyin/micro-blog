package cn.hcnet2006.blog.hcnetwebsite.controller;

import cn.hcnet2006.blog.hcnetwebsite.bean.SysMenu;
import cn.hcnet2006.blog.hcnetwebsite.http.HttpResult;
import cn.hcnet2006.blog.hcnetwebsite.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Api(tags = "菜单信息接口")
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private SysMenuService sysMenuService;
    @ApiOperation(value = "菜单注册",notes = "菜单注册")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "name",value = "菜单名",required = true),
            @ApiImplicitParam(type = "query", name = "parentId",value = "父菜单ID，一级菜单为0",required = true),
            @ApiImplicitParam(type = "query", name = "perms",value = "授权",required = true),
            @ApiImplicitParam(type = "query", name = "createBy",value = "创建人",required = true),

    })
    @PostMapping("/register")
    public HttpResult upload(SysMenu sysMenu){
        try{
            sysMenu.setCreateTime(new Date());
            sysMenu.setLastUpdateTime(new Date());
            sysMenu.setLastUpdateBy(sysMenu.getCreateBy());
            sysMenu.setDelFlag((byte)0);
            sysMenuService.save(sysMenu);
            return HttpResult.ok(sysMenu);
        }catch (DuplicateKeyException e){
            return HttpResult.error("菜单注册重复");
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error("菜单注册失败");
        }
    }


}
