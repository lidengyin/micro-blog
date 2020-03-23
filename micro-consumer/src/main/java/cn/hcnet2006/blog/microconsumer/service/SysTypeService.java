package cn.hcnet2006.blog.microconsumer.service;

import cn.hcnet2006.blog.microconsumer.http.HttpResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "文章类型信息接口")
@RestController
@RequestMapping("/type")
public interface TypeController {
    @PostMapping("/register")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult upload(String name);
    @ApiOperation(value = "批量文章类型修改",notes = "文章类型修改，需要参数如下，自主组合，and模式\n" +
            "        @ApiImplicitParam(type = \"query\",name = \"id\", required = true,value=类型ID),\n" +
            "            @ApiImplicitParam(type = \"query\", name = \"name\",required = false,value=类型名),\n" +
            "            @ApiImplicitParam(type = \"query\", name = \"delFlag\", required = false，value=删除标志)[\n" +
            "实例\n" +
            "  {\n" +
            "    \n" +
            "    \"delFlag\": 0,\n" +
            "    \"id\": 0,\n" +
            "    \"name\": \"string\"\n" +
            "  }，\n" +
            "  {\n" +
            "    \n" +
            "    \"delFlag\": 0,\n" +
            "    \"id\": １,\n" +
            "    \"name\": \"str\"\n" +
            "  }\n" +
            "]")
    @ApiImplicitParams({
    })
    @PutMapping("/update")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult update(@RequestBody List<SysType> sysTypes){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            for(SysType sysType: sysTypes){
                sysType.setLastUpdateBy(authentication.getName());
                sysType.setLastUpdateTime(new Date());
                sysTypeService.update(sysType);
            }
            return HttpResult.ok("类型修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error("类型修改失败");
        }
    }
    @ApiOperation(value = "查看类型列表",notes = "查看类型列表")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "pageNum",value = "页码",required = true),
            @ApiImplicitParam(type = "query", name = "pageSize",value = "页行",required = true),
            @ApiImplicitParam(type = "query",name = "name",value = "类型名"),
            @ApiImplicitParam(type = "query",name = "id",value = "类型ID"),
            @ApiImplicitParam(type = "query",name = "delFlag",value = "删除标志,-1删除，０正常")

    })
    @PostMapping("/find/page")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult findPage(int pageNum, int pageSize, String name, Long id, Byte delFlag){
        try{
            SysType sysType = new SysType();
            sysType.setName(name);
            sysType.setId(id);
            sysType.setDelFlag(delFlag);
            Map<String, Object> map = new HashMap<>();
            map.put("sysType",sysType);
            PageRequest pageRequest = new PageRequest(pageNum, pageSize, map);
            PageResult pageResult = sysTypeService.findPage(pageRequest);
            return HttpResult.ok(pageResult);
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error("分页查询失败");
        }
    }
}