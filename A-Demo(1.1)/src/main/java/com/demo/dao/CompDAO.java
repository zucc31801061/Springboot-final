package com.demo.dao;

import com.demo.entity.Company;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper //创建DAO对象
public interface CompDAO {
    void addUnemployed();
    void register(Company company);
    Company getById(@Param("id")String id);
    int updatePwd(@Param("id")String id, @Param("pwd")String pwd);
    void update(Company company);
    List<Company> findAll();
    void deleteCompany(@Param("id")String id);
}
