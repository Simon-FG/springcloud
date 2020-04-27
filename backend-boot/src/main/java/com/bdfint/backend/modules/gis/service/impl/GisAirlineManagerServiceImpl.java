package com.bdfint.backend.modules.gis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdfint.backend.framework.common.BasePgServiceImpl;
import com.bdfint.backend.framework.config.TargetDataSource;
import com.bdfint.backend.modules.gis.bean.GisAirlineManager;
import com.bdfint.backend.modules.gis.mapper.GisAirlineManagerMapper;
import com.bdfint.backend.modules.gis.service.GisAirlineManagerService;
import com.bdfint.backend.modules.sys.utils.UserUtils;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;
/**  
* <p>Title: GisAirlineManagerServiceImpl</p>  
* <p>Description: 航线管理</p>  
* @author wangchao  
* @date 2018年2月28日  
*/ 
@Service
public class GisAirlineManagerServiceImpl extends BasePgServiceImpl<GisAirlineManager> implements GisAirlineManagerService {

	@Autowired
    private GisAirlineManagerMapper gisAirlineManagerMapper;

	/**
	 * <p>Title: insertAirlineManager</p>  
	 * <p>Description: 新增航线管理</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional
	@TargetDataSource("pg")
	public GisAirlineManager insertAirlineManager(GisAirlineManager record) throws Exception {
		//获取当前登录用户
		record.setUserId(UserUtils.getUserId());
		gisAirlineManagerMapper.insertAirline(record);
		return record;
	}

	/**
	 * <p>Title: findListByPage</p>  
	 * <p>Description: 分页查询航线管理</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@Override
	@TargetDataSource("pg")
	public PageInfo<GisAirlineManager> findListByPage(GisAirlineManager record) throws Exception {
		Example example = new Example(GisAirlineManager.class);
		Example.Criteria criteria = example.or();
		criteria.andEqualTo("userId",UserUtils.getUserId());
		return getPage(record,example);
	}

	/**
	 * <p>Title: updateAirlineManager</p>  
	 * <p>Description: 修改航线管理</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional
	@TargetDataSource("pg")
	public boolean updateAirlineManager(GisAirlineManager record) throws Exception {
		boolean bool=false;
		int i=gisAirlineManagerMapper.updateByPrimaryKeySelective(record);
		if(i>0){
			bool=true;
		}
		return bool;
	}

	/**
	 * <p>Title: delBatch</p>  
	 * <p>Description: 删除航线管理</p>  
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional
	@TargetDataSource("pg")
	public boolean delBatch(Integer... id) throws Exception {
		boolean bool=false;
		int i=gisAirlineManagerMapper.delBatch(id);
		if(i>0){
			bool=true;
		}
		return bool;
	}

	/**
	 * <p>Title: queryById</p>  
	 * <p>Description: 根据主键ID查询航线管理</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@Override
	@TargetDataSource("pg")
	public GisAirlineManager queryById(GisAirlineManager record) throws Exception {
		return gisAirlineManagerMapper.selectByPrimaryKey(record.getId());
	}

}
