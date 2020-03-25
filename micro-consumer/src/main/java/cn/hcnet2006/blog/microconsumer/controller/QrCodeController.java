package cn.hcnet2006.blog.microconsumer.controller;

import cn.hcnet2006.blog.microconsumer.service.SysQRCodeService;
import feign.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Api(tags = "二维码下载接口")
@RequestMapping("/feign")
@Controller
public class QrCodeController {
    @Autowired
    private SysQRCodeService sysQRCodeService;
    /**
     * make the QRCode by url
     * @param
     * @param id
     * @throws IOException
     */
    @ApiOperation(value = "生成二维码",notes = "生成二维码")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query",name = "id",value = "ID",required = true)
    })
    @GetMapping("/qrcode/createCommonQRCode")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    @ResponseBody
    public void createCommonQRCode(HttpServletResponse response1,
                              Long id) throws IOException {

        Response response = sysQRCodeService.createCommonQRCode(response1, id);
        Response.Body body = response.body();

        InputStream fileInputStream = null;
        OutputStream outStream;
        try {
            fileInputStream = body.asInputStream();
            outStream = response1.getOutputStream();

            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = fileInputStream.read(bytes)) != -1) {
                outStream.write(bytes, 0, len);
            }
            fileInputStream.close();
            outStream.close();
            outStream.flush();
        } catch (Exception e) {

        }

    }
}
