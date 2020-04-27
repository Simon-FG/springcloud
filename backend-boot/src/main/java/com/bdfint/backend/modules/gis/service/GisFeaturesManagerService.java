package com.bdfint.backend.modules.gis.service;

import java.util.List;

import com.bdfint.backend.framework.common.BasePgService;
import com.bdfint.backend.modules.gis.bean.GisFeaturesManager;
import com.github.pagehelper.PageInfo;

/**  
* <p>Title: GisFeaturesManagerService</p>  
* <p>Description: 标注特征管理</p>  
* @author wangchao  
* @date 2018年2月27日  
*/ 
public interface GisFeaturesManagerService extends BasePgService<GisFeaturesManager>{

	/**
	 * <p>Title: insertFeaturesManager</p>  
	 * <p>Description: 新增标注特征</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public GisFeaturesManager insertFeaturesManager(GisFeaturesManager record)throws Exception;
	
	/**
	 * <p>Title: findListByPage</p>  
	 * <p>Description: 分页查询标注特征</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public PageInfo<GisFeaturesManager> findListByPage(GisFeaturesManager record)throws Exception;
	
	/**
	 * <p>Title: updateFeaturesManager</p>  
	 * <p>Description: 修改标注特征</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public boolean updateFeaturesManager(GisFeaturesManager record)throws Exception;
	
	/**
	 * <p>Title: delBatch</p>  
	 * <p>Description: 删除标注特征</p>  
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean delBatch(Integer ... id)throws Exception;
	
	/**
	 * <p>Title: queryById</p>  
	 * <p>Description: 根据主键ID查询标注特征</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public GisFeaturesManager queryById(GisFeaturesManager record)throws Exception;
	
	/**
	 * <p>Title: queryBylayerIdFeaturesManager</p>  
	 * <p>Description: 根据图层ID查询标注特征</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public List<GisFeaturesManager> queryBylayerIdFeaturesManager(GisFeaturesManager record)throws Exception;
}
