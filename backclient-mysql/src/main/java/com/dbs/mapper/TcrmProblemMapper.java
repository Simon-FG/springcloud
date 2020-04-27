package com.dbs.mapper;

import com.dbs.model.TcrmProblem;

import java.util.List;

public interface TcrmProblemMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteBatch(Integer ... id);

    int insert(TcrmProblem record);

    int insertSelective(TcrmProblem record);

    TcrmProblem selectByPrimaryKey(Integer id);

    List<TcrmProblem> selectByVo(TcrmProblem tcrmProblem);

    int updateByPrimaryKeySelective(TcrmProblem record);

    int updateByPrimaryKey(TcrmProblem record);
}