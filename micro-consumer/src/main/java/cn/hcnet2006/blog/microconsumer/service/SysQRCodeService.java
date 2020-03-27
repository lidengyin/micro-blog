package cn.hcnet2006.blog.microconsumer.service;
import feign.Response;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@FeignClient(name = "hcnet-website-1",contextId = "e")
public interface SysQRCodeService {
    @GetMapping(value = "/qrcode/createCommonQRCode",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response createCommonQRCode( @RequestParam HttpServletResponse response, @RequestParam Long id );
}
