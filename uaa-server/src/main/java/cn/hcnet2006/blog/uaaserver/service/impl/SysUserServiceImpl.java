package cn.hcnet2006.blog.uaaserver.service.impl;

import cn.hcnet2006.blog.uaaserver.bean.SysMenu;
import cn.hcnet2006.blog.uaaserver.bean.SysUser;
import cn.hcnet2006.blog.uaaserver.mapper.SysRoleMapper;
import cn.hcnet2006.blog.uaaserver.mapper.SysUserMapper;
import cn.hcnet2006.blog.uaaserver.mapper.SysUserRoleMapper;
import cn.hcnet2006.blog.uaaserver.page.PageRequest;
import cn.hcnet2006.blog.uaaserver.page.PageResult;
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
    private SysUserMapper userMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysMenuService sysMenuService;
    @Override
    public SysUser findByName(String username) {
        System.out.println("UserService.findByName");
        return userMapper.findByName(username);
    }
    @Override
    public Set<String> findPermission(String userName) {
        Set<String> perms = new HashSet<>();
        List<SysMenu> sysMenus = sysMenuService.findByUser(userName);
        for(SysMenu sysMenu : sysMenus){
            if(sysMenu.getPerms() != null && !"".equals(sysMenu.getPerms())){
                System.out.println("sysMenu.getPerms():"+sysMenu.getPerms());
                perms.add(sysMenu.getPerms());
            }

        }

        return perms;
    }

    @Override
    public int save(SysUser record) {
        return 0;
    }

    @Override
    public int delete(SysUser record) {
        return 0;
    }

    @Override
    public int delete(List<SysUser> records) {
        return 0;
    }

    @Override
    public SysUser findById(Long id) {
        return null;
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return null;
    }
}
