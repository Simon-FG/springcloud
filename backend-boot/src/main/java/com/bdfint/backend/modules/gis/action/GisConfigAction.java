package com.bdfint.backend.modules.gis.action;

import com.bdfint.backend.framework.common.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bdfint.backend.modules.gis.bean.GisConfig;
import com.bdfint.backend.modules.gis.service.GisConfigService;

@RestController
@RequestMapping(value = "${adminPath}/gisConfig")
public class GisConfigAction extends BaseAction {
	
	@Autowired
    private GisConfigService gisConfigService;
	
	/**
	 * <p>Title: insertGisConfig</p>  
	 * <p>Description: 新增低空平台基本设置和通航设置</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/insertGisConfig",method=RequestMethod.POST)
	public GisConfig insertGisConfig(GisConfig record) throws Exception {
		return gisConfigService.insertGisConfig(record);
	}

	/**
	 * <p>Title: updateGisConfig</p>  
	 * <p>Description: 修改低空平台基本设置和通航设置</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateGisConfig",method=RequestMethod.POST)
	public Integer updateGisConfig(GisConfig record) throws Exception {
		return gisConfigService.updateGisConfig(record);
	}

	/**
	 * <p>Title: queryByUserIdGisConfig</p>  
	 * <p>Description: 根据用户查询低空平台基本设置和通航设置</p>  
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/queryByUserIdGisConfig",method=RequestMethod.GET)
	public GisConfig queryByUserIdGisConfig() throws Exception {
		return gisConfigService.queryByUserIdGisConfig();
	}
}
