package com.bdfint.backend.modules.gis.action;

import com.bdfint.backend.framework.common.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bdfint.backend.modules.gis.bean.GisNoflyZoneManager;
import com.bdfint.backend.modules.gis.service.GisNoflyZoneManagerService;
import com.github.pagehelper.PageInfo;


/**  
* <p>Title: GisNoflyZoneManagerAction</p>  
* <p>Description: 禁区管理</p>  
* @author wangchao  
* @date 2018年2月27日  
*/
@RestController
@RequestMapping(value = "${adminPath}/noflyZoneManager")
public class GisNoflyZoneManagerAction extends BaseAction {

	@Autowired
    private GisNoflyZoneManagerService gisNoflyZoneManagerService;
	
	/**
	 * <p>Title: insertNoflyZoneManager</p>  
	 * <p>Description: 新增禁区</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/insertNoflyZoneManager",method=RequestMethod.POST)
	public GisNoflyZoneManager insertNoflyZoneManager(GisNoflyZoneManager record) throws Exception{
		return gisNoflyZoneManagerService.insertNoflyZoneManager(record);
	}
	
	/**
	 * <p>Title: findListByPage</p>  
	 * <p>Description: 分页查询禁区</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/findListByPage",method=RequestMethod.GET)
	public PageInfo<GisNoflyZoneManager> findListByPage(GisNoflyZoneManager record) throws Exception {
		return gisNoflyZoneManagerService.findListByPage(record);
	}
	
	/**
	 * <p>Title: updateNoflyZoneManager</p>  
	 * <p>Description: 修改禁区</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateNoflyZoneManager",method=RequestMethod.POST)
	public boolean updateNoflyZoneManager(GisNoflyZoneManager record) throws Exception{
		return gisNoflyZoneManagerService.updateNoflyZoneManager(record);
	}
	
	/**
	 * <p>Title: delBatch</p>  
	 * <p>Description: 删除禁区</p>  
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/delBatch",method=RequestMethod.DELETE)
	public boolean delBatch(Integer... id) throws Exception {
		return gisNoflyZoneManagerService.delBatch(id);
	}
	
	/**
	 * <p>Title: queryById</p>  
	 * <p>Description: 根据主键ID查询禁区</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/queryById",method=RequestMethod.GET)
	public GisNoflyZoneManager queryById(GisNoflyZoneManager record) throws Exception {
		return gisNoflyZoneManagerService.queryById(record);
	}
}
