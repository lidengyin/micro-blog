package cn.hcnet2006.blog.hcnetwebsite.mapper;

import cn.hcnet2006.blog.hcnetwebsite.bean.SysDept;
import java.util.List;

public interface SysDeptMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysDept record);

    SysDept selectByPrimaryKey(Long id);

    List<SysDept> selectAll();

    int updateByPrimaryKey(SysDept record);
}