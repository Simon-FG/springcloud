package com.doubleskyline.manage.modules.bd.mapper;

import com.doubleskyline.core.mapper.BaseMapper;
import com.doubleskyline.manage.modules.bd.entity.SmsRtEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 北斗报文实时表
 *
 * @author SIMON
 * @date 2020-04-27 11:38:26
 */
@Mapper
public interface SmsRtMapper extends BaseMapper<SmsRtEntity> {

    //将历史表中最近六个月的报文数据插入到实时表中
    void insertSixDate(@Param("startTime") String startTime, @Param("endTime") String endTime);
}
