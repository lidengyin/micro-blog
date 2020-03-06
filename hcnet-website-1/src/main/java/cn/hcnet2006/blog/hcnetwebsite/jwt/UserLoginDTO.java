package cn.hcnet2006.blog.hcnetwebsite.jwt;

import cn.hcnet2006.blog.hcnetwebsite.bean.SysUser;
import org.springframework.security.jwt.Jwt;

public class UserLoginDTO {
    private JWT jwt;
    private SysUser sysUser;

    public UserLoginDTO(JWT jwt, SysUser sysUser) {
        this.jwt = jwt;
        this.sysUser = sysUser;
    }

    public UserLoginDTO(JWT jwt) {
        this.jwt = jwt;
    }

    public JWT getJwt() {
        return jwt;
    }

    public void setJwt(JWT jwt) {
        this.jwt = jwt;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }
}
