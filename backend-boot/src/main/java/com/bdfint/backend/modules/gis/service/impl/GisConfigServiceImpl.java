package com.bdfint.backend.modules.gis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdfint.backend.framework.common.BaseIntServiceImpl;
import com.bdfint.backend.modules.gis.bean.GisConfig;
import com.bdfint.backend.modules.gis.mapper.GisConfigMapper;
import com.bdfint.backend.modules.gis.service.GisConfigService;
import com.bdfint.backend.modules.sys.utils.UserUtils;

/**
* <p>Title: GisConfigService</p>  
* <p>Description: 用户低空平台基本设置和通航设置</p>  
* @author wangchao  
* @date 2018年3月21日
 */
@Service
public class GisConfigServiceImpl extends BaseIntServiceImpl<GisConfig> implements GisConfigService{

	@Autowired
    private GisConfigMapper gisConfigMapper;
	
	/**
	 * <p>Title: insertGisConfig</p>  
	 * <p>Description: 新增低空平台基本设置和通航设置</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@Override
	public GisConfig insertGisConfig(GisConfig record) throws Exception {
		//获取当前登录用户
		record.setUserId(UserUtils.getUserId());
		gisConfigMapper.insertGisConfig(record);
		return record;
	}

	/**
	 * <p>Title: updateGisConfig</p>  
	 * <p>Description: 修改低空平台基本设置和通航设置</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@Override
	public Integer updateGisConfig(GisConfig record) throws Exception {
		//获取当前登录用户
		record.setUserId(UserUtils.getUserId());
		return gisConfigMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * <p>Title: queryByUserIdGisConfig</p>  
	 * <p>Description: 根据用户查询低空平台基本设置和通航设置</p>  
	 * @return
	 * @throws Exception
	 */
	@Override
	public GisConfig queryByUserIdGisConfig() throws Exception {
		GisConfig record=new GisConfig();
		//获取当前登录用户
		record.setUserId(UserUtils.getUserId());
		record=gisConfigMapper.queryByUserIdGisConfig(record);
		return record;
	}

}
