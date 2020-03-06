package cn.hcnet2006.blog.uaaserver.service;

import cn.hcnet2006.blog.uaaserver.bean.SysUser;

import java.util.Set;

public interface SysUserService {
    public SysUser findUserByName(String username);
    public Set<String> findPermissions(String username);
}
