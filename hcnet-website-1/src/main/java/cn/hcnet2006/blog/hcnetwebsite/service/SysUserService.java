package cn.hcnet2006.blog.hcnetwebsite.service;

import cn.hcnet2006.blog.hcnetwebsite.bean.SysUser;
import cn.hcnet2006.blog.hcnetwebsite.pages.PageRequest;
import cn.hcnet2006.blog.hcnetwebsite.pages.PageResult;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public interface SysUserService extends CurdService<SysUser> {
    @Override
    int save(SysUser record);

    @Override
    int delete(SysUser record);

    @Override
    int delete(List<SysUser> records);

    @Override
    SysUser findById(Long id);

    @Override
    PageResult findPage(PageRequest pageRequest);
}
