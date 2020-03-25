package cn.hcnet2006.blog.microconsumer.service;

import cn.hcnet2006.blog.microconsumer.bean.SysRole;
import cn.hcnet2006.blog.microconsumer.http.HttpResult;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@FeignClient(name = "hcnet-website-1",contextId = "f")
@RequestMapping("/role")
public interface SysRoleService {

    @PostMapping("/register")
    public HttpResult upload(@RequestParam String name,@RequestParam String remark, @RequestBody List<Long> menus);

    @PutMapping("/update/list")
    public HttpResult update(@RequestBody List<SysRole> sysRoles, @RequestParam(value = "菜单编号",required = false) List<Long> menus);

    @PostMapping("/find/page")
    public HttpResult find(@RequestParam int pageNum,@RequestParam  int pageSize,@RequestParam  String name,@RequestParam  Byte delFlag);

    @PostMapping("/find/id")
    public HttpResult findById(@RequestParam Long id);
}
