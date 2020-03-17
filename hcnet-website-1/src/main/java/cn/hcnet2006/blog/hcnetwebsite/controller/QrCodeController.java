package cn.hcnet2006.blog.hcnetwebsite.controller;
import cn.hcnet2006.blog.hcnetwebsite.bean.SysApk;
import cn.hcnet2006.blog.hcnetwebsite.service.SysApkService;
import cn.hcnet2006.blog.hcnetwebsite.util.QRCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Api(tags = "produce the QRCode of download")
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
    @ApiOperation(value = "produce QRCode",notes = "produce QRCode")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query",name = "id",value = "ID",required = true)
    })
    @GetMapping("/qrcode/createCommonQRCode")
    public void createCommonQRCode(HttpServletResponse response,
                              Long id) throws IOException {
        System.out.println("id:"+id);

        ServletOutputStream stream = null;
        try{
            SysApk sysApk = sysApkService.findById(id);
            stream = response.getOutputStream();
            //String imgUrl = "./";
            //produce the QRCode
            QRCodeUtil.encode(sysApk.getApkUrl(),
                    "/usr/local/hc_logo.png",sysApk.getApkName(),stream,true);
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
