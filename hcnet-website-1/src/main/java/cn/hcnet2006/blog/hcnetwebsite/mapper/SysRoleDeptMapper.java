package cn.hcnet2006.blog.hcnetwebsite.mapper;

import cn.hcnet2006.blog.hcnetwebsite.bean.SysRoleDept;
import java.util.List;

public interface SysRoleDeptMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRoleDept record);

    SysRoleDept selectByPrimaryKey(Long id);

    List<SysRoleDept> selectAll();

    int updateByPrimaryKey(SysRoleDept record);
}