package com.bdfint.backend.modules.gis.service;

import java.util.List;

import com.bdfint.backend.framework.common.BasePgService;
import com.bdfint.backend.modules.gis.bean.GisQuickView;
import com.github.pagehelper.PageInfo;

/**  
* <p>Title: GisQuickViewService</p>  
* <p>Description: 快视图</p>  
* @author wangchao  
* @date 2018年2月27日  
*/ 
public interface GisQuickViewService extends BasePgService<GisQuickView>{
	/**
	 * <p>Title: insertQuickView</p>  
	 * <p>Description: 新增快视图</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public GisQuickView insertQuickView(GisQuickView record)throws Exception;
	
	/**
	 * <p>Title: findListByPage</p>  
	 * <p>Description: 分页查询快视图</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public PageInfo<GisQuickView> findListByPage(GisQuickView record)throws Exception;
	
	/**
	 * <p>Title: updateQuickView</p>  
	 * <p>Description: 修改快视图</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public boolean updateQuickView(GisQuickView record)throws Exception;
	
	/**
	 * <p>Title: delBatch</p>  
	 * <p>Description: 删除快视图</p>  
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean delBatch(Integer ... id)throws Exception;
	
	/**
	 * <p>Title: queryById</p>  
	 * <p>Description: 根据主键ID查询快视图</p>  
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public GisQuickView queryById(Integer id)throws Exception;
	
	/**
	 * <p>Title: queryByPid</p>  
	 * <p>Description: 根据用户ID、是否公开查询快视图</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public List<GisQuickView> queryByUserIdAndWhetherOpen(GisQuickView record)throws Exception;
}
