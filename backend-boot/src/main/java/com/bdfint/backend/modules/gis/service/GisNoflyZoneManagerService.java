package com.bdfint.backend.modules.gis.service;

import com.bdfint.backend.framework.common.BasePgService;
import com.bdfint.backend.modules.gis.bean.GisNoflyZoneManager;
import com.github.pagehelper.PageInfo;

/**  
* <p>Title: GisNoflyZoneManagerService</p>  
* <p>Description: 禁区管理</p>  
* @author wangchao  
* @date 2018年2月27日  
*/ 
public interface GisNoflyZoneManagerService extends BasePgService<GisNoflyZoneManager>{

	/**
	 * <p>Title: insertNoflyZoneManager</p>  
	 * <p>Description: 新增禁区</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public GisNoflyZoneManager insertNoflyZoneManager(GisNoflyZoneManager record)throws Exception;
	
	/**
	 * <p>Title: findListByPage</p>  
	 * <p>Description: 分页查询禁区</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public PageInfo<GisNoflyZoneManager> findListByPage(GisNoflyZoneManager record)throws Exception;
	
	/**
	 * <p>Title: updateNoflyZoneManager</p>  
	 * <p>Description: 修改禁区</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public boolean updateNoflyZoneManager(GisNoflyZoneManager record)throws Exception;
	
	/**
	 * <p>Title: delBatch</p>  
	 * <p>Description: 删除禁区</p>  
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean delBatch(Integer ... id)throws Exception;
	
	/**
	 * <p>Title: queryById</p>  
	 * <p>Description: 根据主键ID查询禁区</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public GisNoflyZoneManager queryById(GisNoflyZoneManager record)throws Exception;
}
