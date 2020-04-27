package com.bdfint.backend.modules.sys.service.impl;

import com.bdfint.backend.framework.common.BaseIntServiceImpl;
import com.bdfint.backend.framework.exception.CommonException;
import com.bdfint.backend.framework.util.StringUtils;
import com.bdfint.backend.modules.sys.bean.CrmPrivateMessage;
import com.bdfint.backend.modules.sys.service.CrmPrivateMessageService;
import com.bdfint.backend.modules.sys.utils.UserUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;

/**
 * Created by lsl on 2018/1/17.
 */
@Service
public class CrmPrivateMessageServiceImpl extends BaseIntServiceImpl<CrmPrivateMessage> implements CrmPrivateMessageService {

    @Override
    public PageInfo<CrmPrivateMessage> findListByPage(CrmPrivateMessage privateMessage) throws Exception {
        Example example = new Example(CrmPrivateMessage.class);
        Example.Criteria or = example.or();
        or.andEqualTo("userId",UserUtils.getUserId());
        if(StringUtils.isNotBlank(privateMessage.getStatus())){
            or.andEqualTo("status",privateMessage.getStatus());
        }
        example.setOrderByClause("start_Time desc");
        return getPage(privateMessage,example);
    }

    @Override
    public PageInfo<CrmPrivateMessage> findListNotRead(CrmPrivateMessage privateMessage) throws Exception {
        String userId = UserUtils.getUserId();
        privateMessage.setUserId(userId);
        privateMessage.setStatus("0");
        return findListByPage(privateMessage);
    }

    @Override
    public int getCountNotRead(CrmPrivateMessage privateMessage){
        Example example = new Example(CrmPrivateMessage.class);
        Example.Criteria or = example.or();
        String userId = UserUtils.getUserId();
        or.andEqualTo("userId",userId);
        or.andEqualTo("status","0");
        return mapper.selectCountByExample(example);
    }

    @Override
    public PageInfo<CrmPrivateMessage> findAll(CrmPrivateMessage crmPrivateMessage) throws Exception {
        String userId = UserUtils.getUserId();
        crmPrivateMessage.setUserId(userId);
        return findListByPage(crmPrivateMessage);
    }

    @Override
    @Transactional
    public Boolean setReaded(Integer... id) {
        Example example = new Example(CrmPrivateMessage.class);
        Example.Criteria or = example.or();
        or.andEqualTo("userId", UserUtils.getUserId());
        or.andIn("id", Arrays.asList(id));
        CrmPrivateMessage crmPrivateMessage = new CrmPrivateMessage();
        crmPrivateMessage.setStatus("1");
        crmPrivateMessage.setDelFlag(null);
        mapper.updateByExampleSelective(crmPrivateMessage,example);
//        if(mapper.updateByExampleSelective(crmPrivateMessage,example) != id.length){
//            throw new CommonException("批量设置已读未能完全成功！");
//        }
        return true;
    }

    @Override
    @Transactional
    public Boolean delById(Integer ... id){
        Example example = new Example(CrmPrivateMessage.class);
        Example.Criteria or = example.or();
        or.andIn("id", Arrays.asList(id));
        mapper.deleteByExample(example);
        return true;
    }
}
