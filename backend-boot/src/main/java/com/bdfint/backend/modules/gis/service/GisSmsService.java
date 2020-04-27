package com.bdfint.backend.modules.gis.service;

import com.bdfint.backend.framework.config.TargetDataSource;
import com.bdfint.backend.modules.gis.bean.GisSms;
import com.github.pagehelper.PageInfo;

/**
* <p>Title: GisSmsService</p>  
* <p>Description: </p>  
* @author wangchao  
* @date 2018年4月9日
 */
public interface GisSmsService {
	
	/**
	 * <p>Title: findListByPage</p>  
	 * <p>Description: 分页查询消息</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public PageInfo<GisSms> findListByPage(GisSms record)throws Exception;

	@TargetDataSource("pg")
	PageInfo<GisSms> getListByPage(GisSms sms) throws Exception;

	/**
	 * <p>Title: insertGisSms</p>  
	 * <p>Description: 新增消息</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public GisSms insertGisSms(GisSms record)throws Exception;

    @TargetDataSource("pg")
    int getDaySum(GisSms sms);

}