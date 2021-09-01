package com.demo.service;

import com.demo.entity.Admin;

public interface AdminService {
    //管理员注册
    void addAdmin(Admin admin);
    //管理员登录
    int login(String id, String pwd);
    //修改密码
    int updatePwd(String id, String pwd);
    //获取管理员对象
    Admin getById(String id);
}
