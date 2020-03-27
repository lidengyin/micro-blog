package cn.hcnet2006.blog.microconsumer.service;
import cn.hcnet2006.blog.microconsumer.http.HttpResult;
import io.swagger.annotations.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;

@FeignClient(name = "hcnet-website-1",contextId = "i")
@RequestMapping("/user")
public interface SysUserService {

    @PostMapping("/login")

    public HttpResult login(@RequestParam String name,@RequestParam String password);

    @RequestMapping(value = "/register",method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public HttpResult register(@RequestParam String name,@RequestParam String password,@RequestParam Long deptId,@RequestParam String grade,@RequestParam String email,@RequestParam String mobile,
                               @RequestParam List<Long> roleList,@RequestBody MultipartFile uploadFile);

    @RequestMapping(value = "/update",method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public HttpResult update(@RequestParam Long id,@RequestParam(required = false) String name,@RequestParam(required = false) String password,@RequestParam(required = false) Long deptId,@RequestParam(required = false) String grade,@RequestParam(required = false) String email,@RequestParam(required = false) String mobile,
                             @RequestParam(required = false) List<Long> roleList, @RequestBody(required = false) MultipartFile uploadFile);

    @PostMapping("/find/page")
    public HttpResult find(@RequestParam int pageNum,@RequestParam  int pageSize,@RequestParam(required = false)  Long id,@RequestParam(required = false)  String name,@RequestParam(required = false)  String grade,@RequestParam(required = false)  Long deptId,@RequestParam(required = false) Byte delFlag);

    @PostMapping("/find/id")
    public HttpResult findById(@RequestParam Long id);
}
