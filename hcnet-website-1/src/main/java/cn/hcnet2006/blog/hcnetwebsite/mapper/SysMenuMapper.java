package cn.hcnet2006.blog.hcnetwebsite.mapper;

import cn.hcnet2006.blog.hcnetwebsite.bean.SysMenu;
import java.util.List;

public interface SysMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysMenu record);

    SysMenu selectByPrimaryKey(Integer id);

    List<SysMenu> selectAll();

    int updateByPrimaryKey(SysMenu record);
}