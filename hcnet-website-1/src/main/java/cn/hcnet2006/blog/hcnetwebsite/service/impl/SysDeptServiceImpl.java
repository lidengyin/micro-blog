package cn.hcnet2006.blog.hcnetwebsite.service.impl;

import cn.hcnet2006.blog.hcnetwebsite.bean.SysDept;
import cn.hcnet2006.blog.hcnetwebsite.mapper.SysDeptMapper;
import cn.hcnet2006.blog.hcnetwebsite.pages.MybatisPageHelper;
import cn.hcnet2006.blog.hcnetwebsite.pages.PageRequest;
import cn.hcnet2006.blog.hcnetwebsite.pages.PageResult;
import cn.hcnet2006.blog.hcnetwebsite.service.SysDeptService;
import io.swagger.annotations.OAuth2Definition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SysDeptServiceImpl implements SysDeptService {
    @Autowired
    private SysDeptMapper sysDeptMapper;
    @Override
    public int save(SysDept record) {
        return sysDeptMapper.insert(record);
    }

    @Override
    public int delete(SysDept record) {
        return 0;
    }

    @Override
    public int delete(List<SysDept> records) {
        return 0;
    }

    @Override
    public SysDept findById(Long id) {
        return null;
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, sysDeptMapper,
                "selectAll",pageRequest.getParam("sysDept"));

    }

    @Override
    public int update(SysDept record) {
        return 0;
    }

    @Override
    public int update(List<SysDept> records) {
        return 0;
    }
}
