package com.doubleskyline.manage.modules.bd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.doubleskyline.core.model.PageParam;
import com.doubleskyline.core.model.PageResult;
import com.doubleskyline.core.service.impl.ServiceImpl;
import com.doubleskyline.manage.modules.bd.entity.LocationEntity;
import com.doubleskyline.manage.modules.bd.entity.SmsEntity;
import com.doubleskyline.manage.modules.bd.mapper.LocationMapper;
import com.doubleskyline.manage.modules.bd.service.LocationService;
import com.doubleskyline.manage.modules.bd.vo.LocVO;
import com.doubleskyline.manage.modules.bd.vo.OnLineSumOut;
import com.doubleskyline.manage.modules.mq.service.MqService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 北斗位置
 *
 * @author ZZY
 * @date 2020-02-21 11:38:26
 */
@Service("locationService")
@Slf4j
public class LocationServiceImpl extends ServiceImpl<LocationMapper, LocationEntity> implements LocationService {

    @Autowired
    private MqService mqService;

    @Override
    public boolean save(LocationEntity entity) {
        boolean result = super.save(entity);
        mqService.send(entity);
        return result;
    }

    /**
     * 查找多个终端的最新位置记录
     * @param cardIds
     * @return
     */
    @Override
    public List<LocationEntity> queryRecentBdLocation(String[] cardIds){
        return baseMapper.queryRecentBdLocation(cardIds);
    }

    @Override
    public List<LocVO> getLocData(SmsEntity smsEntity) {
        return baseMapper.getLocData(smsEntity);
    }

    @Override
    public List<LocationEntity> getOnLineTime(LocationEntity locationEntity) {
        return baseMapper.getOnLineTime(locationEntity);
    }

    @Override
    public Map<String, Object> getTimeRate(LocationEntity locationEntity) {

        List<LocationEntity> list = getOnLineTime(locationEntity);

        //离线间隔时间
        int offLineTime = locationEntity.getOffLineTime() * 60 * 1000;

        //在线时长
        long onLineTime = 0;
        for (int i = 0; i < list.size(); i++) {
            if(i+1 < list.size()){
                //如果下一条数据的定位时间比前一条数据小于三分钟，认为在线, 在线时间 = 下一条的时间 - 本条时间
                if(list.get(i+1).getLocationTime().getTime() - list.get(i).getLocationTime().getTime() < offLineTime){
                    onLineTime += (list.get(i+1).getLocationTime().getTime() - list.get(i).getLocationTime().getTime());
                }
            }else {
                break;
            }
        }

        //北斗上报个数 * 上报频率 计算成功上报率
        int i = list.size() * 60 * 1000;
        float successRate = 0.00f;
        if(onLineTime != 0){
            successRate = (float)i / onLineTime;
        }

        Map<String, Object> map = new HashMap<>();
        map.put("onLineTime",onLineTime);
        map.put("successRate",successRate);
        map.put("bdTime","60s");
        map.put("bdNum",list.size());

        return map;
    }

    @Override
    public List<OnLineSumOut> getExcel(LocationEntity locationEntity) {

        ArrayList<OnLineSumOut> list = new ArrayList<>();
        LocationEntity loc = new LocationEntity();
        loc.setCardId(locationEntity.getCardId());
        loc.setStartTime(locationEntity.getStartTime());
        loc.setEndTime(locationEntity.getEndTime());
        loc.setOffLineTime(locationEntity.getOffLineTime());
        Map map = getTimeRate(loc);
        OnLineSumOut out = new OnLineSumOut();
        out.setCardId(locationEntity.getCardId());

        //在线时长
        long onLineTime = Long.valueOf(map.get("onLineTime").toString()) / 60000;
        out.setOnLineTime(String.valueOf(onLineTime));
        //上报成功率
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(2);
        String successRate = nf.format(Float.valueOf(map.get("successRate").toString()));
        out.setSuccessRate(successRate);
        //北斗频率
        out.setBdTime(map.get("bdTime").toString());
//        out.setG4Time(map.get("g4Time").toString());
        //北斗数量
        out.setBdNum(map.get("bdNum").toString());
//        out.setG4Num(map.get("g4Num").toString());
        list.add(out);
        return list;
    }

    @Override
    public List<LocVO> getLocCount() {
        return baseMapper.getLocCount();
    }

    @Override
    public PageResult<LocationEntity> pages(PageParam pageParam, LocationEntity entity) {
        QueryWrapper<LocationEntity> qw = this.buildWrapper(entity).orderByDesc("location_time");
        if(null != pageParam){
            if(ArrayUtils.isNotEmpty(pageParam.getSelects())){
                qw.select(pageParam.getSelects());
            }
            qw.groupBy(ArrayUtils.isNotEmpty(pageParam.getGroupBy()), pageParam.getGroupBy());
        }
        IPage<LocationEntity> ipage = this.page(this.buildPage(pageParam), qw);
        return new PageResult(ipage);
    }

    @Override
    public Integer getCount(List cards) {
        return baseMapper.getCount(cards);
    }


    @Override
    public QueryWrapper<LocationEntity> buildWrapper(LocationEntity entity) {
        return super.buildWrapper(entity)
                .ge(null != entity.getStartTime(), "location_time", entity.getStartTime())
                .le(null != entity.getEndTime(), "location_time", entity.getEndTime());
    }
}
