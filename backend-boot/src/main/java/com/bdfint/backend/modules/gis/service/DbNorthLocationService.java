package com.bdfint.backend.modules.gis.service;

import java.util.List;
import java.util.Map;

import com.bdfint.backend.framework.common.BasePgService;
import com.bdfint.backend.framework.config.TargetDataSource;
import com.bdfint.backend.modules.gis.bean.DbNorthLocation;
import com.bdfint.backend.modules.gis.bean.OnLineSumOut;
import com.github.pagehelper.PageInfo;

/**
* <p>Title: DbNorthLocationService</p>  
* <p>Description: 飞行器历史航迹</p>  
* @author wangchao  
* @date 2018年3月22日
 */
public interface DbNorthLocationService extends BasePgService<DbNorthLocation> {

	/**
	 * <p>Title: findListByPage</p>  
	 * <p>Description: 根据条件分页查询飞行器航迹</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public PageInfo<DbNorthLocation> findListByPage(DbNorthLocation record)throws Exception;

	@TargetDataSource("pg")
	PageInfo<DbNorthLocation> getList1(DbNorthLocation record) throws Exception;

	/**
	 * <p>Title: queryByParamNorthLocation</p>  
	 * <p>Description: 根据条件查询飞行器航迹数据</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public List<DbNorthLocation> queryByParamNorthLocation(DbNorthLocation record)throws Exception;
	
	/**
	 * <p>Title: exportWithResponse</p>  
	 * <p>Description: 根据条件查询导出飞行器历史航迹数据</p>  
	 * @param record
	 * @throws Exception
	 */
	public List<DbNorthLocation> exportWithResponse(DbNorthLocation record)throws Exception;

	@TargetDataSource("pg")
	int getDaySum(DbNorthLocation location);

	Map getOnLineTimeAndSuccessRate(DbNorthLocation location) throws Exception;

	List<OnLineSumOut> successRateBatchOut(DbNorthLocation location) throws Exception;
}
