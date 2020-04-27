package com.bdfint.backend.modules.gis.service;

import com.bdfint.backend.framework.common.BasePgService;
import com.bdfint.backend.modules.gis.bean.GisStyleControl;
import com.github.pagehelper.PageInfo;

/**  
* <p>Title: GisStyleControlService</p>  
* <p>Description: 样式控制</p>  
* @author wangchao  
* @date 2018年2月27日  
*/ 
public interface GisStyleControlService extends BasePgService<GisStyleControl>{

	/**
	 * <p>Title: insertStyleControl</p>  
	 * <p>Description: 新增样式控制</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public GisStyleControl insertStyleControl(GisStyleControl record)throws Exception;
	
	/**
	 * <p>Title: findListByPage</p>  
	 * <p>Description: 分页查询样式控制</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public PageInfo<GisStyleControl> findListByPage(GisStyleControl record)throws Exception;
	
	/**
	 * <p>Title: updateStyleControl</p>  
	 * <p>Description: 修改样式</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public boolean updateStyleControl(GisStyleControl record)throws Exception;
	
	/**
	 * <p>Title: delBatch</p>  
	 * <p>Description: 删除样式</p>  
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean delBatch(Integer ... id)throws Exception;
	
	/**
	 * <p>Title: queryById</p>  
	 * <p>Description: 根据主键ID查询样式</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public GisStyleControl queryById(GisStyleControl record)throws Exception;
}
