package com.bdfint.backend.modules.gis.service;

import com.bdfint.backend.framework.common.BasePgService;
import com.bdfint.backend.modules.gis.bean.GisAirlineManager;
import com.github.pagehelper.PageInfo;

/**  
* <p>Title: GisAirlineManagerService</p>  
* <p>Description: 航线管理</p>  
* @author wangchao  
* @date 2018年2月28日  
*/ 
public interface GisAirlineManagerService extends BasePgService<GisAirlineManager>{

	/**
	 * <p>Title: insertAirlineManager</p>  
	 * <p>Description: 新增航线管理</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public GisAirlineManager insertAirlineManager(GisAirlineManager record)throws Exception;
	
	/**
	 * <p>Title: findListByPage</p>  
	 * <p>Description: 分页查询航线管理</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public PageInfo<GisAirlineManager> findListByPage(GisAirlineManager record)throws Exception;
	
	/**
	 * <p>Title: updateAirlineManager</p>  
	 * <p>Description: 修改航线管理</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public boolean updateAirlineManager(GisAirlineManager record)throws Exception;
	
	/**
	 * <p>Title: delBatch</p>  
	 * <p>Description: 删除航线管理</p>  
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean delBatch(Integer ... id)throws Exception;
	
	/**
	 * <p>Title: queryById</p>  
	 * <p>Description: 根据主键ID查询航线管理</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public GisAirlineManager queryById(GisAirlineManager record)throws Exception;
}
