package cn.hcnet2006.blog.uaaserver.service.impl;

import cn.hcnet2006.blog.uaaserver.bean.SysMenu;
import cn.hcnet2006.blog.uaaserver.mapper.SysMenuMapper;
import cn.hcnet2006.blog.uaaserver.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Override
    public List<SysMenu> findMenusByUsername(String username) {
        return sysMenuMapper.selectMenusByUsername(username);
    }
}
