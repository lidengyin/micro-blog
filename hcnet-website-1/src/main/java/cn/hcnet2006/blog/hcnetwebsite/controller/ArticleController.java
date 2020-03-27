package cn.hcnet2006.blog.hcnetwebsite.controller;

import cn.hcnet2006.blog.hcnetwebsite.bean.SysArticle;
import cn.hcnet2006.blog.hcnetwebsite.bean.SysDeptArticle;
import cn.hcnet2006.blog.hcnetwebsite.bean.SysTypeArticle;
import cn.hcnet2006.blog.hcnetwebsite.bean.SysUserArticle;
import cn.hcnet2006.blog.hcnetwebsite.service.SysArticleService;
import cn.hcnet2006.blog.hcnetwebsite.http.HttpResult;
import cn.hcnet2006.blog.hcnetwebsite.page.PageRequest;
import cn.hcnet2006.blog.hcnetwebsite.page.PageResult;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api(tags = "文章传输接口")
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private SysArticleService sysArticleService;

    @ApiOperation(value = "文章上传接口",notes = "文章上传接口")
    @ApiImplicitParams({
    })
    @PostMapping("/register")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult upload( @RequestParam String articleName, @RequestParam String articleImgUrl, @RequestParam String articleContentUrl,
                             @RequestParam String articleIntroUrl, @RequestParam  List<Long> depts, @RequestParam  List<Long> users, @RequestParam  List<Long> types){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            SysArticle sysArticle= new SysArticle();

            sysArticle.setArticleName(articleName);
            sysArticle.setArticleContentUrl(articleContentUrl);
            sysArticle.setArticleImgUrl(articleImgUrl);
            sysArticle.setArticleIntroUrl(articleIntroUrl);
            sysArticle.setDelFlag((byte)0);
            sysArticle.setCreateBy(authentication.getName());
            sysArticle.setLastUpdateBy(authentication.getName());
            sysArticle.setCreateTime(new Date());
            sysArticle.setLastUpdateTime(new Date());
            sysArticleService.save(sysArticle);
            System.out.println("id:"+sysArticle.getId());
            if(depts != null ){
               // sysArticleService.deleteDeptAndArticle(sysArticle.getId());
                for(Long dept: depts){
                    SysDeptArticle sysDeptArticle = new SysDeptArticle();
                    sysDeptArticle.setId(UUID.randomUUID().toString());
                    sysDeptArticle.setArticleId(sysArticle.getId());
                    sysDeptArticle.setDeptId(dept);
                    sysDeptArticle.setCreateBy(authentication.getName());
                    sysDeptArticle.setCreateTime(new Date());
                    sysDeptArticle.setLastUdpateBy(authentication.getName());
                    sysDeptArticle.setLastUpdateTime(new Date());
                    sysDeptArticle.setDelFlag((byte)0);
                    sysArticleService.saveDeptAndArticle(sysDeptArticle);
                    System.out.println(dept);

                }
            }
            if(users != null){
                //sysArticleService.deleteUserAndArticle(sysArticle.getId());
                for(Long user: users){
                    SysUserArticle sysUserArticle = new SysUserArticle();
                    sysUserArticle.setId(UUID.randomUUID().toString());
                    sysUserArticle.setArticleId(sysArticle.getId());
                    sysUserArticle.setUserId(user);
                    sysUserArticle.setCreateBy(authentication.getName());
                    sysUserArticle.setCreateTime(new Date());
                    sysUserArticle.setLastUpdateBy(authentication.getName());
                    sysUserArticle.setLastUpdateTime(new Date());
                    sysUserArticle.setDelFlag((byte)0);
                    sysArticleService.saveUserAndArticle(sysUserArticle);
                    System.out.println(user);
                }
            }
            if(types != null){
                for(Long type: types){
                    SysTypeArticle sysTypeArticle = new SysTypeArticle();
                    sysTypeArticle.setId(UUID.randomUUID().toString());
                    sysTypeArticle.setArticleId(sysArticle.getId());
                    sysTypeArticle.setTypeId(type);
                    sysTypeArticle.setCreateBy(authentication.getName());
                    sysTypeArticle.setCreateTime(new Date());
                    sysTypeArticle.setLastUpdateTime(new Date());
                    sysTypeArticle.setLastUpdateBy(authentication.getName());
                    sysTypeArticle.setDelFlag((byte)0);
                    sysArticleService.saveTypeAndArticle(sysTypeArticle);
                    System.out.println(type);
                }
            }
            return HttpResult.ok(sysArticle);
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error("文章注册失败");
        }
    }
    @ApiOperation(value = "文章修改操作",notes = "文章修改操作，修改操作依旧是组合操作")
    @ApiImplicitParams({
    })
    @PutMapping("/update")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult update(@RequestParam(required = false) Byte delFlag,@RequestParam Long id,  @RequestParam(required = false) String articleName, @RequestParam(required = false) String articleImgUrl, @RequestParam String articleContentUrl,
                             @RequestParam(required = false) String articleIntroUrl, @RequestParam(required = false)  List<Long> depts, @RequestParam(required = false)  List<Long> users, @RequestParam(required = false)  List<Long> types){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            SysArticle sysArticle= new SysArticle();
            sysArticle.setId(id);
            System.out.println("id:"+id);
            System.out.println("name:"+articleName);
            sysArticle.setArticleName(articleName);
            sysArticle.setArticleContentUrl(articleContentUrl);
            sysArticle.setArticleImgUrl(articleImgUrl);
            sysArticle.setArticleIntroUrl(articleIntroUrl);
            sysArticle.setDelFlag(delFlag);
            sysArticle.setLastUpdateBy(authentication.getName());
            sysArticle.setLastUpdateTime(new Date());
            sysArticleService.update(sysArticle);
            System.out.println("id:"+sysArticle.getId());
            if(depts != null ){
                sysArticleService.deleteDeptAndArticle(sysArticle.getId());
                for(Long dept: depts){
                    SysDeptArticle sysDeptArticle = new SysDeptArticle();
                    sysDeptArticle.setId(UUID.randomUUID().toString());
                    sysDeptArticle.setArticleId(sysArticle.getId());
                    sysDeptArticle.setDeptId(dept);
                    sysDeptArticle.setCreateBy(authentication.getName());
                    sysDeptArticle.setCreateTime(new Date());
                    sysDeptArticle.setLastUdpateBy(authentication.getName());
                    sysDeptArticle.setLastUpdateTime(new Date());
                    sysDeptArticle.setDelFlag((byte)0);
                    sysArticleService.saveDeptAndArticle(sysDeptArticle);
                    System.out.println(dept);

                }
            }
            if(users != null){
                sysArticleService.deleteUserAndArticle(sysArticle.getId());
                for(Long user: users){
                    SysUserArticle sysUserArticle = new SysUserArticle();
                    sysUserArticle.setId(UUID.randomUUID().toString());
                    sysUserArticle.setArticleId(sysArticle.getId());
                    sysUserArticle.setUserId(user);
                    sysUserArticle.setCreateBy(authentication.getName());
                    sysUserArticle.setCreateTime(new Date());
                    sysUserArticle.setLastUpdateBy(authentication.getName());
                    sysUserArticle.setLastUpdateTime(new Date());
                    sysUserArticle.setDelFlag((byte)0);
                    sysArticleService.saveUserAndArticle(sysUserArticle);
                    System.out.println(user);
                }
            }
            if(types != null){
                sysArticleService.deleteTypeAndArticle(sysArticle.getId());
                for(Long type: types){
                    SysTypeArticle sysTypeArticle = new SysTypeArticle();
                    sysTypeArticle.setId(UUID.randomUUID().toString());
                    sysTypeArticle.setArticleId(sysArticle.getId());
                    sysTypeArticle.setTypeId(type);
                    sysTypeArticle.setCreateBy(authentication.getName());
                    sysTypeArticle.setCreateTime(new Date());
                    sysTypeArticle.setLastUpdateTime(new Date());
                    sysTypeArticle.setLastUpdateBy(authentication.getName());
                    sysTypeArticle.setDelFlag((byte)0);
                    sysArticleService.saveTypeAndArticle(sysTypeArticle);
                    System.out.println(type);
                }
            }
            return HttpResult.ok(sysArticle);
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error("修改失败");
        }
    }
    @ApiOperation(value = "具体查看某个文章信息",notes = "具体查看某个文章信息")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "id", value = "文章编号", required = false)
    })
    @PostMapping("/find/id")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult findId(Long id){
        try{
            SysArticle sysArticle = sysArticleService.findById(id);
            return HttpResult.ok(sysArticle);
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error("文章查看失败");
        }
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
        try{
            System.out.println("delFlag"+delFlag);
            SysArticle sysArticle = new SysArticle();
            sysArticle.setId(id);
            sysArticle.setDelFlag(delFlag);
            sysArticle.setArticleName(name);
            sysArticle.setDeptId(deptId);
            sysArticle.setTypeId(typeId);
            sysArticle.setUserId(userId);
            Map<String, Object> map = new HashMap<>();
            map.put("sysArticle", sysArticle);
            PageRequest pageRequest = new PageRequest(pageNum,pageSize,map);
            PageResult pageResult = sysArticleService.findPage(pageRequest);
            return HttpResult.ok(pageResult);
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error("查询失败");
        }


    }
}
