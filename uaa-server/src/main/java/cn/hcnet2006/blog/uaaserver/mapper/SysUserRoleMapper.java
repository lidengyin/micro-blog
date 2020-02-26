package cn.hcnet2006.blog.uaaserver.mapper;

import cn.hcnet2006.blog.uaaserver.bean.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUserRole record);

    SysUserRole selectByPrimaryKey(Long id);

    List<SysUserRole> selectAll();

    int updateByPrimaryKey(SysUserRole record);
}