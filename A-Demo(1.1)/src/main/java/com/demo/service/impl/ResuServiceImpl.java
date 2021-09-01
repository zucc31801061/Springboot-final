package com.demo.service.impl;

import com.demo.dao.EmplDAO;
import com.demo.dao.ResuDAO;
import com.demo.entity.Resume;
import com.demo.service.ResuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ResuServiceImpl implements ResuService {
    @Autowired
    private ResuDAO resuDAO;
    @Autowired
    private EmplDAO emplDAO;

    @Override
    public void add(Resume resume){
        resuDAO.add(resume);
    }

    @Override
    public void update(Resume resume){
        resuDAO.update(resume);
    }

    @Override
    public void delete(int id){
        System.out.println(id);
        emplDAO.setNULL(id);
        resuDAO.delete(id);
    }
}
