package com.demo.controller;

import com.demo.entity.Company;
import com.demo.entity.Employee;
import com.demo.service.CompService;
import com.demo.service.EmplService;
import com.demo.service.EvalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin //允许跨域
@RequestMapping("company")
@Slf4j
public class CompController {
    @Autowired
    private CompService compService;

    @Autowired
    private EvalService evalService;

    @Autowired
    private EmplService emplService;

    //添加无公司
    @GetMapping("addUnemployed")
    public void addUnemployed(){
        compService.addUnemployed();
    }

    //注册公司
    @PostMapping("register")
    public Map<String,Object> register(@RequestBody Company company){
        log.info("公司信息:[{}]",company.toString());
        Map<String,Object> map = new HashMap<>();
        try {
            compService.register(company);
            map.put("state",true);
            map.put("msg","提示:注册成功!");
        }catch (Exception e){
            e.printStackTrace();
            map.put("state",false);
            map.put("msg","提示:"+e.getMessage());
        }
        return map;
    }

    //公司登录
    @PostMapping("login")
    public Map<String,Object> login(String id, String pwd){
        log.info(id+pwd);
        Map<String,Object> map = new HashMap<>();
        try {
            compService.login(id,pwd);
            map.put("state",true);
            map.put("msg","提示:登录成功!");
        }catch (Exception e){
            e.printStackTrace();
            map.put("state",false);
            map.put("msg","提示:"+e.getMessage());
        }
        return map;
    }

    //查看公司
    @GetMapping("select")
    public Map<String,Object> select(String id){
        Map<String,Object> map = new HashMap<>();
        try {
            Company company = compService.getById(id);
            map.put("state",true);
            map.put("comp",company);
            log.info("公司信息:[{}]",map.get("comp"));
        }catch (Exception e){
            e.printStackTrace();
            map.put("state",false);
            map.put("msg","提示:"+e.getMessage());
        }
        return map;
    }

    //修改公司
    @PostMapping("update")
    public Map<String,Object> update(@RequestBody Company company){
        log.info("公司信息:[{}]",company.toString());
        Map<String,Object> map = new HashMap<>();
        try {
            compService.update(company);
            map.put("state",true);
            map.put("msg","提示:修改成功!");
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
            compService.updatePwd(id, pwd);
            map.put("state",true);
            map.put("msg","提示:修改成功!");
        }catch (Exception e){
            e.printStackTrace();
            map.put("state",false);
            map.put("msg","提示:"+e.getMessage());
        }
        return map;
    }

    //获取所有公司列表
    @GetMapping("findAll")
    public List<Company> findAll(){
        return compService.findAll();
    }

    //删除公司
    @GetMapping("delete")
    public Map<String,Object> delete(String id){
        log.info(id);
        Map<String,Object> map = new HashMap<>();
        List<Employee> list = emplService.findByCompId(id);
        if(id.equals("0")){
            map.put("state",false);
            map.put("msg","提示:该公司为默认公司，无法删除");
        }
        else {
            if(list.size()==0){
                try {
                    evalService.deleteEvalByCompId(id);
                    compService.deleteCompany(id);
                    map.put("state",true);
                    map.put("msg","提示:已成功删除该公司!");
                }catch (Exception e){
                    e.printStackTrace();
                    map.put("state",false);
                    map.put("msg","提示:"+e.getMessage());
                }
            }
            else {
                map.put("state",false);
                map.put("msg","提示:该公司仍有员工,无法删除");
            }
        }
        return map;
    }
}
