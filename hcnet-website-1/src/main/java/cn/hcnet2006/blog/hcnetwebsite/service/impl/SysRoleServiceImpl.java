package cn.hcnet2006.blog.hcnetwebsite.service.impl;

import cn.hcnet2006.blog.hcnetwebsite.service.SysRoleService;
import cn.hcnet2006.blog.hcnetwebsite.mapper.SysRoleMapper;
import cn.hcnet2006.blog.hcnetwebsite.mapper.SysRoleMenuMapper;
import cn.hcnet2006.blog.hcnetwebsite.page.MybatisPageHelper;
import cn.hcnet2006.blog.hcnetwebsite.page.PageRequest;
import cn.hcnet2006.blog.hcnetwebsite.page.PageResult;
import cn.hcnet2006.blog.hcnetwebsite.bean.SysRole;
import cn.hcnet2006.blog.hcnetwebsite.bean.SysRoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
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
        return sysRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {


        return  MybatisPageHelper.findPage(pageRequest,sysRoleMapper, "selectAll", pageRequest.getParam("sysRole"));
    }

    @Override
    public int saveRoleAndMenu(SysRoleMenu record) {
        return sysRoleMenuMapper.insert(record);
    }

    @Override
    public int updateRoleAndMenuDelFlag(Long roleId) {
        return sysRoleMenuMapper.deleteByPrimaryKey(roleId);
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
