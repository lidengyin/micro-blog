package cn.hcnet2006.blog.uaaserver.service;

import cn.hcnet2006.blog.uaaserver.bean.SysMenu;

import java.util.List;

public interface SysMenuService {
    public List<SysMenu> findMenusByUsername(String username);
}
