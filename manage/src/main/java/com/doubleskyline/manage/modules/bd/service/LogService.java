package com.doubleskyline.manage.modules.bd.service;

import com.doubleskyline.core.service.IService;
import com.doubleskyline.manage.modules.bd.entity.LogEntity;

/**
 * 日志
 *
 * @author SIMON
 * @date 2020-02-21 11:38:26
 */
public interface LogService extends IService<LogEntity> {


    //封装保存日志
    int logSave();

}

