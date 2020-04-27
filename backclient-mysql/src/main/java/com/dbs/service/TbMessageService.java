package com.dbs.service;

import com.dbs.mapper.TbMessageMapper;
import com.dbs.model.SysHzhb;
import com.dbs.model.TbMessage;
import com.dbs.service.impl.ITbMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lsl on 2018/1/3.
 */
@Service
@Transactional
public class TbMessageService implements ITbMessageService {

    @Autowired
    private TbMessageMapper tbMessageMapper;

    @Override
    public TbMessage getOne(Integer id) {
        if(null != id){
            return tbMessageMapper.selectByPrimaryKey(id);
        }
        return null;
    }

    @Override
    public List<TbMessage> findListByVo(TbMessage tbMessage) {
        if(null != tbMessage){
            return tbMessageMapper.selectByVo(tbMessage);
        }
        return null;
    }

    @Override
    public Integer getCountNotRead(TbMessage tbMessage) {
        if(null != tbMessage && null != tbMessage.getUserId()){
            tbMessage.setStatus("0");
            return tbMessageMapper.selectCount(tbMessage);
        }
        return null;
    }

    @Override
    public Boolean addOne(TbMessage tbMessage) {
        if(null != tbMessage){
            tbMessage.setStatus("0");
            return tbMessageMapper.insertSelective(tbMessage) > 0;
        }
        return false;
    }

    @Override
    public Boolean editOne(TbMessage tbMessage) {
        return null != tbMessage && tbMessageMapper.updateByPrimaryKeySelective(tbMessage) > 0;
    }

    @Override
    public Boolean setReaded(Integer ... id) {
        return null != id && id.length > 0 && tbMessageMapper.readedBatch(id) == id.length;
    }

    @Override
    public Boolean delById(Integer... id) {
        if(null != id && id.length > 0){
            if(id.length == 1){
                return tbMessageMapper.deleteByPrimaryKey(id[0]) > 0;
            }else {
                return tbMessageMapper.deleteBatch(id) == id.length;
            }
        }
        return false;
    }
}
