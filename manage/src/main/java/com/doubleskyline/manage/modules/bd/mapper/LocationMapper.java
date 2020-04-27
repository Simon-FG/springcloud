package com.doubleskyline.manage.modules.bd.mapper;

import com.doubleskyline.core.mapper.BaseMapper;
import com.doubleskyline.manage.modules.bd.entity.LocationEntity;
import com.doubleskyline.manage.modules.bd.entity.SmsEntity;
import com.doubleskyline.manage.modules.bd.vo.LocVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 北斗位置
 *
 * @author ZZY
 * @date 2020-02-21 11:38:26
 */
@Mapper
public interface LocationMapper extends BaseMapper<LocationEntity> {

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

    //位置累计条数
    List<LocVO> getLocCount();

    //根据cards查询个数
    Integer getCount(List cards);
}
