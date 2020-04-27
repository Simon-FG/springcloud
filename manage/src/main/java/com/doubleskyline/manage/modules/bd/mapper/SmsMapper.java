package com.doubleskyline.manage.modules.bd.mapper;

import com.doubleskyline.core.mapper.BaseMapper;
import com.doubleskyline.manage.modules.bd.entity.SmsEntity;
import com.doubleskyline.manage.modules.bd.entity.SosEntity;
import com.doubleskyline.manage.modules.bd.vo.SmsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 北斗报文
 *
 * @author ZZY
 * @date 2020-02-21 11:38:26
 */
@Mapper
public interface SmsMapper extends BaseMapper<SmsEntity> {

    //报文消息入库
    int insertSms(SmsEntity smsEntity);

    List<SmsEntity> getList(SmsEntity smsEntity);

    //更新状态消息
    int updateStatus(SmsEntity smsEntity);

    //报文统计数据
    List<SmsVO> getSmsData(SmsEntity smsEntity);

    //获取最新五条数据
    List<SmsEntity> getListNew5();

    //消息累计条数
    List<SmsVO> getSmsCount();

    //查询最新六个月的通信信息
    List<SmsVO> getSmsMonth(List liuyue);

    //根据cards查询个数
    Integer getCount(List cards);
}
