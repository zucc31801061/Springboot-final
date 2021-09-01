package com.demo.controller;

import com.demo.entity.Employee;
import com.demo.entity.Resume;
import com.demo.service.EmplService;
import com.demo.service.EvalService;
import com.demo.service.ResuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin //允许跨域
@RequestMapping("employee")
@Slf4j
public class EmplController {
    @Autowired
    private EmplService emplService;

    @Autowired
    private ResuService resuService;

    @Autowired
    private EvalService evalService;

    //添加员工
    @PostMapping("add")
    public Map<String,Object> add(@RequestBody Employee employee){
        log.info("员工信息:[{}]",employee.toString());
        Map<String,Object> map = new HashMap<>();
        employee.setAge();
        emplService.add(employee);

        Resume resume = new Resume();
        resume.setEmplId(employee.getEmplId());
        resume.setContent(employee.getContent());
        resuService.add(resume);

        employee.setResuId(resume.getResuId());
        emplService.update(employee);

        map.put("msg","添加成功");
        return map;
    }

    //查看员工
    @GetMapping("select")
    public Map<String,Object> select(int id){
        Map<String,Object> map = new HashMap<>();
        try {
            Employee employee = emplService.select(id);
            employee.setNowAge();
            map.put("state",true);
            map.put("empl",employee);
            log.info("员工信息:[{}]",map.get("empl"));
        }catch (Exception e){
            e.printStackTrace();
            map.put("state",false);
            map.put("msg","提示:"+e.getMessage());
        }
        return map;
    }

    //修改员工
    @PostMapping("update")
    public Map<String,Object> update(@RequestBody Employee employee){
        log.info("员工信息:[{}]",employee.toString());
        Map<String,Object> map = new HashMap<>();
        employee.setAge();
        emplService.update(employee);

        Resume resume = new Resume();
        resume.setEmplId(employee.getEmplId());
        resume.setContent(employee.getContent());
        log.info("员工信息:[{}]",resume.toString());
        resuService.update(resume);

        map.put("msg","修改成功");
        return map;
    }

    //获取本公司员工列表
    @GetMapping("findByCompId")
    public List<Employee> findByCompId(String id){
        List<Employee> list = emplService.findByCompId(id);
        for(int i=0;i<list.size();i++){
            list.get(i).setNowAge();
        }
        return list;
    }

    //获取非本公司员工列表
    @GetMapping("findOther")
    public List<Employee> findOther(String id){
        List<Employee> list = emplService.findOther(id);
        for(int i=0;i<list.size();i++){
            list.get(i).setNowAge();
        }
        return list;
    }

    //招聘员工
    @GetMapping("recruitEmployee")
    public Map<String,Object> recruitEmployee(String compId, String emplId){
        log.info(compId+emplId);
        Map<String,Object> map = new HashMap<>();
        try {
            emplService.recruitEmployee(compId, emplId);
            map.put("state",true);
            map.put("msg","提示:已成功招聘该员工!");
        }catch (Exception e){
            e.printStackTrace();
            map.put("state",false);
            map.put("msg","提示:"+e.getMessage());
        }
        return map;
    }

    //辞退员工
    @GetMapping("dismissEmployee")
    public Map<String,Object> dismissEmployee(int id){
        Map<String,Object> map = new HashMap<>();
        try {
            emplService.dismissEmployee(id);
            map.put("state",true);
            map.put("msg","提示:已成功辞退该员工!");
        }catch (Exception e){
            e.printStackTrace();
            map.put("state",false);
            map.put("msg","提示:"+e.getMessage());
        }
        return map;
    }

    //删除员工
    @GetMapping("delete")
    public Map<String,Object> delete(int id){
        Map<String,Object> map = new HashMap<>();
        try {
            evalService.deleteEvalByEmplId(id);
            resuService.delete(id);
            emplService.delete(id);
            map.put("state",true);
            map.put("msg","提示:已成功删除该员工!");
        }catch (Exception e){
            e.printStackTrace();
            map.put("state",false);
            map.put("msg","提示:"+e.getMessage());
        }
        return map;
    }

    //获取所有员工列表
    @GetMapping("findAll")
    public List<Employee> findAll(){
        List<Employee> list = emplService.findAll();
        for(int i=0;i<list.size();i++){
            list.get(i).setNowAge();
        }
        return list;
    }
}
