package cn.hcnet2006.blog.hcnetwebsite.controller;

import cn.hcnet2006.blog.hcnetwebsite.bean.SysDept;
import cn.hcnet2006.blog.hcnetwebsite.http.HttpResult;
import cn.hcnet2006.blog.hcnetwebsite.pages.PageRequest;
import cn.hcnet2006.blog.hcnetwebsite.pages.PageResult;
import cn.hcnet2006.blog.hcnetwebsite.service.SysDeptService;
import cn.hcnet2006.blog.hcnetwebsite.util.OSSUtils;
import com.netflix.ribbon.proxy.annotation.Http;
import io.swagger.annotations.*;
import org.bytedeco.javacv.FrameFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.handler.FilteringWebHandler;
import sun.plugin2.gluegen.runtime.StructAccessor;

import java.io.DataInput;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Api(tags = "机构信息接口")
@RestController
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private SysDeptService sysDeptService;
    @ApiOperation(value = "上传机构信息",notes = "上传机构信息")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query",name = "name",value = "机构名",required = true),
            @ApiImplicitParam(type = "query",name = "parentId",value = "上级机构ID，一级机构为0",required = true),
            @ApiImplicitParam(type = "query",name = "createBy",value = "创建人",required = true)

    })
    @PostMapping("/register")
    public HttpResult upload(SysDept sysDept, @ApiParam("uploadFile")MultipartFile uploadFile) throws FileNotFoundException {
        String url = ResourceUtils.getURL("").getPath()+uploadFile.getOriginalFilename();
        File folder = new File(url);
        try{
            uploadFile.transferTo(folder);
            String logo_url = OSSUtils.upload(folder, sysDept.getName()+".jpg");
            sysDept.setDeptLogo(logo_url);
            sysDept.setCreateTime(new Date());
            sysDept.setLastUpdateTime(new Date());
            sysDept.setLastUpdateBy(sysDept.getCreateBy());
            sysDept.setDelFlag((byte)0);
            sysDeptService.save(sysDept);
            return HttpResult.ok(sysDept);
        }catch (DuplicateKeyException | IOException e){
            return HttpResult.error("该机构注册");
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error("机构注册失败，请重新注册");
        }
    }
    @ApiOperation(value = "修改机构信息",notes = "修改机构信息")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query",name = "id",value = "机构编号",required = true),
            @ApiImplicitParam(type = "query",name = "name",value = "机构名"),
            @ApiImplicitParam(type = "query",name = "parentId",value = "上级机构ID，一级机构为0"),
            @ApiImplicitParam(type = "query",name = "lastUpdateBy",value = "修改人"),
            @ApiImplicitParam(type = "query", name = "delFlag",value = "删除标志，-1删除，0正常")
    })
    @PostMapping("/update")
    public HttpResult update(SysDept sysDept, @ApiParam("uploadFile") MultipartFile uploadFile) throws FileNotFoundException {
        try{
            if(uploadFile != null){
                String url = ResourceUtils.getURL("").getPath()+uploadFile.getOriginalFilename();
                File folder = new File(url);
                String deptLogo = OSSUtils.upload(folder, UUID.randomUUID().toString()+".jpg");
                folder.delete();
                sysDept.setDeptLogo(deptLogo);
            }
            sysDept.setLastUpdateTime(new Date());
            sysDeptService.update(sysDept);
            return HttpResult.ok(sysDept);
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error("机构修改失败");
        }
    }
    @ApiOperation(value = "分页查询机构信息",notes = "分页显示机构信息\n" +
            "可选参数进行and组合，全部为空则为查询全部\n" +
            "@ApiImplicitParam(type = \"query\",name = \"id\",value = \"机构编号\",required = true),\n" +
            "            @ApiImplicitParam(type = \"query\",name = \"name\",value = \"机构名\"),\n" +
            "            @ApiImplicitParam(type = \"query\",name = \"parentId\",value = \"上级机构ID，一级机构为0\"),\n" +
            "            @ApiImplicitParam(type = \"query\", name = \"delFlag\",value = \"删除标志，-1删除，0正常\")")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "当前页码",required = true),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页行数",required = true)

    })
    @PostMapping("/find/page")
    public HttpResult find(int pageNum, int pageSize, @RequestBody SysDept sysDept){
        try{
            Map<String, Object> map = new HashMap<>();
            map.put("sysDept",sysDept);
            PageRequest pageRequest = new PageRequest(pageNum, pageSize, map);
            PageResult pageResult = sysDeptService.findPage(pageRequest);
            return HttpResult.ok(pageResult);
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error("查询失败");
        }
    }
    @ApiOperation(value = "具体查看某个机构信息")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "id", value = "机构编号",required = true)
    })
    @PostMapping("/find/id")
    public HttpResult findById(Long id){
        try{
            SysDept sysDept = sysDeptService.findById(id);
            return HttpResult.ok(sysDept);
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error("查询机构失败");
        }

    }
}
