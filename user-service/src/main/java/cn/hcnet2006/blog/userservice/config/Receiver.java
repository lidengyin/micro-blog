package cn.hcnet2006.blog.userservice.config;

import cn.hcnet2006.blog.userservice.bean.SysLog;
import cn.hcnet2006.blog.userservice.service.LogService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * 接受RabbitMQ消息存储到数据库
 */
@Component
public class Receiver {
    //信号量
    private CountDownLatch latch = new CountDownLatch(1);
    @Autowired
    private LogService logService;
    public void receiveMessage(String message){
        System.out.println("Received <"+message+">");
        //进行序列化操作
        SysLog sysLog = JSON.parseObject(message, SysLog.class);
        logService.save(sysLog);
        //释放信号
        latch.countDown();
    }
}
