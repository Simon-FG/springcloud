package com.bdfint.backend.modules.gis.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdfint.backend.framework.common.BasePgServiceImpl;
import com.bdfint.backend.framework.config.TargetDataSource;
import com.bdfint.backend.modules.gis.bean.GisNoflyZoneManager;
import com.bdfint.backend.modules.gis.mapper.GisNoflyZoneManagerMapper;
import com.bdfint.backend.modules.gis.service.GisNoflyZoneManagerService;
import com.bdfint.backend.modules.sys.utils.UserUtils;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;

/**  
* <p>Title: GisNoflyZoneManagerServiceImpl</p>  
* <p>Description: 禁区管理</p>  
* @author wangchao  
* @date 2018年2月27日  
*/ 
@Service
public class GisNoflyZoneManagerServiceImpl extends BasePgServiceImpl<GisNoflyZoneManager> implements GisNoflyZoneManagerService {

	@Autowired
    private GisNoflyZoneManagerMapper gisNoflyZoneManagerMapper;
	
	/**
	 * <p>Title: insertNoflyZoneManager</p>  
	 * <p>Description: 新增禁区</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional
	@TargetDataSource("pg")
	public GisNoflyZoneManager insertNoflyZoneManager(GisNoflyZoneManager record) throws Exception {
		//获取当前登录用户
		record.setUserId(UserUtils.getUserId());
		record.setCreateTime(new Date());
		gisNoflyZoneManagerMapper.insertNoflyZone(record);
		return record;
	}

	/**
	 * <p>Title: findListByPage</p>  
	 * <p>Description: 分页查询禁区</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@Override
	@TargetDataSource("pg")
	public PageInfo<GisNoflyZoneManager> findListByPage(GisNoflyZoneManager record) throws Exception {
		Example example = new Example(GisNoflyZoneManager.class);
		Example.Criteria criteria = example.or();
		criteria.andEqualTo("userId",UserUtils.getUserId());
		return getPage(record,example);
	}

	/**
	 * <p>Title: updateNoflyZoneManager</p>  
	 * <p>Description: 修改禁区</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional
	@TargetDataSource("pg")
	public boolean updateNoflyZoneManager(GisNoflyZoneManager record) throws Exception {
		boolean bool=false;
		int i=gisNoflyZoneManagerMapper.updateByPrimaryKeySelective(record);
		if(i>0){
			bool=true;
		}
		return bool;
	}

	/**
	 * <p>Title: delBatch</p>  
	 * <p>Description: 删除禁区</p>  
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional
	@TargetDataSource("pg")
	public boolean delBatch(Integer... id) throws Exception {
		boolean bool=false;
		int i=gisNoflyZoneManagerMapper.delBatch(id);
		if(i>0){
			bool=true;
		}
		return bool;
	}

	/**
	 * <p>Title: queryById</p>  
	 * <p>Description: 根据主键ID查询禁区</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@Override
	@TargetDataSource("pg")
	public GisNoflyZoneManager queryById(GisNoflyZoneManager record) throws Exception {
		return gisNoflyZoneManagerMapper.selectByPrimaryKey(record.getId());
	}

}
