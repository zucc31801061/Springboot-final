package com.demo.controller;

import com.demo.entity.Evaluate;
import com.demo.service.EvalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin //允许跨域
@RequestMapping("evaluate")
@Slf4j
public class EvalController {
    @Autowired
    private EvalService evalService;

    //添加评论
    @PostMapping("add")
    public Map<String,Object> add(@RequestBody Evaluate evaluate){
        log.info("评论信息:[{}]",evaluate.toString());
        Map<String,Object> map = new HashMap<>();
        evalService.addEval(evaluate);

        map.put("msg","添加成功");
        return map;
    }

    //获取该简历评论列表
    @GetMapping("findByEmplId")
    public List<Evaluate> findByEmplId(int id){
        List<Evaluate> list = evalService.findByEmplId(id);
        return list;
    }

    //删除单条评论
    @GetMapping("deleteEvalById")
    public Map<String,Object> deleteEvalById(int id){
        Map<String,Object> map = new HashMap<>();
        try {
            evalService.deleteEvalById(id);
            map.put("state",true);
            map.put("msg","提示:已成功删除该评论!");
        }catch (Exception e){
            e.printStackTrace();
            map.put("state",false);
            map.put("msg","提示:"+e.getMessage());
        }
        return map;
    }
}
