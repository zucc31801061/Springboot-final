package com.demo.service.impl;

import com.demo.dao.CompDAO;
import com.demo.entity.Company;
import com.demo.service.CompService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CompServiceImpl implements CompService {
    @Autowired
    private CompDAO compDAO;

    @Override
    public void addUnemployed(){
        if(compDAO.getById("0")==null){
            compDAO.addUnemployed();
        }
    }

    @Override
    public void register(Company company){
        if(compDAO.getById(company.getCompanyId())!=null){
            throw new RuntimeException("用户名已存在");
        }
        else{
            compDAO.register(company);
        }
    }

    @Override
    public int login(String id, String pwd){
        Company companyDB=compDAO.getById(id);
        if(companyDB!=null){
            if(companyDB.getCompanyPwd().equals(pwd)){
                return 1;
            }
            else {
                throw new RuntimeException("密码错误");
            }
        }
        else{
            throw new RuntimeException("用户名不存在");
        }
    }

    @Override
    public Company getById(String id){
        Company company = compDAO.getById(id);
        if(company!=null){
            return company;
        }
        else{
            throw new RuntimeException("公司不存在");
        }
    }

    @Override
    public void update(Company company){
        compDAO.update(company);
    }

    @Override
    public void updatePwd(String id, String pwd){
        compDAO.updatePwd(id, pwd);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Company> findAll(){
        return compDAO.findAll();
    }

    @Override
    public void deleteCompany(String id){
        Company company = compDAO.getById(id);
        if(company!=null){
            compDAO.deleteCompany(id);
        }
        else{
            throw new RuntimeException("该公司已被删除");
        }
    }
}
