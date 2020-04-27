package com.dbs.mapper;

import com.dbs.model.TcrmDeliveraddress;

import java.util.List;

public interface TcrmDeliveraddressMapper {
    int deleteByPrimaryKey(Integer addressId);

    int deleteBatch(Integer ... id);

    int insert(TcrmDeliveraddress record);

    int insertSelective(TcrmDeliveraddress record);

    TcrmDeliveraddress selectByPrimaryKey(Integer addressId);

    List<TcrmDeliveraddress> selectByUser(Integer userId);

    int updateByPrimaryKeySelective(TcrmDeliveraddress record);

    int updateByPrimaryKey(TcrmDeliveraddress record);
}