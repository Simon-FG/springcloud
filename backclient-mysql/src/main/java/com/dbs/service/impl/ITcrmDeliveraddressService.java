package com.dbs.service.impl;

import com.dbs.model.TcrmDeliveraddress;

import java.util.List;

/**
 * Created by lsl on 2018/1/2.
 */
public interface ITcrmDeliveraddressService {

    TcrmDeliveraddress getById(Integer id);

    List<TcrmDeliveraddress> findListByUser(Integer userId);

    Boolean addOne(TcrmDeliveraddress tcrmDeliveraddress);

    Boolean editOne(TcrmDeliveraddress tcrmDeliveraddress);

    Boolean delById(Integer ... id);
}
