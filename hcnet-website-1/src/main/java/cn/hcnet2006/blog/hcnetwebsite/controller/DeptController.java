package cn.hcnet2006.blog.hcnetwebsite.controller;

import cn.hcnet2006.blog.hcnetwebsite.bean.SysDept;
import cn.hcnet2006.blog.hcnetwebsite.http.HttpResult;
import cn.hcnet2006.blog.hcnetwebsite.service.SysDeptService;
import cn.hcnet2006.blog.hcnetwebsite.util.OSSUtils;
import io.swagger.annotations.*;
import org.bytedeco.javacv.FrameFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.plugin2.gluegen.runtime.StructAccessor;

import java.io.DataInput;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

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
}
