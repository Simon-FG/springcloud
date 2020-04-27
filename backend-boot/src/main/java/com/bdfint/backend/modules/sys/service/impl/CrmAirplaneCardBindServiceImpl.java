package com.bdfint.backend.modules.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bdfint.backend.framework.common.Global;
import com.bdfint.backend.framework.exception.CommonException;
import com.bdfint.backend.framework.util.StringUtils;
import com.bdfint.backend.modules.operator.service.CardBindOperatorService;
import com.bdfint.backend.modules.sys.bean.*;

import com.bdfint.backend.modules.sys.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

//import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdfint.backend.framework.common.BaseEntity;
import com.bdfint.backend.framework.common.BaseIntServiceImpl;
import com.bdfint.backend.modules.sys.mapper.CrmAirplaneCardBindMapper;
import com.bdfint.backend.modules.sys.mapper.CrmAirplaneManagerMapper;
import com.bdfint.backend.modules.sys.mapper.CrmNorthCardMapper;
import com.bdfint.backend.modules.sys.service.CrmAirplaneCardBindService;
import com.bdfint.backend.modules.sys.utils.UserUtils;

import tk.mybatis.mapper.entity.Example;

/**
 * 北斗卡绑定Service
 * @author 74091
 *
 */
@Service
@Transactional
public class CrmAirplaneCardBindServiceImpl extends BaseIntServiceImpl<CrmAirplaneCardBind> implements CrmAirplaneCardBindService {
	@Autowired
	private CrmAirplaneCardBindMapper crmAirplaneCardBindMapper;
	
	@Autowired
	private CrmNorthCardMapper crmNorthCardMapper;
	
	@Autowired
	private CrmAirplaneManagerMapper crmAirplaneManagerMapper;

    @Autowired
    private UserMapper userMapper;

	@Autowired
	private CardBindOperatorService cardBindOperatorService;
	
	/**
	 * 北斗卡绑定申请方法
	 * 
	 */
	@Override
	@Transactional
	public Boolean add(CrmAirplaneCardBind crmAirplaneCardBind){
		try {
			crmAirplaneCardBind.setStartTime(new Date());
			crmAirplaneCardBind.setUserId(UserUtils.getUser().getId());
			super.save(crmAirplaneCardBind);
			if(UserUtils.isYWorYY()){
				return cardBindOperatorService.add(crmAirplaneCardBind);
			}

            changeCardBound(crmAirplaneCardBind.getCardId(),"2");
            changeAirplaneBound(crmAirplaneCardBind.getAirplaneId(),"2");

            return true;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	//设置北斗卡绑定状态
    @Override
	public boolean changeCardBound(String cardId, String bound){
		Example exampleRequestCard = new Example(CrmNorthCard.class);
		exampleRequestCard.createCriteria().andEqualTo("cardId", cardId);
		List<CrmNorthCard> selectByExampleRequestCard = crmNorthCardMapper.selectByExample(exampleRequestCard);
		if(selectByExampleRequestCard.size() == 1) {
			CrmNorthCard crmNorthCard = selectByExampleRequestCard.get(0);
			try {
				crmNorthCard.setBound(bound);
				exampleRequestCard.createCriteria().andCondition("card_id='" + cardId + "'");
				crmNorthCardMapper.updateByExample(crmNorthCard, exampleRequestCard);
				return true;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return false;
	}

	//设置飞行器绑定状态
    @Override
	public boolean changeAirplaneBound(String airplaneId, String bound){
		Example exampleRequest = new Example(CrmAirplaneManager.class);
		exampleRequest.createCriteria().andEqualTo("tailCode", airplaneId);
		List<CrmAirplaneManager> selectByExampleRequest = crmAirplaneManagerMapper.selectByExample(exampleRequest);
		if(selectByExampleRequest.size()>0)
		{
			CrmAirplaneManager crmAirplaneManager = selectByExampleRequest.get(0);
			try {
				crmAirplaneManager.setBound(bound);
				exampleRequest.createCriteria().andCondition("tail_code='"+airplaneId+"'");
				crmAirplaneManagerMapper.updateByExample(crmAirplaneManager, exampleRequest);
				return true;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 获取北斗卡ID和变更理由修改status状态变为0,置delFlag标志为“1”   ---解绑
	 */
	@Override
    @Transactional
	public String changeBind(String cardId, String note) {
		String sub = "";
		Example exampleRequest = new Example(CrmAirplaneCardBind.class);
		exampleRequest.createCriteria()
                .andEqualTo("cardId", cardId)
                .andEqualTo("delFlag", "0");
		List<CrmAirplaneCardBind> selectByExampleRequest = crmAirplaneCardBindMapper.selectByExample(exampleRequest);
		if(selectByExampleRequest.size()==1)
		{
			CrmAirplaneCardBind crmAirplaneCardBind1 = selectByExampleRequest.get(0);
			try {
				crmAirplaneCardBind1.setNote(note);
				crmAirplaneCardBind1.setStatus(UserUtils.STATUS_VALd);
				crmAirplaneCardBind1.setDelFlag("1");
                crmAirplaneCardBind1.setUnbindTime(new Date());
				Example exampleRequest1 = new Example(CrmAirplaneCardBind.class);
				exampleRequest1.createCriteria().andCondition("card_id='"+cardId+"'");
				int updateByExample1 = crmAirplaneCardBindMapper.updateByExample(crmAirplaneCardBind1, exampleRequest1);
				// 修改飞行器绑定状态
                Example example = new Example(CrmAirplaneManager.class);
                example.or().andEqualTo("tailCode", crmAirplaneCardBind1.getAirplaneId());
                CrmAirplaneManager crmAirplaneManager = new CrmAirplaneManager();
                crmAirplaneManager.setBound("0");
                crmAirplaneManagerMapper.updateByExampleSelective(crmAirplaneManager,example);
                //修改北斗卡绑定状态
                Example exampleCard = new Example(CrmNorthCard.class);
                exampleCard.or().andEqualTo("cardId", cardId);
                CrmNorthCard crmNorthCard = new CrmNorthCard();
                crmNorthCard.setBound(UserUtils.STATUS_VALd);
                crmNorthCardMapper.updateByExampleSelective(crmNorthCard,exampleCard);
                
                if(updateByExample1 == 1){
					sub = "1";
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return sub;
	}
	
	@Override
	public List getCardAndAirplane(String userId) {
		List cardList = new ArrayList();
		List bandList = new ArrayList();  
		List airplaneList = new ArrayList();
		try {
			CrmNorthCard crmNorthCard = new CrmNorthCard();
			Example example = new Example(CrmNorthCard.class);
			example.createCriteria().andCondition("user_id"+"=",userId);
	        List<CrmNorthCard> selectByCard= crmNorthCardMapper.selectByExample(example);
	        for(CrmNorthCard card: selectByCard){  
	        	Map<String, String> map = new HashMap<String, String>();  
	            map.put("cardId", card.getCardId());  
	        	cardList.add(map);
	        }
	        if(cardList.size() > 0 ){
	        	CrmAirplaneCardBind crmAirplaneCardBind = new CrmAirplaneCardBind();
				Example example1 = new Example(CrmAirplaneCardBind.class);
				example1.createCriteria().andCondition("user_id"+"=",userId);
		        List<CrmAirplaneCardBind> selectByCardBind= crmAirplaneCardBindMapper.selectByExample(example1);
		        for(CrmAirplaneCardBind bind: selectByCardBind){  
		        	Map<String, Object> map = new HashMap<String, Object>();  
		            map.put("cardId", bind.getCardId()); 
		        	bandList.add(map);
		        } 
	        }
	        cardList.removeAll(bandList);
	        
	        CrmAirplaneManager crmAirplaneManager = new CrmAirplaneManager();
			Example example2 = new Example(CrmAirplaneManager.class);
			example.createCriteria().andCondition("user_id"+"=",userId);
			example.createCriteria().andCondition("bound" + "=" ,"0");
			List<CrmAirplaneManager> selectByAirPlane = crmAirplaneManagerMapper.selectByExample(example);
	        for(CrmAirplaneManager airplane: selectByAirPlane){  
	        	Map<String, String> map = new HashMap<String, String>();  
	            map.put("tailCode", airplane.getTailCode());  
	            airplaneList.add(map);
	        }  
	        cardList.addAll(airplaneList);
	        
			return cardList;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Boolean deleteBind(String cardId) {
		
		Example exampleRequest = new Example(CrmAirplaneCardBind.class);
		exampleRequest.createCriteria().andEqualTo("cardId", cardId);
		List<CrmAirplaneCardBind> selectByExampleRequest = crmAirplaneCardBindMapper.selectByExample(exampleRequest);
		if(selectByExampleRequest.size()==1)
		{
			CrmAirplaneCardBind crmAirplaneCardBind= selectByExampleRequest.get(0);
			try {
				crmAirplaneCardBind.setDelFlag(UserUtils.DEL_VAL);
				exampleRequest.createCriteria().andCondition("card_id='"+cardId+"'");
				int updateByExample = crmAirplaneCardBindMapper.updateByExample(crmAirplaneCardBind, exampleRequest);
				if(updateByExample == 1){
					Example exampleRequest1 = new Example(CrmAirplaneManager.class);
					System.out.println(crmAirplaneCardBind.getAirplaneId());
					exampleRequest1.createCriteria().andEqualTo("tailCode", crmAirplaneCardBind.getAirplaneId());
					List<CrmAirplaneManager> selectByExampleRequest1 = crmAirplaneManagerMapper.selectByExample(exampleRequest1);
					if(selectByExampleRequest1.size()==1)
					{
						CrmAirplaneManager crmAirplaneManager1= selectByExampleRequest1.get(0);
						try {
							crmAirplaneManager1.setBound(UserUtils.STATUS_VALd);
							exampleRequest1.createCriteria().andCondition("tail_code='"+crmAirplaneManager1.getTailCode()+"'");
							crmAirplaneManagerMapper.updateByExample(crmAirplaneManager1, exampleRequest1);
							
							//修改北斗卡绑定状态
			                Example exampleCard = new Example(CrmNorthCard.class);
			                exampleCard.or().andEqualTo("cardId", cardId);
			                CrmNorthCard crmNorthCard = new CrmNorthCard();
			                crmNorthCard.setBound(UserUtils.STATUS_VALd);
			                crmNorthCardMapper.updateByExampleSelective(crmNorthCard,exampleCard);
			                
							return true;
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return false;
	}
	
	/**
	 * <p>Title: queryByCardIdAirplaneCardBind</p>  
	 * <p>Description: 根据北斗卡cardId查询飞行器信息 </p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@Override
	public CrmAirplaneCardBind queryByCardIdAirplaneCardBind(CrmAirplaneCardBind record) throws Exception {
		 record=crmAirplaneCardBindMapper.queryByCardIdAirplaneCardBind(record);
		 return record;
	}
	
	/**
	 * 查询机尾号是否被占用
	 */
	@Override
	public String queryAirplaneOccupy(String airplaneId) {
		//判断该航空器是否属于当前用户
		Example exampleRequest1 = new Example(CrmAirplaneManager.class);
		exampleRequest1.createCriteria().andEqualTo("tailCode", airplaneId);
		exampleRequest1.getOredCriteria().get(0).andNotEqualTo("delFlag", BaseEntity.DEL_FLAG_DELETE);
		List<CrmAirplaneManager> selectByExampleRequest1 = crmAirplaneManagerMapper.selectByExample(exampleRequest1);
		if(selectByExampleRequest1.size() == 1){
			CrmAirplaneManager crmAirplaneManager= selectByExampleRequest1.get(0);
			if(!crmAirplaneManager.getUserId().equals(UserUtils.getUser().getId())){
				return "1";
			}
		}
		//判断该航空器是否被占用
		Example exampleRequest = new Example(CrmAirplaneCardBind.class);
		exampleRequest.createCriteria().andEqualTo("airplaneId", airplaneId);
		exampleRequest.getOredCriteria().get(0).andNotEqualTo("delFlag", BaseEntity.DEL_FLAG_DELETE);
		List<CrmAirplaneCardBind> selectByExampleRequest = crmAirplaneCardBindMapper.selectByExample(exampleRequest);
		if(selectByExampleRequest.size() > 0)
		{
			return "2";
		}
		return null;
	}

    @Override
    public PageInfo<CrmAirplaneCardBind> getReqList(CrmAirplaneCardBind object) throws Exception {
        if(UserUtils.isYWorYY()){
            object.setUserId(null);
        }
		int pageSize = object.getPageSize() == 0 ? Global.PAGE_SIZE : object.getPageSize();
		if (pageSize != -1) {
			PageHelper.startPage(object.getPageNum(), pageSize);
		}
		List<CrmAirplaneCardBind> list = crmAirplaneCardBindMapper.getReqList(object);
		PageInfo<CrmAirplaneCardBind> page = new PageInfo<>(list);
		page.setPageNum(object.getPageNum());
		page.setPageSize(pageSize);
		return page;
    }

    @Override
    public PageInfo<CrmAirplaneCardBind> getPageCardLeft(CrmAirplaneCardBind object) {
        if(UserUtils.isYWorYY()){
            object.setUserId(null);
        }
        int pageSize = object.getPageSize() == 0 ? Global.PAGE_SIZE : object.getPageSize();
        if (pageSize != -1) {
            PageHelper.startPage(object.getPageNum(), pageSize);
        }
        List<CrmAirplaneCardBind> list = crmAirplaneCardBindMapper.getListBy(object);
        PageInfo<CrmAirplaneCardBind> page = new PageInfo<>(list);
        page.setPageNum(object.getPageNum());
        page.setPageSize(pageSize);
        return page;
    }

	/**
	 * 查询已绑定的北斗卡与机尾号关联
	 */
    @Override
	public String queryBindList() throws Exception {
//    	List<CrmAirplaneCardBind> bindList = crmAirplaneCardBindMapper.queryBindList();
//    	String bindJson =JSONArray.fromObject(bindList).toString();
//		return bindJson;
        return "";
	}

}