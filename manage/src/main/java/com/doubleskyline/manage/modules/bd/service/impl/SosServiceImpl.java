package com.doubleskyline.manage.modules.bd.service.impl;

import com.doubleskyline.core.service.impl.ServiceImpl;
import com.doubleskyline.manage.modules.bd.entity.SosEntity;
import com.doubleskyline.manage.modules.bd.mapper.SosMapper;
import com.doubleskyline.manage.modules.bd.service.SosService;
import com.doubleskyline.manage.modules.bd.vo.SmsVO;
import com.doubleskyline.manage.modules.mq.service.MqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 北斗应急求救
 *
 * @author ZZY
 * @date 2020-02-21 11:38:26
 */
@Service("sosService")
@Slf4j
public class SosServiceImpl extends ServiceImpl<SosMapper, SosEntity> implements SosService {

    @Autowired
    private MqService mqService;


    @Override
    public boolean save(SosEntity entity) {
        boolean result = super.save(entity);
        mqService.send(entity);
        return result;
    }

    @Override
    public int insertSos(SosEntity sosEntity) {
        return baseMapper.insertSos(sosEntity);
    }

    @Override
    public int updateStatus(SosEntity sosEntity) {
        return baseMapper.updateStatus(sosEntity);
    }

    @Override
    public List<SosEntity> getListNew5() {
        return baseMapper.getListNew5();
    }

    @Override
    public List<SmsVO> getSosCount() {
        return baseMapper.getSosCount();
    }
}
