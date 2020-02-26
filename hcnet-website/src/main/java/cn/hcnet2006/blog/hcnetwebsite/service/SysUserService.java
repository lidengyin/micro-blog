package cn.hcnet2006.blog.hcnetwebsite.service;



import cn.hcnet2006.blog.hcnetwebsite.bean.SysUser;
import cn.hcnet2006.blog.hcnetwebsite.page.PageRequest;
import cn.hcnet2006.blog.hcnetwebsite.page.PageResult;

import java.util.List;
import java.util.Set;


public interface SysUserService extends CurdService<SysUser>{
    //根据用户名查询
    public SysUser findByName(String username);

    /**
     * 查找用户的菜单权限标识集合
     * @param userName
     * @return
     */
    Set<String> findPermission(String userName);
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
