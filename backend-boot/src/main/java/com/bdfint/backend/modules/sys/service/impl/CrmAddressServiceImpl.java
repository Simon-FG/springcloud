package com.bdfint.backend.modules.sys.service.impl;

import com.bdfint.backend.framework.common.BaseIntServiceImpl;
import com.bdfint.backend.framework.exception.CommonException;
import com.bdfint.backend.framework.util.StringUtils;
import com.bdfint.backend.modules.sys.bean.CrmAddress;
import com.bdfint.backend.modules.sys.mapper.CrmAddressMapper;
import com.bdfint.backend.modules.sys.service.CrmAddressService;
import com.bdfint.backend.modules.sys.utils.UserUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * Created by lsl on 2018/1/12.
 */
@Service
public class CrmAddressServiceImpl extends BaseIntServiceImpl<CrmAddress> implements CrmAddressService {

    @Autowired
    private CrmAddressMapper crmAddressMapper;


    @Override
    public PageInfo<CrmAddress> findListByPage(CrmAddress crmAddress) throws Exception {
        Example example = new Example(CrmAddress.class);
        Example.Criteria criteria = example.or();
        criteria.andEqualTo("userId",UserUtils.getUserId());
        example.setOrderByClause("level desc");
        return getPage(crmAddress,example);
    }

    /**
     * 设置默认地址
     * @param crmAddress   (id、userId)
     * @return
     */
    @Override
    @Transactional
    public Boolean setDefaultAddress(CrmAddress crmAddress) {
        String userId = UserUtils.getUserId();
        crmAddress.setUserId(userId);
        crmAddressMapper.setNormalAddress(crmAddress);
        crmAddressMapper.setDefaultAddress(crmAddress);
        return true;
    }

    @Override
    public Boolean setNormalAddress(CrmAddress crmAddress){
        crmAddressMapper.setNormalAddress(crmAddress);
        return true;
    }

    /**
     * 批量删除（将"delFlag"置为“1”）
     * @param id
     * @return
     */
    @Override
    @Transactional
    public Boolean delById(Integer... id) {
        return id != null && id.length > 0 && crmAddressMapper.delBatch(id) == id.length;
    }
}
