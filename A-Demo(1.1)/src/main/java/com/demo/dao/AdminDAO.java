package com.demo.dao;

import com.demo.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper //创建DAO对象
public interface AdminDAO {
    void addAdmin(Admin admin);
    Admin getById(@Param("id")String id);
    int updatePwd(@Param("id")String id, @Param("pwd")String pwd);
}
