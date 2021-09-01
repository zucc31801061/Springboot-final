package com.demo.service;

import com.demo.entity.Company;

import java.util.List;

public interface CompService {
    //添加无公司
    void addUnemployed();
    //注册公司
    void register(Company company);
    //登录
    int login(String id, String pwd);
    //查找公司
    Company getById(String id);
    //修改公司
    void update(Company company);
    //修改密码
    void updatePwd(String id, String pwd);
    //查找所有公司
    List<Company> findAll();
    //删除公司
    void deleteCompany(String id);
}
