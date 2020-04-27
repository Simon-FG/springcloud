package com.bdfint.backend.modules.sys.service.impl;

import java.util.Date;
import java.util.List;

import com.bdfint.backend.framework.exception.CommonException;
import com.bdfint.backend.modules.sys.bean.SysHardwareItem;
import com.bdfint.backend.modules.sys.mapper.SysHardwareItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdfint.backend.framework.common.BaseIntServiceImpl;
import com.bdfint.backend.framework.util.NorthCardUtils;
import com.bdfint.backend.modules.sys.bean.CrmHardwareRequest;
import com.bdfint.backend.modules.sys.bean.CrmOrderItem;
import com.bdfint.backend.modules.sys.mapper.CrmOrderItemMapper;
import com.bdfint.backend.modules.sys.service.CrmHardwareRequestService;
import com.bdfint.backend.modules.sys.utils.UserUtils;
import tk.mybatis.mapper.entity.Example;

/**
 * 硬件设备申请Service
 * @author 74091
 *
 */
@Service
@Transactional
public class CrmHardwareRequestServiceImpl extends BaseIntServiceImpl<CrmHardwareRequest> implements CrmHardwareRequestService {
	
	@Autowired
	private CrmOrderItemMapper crmOrderItemMapper;

    @Autowired
    private SysHardwareItemMapper sysHardwareItemMapper;
	
	/**
	 * 订单申请方法
	 */
	@Override
	@Transactional
	public String add(CrmHardwareRequest crmHardwareRequest) throws Exception {
		
		CrmOrderItem crmOrderItem = new CrmOrderItem();
		String sub = "";
		String requestId = NorthCardUtils.getRequestCardNumber(UserUtils.ORDER_TYPE_HARDWARE);
//		try {
			crmHardwareRequest.setRequestId(requestId);
			crmHardwareRequest.setStartTime(new Date());
			crmHardwareRequest.setUserId(UserUtils.getUser().getId());
			crmHardwareRequest.setStatus("1");
			super.save(crmHardwareRequest);
			crmOrderItem.setOrderId(requestId);
			crmOrderItem.setCreateTime(new Date());
			crmOrderItem.setFullPrice(crmHardwareRequest.getFullPrice());
			crmOrderItem.setUserName(UserUtils.getUser().getName());
//			crmOrderItem.setGoodsId(2);
			crmOrderItem.setGoodsId(crmHardwareRequest.getHardwareItmeId());
			crmOrderItem.setMenuId("9");
//			crmOrderItem.setGoodsId(Integer.parseInt(params.getParameter("goodsId")));
//			crmOrderItem.setMenuId(params.getParameter("menuId"));			
			crmOrderItem.setOrderType(UserUtils.ORDER_TYPE_HARDWARE);
			crmOrderItem.setUserId(UserUtils.getUser().getId());
//            Example example = new Example(SysHardwareItem.class);
//            example.or().andEqualTo("itemId",crmHardwareRequest.getHardwareItmeId());
//            List<SysHardwareItem> sysHardwareItems = sysHardwareItemMapper.selectByExample(example);
//            if(sysHardwareItems.size() > 0){
//                crmOrderItem.setImg(sysHardwareItems.get(0).getHardwareImg());
            SysHardwareItem oneById = sysHardwareItemMapper.getOneById(crmHardwareRequest.getHardwareItmeId());
            if(oneById != null){
                crmOrderItem.setImg(oneById.getHardwareImg());
            }else{
                throw new CommonException("该硬件不存在！");
            }

			crmOrderItemMapper.add(crmOrderItem);
			sub = requestId;
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//            throw new Exception(e.getCause());
//		}
		return sub;
	}
	
}