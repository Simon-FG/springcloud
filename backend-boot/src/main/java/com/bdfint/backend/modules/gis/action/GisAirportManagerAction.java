package com.bdfint.backend.modules.gis.action;

import com.bdfint.backend.framework.common.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bdfint.backend.modules.gis.bean.GisAirportManager;
import com.bdfint.backend.modules.gis.service.GisAirportManagerService;
import com.github.pagehelper.PageInfo;

/**  
* <p>Title: GisAirportManagerAction</p>  
* <p>Description: 机场管理</p>  
* @author wangchao  
* @date 2018年2月28日  
*/
@RestController
@RequestMapping(value = "${adminPath}/airportManager")
public class GisAirportManagerAction extends BaseAction {

	@Autowired
    private GisAirportManagerService gisAirportManagerService;
	
	/**
	 * <p>Title: insertAirportManager</p>  
	 * <p>Description: 新增机场信息</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/insertAirportManager",method=RequestMethod.POST)
	public GisAirportManager insertAirportManager(GisAirportManager record) throws Exception{
		return gisAirportManagerService.insertAirportManager(record);
	}
	
	/**
	 * <p>Title: findListByPage</p>  
	 * <p>Description: 分页查询机场信息</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/findListByPage",method=RequestMethod.GET)
	public PageInfo<GisAirportManager> findListByPage(GisAirportManager record) throws Exception {
		return gisAirportManagerService.findListByPage(record);
	}
	
	/**
	 * <p>Title: updateAirportManager</p>  
	 * <p>Description: 修改机场信息</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateAirportManager",method=RequestMethod.POST)
	public boolean updateAirportManager(GisAirportManager record) throws Exception{
		return gisAirportManagerService.updateAirportManager(record);
	}
	
	/**
	 * <p>Title: delBatch</p>  
	 * <p>Description: 删除机场信息</p>  
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/delBatch",method=RequestMethod.DELETE)
	public boolean delBatch(Integer... id) throws Exception {
		return gisAirportManagerService.delBatch(id);
	}
	
	/**
	 * <p>Title: queryById</p>  
	 * <p>Description: 根据主键ID查询机场信息</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/queryById",method=RequestMethod.GET)
	public GisAirportManager queryById(GisAirportManager record) throws Exception {
		return gisAirportManagerService.queryById(record);
	}
}
