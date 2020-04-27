package com.bdfint.backend.modules.gis.service;

import com.bdfint.backend.framework.common.BasePgService;
import com.bdfint.backend.modules.gis.bean.GisAirportManager;
import com.github.pagehelper.PageInfo;

/**  
* <p>Title: GisAirportManagerService</p>  
* <p>Description: 机场管理</p>  
* @author wangchao  
* @date 2018年2月28日  
*/ 
public interface GisAirportManagerService extends BasePgService<GisAirportManager>{

	/**
	 * <p>Title: insertAirportManager</p>  
	 * <p>Description: 新增机场信息</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public GisAirportManager insertAirportManager(GisAirportManager record)throws Exception;
	
	/**
	 * <p>Title: findListByPage</p>  
	 * <p>Description: 分页查询机场信息</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public PageInfo<GisAirportManager> findListByPage(GisAirportManager record)throws Exception;
	
	/**
	 * <p>Title: updateAirportManager</p>  
	 * <p>Description: 修改机场信息</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public boolean updateAirportManager(GisAirportManager record)throws Exception;
	
	/**
	 * <p>Title: delBatch</p>  
	 * <p>Description: 删除机场信息</p>  
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean delBatch(Integer ... id)throws Exception;
	
	/**
	 * <p>Title: queryById</p>  
	 * <p>Description: 根据主键ID查询机场信息</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public GisAirportManager queryById(GisAirportManager record)throws Exception;
}
