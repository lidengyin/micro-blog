package cn.hcnet2006.blog.hcnetwebsite.controller;
import cn.hcnet2006.blog.hcnetwebsite.bean.SysApk;
import cn.hcnet2006.blog.hcnetwebsite.service.SysApkService;
import cn.hcnet2006.blog.hcnetwebsite.util.QRCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@Api(tags = "二维码下载接口")
@RestController
public class QrCodeController {
    @Autowired
    private SysApkService sysApkService;
    /**
     * make the QRCode by url
     * @param response
     * @param id
     * @throws IOException
     */
    @ApiOperation(value = "生成二维码",notes = "生成二维码")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query",name = "id",value = "ID",required = true)
    })
    @GetMapping("/qrcode/createCommonQRCode")
    @CrossOrigin(origins = "*", allowCredentials = "true",allowedHeaders = "*",methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH})
    public void createCommonQRCode(HttpServletResponse response,
                              Long id) throws IOException {
        System.out.println("id:"+id);

        ServletOutputStream stream = null;
        try{
            SysApk sysApk = sysApkService.findById(id);
            //返回图片内容
            response.setContentType("image/jpeg");
            stream = response.getOutputStream();

            InputStream inputStream = QRCodeUtil.encode(sysApk.getApkUrl(),
                    "/usr/local/hc_logo.png",sysApk.getApkName(),stream,true);
            try {
                stream = response.getOutputStream();
                byte[] bytes = new byte[1024];
                int len = 0;
                while ((len = inputStream.read(bytes)) != -1) {
                    stream.write(bytes, 0, len);
                }
                inputStream.close();
                stream.close();
                stream.flush();
            } catch (Exception e) {

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(stream != null){
                stream.flush();
                stream.close();
            }
        }
    }
}
