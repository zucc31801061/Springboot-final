package com.demo.controller;

import com.demo.entity.Admin;
import com.demo.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin //允许跨域
@RequestMapping("admin")
@Slf4j
public class AdminController {
    @Autowired
    private AdminService adminService;

    //管理员注册
    @PostMapping("register")
    public Map<String,Object> register(@RequestBody Admin admin){
        log.info("管理员信息:[{}]",admin.toString());
        Map<String,Object> map = new HashMap<>();
        try {
            adminService.addAdmin(admin);
            map.put("state",true);
            map.put("msg","提示:注册成功!");
        }catch (Exception e){
            e.printStackTrace();
            map.put("state",false);
            map.put("msg","提示:"+e.getMessage());
        }
        return map;
    }

    //管理员登录
    @PostMapping("login")
    public Map<String,Object> login(String id, String pwd){
        log.info(id+pwd);
        Map<String,Object> map = new HashMap<>();
        try {
            adminService.login(id,pwd);
            map.put("state",true);
            map.put("msg","提示:登录成功!");
        }catch (Exception e){
            e.printStackTrace();
            map.put("state",false);
            map.put("msg","提示:"+e.getMessage());
        }
        return map;
    }

    //修改密码
    @GetMapping("updatePwd")
    public Map<String,Object> updatePwd(String id, String pwd){
        Map<String,Object> map = new HashMap<>();
        try {
            adminService.updatePwd(id, pwd);
            map.put("state",true);
            map.put("msg","提示:修改成功!");
        }catch (Exception e){
            e.printStackTrace();
            map.put("state",false);
            map.put("msg","提示:"+e.getMessage());
        }
        return map;
    }

    //查看管理员
    @GetMapping("select")
    public Map<String,Object> select(String id){
        Map<String,Object> map = new HashMap<>();
        try {
            Admin admin = adminService.getById(id);
            map.put("state",true);
            map.put("ad",admin);
            log.info("管理员信息:[{}]",map.get("ad"));
        }catch (Exception e){
            e.printStackTrace();
            map.put("state",false);
            map.put("msg","提示:"+e.getMessage());
        }
        return map;
    }
}
