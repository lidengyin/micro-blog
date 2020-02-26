package cn.hcnet2006.blog.uploadapk.service.impl;

import cn.hcnet2006.blog.uploadapk.bean.SysMenu;
import cn.hcnet2006.blog.uploadapk.bean.SysUser;
import cn.hcnet2006.blog.uploadapk.mapper.SysRoleMapper;
import cn.hcnet2006.blog.uploadapk.mapper.SysUserMapper;
import cn.hcnet2006.blog.uploadapk.mapper.SysUserRoleMapper;
import cn.hcnet2006.blog.uploadapk.page.MybatisPageHelper;
import cn.hcnet2006.blog.uploadapk.page.PageRequest;
import cn.hcnet2006.blog.uploadapk.page.PageResult;
import cn.hcnet2006.blog.uploadapk.service.SysMenuService;
import cn.hcnet2006.blog.uploadapk.service.SysUserService;
import com.github.pagehelper.PageHelper;
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
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysMenuService sysMenuService;
    @Override
    public SysUser findByName(String username) {
        return sysUserMapper.findByName(username);
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
