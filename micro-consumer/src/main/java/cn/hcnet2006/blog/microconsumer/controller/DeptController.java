package cn.hcnet2006.blog.microconsumer.controller;

import cn.hcnet2006.blog.microconsumer.http.HttpResult;
import cn.hcnet2006.blog.microconsumer.service.SysDeptService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.FileNotFoundException;


@Api(tags = "机构信息接口")
@RestController
@RequestMapping("/feign/dept")
public class DeptController {
    @Autowired
    private SysDeptService sysDeptService;
    @ApiOperation(value = "上传机构信息",notes = "上传机构信息")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query",name = "name",value = "机构名",required = true),
            @ApiImplicitParam(type = "query",name = "parentId",value = "上级机构ID，一级机构父ID为-1",required = true),
    })
    @PostMapping("/register")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult upload(String name, Long parentId, MultipartFile uploadFile) throws FileNotFoundException {
       return sysDeptService.upload(name,parentId,uploadFile);
    }
    @ApiOperation(value = "修改机构信息",notes = "修改机构信息")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query",name = "id",value = "机构编号",required = true),
            @ApiImplicitParam(type = "query",name = "name",value = "机构名"),
            @ApiImplicitParam(type = "query",name = "parentId",value = "上级机构ID，一级机构父ID为-1"),
            @ApiImplicitParam(type = "query", name = "delFlag",value = "删除标志，-1删除，0正常")
    })
    @PutMapping("/update")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult update(String name, Long id, Long parentId, Byte delFlag , MultipartFile uploadFile) throws FileNotFoundException {
        return sysDeptService.update(name,id,parentId,delFlag,uploadFile);
    }
    @ApiOperation(value = "分页查询机构信息",notes = "分页显示机构信息\n" +
            "可选参数进行and组合，全部为空则为查询全部\n" +
            "@ApiImplicitParam(type = \"query\",name = \"id\",value = \"机构编号\",required = true),\n" +
            "            @ApiImplicitParam(type = \"query\",name = \"name\",value = \"机构名\"),\n" +
            "            @ApiImplicitParam(type = \"query\",name = \"parentId\",value = \"上级机构ID，一级机构为0\"),\n" +
            "            @ApiImplicitParam(type = \"query\", name = \"delFlag\",value = \"删除标志，-1删除，0正常\")")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "当前页码",required = true),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页行数",required = true),
            @ApiImplicitParam(paramType = "query",name = "id", value = "机构编号"),
            @ApiImplicitParam(paramType = "query",name = "name", value = "机构名称", required = false),
            @ApiImplicitParam(paramType = "query",name = "parentId", value = "父机构ID，顶级机构父ID为－１", required = false),
            @ApiImplicitParam(paramType = "query",name = "delFlag", value = "删除标志，－１删除状态，０正常", required = false),

    })
    @PostMapping("/find/page")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult find(int pageNum, int pageSize, Long id,String name,Long parentId,  Byte delFlag){
        return sysDeptService.find(pageNum, pageSize, id, name, parentId, delFlag);
    }
    @ApiOperation(value = "具体查看某个机构信息")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "id", value = "机构编号",required = true)
    })
    @PostMapping("/find/id")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult findById(Long id){
        return sysDeptService.findById(id);
    }
    @ApiOperation(value = "查询机构树",notes = "查询机构树")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "parentId", value = "规定根目录的父ID为-1，",required = true)

    })
    @PostMapping("/find/tree")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult findDeptNodes(Long parentId){
        return sysDeptService.findDeptNodes(parentId);
    }

}
