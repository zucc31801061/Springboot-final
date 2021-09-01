package com.demo.service;

import com.demo.entity.Employee;

import java.util.List;

public interface EmplService {
    //添加员工
    void add(Employee employee);
    //修改员工
    void update(Employee employee);
    //查看员工
    Employee select(int id);
    //查找本公司员工
    List<Employee> findByCompId(String id);
    //查找其他公司员工
    List<Employee> findOther(String id);
    //招聘员工
    void recruitEmployee(String compId, String emplId);
    //解雇员工
    void dismissEmployee(int id);
    //删除员工
    void delete(int id);
    //查找所有员工
    List<Employee> findAll();
}
