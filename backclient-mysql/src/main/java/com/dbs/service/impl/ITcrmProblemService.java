package com.dbs.service.impl;

import com.dbs.model.TcrmProblem;

import java.util.List;

/**
 * Created by lsl on 2018/1/2.
 */
public interface ITcrmProblemService {

    public TcrmProblem getById(Integer id);

    public List<TcrmProblem> findListByVo(TcrmProblem tcrmProblem);

    public Boolean addOne(TcrmProblem tcrmProblem);

    public Boolean editOne(TcrmProblem tcrmProblem);

    public Boolean feedBack(TcrmProblem tcrmProblem);

    public Boolean delById(Integer ... id);
}
