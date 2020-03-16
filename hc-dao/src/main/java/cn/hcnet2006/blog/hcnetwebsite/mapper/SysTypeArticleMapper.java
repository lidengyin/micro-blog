package cn.hcnet2006.blog.hcnetwebsite.mapper;

import cn.hcnet2006.blog.hcnetwebsite.bean.SysTypeArticle;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface SysTypeArticleMapper {
    int deleteByPrimaryKey( Long articleId);

    int insert(SysTypeArticle record);

    SysTypeArticle selectByPrimaryKey(@Param("articleId") Long articleId, @Param("typeId") Long typeId);

    List<SysTypeArticle> selectAll();

    int updateByPrimaryKey(SysTypeArticle record);
}