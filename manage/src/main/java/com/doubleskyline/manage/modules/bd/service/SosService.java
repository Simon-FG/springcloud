package com.doubleskyline.manage.modules.bd.service;

import com.doubleskyline.core.service.IService;
import com.doubleskyline.core.model.PageResult;
import com.doubleskyline.manage.modules.bd.entity.SosEntity;
import com.doubleskyline.manage.modules.bd.vo.SmsVO;

import java.util.List;

/**
 * 北斗应急求救
 *
 * @author ZZY
 * @date 2020-02-21 11:38:26
 */
public interface SosService extends IService<SosEntity> {
    //应急消息入库
    int insertSos(SosEntity sosEntity);
    //更新状态消息
    int updateStatus(SosEntity sosEntity);
    //获取最新五条数据
    List<SosEntity> getListNew5();
    //报警累计条数
    List<SmsVO> getSosCount();
}

