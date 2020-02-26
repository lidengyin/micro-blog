package cn.hcnet2006.blog.userservice.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 */
public class UserLoginException extends RuntimeException {
    public UserLoginException(String message){
        super(message);
    }
}
