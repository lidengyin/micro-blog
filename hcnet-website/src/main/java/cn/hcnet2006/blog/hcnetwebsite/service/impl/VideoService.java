package cn.hcnet2006.blog.hcnetwebsite.service.impl;

import cn.hcnet2006.blog.hcnetwebsite.bean.SysVideo;
import cn.hcnet2006.blog.hcnetwebsite.mapper.SysVideoMapper;
import cn.hcnet2006.blog.hcnetwebsite.page.MybatisPageHelper;
import cn.hcnet2006.blog.hcnetwebsite.page.PageRequest;
import cn.hcnet2006.blog.hcnetwebsite.page.PageResult;
import cn.hcnet2006.blog.hcnetwebsite.utils.ReflectionUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {
    @Autowired
    private SysVideoMapper sysVideoMapper;

    /**
     * 保存视频
     * @param sysVideo
     */
    public void saveVideo(SysVideo sysVideo){
        sysVideoMapper.saveVideo(sysVideo);
    }

    /**
     * 分页查看所有视频
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageResult selectAllByPage(PageRequest pageRequest){
        return MybatisPageHelper.findPage(pageRequest, sysVideoMapper, "selectAll", pageRequest.getParam("delFlag"));

    }

    /**
     * 通过主键查看视频
     * @param id
     * @return
     */
    public SysVideo findVideoById(Integer id){
        Object result = ReflectionUtils.invoke(sysVideoMapper, "selectByPrimaryKey",id);
        return (SysVideo) result;
    }

    /**
     * 根据年份分页查询视频
     * @param
     * @param
     * @param
     * @return
     */
    public PageResult findVideosByAge(PageRequest pageRequest){
        return MybatisPageHelper.findPage(pageRequest, sysVideoMapper, "selectVideoByAge", pageRequest.getParam("sysVideo"));
    }

    /**
     * 查询所有年份
     * @return
     */
    public List<String> findAllAges(){
        List<String> ages = sysVideoMapper.selectAllAges();
        return ages;
    }
    /**
     * 通过主键修改视频
     * @param sysVideo
     */
    public void UpdateVideoById(SysVideo sysVideo){
        sysVideoMapper.updateByPrimaryKey(sysVideo);
    }

    /**
     * 通过组别分类获取视频
     * @param pageNum
     * @param pageSize
     * @param
     * @return
     */
    public Page<SysVideo> findVideoByGroup(Integer pageNum, Integer pageSize, SysVideo sysVideo){
        PageHelper.startPage(pageNum,pageSize);
        Page<SysVideo> videoPage = sysVideoMapper.selectVideoByGroup(sysVideo);
        return videoPage;
    }
    public int updateDelFlag(SysVideo sysVideo){
        return sysVideoMapper.updateDelFlag(sysVideo);
    }
}
