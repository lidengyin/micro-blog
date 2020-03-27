package cn.hcnet2006.blog.microconsumer.service;
import cn.hcnet2006.blog.microconsumer.http.HttpResult;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@FeignClient(name = "hcnet-website-1",contextId = "b")
@RequestMapping("/article")
public interface SysArticleService {

    @PostMapping("/register")
    public HttpResult upload(@RequestParam String articleName, @RequestParam String articleImgUrl, @RequestParam String articleContentUrl,
                             @RequestParam String articleIntroUrl, @RequestParam  List<Long> depts, @RequestParam  List<Long> users, @RequestParam  List<Long> types);

    @PutMapping("/update")
    public HttpResult update(@RequestParam(required = false) Byte delFlag,@RequestParam Long id,  @RequestParam(required = false) String articleName, @RequestParam(required = false) String articleImgUrl, @RequestParam String articleContentUrl,
                             @RequestParam(required = false) String articleIntroUrl, @RequestParam(required = false)  List<Long> depts, @RequestParam(required = false)  List<Long> users, @RequestParam(required = false)  List<Long> types);


    @PostMapping("/find/id")
    public HttpResult findId(@RequestParam Long id);

    @PostMapping("/find/list")
    public HttpResult findByPage(@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam Long id,
                                 @RequestParam String name,@RequestParam Long typeId,@RequestParam Long userId,
                                 @RequestParam Long deptId,@RequestParam Byte delFlag);
}
