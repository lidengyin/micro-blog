package cn.hcnet2006.blog.hcnetwebsite.mapper;

import cn.hcnet2006.blog.hcnetwebsite.bean.SysRoleDept;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface SysRoleDeptMapper {
    int deleteByPrimaryKey(@Param("roleId") Long roleId, @Param("deptId") Long deptId);

    int insert(SysRoleDept record);

    SysRoleDept selectByPrimaryKey(@Param("roleId") Long roleId, @Param("deptId") Long deptId);

    List<SysRoleDept> selectAll();

    int updateByPrimaryKey(SysRoleDept record);
}