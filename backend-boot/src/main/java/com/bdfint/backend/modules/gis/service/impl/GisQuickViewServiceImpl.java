package com.bdfint.backend.modules.gis.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdfint.backend.framework.common.BasePgServiceImpl;
import com.bdfint.backend.framework.config.TargetDataSource;
import com.bdfint.backend.modules.gis.bean.GisQuickView;
import com.bdfint.backend.modules.gis.mapper.GisQuickViewMapper;
import com.bdfint.backend.modules.gis.service.GisQuickViewService;
import com.bdfint.backend.modules.sys.utils.UserUtils;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;

/**  
* <p>Title: GisQuickViewServiceImpl</p>  
* <p>Description: 快视图</p>  
* @author wangchao  
* @date 2018年2月27日  
*/
@Service
public class GisQuickViewServiceImpl extends BasePgServiceImpl<GisQuickView> implements GisQuickViewService {

	@Autowired
    private GisQuickViewMapper gisQuickViewMapper;

	/**
	 * <p>Title: insertQuickView</p>  
	 * <p>Description: 新增快视图</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional
	@TargetDataSource("pg")
	public GisQuickView insertQuickView(GisQuickView record) throws Exception {
		//获取当前登录用户
		record.setUserId(UserUtils.getUserId());//
		record.setCreateTime(new Date());
		gisQuickViewMapper.insertQuickView(record);
		return record;
	}

	/**
	 * <p>Title: findListByPage</p>  
	 * <p>Description: 分页查询快视图</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@Override
	@TargetDataSource("pg")
	public PageInfo<GisQuickView> findListByPage(GisQuickView record) throws Exception {
		Example example = new Example(GisQuickView.class);
		Example.Criteria criteria = example.or();
		criteria.andEqualTo("userId",UserUtils.getUserId());
		return getPage(record,example);
	}

	/**
	 * <p>Title: updateQuickView</p>  
	 * <p>Description: 修改快视图</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional
	@TargetDataSource("pg")
	public boolean updateQuickView(GisQuickView record) throws Exception {
		boolean bool=false;
		int i=gisQuickViewMapper.updateByPrimaryKeySelective(record);
		if(i>0){
			bool=true;
		}
		return bool;
	}

	/**
	 * <p>Title: delBatch</p>  
	 * <p>Description: 删除快视图</p>  
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional
	@TargetDataSource("pg")
	public boolean delBatch(Integer... id) throws Exception {
		boolean bool=false;
		int i=gisQuickViewMapper.delBatch(id);
		if(i>0){
			bool=true;
		}
		return bool;
	}

	/**
	 * <p>Title: queryById</p>  
	 * <p>Description: 根据主键ID查询快视图</p>  
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	@TargetDataSource("pg")
	public GisQuickView queryById(Integer id) throws Exception {
		return gisQuickViewMapper.queryById(id);
	}

	/**
	 * <p>Title: queryByPid</p>  
	 * <p>Description: 根据用户ID、是否公开查询快视图</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@Override
	@TargetDataSource("pg")
	public List<GisQuickView> queryByUserIdAndWhetherOpen(GisQuickView record) throws Exception {
		//获取当前登录用户
		record.setUserId(UserUtils.getUserId());
		List<GisQuickView> qvlist=new ArrayList<GisQuickView>();
		if(record.getInit().equals("1")){
			record.setWhetherOpen(GisQuickView.WHETHER_OPEN_IS);
			qvlist=gisQuickViewMapper.queryByUserIdAndWhetherOpen(record);
		}else{
			qvlist=gisQuickViewMapper.queryByUserIdAndInit(record);
		}
		return qvlist;
	}


}
