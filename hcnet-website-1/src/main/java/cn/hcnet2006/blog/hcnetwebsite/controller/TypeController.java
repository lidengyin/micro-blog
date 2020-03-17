package cn.hcnet2006.blog.hcnetwebsite.controller;

import cn.hcnet2006.blog.hcnetwebsite.bean.SysType;
import cn.hcnet2006.blog.hcnetwebsite.service.SysTypeService;
import cn.hcnet2006.blog.hcnetwebsite.http.HttpResult;
import cn.hcnet2006.blog.hcnetwebsite.page.PageRequest;
import cn.hcnet2006.blog.hcnetwebsite.page.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "文章类型信息接口")
@RestController
@RequestMapping("/type")
public class TypeController {
    @Autowired
    private SysTypeService sysTypeService;
    @ApiOperation(value = "文章类型注册",notes = "文章类型注册")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query",name = "name",required = true)
    })
    @PostMapping("/register")
    public HttpResult upload(String name){

        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            SysType sysType = new SysType();
            sysType.setName(name);
            sysType.setCreateBy(authentication.getName());
            sysType.setCreateTime(new Date());
            sysType.setLastUpdateTime(new Date());
            sysType.setDelFlag((byte)0);
            sysType.setLastUpdateBy(authentication.getName());
            sysTypeService.save(sysType);
            return HttpResult.ok(sysType);
        }catch (DuplicateKeyException e){
            e.printStackTrace();
            return HttpResult.error("类型重复注册");
        }
    }
    @ApiOperation(value = "批量文章类型修改",notes = "文章类型修改，需要参数如下，自主组合，and模式\n" +
            "        @ApiImplicitParam(type = \"query\",name = \"id\", required = true,value=类型ID),\n" +
            "            @ApiImplicitParam(type = \"query\", name = \"name\",required = false,value=类型名),\n" +
            "            @ApiImplicitParam(type = \"query\", name = \"delFlag\", required = false，value=删除标志)")
    @ApiImplicitParams({
    })
    @PutMapping("/update")
    public HttpResult update(@RequestBody List<SysType> sysTypes){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            for(SysType sysType: sysTypes){
                sysType.setLastUpdateBy(authentication.getName());
                sysType.setLastUpdateTime(new Date());
                sysTypeService.update(sysType);
            }
            return HttpResult.ok("类型修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error("类型修改失败");
        }
    }
    @ApiOperation(value = "查看类型列表",notes = "查看类型列表\n" +
            "            @ApiImplicitParam(type = \"query\", name = \"pageNum\",value = \"页码\"),\n" +
            "            @ApiImplicitParam(type = \"query\", name = \"pageSize\",value = \"页行\"),\n" +
            "            @ApiImplicitParam(type = \"query\",name = \"id\",value = \"类型ID\"),\n" +
            "            @ApiImplicitParam(type = \"query\", name = \"name\", value = \"类型名\"),\n" +
            "            @ApiImplicitParam(type = \"query\",name = \"delFlag\", value = \"删除标志\")")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "pageNum",value = "页码"),
            @ApiImplicitParam(type = "query", name = "pageSize",value = "页行"),

    })
    @PostMapping("/find/page")
    public HttpResult findPage(int pageNum, int pageSize, @RequestBody SysType sysType){
        try{
            Map<String, Object> map = new HashMap<>();
            map.put("sysType",sysType);
            PageRequest pageRequest = new PageRequest(pageNum, pageSize, map);
            PageResult pageResult = sysTypeService.findPage(pageRequest);
            return HttpResult.ok(pageResult);
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error("分页查询失败");
        }
    }
}
