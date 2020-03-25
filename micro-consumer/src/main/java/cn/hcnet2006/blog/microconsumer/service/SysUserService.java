package cn.hcnet2006.blog.microconsumer.service;
import cn.hcnet2006.blog.microconsumer.http.HttpResult;
import io.swagger.annotations.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;

@FeignClient(name = "hcnet-website-1",contextId = "i")
@RequestMapping("/user")
public interface SysUserService {

    @PostMapping("/login")
    public HttpResult login(@RequestParam String name,@RequestParam String password);

    @PostMapping("/register")

    public HttpResult register(@RequestParam String name,@RequestParam String password,@RequestParam Long deptId,@RequestParam String grade,@RequestParam String email,@RequestParam String mobile, @RequestParam(value = "角色ID列表") List<Long> roleList, MultipartFile uploadFile  );
    @PutMapping("/update")

    public HttpResult update(@RequestParam Long id,@RequestParam String name,@RequestParam String password,@RequestParam Long deptId,@RequestParam String grade,@RequestParam String email,@RequestParam String mobile,
                             @RequestParam(value = "角色ID列表",required = false) List<Long> roleList, @RequestParam(value = "uploadFile",required = false) MultipartFile uploadFile);

    @PostMapping("/find/page")
    public HttpResult find(@RequestParam int pageNum,@RequestParam  int pageSize,@RequestParam  Long id,@RequestParam  String name,@RequestParam  String grade,@RequestParam  Long deptId,@RequestParam Byte delFlag);

    @PostMapping("/find/id")
    public HttpResult findById(@RequestParam Long id);
}
