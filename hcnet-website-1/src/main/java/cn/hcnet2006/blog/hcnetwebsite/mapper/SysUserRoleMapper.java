package cn.hcnet2006.blog.hcnetwebsite.mapper;

import cn.hcnet2006.blog.hcnetwebsite.bean.SysUserRole;
import java.util.List;

public interface SysUserRoleMapper {
    int insert(SysUserRole record);
    List<SysUserRole> selectAll();
}