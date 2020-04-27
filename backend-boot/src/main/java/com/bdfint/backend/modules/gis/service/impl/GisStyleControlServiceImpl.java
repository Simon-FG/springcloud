package com.bdfint.backend.modules.gis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdfint.backend.framework.common.BasePgServiceImpl;
import com.bdfint.backend.framework.config.TargetDataSource;
import com.bdfint.backend.modules.gis.bean.GisStyleControl;
import com.bdfint.backend.modules.gis.mapper.GisStyleControlMapper;
import com.bdfint.backend.modules.gis.service.GisStyleControlService;
import com.bdfint.backend.modules.sys.utils.UserUtils;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;
/**  
* <p>Title: GisStyleControlServiceImpl</p>  
* <p>Description: 样式控制</p>  
* @author wangchao  
* @date 2018年2月27日  
*/ 
@Service
public class GisStyleControlServiceImpl extends BasePgServiceImpl<GisStyleControl> implements GisStyleControlService {

	@Autowired
    private GisStyleControlMapper gisStyleControlMapper;

	/**
	 * <p>Title: findListByPage</p>  
	 * <p>Description: 分页查询样式控制</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@Override
	@TargetDataSource("pg")
	public PageInfo<GisStyleControl> findListByPage(GisStyleControl record) throws Exception {
		Example example = new Example(GisStyleControl.class);
		Example.Criteria criteria = example.or();
		criteria.andEqualTo("userId",UserUtils.getUserId());
		return getPage(record,example);
	}

	/**
	 * <p>Title: insertStyleControl</p>  
	 * <p>Description: 新增样式控制</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional
	@TargetDataSource("pg")
	public GisStyleControl insertStyleControl(GisStyleControl record) throws Exception {
		//获取当前登录用户ID
		record.setUserId(UserUtils.getUserId());
		gisStyleControlMapper.insertStyleControl(record);
		return record;
	}

	/**
	 * <p>Title: updateStyleControl</p>  
	 * <p>Description: 修改样式</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional
	@TargetDataSource("pg")
	public boolean updateStyleControl(GisStyleControl record) throws Exception{
		boolean bool=false;
		record.setUserId(UserUtils.getUserId());
		int i=gisStyleControlMapper.updateByPrimaryKeySelective(record);
		if(i>0){
			bool=true;
		}
		return bool;
	}

	/**
	 * <p>Title: delBatch</p>  
	 * <p>Description: 删除样式</p>  
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional
	@TargetDataSource("pg")
	public boolean delBatch(Integer... id) throws Exception {
		boolean bool=false;
		int i=gisStyleControlMapper.delBatch(id);
		if(i>0){
			bool=true;
		}
		return bool;
	}

	/**
	 * <p>Title: queryById</p>  
	 * <p>Description: 根据主键ID查询样式</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@Override
	@TargetDataSource("pg")
	public GisStyleControl queryById(GisStyleControl record) throws Exception {
		return gisStyleControlMapper.queryById(record);
	}

}
