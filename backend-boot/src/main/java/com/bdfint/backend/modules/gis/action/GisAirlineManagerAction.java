package com.bdfint.backend.modules.gis.action;

import com.bdfint.backend.framework.common.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bdfint.backend.modules.gis.bean.GisAirlineManager;
import com.bdfint.backend.modules.gis.service.GisAirlineManagerService;
import com.github.pagehelper.PageInfo;

/**  
* <p>Title: GisAirlineManagerAction</p>  
* <p>Description: 航线管理</p>  
* @author wangchao  
* @date 2018年2月28日  
*/ 
@RestController
@RequestMapping(value = "${adminPath}/airlineManage")
public class GisAirlineManagerAction extends BaseAction {
	
	@Autowired
    private GisAirlineManagerService gisAirlineManagerService;
	
	/**
	 * <p>Title: insertAirlineManager</p>  
	 * <p>Description: 新增航线管理</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/insertAirlineManager",method=RequestMethod.POST)
	public GisAirlineManager insertAirlineManager(GisAirlineManager record) throws Exception{
		return gisAirlineManagerService.insertAirlineManager(record);
	}
	
	/**
	 * <p>Title: findListByPage</p>  
	 * <p>Description: 分页查询航线管理</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/findListByPage",method=RequestMethod.GET)
	public PageInfo<GisAirlineManager> findListByPage(GisAirlineManager record) throws Exception {
		return gisAirlineManagerService.findListByPage(record);
	}
	
	/**
	 * <p>Title: updateAirlineManager</p>  
	 * <p>Description: 修改航线管理</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateAirlineManager",method=RequestMethod.POST)
	public boolean updateAirlineManager(GisAirlineManager record) throws Exception{
		return gisAirlineManagerService.updateAirlineManager(record);
	}
	
	/**
	 * <p>Title: delBatch</p>  
	 * <p>Description: 删除航线管理</p>  
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/delBatch",method=RequestMethod.DELETE)
	public boolean delBatch(Integer... id) throws Exception {
		return gisAirlineManagerService.delBatch(id);
	}
	
	/**
	 * <p>Title: queryById</p>  
	 * <p>Description: 根据主键ID查询航线管理</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/queryById",method=RequestMethod.GET)
	public GisAirlineManager queryById(GisAirlineManager record) throws Exception {
		return gisAirlineManagerService.queryById(record);
	}
}
