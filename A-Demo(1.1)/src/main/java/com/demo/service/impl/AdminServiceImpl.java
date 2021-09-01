package com.demo.service.impl;

import com.demo.dao.AdminDAO;
import com.demo.entity.Admin;
import com.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDAO adminDAO;

    @Override
    public void addAdmin(Admin admin){
        if(adminDAO.getById(admin.getAdId())!=null){
            throw new RuntimeException("用户名已存在");
        }
        else{
            adminDAO.addAdmin(admin);
        }
    }

    @Override
    public int login(String id, String pwd) {
        Admin adminDB = adminDAO.getById(id);
        if (adminDB != null) {
            if (adminDB.getAdPwd().equals(pwd)) {
                return 1;
            } else {
                throw new RuntimeException("密码错误");
            }
        } else {
            throw new RuntimeException("用户名不存在");
        }
    }

    @Override
    public int updatePwd(String id, String pwd){
        return adminDAO.updatePwd(id,pwd);
    }

    @Override
    public Admin getById(String id){
        Admin admin = adminDAO.getById(id);
        if(admin!=null){
            return admin;
        }
        else{
            throw new RuntimeException("管理员不存在");
        }
    }
}
