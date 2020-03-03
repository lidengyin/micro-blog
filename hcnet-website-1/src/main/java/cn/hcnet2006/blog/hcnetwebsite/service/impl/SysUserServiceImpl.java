package cn.hcnet2006.blog.hcnetwebsite.service.impl;

import cn.hcnet2006.blog.hcnetwebsite.bean.SysUser;
import cn.hcnet2006.blog.hcnetwebsite.mapper.SysUserMapper;
import cn.hcnet2006.blog.hcnetwebsite.pages.MybatisPageHelper;
import cn.hcnet2006.blog.hcnetwebsite.pages.PageRequest;
import cn.hcnet2006.blog.hcnetwebsite.pages.PageResult;
import cn.hcnet2006.blog.hcnetwebsite.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public int save(SysUser record) {
        return sysUserMapper.insert(record);
    }

    @Override
    public int delete(SysUser record) {
        return 0;
    }

    @Override
    public int delete(List<SysUser> records) {
        return 0;
    }

    @Override
    public SysUser findById(Long id) {
        return null;
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, sysUserMapper, "selectAll", pageRequest.getParam("sysUser"));
    }

    @Override
    public int update(SysUser record) {
        return sysUserMapper.updateByPrimaryKey(record);
    }

    @Override
    public int update(List<SysUser> records) {
        return 0;
    }
}
