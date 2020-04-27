package com.bdfint.backend.modules.gis.action;

import com.bdfint.backend.framework.common.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bdfint.backend.modules.gis.bean.GisStyleControl;
import com.bdfint.backend.modules.gis.service.GisStyleControlService;
import com.github.pagehelper.PageInfo;

/**  
* <p>Title: GisStyleControlAction</p>  
* <p>Description: 样式控制</p>  
* @author wangchao  
* @date 2018年2月27日  
*/ 
@RestController
@RequestMapping(value = "${adminPath}/styleControl")
public class GisStyleControlAction extends BaseAction {

	@Autowired
    private GisStyleControlService gisStyleControlService;
	
	/**
	 * <p>Title: insertStyleControl</p>  
	 * <p>Description: 新增样式控制</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/insertStyleControl",method=RequestMethod.POST)
	public GisStyleControl insertStyleControl(GisStyleControl record) throws Exception{
		return gisStyleControlService.insertStyleControl(record);
	}
	
	/**
	 * <p>Title: findListByPage</p>  
	 * <p>Description: 分页查询样式控制</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/findListByPage",method=RequestMethod.GET)
	public PageInfo<GisStyleControl> findListByPage(GisStyleControl record) throws Exception {
		return gisStyleControlService.findListByPage(record);
	}
	
	/**
	 * <p>Title: updateStyleControl</p>  
	 * <p>Description: 修改样式</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateStyleControl",method=RequestMethod.POST)
	public boolean updateStyleControl(GisStyleControl record) throws Exception{
		return gisStyleControlService.updateStyleControl(record);
	}
	
	/**
	 * <p>Title: delBatch</p>  
	 * <p>Description: 删除样式</p>  
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/delBatch",method=RequestMethod.DELETE)
	public boolean delBatch(Integer... id) throws Exception {
		return gisStyleControlService.delBatch(id);
	}
	
	/**
	 * <p>Title: queryById</p>  
	 * <p>Description: 根据主键ID查询样式</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/queryById",method=RequestMethod.GET)
	public GisStyleControl queryById(GisStyleControl record) throws Exception {
		return gisStyleControlService.queryById(record);
	}
}
