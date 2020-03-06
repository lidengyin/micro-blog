package cn.hcnet2006.blog.uaaserver.service.impl;

import cn.hcnet2006.blog.uaaserver.bean.SysMenu;
import cn.hcnet2006.blog.uaaserver.bean.SysUser;
import cn.hcnet2006.blog.uaaserver.mapper.SysUserMapper;
import cn.hcnet2006.blog.uaaserver.service.SysMenuService;
import cn.hcnet2006.blog.uaaserver.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysMenuService sysMenuService;
    @Override
    public SysUser findUserByName(String username) {
        return sysUserMapper.findByUserName(username);
    }
    @Override
    public Set<String> findPermissions(String username) {
        Set<String> perms = new HashSet<>();
        List<SysMenu> sysMenus = sysMenuService.findMenusByUsername(username);
        for (SysMenu sysMenu : sysMenus){
            if(sysMenu.getPerms() != null && sysMenu.getPerms() != ""){
                perms.add(sysMenu.getPerms());
            }
        }
        return perms;
    }
}
