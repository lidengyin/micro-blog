package cn.hcnet2006.blog.hcnetwebsite.controller;

import cn.hcnet2006.blog.hcnetwebsite.bean.SysDept;
import cn.hcnet2006.blog.hcnetwebsite.bean.SysMenu;
import cn.hcnet2006.blog.hcnetwebsite.bean.SysRole;
import cn.hcnet2006.blog.hcnetwebsite.http.HttpResult;
import cn.hcnet2006.blog.hcnetwebsite.service.SysMenuService;
import cn.hcnet2006.blog.hcnetwebsite.util.OSSUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    @ApiOperation(value = "批量菜单修改",notes = "批量菜单修改\n" +
            "示例" +
            "@ApiImplicitParams({\n" +
            "            @ApiImplicitParam(type = \"query\", name = \"id\", value = \"菜单编号\",required = true),\n" +
            "            @ApiImplicitParam(type = \"query\", name = \"name\",value = \"菜单名\"),\n" +
            "            @ApiImplicitParam(type = \"query\", name = \"parentId\",value = \"父菜单ID，一级菜单为0\"),\n" +
            "            @ApiImplicitParam(type = \"query\", name = \"perms\",value = \"授权\"),\n" +
            "            @ApiImplicitParam(type = \"query\", name = \"lastUpdateBy\",value = \"修改人\"),\n" +
            "            @ApiImplicitParam(type = \"query\", name = \"delFlag\",value = \"删除标志，-1删除，0正常\")\n" +
            "    })")
    @ApiImplicitParams({
    })
    @PostMapping("/update/list")
    public HttpResult update(@RequestBody List<SysMenu> sysMenus) throws IOException {
        try{
            for (SysMenu sysMenu: sysMenus){
                sysMenu.setLastUpdateTime(new Date());
                sysMenuService.update(sysMenu);
            }
            return HttpResult.ok(sysMenus);
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error("菜单修改失败");
        }
    }
}
