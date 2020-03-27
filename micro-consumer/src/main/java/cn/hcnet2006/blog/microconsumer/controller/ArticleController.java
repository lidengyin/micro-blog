package cn.hcnet2006.blog.microconsumer.controller;

import cn.hcnet2006.blog.microconsumer.http.HttpResult;
import cn.hcnet2006.blog.microconsumer.service.SysArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api(tags = "文章传输接口")
@RestController
@RequestMapping("/feign/article")
public class ArticleController {
    @Autowired
    private SysArticleService sysArticleService;

    @ApiOperation(value = "文章上传接口",notes = "文章上传接口")
    @ApiImplicitParams({
    })
    @PostMapping("/register")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult upload(@RequestParam String articleName, @RequestParam String articleImgUrl, @RequestParam String articleContentUrl,
                             @RequestParam String articleIntroUrl, @RequestParam  List<Long> depts, @RequestParam  List<Long> users, @RequestParam  List<Long> types){
       return sysArticleService.upload(articleName, articleImgUrl, articleContentUrl, articleIntroUrl, depts, users, types);
    }
    @ApiOperation(value = "文章修改操作",notes = "文章修改操作，修改操作依旧是组合操作")
    @ApiImplicitParams({
    })
    @PutMapping("/update")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult update(@RequestParam(required = false) Byte delFlag,@RequestParam Long id,  @RequestParam(required = false) String articleName, @RequestParam(required = false) String articleImgUrl, @RequestParam(required = false) String articleContentUrl,
                             @RequestParam(required = false) String articleIntroUrl, @RequestParam(required = false)  List<Long> depts, @RequestParam(required = false)  List<Long> users, @RequestParam(required = false)  List<Long> types){
        return sysArticleService.update(delFlag,id,articleName,articleImgUrl,articleContentUrl,articleIntroUrl,depts,users,types);
    }

    @ApiOperation(value = "具体查看某个文章信息",notes = "具体查看某个文章信息")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "id", value = "文章编号", required = false)
    })
    @PostMapping("/find/id")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult findId(Long id){
      return sysArticleService.findId(id);
    }
    @ApiOperation(value = "分页分参数查看文章列表信息",notes = "分页分参数查看文章列表信息")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query",name = "pageNum", value = "页码",required = true),
            @ApiImplicitParam(type = "query", name = "pageSize", value = "页行",required = true),
            @ApiImplicitParam(type = "query", name = "name",value = "文章名",required = false),
            @ApiImplicitParam(type = "query", name = "id",value = "文章ID",required = false),
            @ApiImplicitParam(type = "query", name = "delFlag",value = "删除标志",required = false),
            @ApiImplicitParam(type = "query", name = "userId",value = "用户编号",required = false),
            @ApiImplicitParam(type = "query", name = "deptId",value = "机构编号",required = false),
            @ApiImplicitParam(type = "query", name = "userId",value = "人员编号",required = false),
            @ApiImplicitParam(type = "query", name = "typeId",value = "类型编号",required = false),
    })
    @PostMapping("/find/list")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult findByPage(int pageNum,int pageSize, Long id,
     String name,  Long typeId,  Long userId,
     Long deptId, Byte delFlag){
        return sysArticleService.findByPage(pageNum,pageSize,id,name,typeId,userId,deptId,delFlag);
    }
}
