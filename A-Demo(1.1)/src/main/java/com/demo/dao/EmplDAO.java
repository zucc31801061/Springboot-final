package com.demo.dao;

import com.demo.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper //创建DAO对象
public interface EmplDAO {
    void add(Employee employee);
    void update(Employee employee);
    List<Employee> findByCompId(@Param("id")String id);
    List<Employee> findOther(@Param("id")String id);
    List<Employee> findAll();
    void recruitEmployee(@Param("compId")String compId, @Param("emplId")String emplId);
    void dismissEmployee(@Param("id")int id);
    Employee getById(@Param("id")int id);
    Employee checkById(int id);
    void delete(@Param("id")int id);
    void setNULL(int id);
}
