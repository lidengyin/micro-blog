package cn.hcnet2006.blog.userservice.service;

import cn.hcnet2006.blog.userservice.bean.SysLog;
import cn.hcnet2006.blog.userservice.page.PageRequest;
import cn.hcnet2006.blog.userservice.page.PageResult;

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
