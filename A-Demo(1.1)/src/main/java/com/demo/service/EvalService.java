package com.demo.service;

import com.demo.entity.Evaluate;

import java.util.List;

public interface EvalService {
    //增加评论
    void addEval(Evaluate evaluate);
    //获取该员工简历评论列表
    List<Evaluate> findByEmplId(int id);
    //删除单条评论
    void deleteEvalById(int id);
    //删除该员工简历所有评论
    void deleteEvalByEmplId(int id);
    //删除该公司发表的所有评论
    void deleteEvalByCompId(String id);
}
