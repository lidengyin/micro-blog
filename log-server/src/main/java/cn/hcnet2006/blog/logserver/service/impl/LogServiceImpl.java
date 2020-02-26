package cn.hcnet2006.blog.logserver.service.impl;

import cn.hcnet2006.blog.logserver.bean.SysLog;
import cn.hcnet2006.blog.logserver.mapper.SysLogMapper;
import cn.hcnet2006.blog.logserver.page.PageRequest;
import cn.hcnet2006.blog.logserver.page.PageResult;
import cn.hcnet2006.blog.logserver.service.LogService;
import com.sun.org.apache.xml.internal.utils.PrefixResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private SysLogMapper sysLogMapper;
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
}
