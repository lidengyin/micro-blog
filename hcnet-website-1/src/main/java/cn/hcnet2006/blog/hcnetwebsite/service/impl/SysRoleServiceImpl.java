package cn.hcnet2006.blog.hcnetwebsite.service.impl;

import cn.hcnet2006.blog.hcnetwebsite.bean.SysRole;
import cn.hcnet2006.blog.hcnetwebsite.mapper.SysRoleMapper;
import cn.hcnet2006.blog.hcnetwebsite.pages.MybatisPageHelper;
import cn.hcnet2006.blog.hcnetwebsite.pages.PageRequest;
import cn.hcnet2006.blog.hcnetwebsite.pages.PageResult;
import cn.hcnet2006.blog.hcnetwebsite.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Override
    public int save(SysRole record) {
        return sysRoleMapper.insert(record);
    }

    @Override
    public int delete(SysRole record) {
        return 0;
    }

    @Override
    public int delete(List<SysRole> records) {
        return 0;
    }

    @Override
    public SysRole findById(Long id) {
        return null;
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest,sysRoleMapper, "selectAll", pageRequest.getParam("sysRole"));
    }

    @Override
    public int update(SysRole record) {
        return sysRoleMapper.updateByPrimaryKey(record);
    }

    @Override
    public int update(List<SysRole> records) {
        return sysRoleMapper.updateByPrimaryKey(records);
    }
}
