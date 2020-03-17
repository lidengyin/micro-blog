package cn.hcnet2006.blog.hcnetwebsite.service;

import cn.hcnet2006.blog.hcnetwebsite.bean.SysApk;
import cn.hcnet2006.blog.hcnetwebsite.page.PageRequest;
import cn.hcnet2006.blog.hcnetwebsite.page.PageResult;

import java.util.List;

public interface SysApkService extends CurdService<SysApk> {
    @Override
    int save(SysApk record);

    @Override
    int delete(SysApk record);

    @Override
    int delete(List<SysApk> records);

    @Override
    SysApk findById(Long id);

    @Override
    PageResult findPage(PageRequest pageRequest);


}
