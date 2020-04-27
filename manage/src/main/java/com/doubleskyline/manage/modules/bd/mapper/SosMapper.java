package com.doubleskyline.manage.modules.bd.mapper;

import com.doubleskyline.core.mapper.BaseMapper;
import com.doubleskyline.manage.modules.bd.entity.SosEntity;
import com.doubleskyline.manage.modules.bd.vo.SmsVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 北斗应急求救
 *
 * @author ZZY
 * @date 2020-02-21 11:38:26
 */
@Mapper
public interface SosMapper extends BaseMapper<SosEntity> {

    //应急消息入库
    int insertSos(SosEntity sosEntity);

    //更新状态消息
    int updateStatus(SosEntity sosEntity);

    //获取最新五条数据
    List<SosEntity> getListNew5();

    //消息累计条数
    List<SmsVO> getSosCount();
}
