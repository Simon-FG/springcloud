package com.bdfint.backend.modules.sys.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bdfint.backend.framework.common.BaseIntServiceImpl;
import com.bdfint.backend.framework.util.FilesUtils;
import com.bdfint.backend.framework.util.NorthCardUtils;
import com.bdfint.backend.modules.sys.bean.CrmNorthCardRequest;
import com.bdfint.backend.modules.sys.bean.CrmOrderItem;
import com.bdfint.backend.modules.sys.mapper.CrmNorthCardRequestMapper;
import com.bdfint.backend.modules.sys.mapper.CrmOrderItemMapper;
import com.bdfint.backend.modules.sys.service.CrmNorthCardRequestService;
import com.bdfint.backend.modules.sys.utils.UserUtils;

import tk.mybatis.mapper.entity.Example;

/**
 * 北斗卡申请Service
 * @author 74091
 *
 */
@Service
@Transactional
public class CrmNorthCardRequestServiceImpl extends BaseIntServiceImpl<CrmNorthCardRequest> implements CrmNorthCardRequestService {
	@Autowired
	private CrmNorthCardRequestMapper crmNorthCardRequestMapper;
	
	@Autowired
	private CrmOrderItemMapper crmOrderItemMapper;
	
	/**
	 * 订单申请方法
	 */
	@Override
	@Transactional
	public String add(CrmNorthCardRequest crmNorthCardRequest,HttpServletRequest request){
		
//		MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);  
		
		CrmOrderItem crmOrderItem = new CrmOrderItem();
		String sub = "";
		String folder = "BDKSQ";
		String requestId = NorthCardUtils.getRequestCardNumber(UserUtils.ORDER_TYPE_CARD);
		
		try {
			List pathList = FilesUtils.fileUpload(request,folder);
			crmNorthCardRequest.setRequestId(requestId);
			crmNorthCardRequest.setReportFile(pathList.get(0).toString());
			crmNorthCardRequest.setContractFile(pathList.get(1).toString());
			if(pathList.size() > 2){
				crmNorthCardRequest.setAircraftFile(pathList.get(2).toString());
			}
			crmNorthCardRequest.setStartTime(new Date());
			crmNorthCardRequest.setUserId(UserUtils.getUser().getId());
			crmNorthCardRequest.setStatus("1");
			crmNorthCardRequestMapper.insert(crmNorthCardRequest);
			crmOrderItem.setOrderId(requestId);
			crmOrderItem.setCreateTime(new Date());
			crmOrderItem.setFullPrice(crmNorthCardRequest.getFullPrice());
			crmOrderItem.setUserName(UserUtils.getUser().getName());
			crmOrderItem.setGoodsId(1);//前台传参
			crmOrderItem.setMenuId("9");//前台传参
//			crmOrderItem.setGoodsId(crmNorthCardRequest.getParentId);
//			crmOrderItem.setMenuId(crmNorthCardRequest.getMenuId);
			crmOrderItem.setOrderType(UserUtils.ORDER_TYPE_CARD);
			crmOrderItem.setUserId(UserUtils.getUser().getId());
			crmOrderItemMapper.add(crmOrderItem);
			sub = requestId;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return sub;
	}
	
	/**
	 * 申请详细信息
	 */
	@Override
	public List<CrmNorthCardRequest> getDetail(String requestId) {
		Example example = new Example(CrmNorthCardRequest.class);
		example.createCriteria().andEqualTo("requestId", requestId);
		return crmNorthCardRequestMapper.selectByExample(example);
	}

	@Override
	public CrmNorthCardRequest getByRequestId(String requestId) {
		Example example = new Example(CrmNorthCardRequest.class);
        Example.Criteria or = example.or();
        or.andEqualTo("requestId", requestId);
        List<CrmNorthCardRequest> list = crmNorthCardRequestMapper.selectByExample(example);
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }
}