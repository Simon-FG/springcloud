package com.dbs.mapper;

import com.dbs.model.TbMessage;

import java.util.List;

public interface TbMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteBatch(Integer ... id);

    int insert(TbMessage record);

    int insertSelective(TbMessage record);

    TbMessage selectByPrimaryKey(Integer id);

    List<TbMessage> selectByVo(TbMessage tbMessage);

    Integer selectCount(TbMessage tbMessage);

    int updateByPrimaryKeySelective(TbMessage record);

    int updateByPrimaryKey(TbMessage record);

    int readedBatch(Integer ... id);
}