package com.bdfint.backend.modules.sys.service;

import com.bdfint.backend.framework.common.BaseIntService;
import com.bdfint.backend.modules.sys.bean.CrmPrivateMessage;
import com.github.pagehelper.PageInfo;

/**
 * Created by lsl on 2018/1/17.
 */
public interface CrmPrivateMessageService extends BaseIntService<CrmPrivateMessage>{

    public PageInfo<CrmPrivateMessage> findListByPage(CrmPrivateMessage privateMessage) throws Exception;

    public PageInfo<CrmPrivateMessage> findListNotRead(CrmPrivateMessage crmPrivateMessage) throws Exception;

    public int getCountNotRead(CrmPrivateMessage crmPrivateMessage);

    public PageInfo<CrmPrivateMessage> findAll(CrmPrivateMessage crmPrivateMessage) throws Exception;

    public Boolean setReaded(Integer ... id);

    public Boolean delById(Integer ... id);
}
