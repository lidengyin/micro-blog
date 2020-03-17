package cn.hcnet2006.blog.hcnetwebsite.service;

import cn.hcnet2006.blog.hcnetwebsite.bean.SysArticle;
import cn.hcnet2006.blog.hcnetwebsite.bean.SysDeptArticle;
import cn.hcnet2006.blog.hcnetwebsite.bean.SysTypeArticle;
import cn.hcnet2006.blog.hcnetwebsite.bean.SysUserArticle;
import cn.hcnet2006.blog.hcnetwebsite.page.PageRequest;
import cn.hcnet2006.blog.hcnetwebsite.page.PageResult;

import java.util.List;

public interface SysArticleService extends CurdService<SysArticle>{
    @Override
    int save(SysArticle record);

    @Override
    int delete(SysArticle record);

    @Override
    int delete(List<SysArticle> records);

    @Override
    SysArticle findById(Long id);

    @Override
    PageResult findPage(PageRequest pageRequest);

    @Override
    int update(SysArticle record);

    @Override
    int update(List<SysArticle> records);

    int saveUserAndArticle(SysUserArticle sysUserArticle);
    int saveDeptAndArticle(SysDeptArticle sysDeptArticle);
    int saveTypeAndArticle(SysTypeArticle sysTypeArticle);
    int deleteUserAndArticle(Long articleId);
    int deleteDeptAndArticle(Long articleId);
    int deleteTypeAndArticle(Long articleId);

}
