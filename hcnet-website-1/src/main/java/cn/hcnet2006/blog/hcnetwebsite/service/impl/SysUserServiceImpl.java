package cn.hcnet2006.blog.hcnetwebsite.service.impl;

import cn.hcnet2006.blog.hcnetwebsite.bean.SysUser;
import cn.hcnet2006.blog.hcnetwebsite.exception.UserLoginException;
import cn.hcnet2006.blog.hcnetwebsite.jwt.JWT;
import cn.hcnet2006.blog.hcnetwebsite.jwt.UserLoginDTO;
import cn.hcnet2006.blog.hcnetwebsite.jwt.fegin.AuthServiceClient;
import cn.hcnet2006.blog.hcnetwebsite.mapper.SysUserMapper;
import cn.hcnet2006.blog.hcnetwebsite.pages.MybatisPageHelper;
import cn.hcnet2006.blog.hcnetwebsite.pages.PageRequest;
import cn.hcnet2006.blog.hcnetwebsite.pages.PageResult;
import cn.hcnet2006.blog.hcnetwebsite.service.SysUserService;
import cn.hcnet2006.blog.hcnetwebsite.util.PassWordEncoderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public int save(SysUser record) {
        return sysUserMapper.insert(record);
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
        return sysUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, sysUserMapper, "selectAll", pageRequest.getParam("sysUser"));
    }

    @Override
    public int update(SysUser record) {
        return sysUserMapper.updateByPrimaryKey(record);
    }

    @Override
    public int update(List<SysUser> records) {
        return 0;
    }

    @Override
    public SysUser findByUsername(String username) {
        return sysUserMapper.findByUsername(username);
    }
    @Autowired
    AuthServiceClient authServiceClient;
    @Override
    public UserLoginDTO login(String username, String password) {
        SysUser sysUser = sysUserMapper.findByUsername(username);
        if(sysUser == null){
            throw new UserLoginException("该用户不存在");

        }
        System.out.println(PassWordEncoderUtils.matches(password, sysUser.getPassword()));
        System.out.println(sysUser.getPassword());
        if (!PassWordEncoderUtils.matches(password, sysUser.getPassword())){
            throw new UserLoginException("密码错误");
        }
        JWT jwt = authServiceClient.getToken("Basic dXNlci1zZXJ2aWNlOjEyMzQ1Ng==","password",username,password );
        if (jwt == null){
            throw new UserLoginException("error Internal");
        }
        UserLoginDTO userLoginDTO = new UserLoginDTO(jwt, sysUser);
        return userLoginDTO;
    }
}
