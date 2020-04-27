package com.doubleskyline.manage.modules.bd.service.impl;

import com.doubleskyline.core.service.impl.ServiceImpl;
import com.doubleskyline.manage.modules.bd.entity.CardEntity;
import com.doubleskyline.manage.modules.bd.entity.LogEntity;
import com.doubleskyline.manage.modules.bd.mapper.CardMapper;
import com.doubleskyline.manage.modules.bd.mapper.LogMapper;
import com.doubleskyline.manage.modules.bd.service.CardService;
import com.doubleskyline.manage.modules.bd.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 日志
 *
 * @author Simon
 * @date 2020-03-21 11:38:26
 */
@Service("logService")
@Slf4j
public class LogServiceImpl extends ServiceImpl<LogMapper, LogEntity> implements LogService {

    @Autowired
    private HttpServletRequest request;

    @Override
    public int logSave() {

        String url = "";
        url = request.getScheme() +"://" + request.getServerName()
                + ":" +request.getServerPort()
                + request.getServletPath();
        if (request.getQueryString() != null){
            url += "?" + request.getQueryString();
        }

        LogEntity logEntity = new LogEntity();
        logEntity.setRequestUrl(url);
        logEntity.setCreateTime(new Date());
        return baseMapper.insert(logEntity);

    }
}
