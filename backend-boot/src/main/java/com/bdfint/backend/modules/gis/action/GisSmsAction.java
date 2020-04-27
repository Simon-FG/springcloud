package com.bdfint.backend.modules.gis.action;

import com.bdfint.backend.framework.common.BaseAction;
import com.bdfint.backend.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bdfint.backend.modules.gis.bean.GisSms;
import com.bdfint.backend.modules.gis.service.GisSmsService;
import com.github.pagehelper.PageInfo;

/**
* <p>Title: GisSmsAction</p>  
* <p>Description: </p>  
* @author wangchao  
* @date 2018年4月9日
 *
 * -------接口-------
 * 	短报文分页查询（cardId、startTime、endTime）
 * 	admin/gisSms/getListByPage
 */
@RestController
@RequestMapping("{adminPath}/gisSms")
public class GisSmsAction extends BaseAction {
	
	@Autowired
	private GisSmsService gisSmsService;
	
	/**
	 * <p>Title: findListByPage</p>  
	 * <p>Description: 分页查询消息</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findListByPage", method = RequestMethod.GET)
	public PageInfo<GisSms> findListByPage(GisSms record)throws Exception{
		UserUtils.getCardList();
		return gisSmsService.findListByPage(record);
	}

	@RequestMapping(value = "/getListByPage", method = RequestMethod.GET)
	public PageInfo<GisSms> getListByPage(GisSms record)throws Exception{
		UserUtils.getCardList();
		return gisSmsService.getListByPage(record);
	}
	
	@RequestMapping(value = "/insertGisSms", method = RequestMethod.POST)
	public GisSms insertGisSms(GisSms record) throws Exception {
		return gisSmsService.insertGisSms(record);
	}
}
