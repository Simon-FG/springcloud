package com.doubleskyline.manage.modules.bd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.doubleskyline.core.model.PageParam;
import com.doubleskyline.core.model.PageResult;
import com.doubleskyline.core.service.impl.ServiceImpl;
import com.doubleskyline.manage.modules.bd.entity.LocationEntity;
import com.doubleskyline.manage.modules.bd.entity.SmsEntity;
import com.doubleskyline.manage.modules.bd.mapper.SmsMapper;
import com.doubleskyline.manage.modules.bd.service.SmsService;
import com.doubleskyline.manage.modules.bd.vo.SmsVO;
import com.doubleskyline.manage.modules.mq.service.MqService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 北斗报文
 *
 * @author ZZY
 * @date 2020-02-21 11:38:26
 */
@Service("smsService")
@Slf4j
@Transactional
public class SmsServiceImpl extends ServiceImpl<SmsMapper, SmsEntity> implements SmsService {

    @Autowired
    private MqService mqService;


    @Override
    public boolean save(SmsEntity entity) {
        boolean result = super.save(entity);

        //不在这儿做实现
//        if(entity.getMt() == 0) {
//            mqService.send(entity);
//        }
        return result;
    }

    @Override
    public QueryWrapper<SmsEntity> buildWrapper(SmsEntity entity) {
        return super.buildWrapper(entity)
                .ge(null != entity.getStartTime(), "create_time", entity.getStartTime())
                .le(null != entity.getEndTime(), "create_time", entity.getEndTime());
    }

    @Override
    public int insertSms(SmsEntity smsEntity) {
        return baseMapper.insertSms(smsEntity);
    }

    @Override
    public List<SmsEntity> getList(SmsEntity smsEntity) {
        return baseMapper.getList(smsEntity);
    }

    @Override
    public int updateStatus(SmsEntity smsEntity) {
        return baseMapper.updateStatus(smsEntity);
    }

    @Override
    public List<SmsVO> getSmsData(SmsEntity smsEntity) {
        return baseMapper.getSmsData(smsEntity);
    }

    @Override
    public List<SmsEntity> getListNew5() {
        return baseMapper.getListNew5();
    }

    @Override
    public List<SmsVO> getSmsCount() {
        return baseMapper.getSmsCount();
    }

    @Override
    public List<SmsVO> getSmsMonth(List liuyue) {
        return baseMapper.getSmsMonth(liuyue);
    }

    @Override
    public Integer getCount(List cards) {
        return baseMapper.getCount(cards);
    }

    @Override
    public PageResult<SmsEntity> pages(PageParam pageParam, SmsEntity entity) {
        QueryWrapper<SmsEntity> qw = this.buildWrapper(entity).orderByDesc("create_time");
        if(null != pageParam){
            if(ArrayUtils.isNotEmpty(pageParam.getSelects())){
                qw.select(pageParam.getSelects());
            }
            qw.groupBy(ArrayUtils.isNotEmpty(pageParam.getGroupBy()), pageParam.getGroupBy());
        }
        IPage<SmsEntity> ipage = this.page(this.buildPage(pageParam), qw);
        return new PageResult(ipage);
    }

    @Override
    public PageResult<SmsEntity> pageFind(PageParam pageParam, SmsEntity entity) {
        QueryWrapper<SmsEntity> qw = super.buildWrapper(entity)
                .ge(null != entity.getStartTime(), "create_time", entity.getStartTime())
                .le(null != entity.getEndTime(), "create_time", entity.getEndTime())
                .eq(null != entity.getSendCardId(),"send_card_id",entity.getSendCardId())
                .or()
                .eq(null != entity.getSendCardId(),"to_card_id",entity.getSendCardId())
                .orderByDesc("create_time");

        if(null != pageParam){
            if(ArrayUtils.isNotEmpty(pageParam.getSelects())){
                qw.select(pageParam.getSelects());
            }
            qw.groupBy(ArrayUtils.isNotEmpty(pageParam.getGroupBy()), pageParam.getGroupBy());
        }
        IPage<SmsEntity> ipage = this.page(this.buildPage(pageParam), qw);

        return new PageResult(ipage);

    }
}
