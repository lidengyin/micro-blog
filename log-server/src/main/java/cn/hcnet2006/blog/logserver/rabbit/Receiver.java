package cn.hcnet2006.blog.logserver.rabbit;

import cn.hcnet2006.blog.logserver.bean.SysLog;
import cn.hcnet2006.blog.logserver.service.LogService;
import com.alibaba.fastjson.JSON;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * Created by fangzhipeng on 2017/7/12.
 */
@Component
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);

    @Autowired
    LogService sysLogService;
    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        SysLog sysLog=  JSON.parseObject(message,SysLog.class);
        sysLogService.save(sysLog);
        latch.countDown();
    }


}