package com.dbs.controller;

import com.dbs.model.TcrmProblem;
import com.dbs.service.impl.ITcrmProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lsl on 2018/1/2.
 */
@RestController
@RequestMapping("/problem")
public class TcrmProblemController {

    @Autowired
    private ITcrmProblemService tcrmProblemService;

    /**
     * 根据id查找TcrmProblem记录
     * @param id
     * @return
     */
    @RequestMapping("/getOneById")
    public TcrmProblem getOneById(Integer id){
        return tcrmProblemService.getById(id);
    }

    /**
     * 根据参数查找TcrmProblem记录列表
     * @param tcrmProblem （userId、type、status）..
     * @return
     */
    @RequestMapping("/findListByVo")
    public List<TcrmProblem> findListByVo(TcrmProblem tcrmProblem){
        return tcrmProblemService.findListByVo(tcrmProblem);
    }

    /**
     * 添加一条TcrmProblem记录
     * @param tcrmProblem
     * @return
     */
    @RequestMapping("/addOne")
    public Boolean addOne(TcrmProblem tcrmProblem){
        return tcrmProblemService.addOne(tcrmProblem);
    }

    /**
     * 跟新一条TcrmProblem记录
     * @param tcrmProblem
     * @return
     */
    @RequestMapping("/editOne")
    public Boolean editOne(TcrmProblem tcrmProblem){
        return tcrmProblemService.editOne(tcrmProblem);
    }

    /**
     * 管理员反馈用户问题
     * @param tcrmProblem
     * @return
     */
    @RequestMapping("/feedBack")
    public Boolean feedBack(TcrmProblem tcrmProblem){
        return tcrmProblemService.feedBack(tcrmProblem);
    }

    /**
     * 根据id删除TcrmProblem记录
     * @param id
     * @return
     */
    @RequestMapping("/delById")
    public Boolean delById(Integer ... id){
        return tcrmProblemService.delById(id);
    }
}
