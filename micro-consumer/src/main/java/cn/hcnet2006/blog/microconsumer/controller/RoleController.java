package cn.hcnet2006.blog.microconsumer.controller;
import cn.hcnet2006.blog.microconsumer.bean.SysRole;
import cn.hcnet2006.blog.microconsumer.http.HttpResult;
import cn.hcnet2006.blog.microconsumer.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Api(tags = "角色信息接口")
@RestController
@RequestMapping("/feign/role")
public class RoleController {
    @Autowired
    private SysRoleService sysRoleService;
    @ApiOperation(value = "角色注册",notes = "角色注册")
    @ApiImplicitParams({

            @ApiImplicitParam(type = "query",name = "name",value = "角色名",required = true),
            @ApiImplicitParam(type = "query", name = "remark",value = "角色备注",required = true),
    })
    @PostMapping("/register")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult upload(String name, String remark, @RequestBody List<Long> menus){
        return sysRoleService.upload(name, remark, menus);
    }
    @ApiOperation(value = "角色列表修改",notes = "角色列表修改，修改角色关联属性，那么这个角色只能单一修改,切记:\n" +
            " @ApiImplicitParam(type = \"query\", name = \"id\", value = \"编号\",required = true),\n" +
            "            @ApiImplicitParam(type = \"query\",name = \"name\",value = \"角色名\"),\n" +
            "            @ApiImplicitParam(type = \"query\", name = \"remark\",value = \"角色备注\"),\n" +
            "            @ApiImplicitParam(type = \"query\", name = \"lastUpdateBy\",value = \"修改者\"),\n" +
            "            @ApiImplicitParam(type = \"query\", name = \"delFlag\",value = \"删除标志，-1删除，0正常\")" +
            "实例" +
            "[\n" +
            "  {\n" +
            "    \"delFlag\": 0,\n" +
            "    \"id\": 0,\n" +
            "    \"name\": \"string\",\n" +
            "    \"remark\": \"string\"\n" +
            "  }，\n" +
            "  {\n" +
            "    \"delFlag\": 0,\n" +
            "    \"id\": １,\n" +
            "    \"name\": \"stng\",\n" +
            "    \"remark\": \"string\"\n" +
            "  }\n" +
            "]")
    @ApiImplicitParams({
//            @ApiImplicitParam(type = "query",name = "menus", value = "菜单编号列表",paramType = "body",dataType = "java.util.List")
    })

    @PutMapping("/update/list")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult update(@RequestBody List<SysRole> sysRoles, @RequestParam(required = false) List<Long> menus){
        return sysRoleService.update(sysRoles, menus);
    }

    @ApiOperation(value = "分页查询角色",notes = "分页查询角色\n" +
            "@ApiImplicitParam(type = \"query\", name = \"id\",value = \"编号\",required = true)\n" +
            "            @ApiImplicitParam(type = \"query\",name = \"name\",value = \"角色名\",required = true),\n"+
            "            @ApiImplicitParam(type = \"query\", name = \"delFlag\",value = \"删除标志，-1删除，0正常\")\n"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "当前页码",required = true),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页行数",required = true),
            @ApiImplicitParam(type = "query",name = "name",value = "角色名"),
            @ApiImplicitParam(type = "query", name = "delFlag",value = "删除标志，－１时删除，０时正常"),
    })
    @PostMapping("/find/page")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult find(int pageNum, int pageSize, String name, Byte delFlag){
       return sysRoleService.find(pageNum, pageSize, name, delFlag);
    }
    @ApiOperation(value = "具体查询某个角色",notes = "具体查询某个角色")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "id", value = "编号", required = true)
    })
    @PostMapping("/find/id")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public HttpResult findById(Long id){
        return sysRoleService.findById(id);
    }
}
