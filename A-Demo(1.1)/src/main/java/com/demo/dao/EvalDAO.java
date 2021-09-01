package com.demo.dao;

import com.demo.entity.Evaluate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper //创建DAO对象
public interface EvalDAO {
    void addEval(Evaluate evaluate);
    List<Evaluate> findByEmplId(@Param("id")int id);
    void deleteEvalById(@Param("id")int id);
    Evaluate selectEvalById(int id);
    void deleteEvalByCompId(@Param("id")String id);
    void deleteEvalByEmplId(int id);
}
