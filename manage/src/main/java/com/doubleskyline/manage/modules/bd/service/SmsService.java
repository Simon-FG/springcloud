package com.doubleskyline.manage.modules.bd.service;

import com.doubleskyline.core.model.PageParam;
import com.doubleskyline.core.model.PageResult;
import com.doubleskyline.core.service.IService;
import com.doubleskyline.manage.modules.bd.entity.SmsEntity;
import com.doubleskyline.manage.modules.bd.vo.SmsVO;

import java.util.List;

/**
 * 北斗报文
 *
 * @author SIMON
 * @date 2020-02-21 11:38:26
 */
public interface SmsService extends IService<SmsEntity> {

    //短信消息入库
    int insertSms(SmsEntity smsEntity);
    List<SmsEntity> getList(SmsEntity smsEntity);
    //更新状态消息
    int updateStatus(SmsEntity smsEntity);
    //报文统计数据
    List<SmsVO> getSmsData(SmsEntity smsEntity);
    //获取最新五条数据
    List<SmsEntity> getListNew5();
    //报文累计条数
    List<SmsVO> getSmsCount();
    //查询最新六个月的通信信息
    List<SmsVO> getSmsMonth(List liuyue);
    //根据cards查询个数
    Integer getCount(List cards);
    /**
     * 分页查询
     * @param entity
     * @return
     */
    PageResult<SmsEntity> pages(PageParam page, SmsEntity entity);

    /**
     * 分页查询
     * @param entity
     * @return
     */
    PageResult<SmsEntity> pageFind(PageParam page, SmsEntity entity);
}

