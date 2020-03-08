package cn.hcnet2006.blog.hcnetwebsite.service;

import cn.hcnet2006.blog.hcnetwebsite.bean.SysDept;
import cn.hcnet2006.blog.hcnetwebsite.bean.SysRole;
import cn.hcnet2006.blog.hcnetwebsite.bean.SysRoleMenu;
import cn.hcnet2006.blog.hcnetwebsite.pages.PageRequest;
import cn.hcnet2006.blog.hcnetwebsite.pages.PageResult;

import java.util.List;

public interface SysRoleService extends CurdService<SysRole>{
    @Override
    int save(SysRole record);

    @Override
    int delete(SysRole record);

    @Override
    int delete(List<SysRole> records);

    @Override
    SysRole findById(Long id);

    @Override
    PageResult findPage(PageRequest pageRequest);

    int saveRoleAndMenu(SysRoleMenu sysRoleMenu);

    int updateRoleAndMenuDelFlag(Long roleId);

}
