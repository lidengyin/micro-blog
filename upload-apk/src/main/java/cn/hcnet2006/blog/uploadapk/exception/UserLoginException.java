package cn.hcnet2006.blog.uploadapk.exception;

/**
 * 统一异常处理
 */
public class UserLoginException extends RuntimeException {
    public UserLoginException(String message){
        super(message);
    }
}
