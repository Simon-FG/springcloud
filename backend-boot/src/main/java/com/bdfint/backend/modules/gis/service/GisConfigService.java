package com.bdfint.backend.modules.gis.service;

import com.bdfint.backend.framework.common.BasePgService;
import com.bdfint.backend.modules.gis.bean.GisConfig;
/**
* <p>Title: GisConfigService</p>  
* <p>Description: 用户低空平台基本设置和通航设置</p>  
* @author wangchao  
* @date 2018年3月21日
 */
public interface GisConfigService extends BasePgService<GisConfig>{
	
	/**
	 * <p>Title: insertGisConfig</p>  
	 * <p>Description: 新增低空平台基本设置和通航设置</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public GisConfig insertGisConfig(GisConfig record)throws Exception;
	
	/**
	 * <p>Title: updateGisConfig</p>  
	 * <p>Description: 修改低空平台基本设置和通航设置</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public Integer updateGisConfig(GisConfig record)throws Exception;
	
	/**
	 * <p>Title: queryByUserIdGisConfig</p>  
	 * <p>Description: 根据用户查询低空平台基本设置和通航设置</p>  
	 * @return
	 * @throws Exception
	 */
	public GisConfig queryByUserIdGisConfig()throws Exception;
}
