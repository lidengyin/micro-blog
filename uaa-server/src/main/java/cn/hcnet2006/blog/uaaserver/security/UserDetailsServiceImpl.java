package cn.hcnet2006.blog.uaaserver.security;
import cn.hcnet2006.blog.uaaserver.bean.SysUser;
import cn.hcnet2006.blog.uaaserver.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户登录信息认证
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserService sysUserService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = sysUserService.findByName(username);
        System.out.println("sysUser.getName:"+user.getName());
        System.out.println("sysUser.getPass:"+user.getPassword());
        if(user == null){
            System.out.println("该用户不存在");
            throw new UsernameNotFoundException("该用户不存在");
        }
        Set<String> permissions = sysUserService.findPermission(user.getName());
        List<GrantedAuthority> grantedAuthorities =
                permissions.stream().map(GrantedAuthorityImpl::new).collect(Collectors.toList());
        return new JwtUserDetails(user.getName(), user.getPassword(), grantedAuthorities);
    }
}
