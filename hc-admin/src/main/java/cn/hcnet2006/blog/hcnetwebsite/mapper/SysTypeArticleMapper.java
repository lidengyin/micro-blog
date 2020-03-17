package cn.hcnet2006.blog.hcnetwebsite.mapper;

import cn.hcnet2006.blog.hcnetwebsite.bean.SysTypeArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysTypeArticleMapper {
    int deleteByPrimaryKey(Long articleId);

    int insert(SysTypeArticle record);

    SysTypeArticle selectByPrimaryKey(@Param("articleId") Long articleId, @Param("typeId") Long typeId);

    List<SysTypeArticle> selectAll();

    int updateByPrimaryKey(SysTypeArticle record);
}