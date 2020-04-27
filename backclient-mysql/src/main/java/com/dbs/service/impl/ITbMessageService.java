package com.dbs.service.impl;

import com.dbs.model.TbMessage;

import java.util.List;

/**
 * Created by lsl on 2018/1/3.
 */
public interface ITbMessageService {

    public TbMessage getOne(Integer id);

    public List<TbMessage> findListByVo(TbMessage tbMessage);

    public Integer getCountNotRead(TbMessage tbMessage);

    public Boolean addOne(TbMessage tbMessage);

    public Boolean editOne(TbMessage tbMessage);

    public Boolean setReaded(Integer ... id);

    public Boolean delById(Integer ... id);
}
