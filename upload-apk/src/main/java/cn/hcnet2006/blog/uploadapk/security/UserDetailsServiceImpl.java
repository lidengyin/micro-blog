package cn.hcnet2006.blog.uploadapk.security;

import cn.hcnet2006.blog.uploadapk.bean.SysUser;
import cn.hcnet2006.blog.uploadapk.exception.UserLoginException;
import cn.hcnet2006.blog.uploadapk.service.SysUserService;
import cn.hcnet2006.blog.uploadapk.utils.PassWordEncoderUtil;
import cn.hcnet2006.blog.uploadapk.vo.JWT;
import cn.hcnet2006.blog.uploadapk.vo.UserLoginDTO;
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
        if(user == null){
            throw new UsernameNotFoundException("该用户不存在");
        }
        Set<String> permissions = sysUserService.findPermission(user.getName());
        List<GrantedAuthority> grantedAuthorities =
                permissions.stream().map(GrantedAuthorityImpl::new).collect(Collectors.toList());
        return new JwtUserDetails(user.getName(), user.getPassword(), grantedAuthorities);
    }
    @Autowired
    AuthServiceClient client;
    public UserLoginDTO login(String username, String password){
        SysUser  user = sysUserService.findByName(username);
        if(user == null){
            throw new UserLoginException("该用户不存在");
        }
        if(!PassWordEncoderUtil.matches(password,user.getPassword())){
            throw new UserLoginException("密码错误");
        }
        System.out.println("user:"+user.getName());
        System.out.println("pass:"+user.getPassword());
        JWT jwt = client.getToken("Basic dXNlci1zZXJ2aWNlOjEyMzQ1Ng==","password",username,password );
        if(jwt == null){
            throw new UserLoginException("error internal");
        }
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setJwt(jwt);
        userLoginDTO.setUserDetails(user);
        return userLoginDTO;
    }
}
