package cn.hcnet2006.blog.hcnetwebsite.mapper;


import cn.hcnet2006.blog.hcnetwebsite.bean.SysVideo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysVideoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysVideo record);

    //用得到的

    SysVideo selectByPrimaryKey(Integer id);

    List<SysVideo> selectAll(Byte delFlag);

    int updateByPrimaryKey(SysVideo record);

    int saveVideo(SysVideo sysVideo);

    Page<SysVideo> selectVideoByAge(SysVideo sysVideo);

    List<String> selectAllAges();

    Page<SysVideo> selectVideoByGroup(SysVideo sysVideo);

    int updateDelFlag(SysVideo sysVideo);
}