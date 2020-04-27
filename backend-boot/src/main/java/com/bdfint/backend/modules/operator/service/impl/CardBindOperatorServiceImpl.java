package com.bdfint.backend.modules.operator.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.bdfint.backend.framework.exception.CommonException;
import com.bdfint.backend.framework.util.Collections3;
import com.bdfint.backend.framework.util.StringUtils;
import com.bdfint.backend.modules.sys.service.CrmAirplaneCardBindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdfint.backend.framework.common.BaseIntServiceImpl;
import com.bdfint.backend.modules.operator.service.CardBindOperatorService;
import com.bdfint.backend.modules.sys.bean.CrmAirplaneCardBind;
import com.bdfint.backend.modules.sys.bean.CrmAirplaneManager;
import com.bdfint.backend.modules.sys.bean.CrmNorthCard;
import com.bdfint.backend.modules.sys.bean.CrmPrivateMessage;
import com.bdfint.backend.modules.sys.mapper.CrmAirplaneCardBindMapper;
import com.bdfint.backend.modules.sys.mapper.CrmAirplaneManagerMapper;
import com.bdfint.backend.modules.sys.mapper.CrmNorthCardMapper;
import com.bdfint.backend.modules.sys.mapper.CrmPrivateMessageMapper;
import com.bdfint.backend.modules.sys.utils.UserUtils;

import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;

/**
 * 北斗卡绑定审核Service
 * @author 74091
 *
 */
@Service
@Transactional
public class CardBindOperatorServiceImpl extends BaseIntServiceImpl<CrmAirplaneCardBind> implements CardBindOperatorService {

	@Autowired
	private CrmPrivateMessageMapper crmPrivateMessageMapper;
	
	@Autowired
	private CrmAirplaneCardBindMapper crmAirplaneCardBindMapper;//转网mapper
	
	@Autowired
	private CrmAirplaneManagerMapper crmAirplaneManagerMapper;
	
	@Autowired
	private CrmNorthCardMapper crmNorthCardMapper;

    @Autowired
    private CrmAirplaneCardBindService crmAirplaneCardBindService;

    /**
	 * 北斗卡绑定(或变更)
	 * @param crmAirplaneCardBind
	 * @return
	 */
	@Override
	@Transactional
	public Boolean add(CrmAirplaneCardBind crmAirplaneCardBind){
		try {
            Example example = new Example(CrmAirplaneCardBind.class);
            example.or().andEqualTo("cardId",crmAirplaneCardBind.getCardId())
					.andNotEqualTo("delFlag","1");
            example.or().andEqualTo("airplaneId",crmAirplaneCardBind.getAirplaneId())
					.andNotEqualTo("delFlag","1");
            // ---解绑步骤① 将涉及的原绑定记录bind相关的card、airplane的绑定状态设为"0"--未绑定
            List<CrmAirplaneCardBind> bindList = mapper.selectByExample(example);
            if(!Collections3.isEmpty(bindList)){
                for (CrmAirplaneCardBind bind: bindList){
                    crmAirplaneCardBindService.changeCardBound(bind.getCardId(), "0");
                    crmAirplaneCardBindService.changeAirplaneBound(bind.getAirplaneId(), "0");
                }
                //---解绑步骤② 将涉及的原绑定记录bind删除,置delFlag字段为"1"
                CrmAirplaneCardBind bind = new CrmAirplaneCardBind();
                bind.setUnbindTime(new Date());
                bind.setDelFlag("1");
                mapper.updateByExampleSelective(bind,example);
            }

            if(StringUtils.isNotBlank(crmAirplaneCardBind.getAirplaneId())){  //------如果机尾号不为空，则进行绑定操作
                //---绑定步骤① 添加新的绑定记录bind
                crmAirplaneCardBind.setStartTime(new Date());
                crmAirplaneCardBind.setBindTime(new Date());
                crmAirplaneCardBind.setStatus(UserUtils.STATUS_VALb);
                super.save(crmAirplaneCardBind);
                //---绑定步骤② 将涉及的新绑定记录bind相关的card、airplane的绑定状态设为"1"--已绑定
                crmAirplaneCardBindService.changeCardBound(crmAirplaneCardBind.getCardId(), "1");
                boolean b = crmAirplaneCardBindService.changeAirplaneBound(crmAirplaneCardBind.getAirplaneId(), "1");
                //---绑定步骤③ 若飞行器不存在，则在card所属user名下，新增一条飞行器记录
                if(!b){
                    CrmAirplaneManager crmAirplaneManager = new CrmAirplaneManager();
                    crmAirplaneManager.setTailCode(crmAirplaneCardBind.getAirplaneId());
                    crmAirplaneManager.setBound("1");
                    crmAirplaneManager.setUserId(crmAirplaneCardBind.getUserId());
                    crmAirplaneManager.setStatus(UserUtils.STATUS_VALd);//管理员发起默认飞行器已审核
                    crmAirplaneManagerMapper.insert(crmAirplaneManager);
                }
            }
            return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 北斗卡绑定审核
	 * 更新开始绑定时间bindTime
	 * status(1、关联申请 (0、关联变更申请 目前未用)  ---通过--->2、已关联
	 * 		  9、解绑                      ---驳回--->delFlag = 1 删除)
	 * 
	 */
	@Override
    @Transactional
	public Boolean updateStatus(String cardId, String status, String content, String menuId, Integer parentId) {
		CrmPrivateMessage crmPrivateMessage = new CrmPrivateMessage();
		String messageName = "";
		try {
			Example exampleRequest = new Example(CrmAirplaneCardBind.class);
			exampleRequest.createCriteria().andEqualTo("cardId", cardId)
//                    .andEqualTo("status","1")
                    .andNotEqualTo("delFlag","1");
			List<CrmAirplaneCardBind> selectByExampleRequest = crmAirplaneCardBindMapper.selectByExample(exampleRequest);
			if(selectByExampleRequest.size()>0)
			{
                CrmAirplaneCardBind crmAirplaneCardBind1 = new CrmAirplaneCardBind();
                Boolean f = false;
                if (selectByExampleRequest.size() == 2){
                    f=true;
                    for (CrmAirplaneCardBind bind: selectByExampleRequest){
                        if (bind.getStatus().equals("1")){
                            crmAirplaneCardBind1 = bind;
                        }
                    }
                }else {
                    CrmAirplaneCardBind bind = selectByExampleRequest.get(0);
                    if (bind.getStatus().equals("1")){
                        crmAirplaneCardBind1 = bind;   //----待 绑定操作
                    }else {
                        crmAirplaneCardBind1.setId(bind.getId());  //----待 解绑操作
                    }
                }


                try {
					if(!status.equals("9")){

                        add(crmAirplaneCardBind1);

						if(status.equals("1")){
							messageName = UserUtils.CARD_BIND_SUCCESS + "  卡号："+cardId+"  机尾号："+crmAirplaneCardBind1.getAirplaneId();
						}
//						else if(status.equals("0")){
//							messageName = UserUtils.CARD_CHANGE_SUCCESS + "  卡号："+cardId+"  机尾号："+crmAirplaneCardBind1.getAirplaneId();
//						}
                        else{
                            throw new CommonException("1:关联申请 9:解绑  请输入正确的'status'!");
                        }

					}else{
						crmAirplaneCardBind1.setUnbindTime(new Date());
						crmAirplaneCardBind1.setDelFlag(UserUtils.DEL_VAL);
						messageName = UserUtils.CARD_BIND_CANCEL + "  卡号："+cardId+"  机尾号："+crmAirplaneCardBind1.getAirplaneId() + " 原因："+content;
//                        crmAirplaneCardBindService.update(crmAirplaneCardBind1);
                        crmAirplaneCardBindMapper.updateByPrimaryKey(crmAirplaneCardBind1);

                        String bound = "0";
                        if(f){
                            bound ="1";
                        }
                        crmAirplaneCardBindService.changeCardBound(cardId,bound);
                        crmAirplaneCardBindService.changeAirplaneBound(crmAirplaneCardBind1.getAirplaneId(), bound);
					}
                    crmPrivateMessage.setName(messageName);
                    crmPrivateMessage.setMenuId(menuId);
                    crmPrivateMessage.setParentId(parentId);
                    crmPrivateMessage.setUserId(crmAirplaneCardBind1.getUserId());
                    crmPrivateMessage.setStartTime(new Date());
                    crmPrivateMessageMapper.insert(crmPrivateMessage);
					return true;
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}