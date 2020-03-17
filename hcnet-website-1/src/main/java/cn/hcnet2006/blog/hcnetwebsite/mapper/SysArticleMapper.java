package cn.hcnet2006.blog.hcnetwebsite.mapper;

import cn.hcnet2006.blog.hcnetwebsite.bean.SysArticle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysArticleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysArticle record);

    SysArticle selectByPrimaryKey(Long id);

    List<SysArticle> selectAll(SysArticle sysArticle);

    int updateByPrimaryKey(SysArticle record);
}