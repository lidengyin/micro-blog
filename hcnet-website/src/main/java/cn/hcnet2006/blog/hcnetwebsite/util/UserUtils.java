package cn.hcnet2006.blog.hcnetwebsite.util;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

/**
 * 封装当前请求用户，权限，token的工具类
 */
public class UserUtils {
    /**
     * 获取当前请求用户
     * @return
     */
    public static String getCurrentPrinciple(){
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * 获取当前请求的token
     * @return
     */
    public static String getCurrentToken(){
        return null;
    }

    /**
     * 获取当钱请求权限信息
     * @return
     */
    public static List<SimpleGrantedAuthority> getCurrentAuthorities(){
        return (List<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }
}
