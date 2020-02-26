package cn.hcnet2006.blog.microconsumer.controller;

import cn.hcnet2006.blog.microconsumer.service.FeignFooService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Api(tags = "apk文件传输接口")
@RestController
public class FeignFooController {
    @Autowired
    private FeignFooService feignFooService;
    @ApiOperation(value = "获取信息",notes = "获取信息")
    @ApiImplicitParams({

    })
    @RequestMapping("/fegin/call")
    public String call(){
        return feignFooService.foo();
    }

}
