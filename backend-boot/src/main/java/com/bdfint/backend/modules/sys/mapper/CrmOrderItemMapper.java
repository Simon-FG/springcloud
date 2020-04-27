package com.bdfint.backend.modules.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.bdfint.backend.framework.common.CommonMapper;
import com.bdfint.backend.modules.sys.bean.CrmOrderItem;

@Mapper
@Repository
public interface CrmOrderItemMapper extends CommonMapper<CrmOrderItem>{
	
	public void add(CrmOrderItem crmOrderItem);
	
	public void addAddress(CrmOrderItem crmOrderItem);
	
	public void deleteFlag(Object orderId);
	
	public List<CrmOrderItem> queryOrderListByPage();
	
	public List<CrmOrderItem> selectMinOrderDetailByPage(@Param("orderId") String orderId,@Param("goodsId") Integer goodsId,@Param("addressId") Integer addressId,@Param("orderType") String orderType);
	
	public List<CrmOrderItem> selectMaxOrderDetailByPage(@Param("orderId") String orderId,@Param("goodsId") Integer goodsId,@Param("orderType") String orderType);

}