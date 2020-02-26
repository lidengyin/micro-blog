package cn.hcnet2006.blog.uploadapk.service;

import cn.hcnet2006.blog.uploadapk.bean.SysLog;
import cn.hcnet2006.blog.uploadapk.page.PageRequest;
import cn.hcnet2006.blog.uploadapk.page.PageResult;

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
