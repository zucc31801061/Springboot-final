package com.demo.service.impl;

import com.demo.dao.EvalDAO;
import com.demo.entity.Evaluate;
import com.demo.service.EvalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EvalServiceImpl implements EvalService {
    @Autowired
    public EvalDAO evalDAO;

    @Override
    public void addEval(Evaluate evaluate){
        evalDAO.addEval(evaluate);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Evaluate> findByEmplId(int id){
        return evalDAO.findByEmplId(id);
    }

    @Override
    public void deleteEvalById(int id){
        Evaluate evaluate = evalDAO.selectEvalById(id);
        if(evaluate!=null){
            evalDAO.deleteEvalById(id);
        }
        else{
            throw new RuntimeException("该评论已被删除");
        }
    }

    @Override
    public void deleteEvalByEmplId(int id){
        evalDAO.deleteEvalByEmplId(id);
    }

    @Override
    public void deleteEvalByCompId(String id){
        evalDAO.deleteEvalByCompId(id);
    }
}
