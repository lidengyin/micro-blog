package cn.hcnet2006.blog.hcnetwebsite.service.impl;

import cn.hcnet2006.blog.hcnetwebsite.bean.SysType;
import cn.hcnet2006.blog.hcnetwebsite.mapper.SysTypeMapper;
import cn.hcnet2006.blog.hcnetwebsite.service.SysTypeService;
import cn.hcnet2006.blog.hcnetwebsite.page.MybatisPageHelper;
import cn.hcnet2006.blog.hcnetwebsite.page.PageRequest;
import cn.hcnet2006.blog.hcnetwebsite.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SysTypeServiceImpl implements SysTypeService {
    @Autowired
    private SysTypeMapper sysTypeMapper;
    @Override
    public int save(SysType record) {
        return sysTypeMapper.insert(record);
    }

    @Override
    public int delete(SysType record) {
        return 0;
    }

    @Override
    public int delete(List<SysType> records) {
        return 0;
    }

    @Override
    public SysType findById(Long id) {
        return null;
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest,sysTypeMapper,"selectAll", pageRequest.getParam("sysType"));
    }

    @Override
    public int update(SysType record) {
        return sysTypeMapper.updateByPrimaryKey(record);
    }

    @Override
    public int update(List<SysType> records) {
        return 0;
    }
}
