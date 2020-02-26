package cn.hcnet2006.blog.userservice.aop;
import cn.hcnet2006.blog.userservice.annotation.SysLogger;
import cn.hcnet2006.blog.userservice.bean.SysLog;
import cn.hcnet2006.blog.userservice.service.LoggerService;
import cn.hcnet2006.blog.userservice.utils.HttpUtils;
import cn.hcnet2006.blog.userservice.utils.UserUtils;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class SysLoggerAspect {
    @Autowired
    private LoggerService loggerService;

    @Pointcut("@annotation(cn.hcnet2006.blog.userservice.annotation.SysLogger)")
    public void loggerPointCut() {

    }

    @Before("loggerPointCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        SysLog sysLog = new SysLog();
        SysLogger sysLogger = method.getAnnotation(SysLogger.class);
        if(sysLogger != null){
            //注解上的描述
            sysLog.setOperation(sysLogger.value());
        }
        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();

        sysLog.setMethod(className + "." + methodName + "()");
        //请求的参数
        Object[] args = joinPoint.getArgs();
        String params="";
        for(Object o:args){
            params+= JSON.toJSONString(o);
        }
        if(!StringUtils.isEmpty(params)) {
            sysLog.setParams(params);
        }
        //设置IP地址
        sysLog.setIp(HttpUtils.getIpAddress());
        //用户名
        String username = UserUtils.getCurrentPrinciple();
        System.out.println("username:"+username);

        if(!StringUtils.isEmpty(username)) {
            sysLog.setUserName(username);
        }
        //设置创建时间
        sysLog.setCreateTime(new Date());
        //设置执行时长（毫秒）

        //保存系统日志
        loggerService.log(sysLog);
    }
}