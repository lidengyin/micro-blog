package cn.hcnet2006.blog.hcnetwebsite.controller;

import javax.servlet.http.HttpServletResponse;

public interface CurdController {
    public void returnImage(Integer id, HttpServletResponse resp);
    public void returnVideo(Integer id, HttpServletResponse resp);
}
