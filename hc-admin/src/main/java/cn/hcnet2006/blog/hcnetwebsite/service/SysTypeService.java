package cn.hcnet2006.blog.hcnetwebsite.service;

import cn.hcnet2006.blog.hcnetwebsite.bean.SysType;
import cn.hcnet2006.core.page.PageRequest;
import cn.hcnet2006.core.page.PageResult;

import java.util.List;

public interface SysTypeService extends CurdService<SysType>{
    @Override
    int save(SysType record);

    @Override
    int delete(SysType record);

    @Override
    int delete(List<SysType> records);

    @Override
    SysType findById(Long id);

    @Override
    PageResult findPage(PageRequest pageRequest);

    @Override
    int update(SysType record);

    @Override
    int update(List<SysType> records);
}
