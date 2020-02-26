package cn.hcnet2006.blog.uploadapk.mapper;

import cn.hcnet2006.blog.uploadapk.bean.SysApk;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SysApkMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysApk record);

    SysApk selectByPrimaryKey(Long id);

    List<SysApk> selectAll();

    int updateByPrimaryKey(SysApk record);

    int updateDelFlagById(SysApk sysApk);

    List<SysApk> selectByDelFlag(Byte delFlag);
}