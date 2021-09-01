package com.demo.service;

import com.demo.entity.Resume;

public interface ResuService {
    //添加简历
    void add(Resume resume);
    //修改简历
    void update(Resume resume);
    //删除简历
    void delete(int id);
}
