package cn.hcnet2006.blog.hcnetwebsite.service;

import cn.hcnet2006.blog.hcnetwebsite.bean.SysLog;
import cn.hcnet2006.blog.hcnetwebsite.page.PageRequest;
import cn.hcnet2006.blog.hcnetwebsite.page.PageResult;

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

    public void log(SysLog sysLog);
}
