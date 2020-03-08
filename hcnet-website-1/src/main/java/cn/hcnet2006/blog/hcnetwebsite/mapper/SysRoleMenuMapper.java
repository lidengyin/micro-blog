package cn.hcnet2006.blog.hcnetwebsite.mapper;

import cn.hcnet2006.blog.hcnetwebsite.bean.SysRoleMenu;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface SysRoleMenuMapper {
    int deleteByPrimaryKey(@Param("roleId") Long roleId);

    int insert(SysRoleMenu record);

    SysRoleMenu selectByPrimaryKey(@Param("roleId") Long roleId, @Param("menuId") Long menuId);

    List<SysRoleMenu> selectAll();

    int updateByPrimaryKey(SysRoleMenu record);


}