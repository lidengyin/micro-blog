package cn.hcnet2006.blog.hcnetwebsite.controller;

import cn.hcnet2006.blog.hcnetwebsite.bean.SysVideo;
import cn.hcnet2006.blog.hcnetwebsite.http.HttpResult;
import cn.hcnet2006.blog.hcnetwebsite.mapper.SysVideoMapper;
import cn.hcnet2006.blog.hcnetwebsite.page.PageRequest;
import cn.hcnet2006.blog.hcnetwebsite.page.PageResult;
import cn.hcnet2006.blog.hcnetwebsite.service.impl.VideoService;
import cn.hcnet2006.blog.hcnetwebsite.utils.ImageAndVideoUtils;
import cn.hcnet2006.blog.hcnetwebsite.utils.OSSUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *视频传输接口
 */
@Api(tags = "视频传输接口")
@RestController
@RequestMapping("/video")
public class VideoController {
    @Autowired
    private VideoService videoService;
    @Autowired
    private SysVideoMapper sysVideoMapper;

    /**
     * 上传视频
     * @param video
     * @param uploadFile
     * @param request
     * @return
     */
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
    @ApiOperation(value = "上传视频", notes = "上传视频,必须有视频类型格式的视频路径,否则只能自己写一个表单进行测试")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "videoName",value = "视频名",required = true),
            @ApiImplicitParam(type = "query", name = "videoDetail",value = "视频简介",required = true),
            @ApiImplicitParam(type = "query", name = "videoGroup",value = "视频组别",required = true),

    })
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping(value = "/upload",headers = "content-type=multipart/form-data")
    public HttpResult uploadVideo(SysVideo video, @ApiParam(value = "视频文件",required = true) MultipartFile uploadFile, HttpServletRequest request)throws IOException{
        SysVideo sysVideo = new SysVideo();
        //存储服务器暂存视频绝对路径
        String videoUrl = ResourceUtils.getURL("").getPath()+"upload/video/video/"+sdf.format(new Date())+UUID.randomUUID().toString()+(uploadFile.getOriginalFilename()).substring(uploadFile.getOriginalFilename().lastIndexOf("."));
        //存储服务器暂存截图绝对路径
        String imageUrl = ResourceUtils.getURL("").getPath()+"upload/video/Img/"+sdf.format(new Date())+UUID.randomUUID().toString()+".jpg";
        //获取文件目录
        File folder = new File(videoUrl);
        //目录不存在就建一个
        if(!(folder.isDirectory())){
            folder.mkdirs();
        }
        try{
            //将上传文件转移到服务器新建目录中
            uploadFile.transferTo(folder);
            //设置创建时间
            sysVideo.setCreateTime(new Date());
            //获取服务器暂存视频文件
            File convVideoFile = new File(videoUrl);
            //设置视频名
            sysVideo.setVideoName(video.getVideoName());
            //设置视频详情
            sysVideo.setVideoDetail(video.getVideoDetail());
            //设置更新日期
            sysVideo.setUpdateTime(new Date());
            //设置删除标志
            sysVideo.setDelIt("0");
            //设置删除标志
            sysVideo.setDelFlag((byte)0);
            //上传文件到阿里云OSSBucket
            String video_Url = OSSUtils.upload(convVideoFile,video.getVideoName()+(uploadFile.getOriginalFilename()).substring(uploadFile.getOriginalFilename().lastIndexOf(".")));
            //去除accessKeyIDangAccessKeySecret
            video_Url = video_Url.substring(0,video_Url.indexOf("?"));
            //设置https下载
            video_Url = "https"+video_Url.substring(4,video_Url.length());
            System.out.println("videoUrl:"+video_Url);
            //获取屏幕截图
            ImageAndVideoUtils.fetchFrame(video_Url, imageUrl);
            //获取服务器暂存文件
            File convImageFile = new File(imageUrl);
            System.out.println("Image_URL:"+imageUrl);
            //上传
            String image_Url = OSSUtils.upload(convImageFile,video.getVideoName()+"fetchImage.jpg");
            image_Url = image_Url.substring(0,image_Url.indexOf("?"));
            image_Url="https"+image_Url.substring(4,image_Url.length());
            System.out.println("Image_URL:"+image_Url);
            sysVideo.setVideoUrl(video_Url);
            sysVideo.setVideoImgUrl(image_Url);
            videoService.saveVideo(sysVideo);
            return HttpResult.ok(sysVideo);
        }catch(Exception e){
            e.printStackTrace();
            return HttpResult.error("保存失败");
        }
    }
    /**
     *视频列表分页显示
     * @param
     * @param
     * @return
     */
    @ApiOperation(value = "根据可用性视频列表分页显示", notes = "根据可用性视频列表分页显示")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "pageNum",value = "分页页码",required = true),
            @ApiImplicitParam(type = "query", name = "pageSize",value = "分页行数",required = true),
            @ApiImplicitParam(type = "query", name = "delFlag",value = "删除标志",required = true)
    })
    @PostMapping("/page")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public HttpResult findVideoByPaging(Integer pageNum, Integer pageSize, Byte delFlag){
            try {
                Map<String, Object> map = new HashMap<>();
                map.put("delFlag",delFlag);
                PageRequest pageRequest = new PageRequest(pageNum,pageSize,map);
                PageResult pageResult = videoService.selectAllByPage(pageRequest);
                return HttpResult.ok(pageResult);
            }catch (Exception e){
                e.printStackTrace();
                return HttpResult.error("分页失败");
            }
    }
    /**
     * 根据年份分页获取所有视频
     * @param pageNum
     * @param pageSize
     * @param
     * @return
     */
    @ApiOperation(value = "根据可用性，年份分页获取所有视频", notes = "根据可用性，年份分页获取所有视品")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "pageNum",value = "分页页码",required = true),
            @ApiImplicitParam(type = "query", name = "pageSize",value = "分页行数",required = true),
            @ApiImplicitParam(type = "query",name = "year",value = "查询年份",required = true),
            @ApiImplicitParam(type = "query", name = "delFlag", value = "删除标志，0为正常，1为删除",required = true)
    })
    @PostMapping("/age")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public HttpResult findVideoByAge(Integer pageNum, Integer pageSize, String year, Byte delFlag){
        SysVideo sysVideo = new SysVideo();sysVideo.setDelFlag(delFlag);sysVideo.setYear(year);
        try{
            Map<String, Object> params = new HashMap<>();params.put("sysVideo", sysVideo);
            PageRequest pageRequest = new PageRequest(pageNum,pageSize,params);
            PageResult result = videoService.findVideosByAge(pageRequest);
            return HttpResult.ok(result);
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error("获取列表失败");
        }
    }
    @ApiOperation(value = "流式返回视频",notes = "流式返回视频")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query",name = "id",value = "视频ID")
    })
    @GetMapping("/showVideo")
    public void showVideo( Integer id,HttpServletResponse response)throws IOException {
        SysVideo sysVideo = videoService.findVideoById(id);
        String videoUrl = sysVideo.getVideoUrl();
        ImageAndVideoUtils.showVideo(videoUrl, response);
    }
    @ApiOperation(value = "流式返回图片",notes = "流式返回图片")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query",name = "id",value = "图片ID")
    })
    @GetMapping("/showimg")
    public void showImage(Integer id, HttpServletResponse response)throws IOException {


        SysVideo sysVideo = videoService.findVideoById(id);
        System.out.println("videoName:"+sysVideo.getVideoImgUrl());
        String imageUrl = sysVideo.getVideoImgUrl();
        ImageAndVideoUtils.showImage(imageUrl, response);
    }

}
