package cn.hcnet2006.blog.hcnetwebsite.mapper;

import cn.hcnet2006.blog.hcnetwebsite.bean.SysDept;
import cn.hcnet2006.blog.hcnetwebsite.bean.SysDeptArticle;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface SysDeptArticleMapper {
    int deleteByPrimaryKey(Long articleId);

    int insert(SysDeptArticle record);

    SysDeptArticle selectByPrimaryKey(@Param("deptId") Long deptId, @Param("articleId") Long articleId);

    List<SysDeptArticle> selectAll();

    int updateByPrimaryKey(SysDeptArticle record);

}