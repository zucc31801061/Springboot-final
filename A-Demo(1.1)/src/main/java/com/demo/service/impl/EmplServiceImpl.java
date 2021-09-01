package com.demo.service.impl;

import com.demo.dao.EmplDAO;
import com.demo.entity.Employee;
import com.demo.service.EmplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmplServiceImpl implements EmplService {
    @Autowired
    private EmplDAO emplDAO;

    @Override
    public void add(Employee employee){
        emplDAO.add(employee);
    }

    @Override
    public void update(Employee employee){
        Employee employeeDB = emplDAO.getById(employee.getEmplId());
        if(employeeDB!=null){
            emplDAO.update(employee);
        }
        else{
            throw new RuntimeException("该员工不存在");
        }
    }

    @Override
    public Employee select(int id){
        return emplDAO.getById(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Employee> findByCompId(String id){
        return emplDAO.findByCompId(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Employee> findOther(String id){
        return emplDAO.findOther(id);
    }

    @Override
    public void recruitEmployee(String compId, String emplId){
        Employee employee = emplDAO.getById(Integer.parseInt(emplId));
        if(employee!=null){
            if(employee.getCompanyId().equals("0")){
                emplDAO.recruitEmployee(compId, emplId);
            }
            else if(employee.getCompanyId().equals(compId)){
                throw new RuntimeException("该员工已经是您公司的员工了");
            }
            else{
                throw new RuntimeException("该员工正在别的公司工作，您无招聘的权力");
            }
        }
        else{
            throw new RuntimeException("该员工不存在");
        }
    }

    @Override
    public void dismissEmployee(int id){
        Employee employee = emplDAO.getById(id);
        if(employee!=null){
            System.out.println(employee.getCompanyId());
            if(!employee.getCompanyId().equals("0")){
                emplDAO.dismissEmployee(id);
            }
            else{
                throw new RuntimeException("该员工已不在您的公司");
            }
        }
        else{
            throw new RuntimeException("该员工不存在");
        }
    }

    @Override
    public void delete(int id){
        Employee employee = emplDAO.checkById(id);
        if(employee!=null){
            emplDAO.delete(id);
        }
        else{
            throw new RuntimeException("该员工已被删除");
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Employee> findAll(){
        return emplDAO.findAll();
    }
}
