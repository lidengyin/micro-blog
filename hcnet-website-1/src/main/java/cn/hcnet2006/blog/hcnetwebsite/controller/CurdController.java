package cn.hcnet2006.blog.hcnetwebsite.controller;


import cn.hcnet2006.core.http.HttpResult;

public interface CurdController {
    public HttpResult findAll();
    public HttpResult findByDelFlag(byte delFlag);
    public HttpResult findById(Long id);
}
