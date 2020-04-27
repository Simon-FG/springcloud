package com.dbs.service;

import com.dbs.mapper.TcrmProblemMapper;
import com.dbs.model.TcrmProblem;
import com.dbs.service.impl.ITcrmProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by lsl on 2018/1/2.
 */
@Service
@Transactional
public class TcrmProblemService implements ITcrmProblemService{

    @Autowired
    private TcrmProblemMapper tcrmProblemMapper;

    @Override
    public TcrmProblem getById(Integer id) {
        if(null != id){
            return tcrmProblemMapper.selectByPrimaryKey(id);
        }
        return null;
    }

    @Override
    public List<TcrmProblem> findListByVo(TcrmProblem tcrmProblem) {
        if(null != tcrmProblem){
            return tcrmProblemMapper.selectByVo(tcrmProblem);
        }
        return null;
    }

    @Override
    public Boolean addOne(TcrmProblem tcrmProblem) {
        if(null != tcrmProblem){
            tcrmProblem.setStatus("1");
            tcrmProblem.setSubTime(new Date());
            return tcrmProblemMapper.insertSelective(tcrmProblem) > 0;
        }
        return false;
    }

    @Override
    public Boolean editOne(TcrmProblem tcrmProblem) {
        return null != tcrmProblem && tcrmProblemMapper.updateByPrimaryKeySelective(tcrmProblem) > 0;
    }

    @Override
    public Boolean feedBack(TcrmProblem tcrmProblem) {
        if(null != tcrmProblem){
            tcrmProblem.setSolveTime(new Date());
            return tcrmProblemMapper.updateByPrimaryKeySelective(tcrmProblem) > 0;
        }
        return false;
    }

    @Override
    public Boolean delById(Integer... id) {
        if(null != id && id.length >0){
            if(id.length == 1){
                return tcrmProblemMapper.deleteByPrimaryKey(id[0]) > 0;
            }else {
                return tcrmProblemMapper.deleteBatch(id) == id.length;
            }
        }
        return false;
    }
}
