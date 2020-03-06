package cn.hcnet2006.blog.uaaserver.config;

import org.bouncycastle.crypto.generators.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //设置获取基本用户信息和用户权限的信息方法类userDetailService
        //设置密码对比策略，采用BCrypt加密算法
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
    //登录认证过程时委托给AuthenticationManager完成的，Authentication提供了
    //一个默认的实现ProviderManager,而ProviderManager又将验证委托给了AuthenticationProvider
    //DaoAuthenticationProvider继承了AuthenticationProvider的抽象实现，AbstractUserdetailsAuthenticationProvider
    //完成了从DAO方式获取验证需要的用户信息，
    //对于我们一般用的DaoAuthenticationProvider是由UserDetailsService专门获取验证信息的
    //userDetailsSerrvice接口只有一个方法，loadUserByUsername(String username),一般需要我们实现此接口方法
    //根据用户名加载登录认证和访问授权所需要的信息，并返回一个UserDetils的实现类，
    //后面登录认证和访问授权搜需要用到此种的信息
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    //登录认证，使用内置登录验证过滤器
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       //使用JWT默认关闭跨域攻击防御
        http.csrf().disable()
                //登录异常处理
            .exceptionHandling()
                //认证失败异常处理，返回401未授权界面
            .authenticationEntryPoint(((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED)))
             .and()
                .authorizeRequests()
                //路径访问原则,已经认证
                .antMatchers("/actuator/health").permitAll()
                .antMatchers("/**").authenticated()
                .and()
                .httpBasic();
    }
}
