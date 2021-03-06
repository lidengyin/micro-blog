package cn.hcnet2006.blog.hcnetwebsite.service;

import cn.hcnet2006.blog.hcnetwebsite.jwt.UserLoginDTO;
import cn.hcnet2006.blog.hcnetwebsite.page.PageRequest;
import cn.hcnet2006.blog.hcnetwebsite.page.PageResult;
import cn.hcnet2006.blog.hcnetwebsite.bean.SysUser;
import cn.hcnet2006.blog.hcnetwebsite.bean.SysUserRole;
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

    @Override
    int update(SysUser record);

    @Override
    int update(List<SysUser> records);

    SysUser findByUsername(String username);

    UserLoginDTO login(String username, String password);

    int saveUserAndRole(SysUserRole sysUserRole);

    int deleteUserAndRole(Long userId);
}
