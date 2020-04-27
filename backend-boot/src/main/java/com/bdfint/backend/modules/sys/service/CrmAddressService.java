package com.bdfint.backend.modules.sys.service;

import com.bdfint.backend.framework.common.BaseIntService;
import com.bdfint.backend.modules.sys.bean.CrmAddress;
import com.github.pagehelper.PageInfo;

/**
 * Created by lsl on 2018/1/12.
 */
public interface CrmAddressService extends BaseIntService<CrmAddress> {

    public PageInfo<CrmAddress> findListByPage(CrmAddress crmAddress) throws Exception;

    public Boolean setDefaultAddress(CrmAddress crmAddress);

    public Boolean setNormalAddress(CrmAddress crmAddress);

    public Boolean delById(Integer ... id);
}
