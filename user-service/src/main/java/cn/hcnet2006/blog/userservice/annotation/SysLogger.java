package cn.hcnet2006.blog.userservice.annotation;

import java.lang.annotation.*;

/**
 * Created by fangzhipeng on 2017/7/12.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLogger {
    String value() default "";
}
