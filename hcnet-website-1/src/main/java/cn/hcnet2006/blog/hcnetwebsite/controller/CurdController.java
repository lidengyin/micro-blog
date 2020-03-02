package cn.hcnet2006.blog.hcnetwebsite.controller;

import cn.hcnet2006.blog.hcnetwebsite.http.HttpResult;
import org.apache.tomcat.util.http.parser.HttpParser;

public interface CurdController {
    public HttpResult findAll();
    public HttpResult findByDelFlag(byte delFlag);
    public HttpResult findById(Long id);
}
