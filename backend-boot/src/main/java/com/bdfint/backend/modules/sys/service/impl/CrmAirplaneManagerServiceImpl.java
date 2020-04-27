package com.bdfint.backend.modules.sys.service.impl;

import com.bdfint.backend.framework.common.BaseIntServiceImpl;
import com.bdfint.backend.framework.exception.CommonException;
import com.bdfint.backend.framework.util.Collections3;
import com.bdfint.backend.framework.util.StringUtils;
import com.bdfint.backend.modules.sys.bean.CrmAirplaneManager;
import com.bdfint.backend.modules.sys.mapper.CrmAirplaneManagerMapper;
import com.bdfint.backend.modules.sys.service.CrmAirplaneManagerService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * Created by lsl on 2018/1/16.
 */
@Service
public class CrmAirplaneManagerServiceImpl extends BaseIntServiceImpl<CrmAirplaneManager> implements CrmAirplaneManagerService {

    @Autowired
    CrmAirplaneManagerMapper crmAirplaneManagerMapper;

    @Override
    public Boolean register(CrmAirplaneManager crmAirplaneManager) throws Exception {
        if(StringUtils.isNotBlank(crmAirplaneManager.getTailCode())){
            CrmAirplaneManager code = getOneByTailCode(crmAirplaneManager.getTailCode());
            if(code == null){
                crmAirplaneManager.setStatus("0");
                crmAirplaneManager.setRegTime(new Date());
                crmAirplaneManager.setFactoryTime(crmAirplaneManager.getFactoryTime());
                crmAirplaneManager.setBdDevice(crmAirplaneManager.getBdDevice());
                return insert(crmAirplaneManager) > 0;
            }
            throw new CommonException("tailCode已存在");
        }
        throw new CommonException("tailCode不能为空！");
    }

    @Override
    public CrmAirplaneManager getOneByTailCode(String tailCode) {
        if(StringUtils.isNotBlank(tailCode)){
            Example example = new Example(CrmAirplaneManager.class);
            Example.Criteria or = example.or();
            or.andEqualTo("tailCode", tailCode);
            List<CrmAirplaneManager> list = mapper.selectByExample(example);
            if(!Collections3.isEmpty(list)){
                return list.get(0);
            }
        }
        return null;
    }

    @Override
    public PageInfo<CrmAirplaneManager> findListByPage(CrmAirplaneManager crmAirplaneManager) throws Exception {
        Example example = new Example(CrmAirplaneManager.class);
        Example.Criteria or = example.or();
        if(StringUtils.isNotBlank(crmAirplaneManager.getTailCode())){
            or.andEqualTo("tailCode", crmAirplaneManager.getTailCode());
        }else{
            if(StringUtils.isNotBlank(crmAirplaneManager.getUserId())){
                or.andEqualTo("userId", crmAirplaneManager.getUserId());
            }
            if(StringUtils.isNotBlank(crmAirplaneManager.getType())){
                or.andEqualTo("type", crmAirplaneManager.getType());
            }
            if(StringUtils.isNotBlank(crmAirplaneManager.getStatus())){
                or.andEqualTo("status", crmAirplaneManager.getStatus());
            }
        }
        example.setOrderByClause("id desc");
        return getPage(crmAirplaneManager, example);
    }

    @Override
	public int editAirplane(CrmAirplaneManager crmAirplaneManager) throws Exception {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(crmAirplaneManager);
	}

}
