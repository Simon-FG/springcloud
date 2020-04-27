package com.bdfint.backend.modules.gis.service;

import java.util.List;
import java.util.Map;

import com.bdfint.backend.framework.common.BasePgService;
import com.bdfint.backend.modules.gis.bean.GisLayerControl;
import com.github.pagehelper.PageInfo;

/**  
* <p>Title: GisLayerControlService</p>  
* <p>Description: 图层控制</p>  
* @author wangchao  
* @date 2018年2月27日  
*/ 
public interface GisLayerControlService extends BasePgService<GisLayerControl>{

	/**
	 * <p>Title: insertLayerControl</p>  
	 * <p>Description: 新增图层控制</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public GisLayerControl insertLayerControl(GisLayerControl record)throws Exception;
	
	/**
	 * <p>Title: findListByPage</p>  
	 * <p>Description: 分页查询图层控制</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public PageInfo<GisLayerControl> findListByPage(GisLayerControl record)throws Exception;
	
	/**
	 * <p>Title: updateLayerControl</p>  
	 * <p>Description: 修改图层控制</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public boolean updateLayerControl(GisLayerControl record)throws Exception;
	
	/**
	 * <p>Title: delBatch</p>  
	 * <p>Description: 删除图层控制</p>  
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean delBatch(Integer ... id)throws Exception;
	
	/**
	 * <p>Title: queryById</p>  
	 * <p>Description: 根据主键ID查询图层控制</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public GisLayerControl queryById(GisLayerControl record)throws Exception;
	
	/**
	 * <p>Title: queryBylayerGroupIdLayer</p>  
	 * <p>Description: 根据图层组ID查询图层</p>  
	 * @param record
	 * @return
	 */
	public List<GisLayerControl> queryBylayerGroupIdLayer(GisLayerControl record,Map<String, String> map)throws Exception;
	
	/**
	 * <p>Title: updateByIdlayerOrderAndlayerDisplay</p>  
	 * <p>Description: 根据主键ID修改图层排序和是否可视</p>  
	 * @param record
	 * @return
	 */
	public boolean updateByIdlayerOrderAndlayerDisplay(GisLayerControl record);
}
