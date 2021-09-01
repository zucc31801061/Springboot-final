package com.demo.dao;

import com.demo.entity.Resume;
import org.apache.ibatis.annotations.Mapper;

@Mapper //创建DAO对象
public interface ResuDAO {
    void add(Resume resume);
    void update(Resume resume);
    void delete(int id);
}
