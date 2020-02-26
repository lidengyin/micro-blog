package cn.hcnet2006.blog.logserver.mapper;

import cn.hcnet2006.blog.logserver.bean.SysLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SysLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysLog record);

    SysLog selectByPrimaryKey(Long id);

    List<SysLog> selectAll();

    int updateByPrimaryKey(SysLog record);
}