package cn.hcnet2006.blog.hcnetwebsite.service.impl;

import cn.hcnet2006.blog.hcnetwebsite.bean.SysApk;
import cn.hcnet2006.blog.hcnetwebsite.mapper.SysApkMapper;
import cn.hcnet2006.blog.hcnetwebsite.page.MybatisPageHelper;
import cn.hcnet2006.blog.hcnetwebsite.page.PageRequest;
import cn.hcnet2006.blog.hcnetwebsite.page.PageResult;
import cn.hcnet2006.blog.hcnetwebsite.service.SysApkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysApkServiceImpl implements SysApkService {
    @Autowired
    private SysApkMapper sysApkMapper;
    @Override
    public int save(SysApk record) {
        return sysApkMapper.insert(record);
    }

    @Override
    public int delete(SysApk record) {
        int result = sysApkMapper.updateDelFlagById(record);
        return result;
    }

    @Override
    public int delete(List<SysApk> records) {
        return 0;
    }

    @Override
    public SysApk findById(Long id) {
        return sysApkMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        Object delFlag = pageRequest.getParam("delFlag");
        System.out.println("delFlag:"+ delFlag);
           return MybatisPageHelper.findPage(pageRequest, sysApkMapper, "selectByDelFlag", (Byte)delFlag);

    }

    @Override
    public int update(SysApk record) {
        return 0;
    }

    @Override
    public int update(List<SysApk> records) {
        return 0;
    }
}
