/*
 * Copyright (c) 2017. <a href="cf">cf</a> All rights reserved.
 */

package com.bdfint.backend.modules.sys.service;

import com.bdfint.backend.framework.common.BaseService;
import com.bdfint.backend.modules.sys.bean.Log;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统日志service
 *
 * @author lucf
 * @version 2016-01-15 09:56:22
 */
public interface LogService extends BaseService<Log> {

    /**
     * 保存日志
     *
     * @param request HttpServletRequest
     * @param title   日志标题
     */
    void save(HttpServletRequest request, String title) throws Exception;

    /**
     * 保存日志
     *
     * @param request HttpServletRequest
     * @param title   日志标题
     */
    void save(HttpServletRequest request, Object handler, Exception ex, String title) throws Exception;

    /**
     * 清空
     */
    void empty();
}
