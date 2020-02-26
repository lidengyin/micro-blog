package cn.hcnet2006.blog.logserver.service;

import cn.hcnet2006.blog.logserver.bean.SysLog;
import cn.hcnet2006.blog.logserver.page.PageRequest;
import cn.hcnet2006.blog.logserver.page.PageResult;

import java.util.List;

public interface LogService extends CurdService<SysLog>{
    @Override
    int save(SysLog record);

    @Override
    int delete(SysLog record);

    @Override
    int delete(List<SysLog> records);

    @Override
    SysLog findById(Long id);

    @Override
    PageResult findPage(PageRequest pageRequest);
}
