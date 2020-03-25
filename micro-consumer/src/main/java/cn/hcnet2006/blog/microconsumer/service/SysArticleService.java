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
    public HttpResult upload(@RequestParam(value = "文章名",required = false) String articleName, @RequestParam(value = "封面图片URL",required = false) String articleImgUrl, @RequestParam(value = "文章内容URL") String articleContentUrl,
                             @RequestParam(value = "封面简介URL",required = false) String articleIntroUrl, @RequestParam(value = "机构ID列表",required = false)  List<Long> depts, @RequestParam(value = "成员ID列表",required = false)  List<Long> users, @RequestParam(value = "文章类型ID列表",required = false)  List<Long> types);

    @PutMapping("/update")
    public HttpResult update(@RequestParam(value = "删除标志，－１已经删除，０正常",required = false) Byte delFlag,@RequestParam(value = "文章ID") Long id,  @RequestParam(value = "文章名",required = false) String articleName, @RequestParam(value = "封面图片URL",required = false) String articleImgUrl, @RequestParam(value = "文章内容URL") String articleContentUrl,
                             @RequestParam(value = "封面简介URL",required = false) String articleIntroUrl, @RequestParam(value = "机构ID列表",required = false)  List<Long> depts, @RequestParam(value = "成员ID列表",required = false)  List<Long> users, @RequestParam(value = "文章类型ID列表",required = false)  List<Long> types);


    @PostMapping("/find/id")
    public HttpResult findId(@RequestParam Long id);

    @PostMapping("/find/list")
    public HttpResult findByPage(@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam Long id,
                                 @RequestParam String name,@RequestParam Long typeId,@RequestParam Long userId,
                                 @RequestParam Long deptId,@RequestParam Byte delFlag);
}
