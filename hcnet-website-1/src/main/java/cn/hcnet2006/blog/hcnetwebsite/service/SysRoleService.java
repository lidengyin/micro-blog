package cn.hcnet2006.blog.hcnetwebsite.service;


import cn.hcnet2006.blog.hcnetwebsite.page.PageRequest;
import cn.hcnet2006.blog.hcnetwebsite.page.PageResult;
import cn.hcnet2006.blog.hcnetwebsite.bean.SysRole;
import cn.hcnet2006.blog.hcnetwebsite.bean.SysRoleMenu;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
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
