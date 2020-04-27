package com.bdfint.backend.modules.gis.service;

import java.util.List;

import com.bdfint.backend.framework.common.BasePgService;
import com.bdfint.backend.modules.gis.bean.GisDistanceRingManager;
import com.github.pagehelper.PageInfo;

/**  
* <p>Title: GisDistanceRingManagerService</p>  
* <p>Description: 距离环管理</p>  
* @author wangchao  
* @date 2018年2月28日  
*/ 
public interface GisDistanceRingManagerService extends BasePgService<GisDistanceRingManager>{

	/**
	 * <p>Title: insertDistanceRing</p>  
	 * <p>Description: 新增距离环信息</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public GisDistanceRingManager insertDistanceRing(GisDistanceRingManager record)throws Exception;
	
	/**
	 * <p>Title: findListByPage</p>  
	 * <p>Description: 分页查询距离环信息</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public PageInfo<GisDistanceRingManager> findListByPage(GisDistanceRingManager record)throws Exception;
	
	/**
	 * <p>Title: updateDistanceRing</p>  
	 * <p>Description: 修改距离环信息</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public boolean updateDistanceRing(GisDistanceRingManager record)throws Exception;
	
	/**
	 * <p>Title: delBatch</p>  
	 * <p>Description: 根据主键ID删除距离环信息</p>  
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean delBatch(Integer ... id)throws Exception;
	
	/**
	 * <p>Title: queryById</p>  
	 * <p>Description: 根据主键ID查询距离环信息</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public GisDistanceRingManager queryById(GisDistanceRingManager record)throws Exception;
	
	/**
	 * <p>Title: queryByParamDistanceRing</p>  
	 * <p>Description: 根据条件查询距离环</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public List<GisDistanceRingManager> queryByParamDistanceRing(GisDistanceRingManager record)throws Exception;
}
