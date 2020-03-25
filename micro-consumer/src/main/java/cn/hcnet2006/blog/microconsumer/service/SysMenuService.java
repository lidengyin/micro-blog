package cn.hcnet2006.blog.microconsumer.service;

import cn.hcnet2006.blog.microconsumer.bean.SysMenu;
import cn.hcnet2006.blog.microconsumer.http.HttpResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

@FeignClient(name = "hcnet-website-1",contextId = "d")
@RequestMapping("/menu")
public interface SysMenuService {

    @PostMapping("/register")
    public HttpResult upload(@RequestParam String name, @RequestParam Long parentId, @RequestParam String perms);

    @PutMapping("/update/list")
    public HttpResult update(@RequestBody List<SysMenu> sysMenus);

    @PostMapping("/find/page")
    public HttpResult find(@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam Long id , @RequestParam String name,@RequestParam  Long parentId, @RequestParam String perms, @RequestParam Byte delFlag);

}
