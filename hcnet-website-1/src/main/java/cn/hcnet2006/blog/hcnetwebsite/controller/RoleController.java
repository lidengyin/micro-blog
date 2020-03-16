package cn.hcnet2006.blog.hcnetwebsite.controller;

import cn.hcnet2006.blog.hcnetwebsite.bean.SysMenu;
import cn.hcnet2006.blog.hcnetwebsite.bean.SysRole;
import cn.hcnet2006.blog.hcnetwebsite.bean.SysRoleMenu;
import cn.hcnet2006.core.http.HttpResult;
import cn.hcnet2006.core.page.PageRequest;
import cn.hcnet2006.core.page.PageResult;
import cn.hcnet2006.blog.hcnetwebsite.service.SysRoleService;
import com.netflix.hystrix.metric.consumer.HystrixDashboardStream;
import com.netflix.ribbon.proxy.annotation.Http;
import io.swagger.annotations.*;
import org.bytedeco.javacpp.freenect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.scheduling.SchedulingException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import sun.security.acl.PrincipalImpl;

import javax.validation.constraints.Max;
import java.security.Principal;
import java.util.*;

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
    public HttpResult upload(SysRole sysRole, @RequestBody List<Long> menus){
        try{
            sysRole.setCreateTime(new Date());
            sysRole.setLastUpdateTime(new Date());
            sysRole.setLastUpdateBy(sysRole.getCreateBy());
            sysRole.setDelFlag((byte)0);
            sysRoleService.save(sysRole);
            //初始化设置关联权限
           for(long sysMenu: menus){
               SysRoleMenu sysRoleMenu = new SysRoleMenu();
               sysRoleMenu.setId(UUID.randomUUID().toString());
               sysRoleMenu.setCreateBy(sysRole.getCreateBy());
               sysRoleMenu.setRoleId(sysRole.getId());
               sysRoleMenu.setMenuId(sysMenu);
               sysRoleMenu.setCreateTime(new Date());
               sysRoleMenu.setLastUpdateTime(new Date());
               sysRoleMenu.setLastUpdateBy(sysRole.getCreateBy());
               sysRoleMenu.setDelFlag((byte)0);
               sysRoleService.saveRoleAndMenu(sysRoleMenu);
           }
            return HttpResult.ok(sysRole);
        }catch (DuplicateKeyException e){
            return HttpResult.error("角色注册重复");
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error("角色注册失败");
        }
    }
    @ApiOperation(value = "角色列表修改",notes = "角色列表修改，修改角色只能单一修改,切记:\n" +
            " @ApiImplicitParam(type = \"query\", name = \"id\", value = \"编号\",required = true),\n" +
            "            @ApiImplicitParam(type = \"query\",name = \"name\",value = \"角色名\"),\n" +
            "            @ApiImplicitParam(type = \"query\", name = \"remark\",value = \"角色备注\"),\n" +
            "            @ApiImplicitParam(type = \"query\", name = \"lastUpdateBy\",value = \"修改者\"),\n" +
            "            @ApiImplicitParam(type = \"query\", name = \"delFlag\",value = \"删除标志，-1删除，0正常\")")
    @ApiImplicitParams({
//            @ApiImplicitParam(type = "query",name = "menus", value = "菜单编号列表",paramType = "body",dataType = "java.util.List")
    })

    @PostMapping("/update/list")
    public HttpResult update(@RequestBody List<SysRole> sysRoles, @RequestParam(value = "菜单编号",required = false) List<Long> menus,
                             Principal principal){
        try{
            System.out.println("prin:"+principal.getName());
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            System.out.println("auth:"+authentication.getName());
            for(SysRole sysRole: sysRoles){


                if(menus != null){
                    int result = sysRoleService.updateRoleAndMenuDelFlag(sysRole.getId());
                    for(Long sysMenu: menus){

                        SysRoleMenu sysRoleMenu = new SysRoleMenu();
                        sysRoleMenu.setId(UUID.randomUUID().toString());
                        sysRoleMenu.setCreateBy(principal.getName());
                        sysRoleMenu.setRoleId(sysRole.getId());
                        sysRoleMenu.setMenuId(sysMenu);
                        sysRoleMenu.setCreateTime(new Date());
                        sysRoleMenu.setLastUpdateTime(new Date());
                        sysRoleMenu.setLastUpdateBy(principal.getName());
                        sysRoleMenu.setDelFlag((byte)0);
                        sysRoleService.saveRoleAndMenu(sysRoleMenu);
                    }
                }else{
                    sysRole.setLastUpdateTime(new Date());
                    sysRoleService.update(sysRole);
                }
            }
            return HttpResult.ok(sysRoles);
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error("角色修改失败");
        }
    }

    @ApiOperation(value = "分页查询角色",notes = "分页查询角色\n" +
            "@ApiImplicitParam(type = \"query\", name = \"id\",value = \"编号\",required = true)\n" +
            "            @ApiImplicitParam(type = \"query\",name = \"name\",value = \"角色名\",required = true),\n"+
            "            @ApiImplicitParam(type = \"query\", name = \"delFlag\",value = \"删除标志，-1删除，0正常\")\n"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "当前页码",required = true),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页行数",required = true),
    })
    @PostMapping("/find/page")
    public HttpResult find(int pageNum, int pageSize, @RequestBody SysRole sysRole){
        try{
            Map<String, Object> map = new HashMap<>();
            map.put("sysRole", sysRole);
            PageRequest pageRequest  = new PageRequest(pageNum, pageSize, map);
            PageResult pageResult = sysRoleService.findPage(pageRequest);
            return HttpResult.ok(pageResult);
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error("分页查询失败");
        }
    }
    @ApiOperation(value = "具体查询某个角色",notes = "具体查询某个角色")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "id", value = "编号", required = true)
    })
    @PostMapping("/find/id")
    public HttpResult findById(Long id){
        try{
            SysRole sysRole = sysRoleService.findById(id);
            return HttpResult.ok(sysRole);

        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error("查询菜单失败");
        }
    }
    @ApiOperation(value = "角色关联菜单", notes = "角色关联菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "roleId", value = "角色编号",required = true),
            @ApiImplicitParam(type = "query", name = "menuId", value = "菜单编号",required = true),
            @ApiImplicitParam(type = "query", name = "createBy", value = "创建者",required = true)
    })
    @PostMapping("/register/connect/menu")
    public HttpResult connectRoleAndUser(SysRoleMenu sysRoleMenu){
        return HttpResult.ok("hello");
    }
}
