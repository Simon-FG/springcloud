package com.dbs.service;

import com.dbs.mapper.TcrmDeliveraddressMapper;
import com.dbs.model.TcrmDeliveraddress;
import com.dbs.service.impl.ITcrmDeliveraddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lsl on 2018/1/2.
 */
@Service
@Transactional
public class TcrmDeliveraddressService implements ITcrmDeliveraddressService {

    @Autowired
    TcrmDeliveraddressMapper tcrmDeliveraddressMapper;

    @Override
    public TcrmDeliveraddress getById(Integer id) {
        if(null != id){
            return tcrmDeliveraddressMapper.selectByPrimaryKey(id);
        }
        return null;
    }

    @Override
    public List<TcrmDeliveraddress> findListByUser(Integer userId) {
        if(null != userId){
            return tcrmDeliveraddressMapper.selectByUser(userId);
        }
        return null;
    }

    @Override
    public Boolean addOne(TcrmDeliveraddress tcrmDeliveraddress) {
        return null != tcrmDeliveraddress && tcrmDeliveraddressMapper.insertSelective(tcrmDeliveraddress) > 0;
    }

    @Override
    public Boolean editOne(TcrmDeliveraddress tcrmDeliveraddress) {
        if(null != tcrmDeliveraddress){
            tcrmDeliveraddressMapper.updateByPrimaryKeySelective(tcrmDeliveraddress);
            return true;
        }
        return false;
    }

    @Override
    public Boolean delById(Integer... id) {
        if(null != id && id.length > 0){
            if(id.length == 1){
                return tcrmDeliveraddressMapper.deleteByPrimaryKey(id[0]) > 0;
            }else{
                return tcrmDeliveraddressMapper.deleteBatch(id) == id.length;
            }
        }
        return false;
    }
}
