package cn.hcnet2006.blog.userservice.service;

import cn.hcnet2006.blog.userservice.bean.SysLog;
import cn.hcnet2006.blog.userservice.config.RabbitConfig;
import com.alibaba.fastjson.JSON;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by fangzhipeng on 2017/7/12.
 */
@Service
public class LoggerService {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void log(SysLog sysLog){
        rabbitTemplate.convertAndSend(RabbitConfig.queueName, JSON.toJSONString(sysLog));
    }
}
