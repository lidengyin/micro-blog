package cn.hcnet2006.blog.hcnetwebsite.service;


import cn.hcnet2006.blog.hcnetwebsite.bean.SysMenu;
import cn.hcnet2006.core.page.PageRequest;
import cn.hcnet2006.core.page.PageResult;


import java.util.List;

public interface SysMenuService extends CurdService<SysMenu> {
    @Override
    int save(SysMenu record);

    @Override
    int delete(SysMenu record);

    @Override
    int delete(List<SysMenu> records);

    @Override
    SysMenu findById(Long id);

    @Override
    PageResult findPage(PageRequest pageRequest);
}
