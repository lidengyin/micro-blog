package cn.hcnet2006.blog.microconsumer.service;

import cn.hcnet2006.blog.microconsumer.bean.SysType;
import cn.hcnet2006.blog.microconsumer.http.HttpResult;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@FeignClient(name = "hcnet-website-1",contextId = "g")
@RequestMapping("/type")
public interface SysTypeService {
    @PostMapping("/register")
    public HttpResult upload(@RequestParam String name);

    @PutMapping("/update")
    public HttpResult update(@RequestBody List<SysType> sysTypes);

    @PostMapping("/find/page")
    public HttpResult findPage(@RequestParam int pageNum,@RequestParam  int pageSize,@RequestParam  String name,@RequestParam  Long id,@RequestParam Byte delFlag);
}
