package cn.hcnet2006.blog.hcnetwebsite.service.impl;

import cn.hcnet2006.blog.hcnetwebsite.bean.SysMenu;
import cn.hcnet2006.blog.hcnetwebsite.constant.SysConstants;
import cn.hcnet2006.blog.hcnetwebsite.mapper.SysMenuMapper;
import cn.hcnet2006.blog.hcnetwebsite.page.PageRequest;
import cn.hcnet2006.blog.hcnetwebsite.page.PageResult;
import cn.hcnet2006.blog.hcnetwebsite.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Override
    public List<SysMenu> findByUser(String username) {
        if(username == null || "".equals(username)
        || SysConstants.ADMIN.equalsIgnoreCase(username)){
            System.out.println("用户不存在，因此列出所有权限");
        return sysMenuMapper.selectAll();
        }
        System.out.println("用户存在，菜单存在");
        return sysMenuMapper.findByUserName(username);
    }

    @Override
    public int save(SysMenu record) {
        return 0;
    }

    @Override
    public int delete(SysMenu record) {
        return 0;
    }

    @Override
    public int delete(List<SysMenu> records) {
        return 0;
    }

    @Override
    public SysMenu findById(Long id) {
        return null;
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return null;
    }
}
