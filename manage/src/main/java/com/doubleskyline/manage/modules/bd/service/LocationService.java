package com.doubleskyline.manage.modules.bd.service;

import com.doubleskyline.core.model.PageParam;
import com.doubleskyline.core.model.PageResult;
import com.doubleskyline.core.service.IService;
import com.doubleskyline.manage.modules.bd.entity.LocationEntity;
import com.doubleskyline.manage.modules.bd.entity.SmsEntity;
import com.doubleskyline.manage.modules.bd.vo.LocVO;
import com.doubleskyline.manage.modules.bd.vo.OnLineSumOut;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.Map;

/**
 * 北斗位置
 *
 * @author ZZY
 * @date 2020-02-21 11:38:26
 */
public interface LocationService extends IService<LocationEntity> {

    /**
     * 查找多个终端的最新位置记录
     * @param cardIds
     * @return
     */
    List<LocationEntity> queryRecentBdLocation(String[] cardIds);
    //位置统计数据
    List<LocVO> getLocData(SmsEntity smsEntity);
    //查询在线时长
    List<LocationEntity> getOnLineTime(LocationEntity locationEntity);
    //查询在线时长与位置上报率
    Map<String,Object> getTimeRate(LocationEntity locationEntity);
    //导出位置统计数据
    List<OnLineSumOut> getExcel(LocationEntity locationEntity);
    //位置累计条数
    List<LocVO> getLocCount();
    /**
     * 分页查询
     * @param entity
     * @return
     */
    PageResult<LocationEntity> pages(PageParam page, LocationEntity entity);

    //根据cards查询个数
    Integer getCount(List cards);
}

