package cn.hcnet2006.blog.uploadapk.service.impl;

import cn.hcnet2006.blog.uploadapk.bean.SysLog;
import cn.hcnet2006.blog.uploadapk.config.RabbitConfig;
import cn.hcnet2006.blog.uploadapk.mapper.SysLogMapper;
import cn.hcnet2006.blog.uploadapk.page.PageRequest;
import cn.hcnet2006.blog.uploadapk.page.PageResult;
import cn.hcnet2006.blog.uploadapk.service.LogService;
import com.alibaba.fastjson.JSON;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private SysLogMapper sysLogMapper;
    @Autowired
    AmqpTemplate rabbitTemplate;
    @Override
    public int save(SysLog record) {
        return sysLogMapper.insert(record);
    }

    @Override
    public int delete(SysLog record) {
        return 0;
    }

    @Override
    public int delete(List<SysLog> records) {
        return 0;
    }

    @Override
    public SysLog findById(Long id) {
        return null;
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return null;
    }

    @Override
    public void log(SysLog sysLog) {
        rabbitTemplate.convertAndSend
                (RabbitConfig.queueName, JSON.toJSONString(sysLog));
    }
}
