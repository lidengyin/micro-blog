package cn.hcnet2006.blog.hcnetwebsite.service.impl;
import cn.hcnet2006.blog.hcnetwebsite.bean.*;
import cn.hcnet2006.blog.hcnetwebsite.mapper.SysArticleMapper;
import cn.hcnet2006.blog.hcnetwebsite.mapper.SysDeptArticleMapper;
import cn.hcnet2006.blog.hcnetwebsite.mapper.SysTypeArticleMapper;
import cn.hcnet2006.blog.hcnetwebsite.mapper.SysUserArticleMapper;
import cn.hcnet2006.blog.hcnetwebsite.service.SysArticleService;
import cn.hcnet2006.blog.hcnetwebsite.page.MybatisPageHelper;
import cn.hcnet2006.blog.hcnetwebsite.page.PageRequest;
import cn.hcnet2006.blog.hcnetwebsite.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SysArticleServiceImpl  implements SysArticleService {
    @Autowired
    private SysArticleMapper sysArticleMapper;
    @Autowired
    private SysTypeArticleMapper sysTypeArticleMapper;
    @Autowired
    private SysUserArticleMapper sysUserArticleMapper;
    @Autowired
    private SysDeptArticleMapper sysDeptArticleMapper;
    @Override
    public int save(SysArticle record) {
        return sysArticleMapper.insert(record);
    }

    @Override
    public int delete(SysArticle record) {
        return 0;
    }

    @Override
    public int delete(List<SysArticle> records) {
        return 0;
    }

    @Override
    public SysArticle findById(Long id) {
        return sysArticleMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, sysArticleMapper, "selectAll", pageRequest.getParam("sysArticle"));
    }

    @Override
    public int update(SysArticle record) {
        return sysArticleMapper.updateByPrimaryKey(record);
    }

    @Override
    public int update(List<SysArticle> records) {
        return 0;
    }

    @Override
    public int saveUserAndArticle(SysUserArticle sysUserArticle) {
        return sysUserArticleMapper.insert(sysUserArticle);
    }

    @Override
    public int saveDeptAndArticle(SysDeptArticle sysDeptArticle) {
        return sysDeptArticleMapper.insert(sysDeptArticle);
    }

    @Override
    public int saveTypeAndArticle(SysTypeArticle sysTypeArticle) {
        return sysTypeArticleMapper.insert(sysTypeArticle);
    }

    @Override
    public int deleteUserAndArticle(Long articleId) {
        return sysUserArticleMapper.deleteByPrimaryKey(articleId);
    }

    @Override
    public int deleteDeptAndArticle(Long articleId) {
        return sysDeptArticleMapper.deleteByPrimaryKey(articleId);
    }

    @Override
    public int deleteTypeAndArticle(Long articleId) {
        return sysTypeArticleMapper.deleteByPrimaryKey(articleId);
    }

}
