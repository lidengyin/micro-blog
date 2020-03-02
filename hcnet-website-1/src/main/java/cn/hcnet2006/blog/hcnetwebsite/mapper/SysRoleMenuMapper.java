package cn.hcnet2006.blog.hcnetwebsite.mapper;

import cn.hcnet2006.blog.hcnetwebsite.bean.SysRoleMenu;
import java.util.List;

public interface SysRoleMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRoleMenu record);

    SysRoleMenu selectByPrimaryKey(Long id);

    List<SysRoleMenu> selectAll();

    int updateByPrimaryKey(SysRoleMenu record);
}