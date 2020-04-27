package com.bdfint.backend.modules.gis.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdfint.backend.framework.common.BasePgServiceImpl;
import com.bdfint.backend.framework.config.TargetDataSource;
import com.bdfint.backend.modules.gis.bean.GisLayerControl;
import com.bdfint.backend.modules.gis.mapper.GisLayerControlMapper;
import com.bdfint.backend.modules.gis.service.GisLayerControlService;
import com.bdfint.backend.modules.sys.bean.Dict;
import com.bdfint.backend.modules.sys.utils.DictUtils;
import com.bdfint.backend.modules.sys.utils.UserUtils;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;

/**  
* <p>Title: GisLayerControlServiceImpl</p>  
* <p>Description: 图层控制</p>  
* @author wangchao  
* @date 2018年2月27日  
*/ 
@Service
public class GisLayerControlServiceImpl extends BasePgServiceImpl<GisLayerControl> implements GisLayerControlService {

	@Autowired
    private GisLayerControlMapper gisLayerControlMapper;
	
	/**
	 * <p>Title: insertLayerControl</p>  
	 * <p>Description: 新增图层控制</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional
	@TargetDataSource("pg")
	public GisLayerControl insertLayerControl(GisLayerControl record) throws Exception {
		//获取当前登录用户
		record.setUserId(UserUtils.getUserId());//
		record.setLayerType(GisLayerControl.LAYER_TYPE_VECTOR);
		record.setLayerDisplay(GisLayerControl.LAYER_DISPLAY_SO);
		record.setLayerStyleId("0");
		int count=gisLayerControlMapper.queryBylayerGroupIdCount(record);
		record.setLayerOrder(count+1);
		gisLayerControlMapper.insertGisLayerControl(record);
		return record;
	}

	/**
	 * <p>Title: findListByPage</p>  
	 * <p>Description: 分页查询图层控制</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@Override
	@TargetDataSource("pg")
	public PageInfo<GisLayerControl> findListByPage(GisLayerControl record) throws Exception {
		Example example = new Example(GisLayerControl.class);
		Example.Criteria criteria = example.or();
		criteria.andEqualTo("userId",UserUtils.getUserId());
		return getPage(record,example);
	}

	/**
	 * <p>Title: updateLayerControl</p>  
	 * <p>Description: 修改图层控制</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional
	@TargetDataSource("pg")
	public boolean updateLayerControl(GisLayerControl record) throws Exception {
		boolean bool=false;
		int i=gisLayerControlMapper.updateByPrimaryKeySelective(record);
		if(i>0){
			bool=true;
		}
		return bool;
	}

	/**
	 * <p>Title: delBatch</p>  
	 * <p>Description: 删除图层控制</p>  
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional
	@TargetDataSource("pg")
	public boolean delBatch(Integer... id) throws Exception {
		boolean bool=false;
		int i=gisLayerControlMapper.delBatch(id);
		if(i>0){
			bool=true;
		}
		return bool;
	}

	/**
	 * <p>Title: queryById</p>  
	 * <p>Description: 根据主键ID查询图层控制</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@Override
	@TargetDataSource("pg")
	public GisLayerControl queryById(GisLayerControl record) throws Exception {
		return gisLayerControlMapper.selectByPrimaryKey(record.getId());
	}

	/**
	 * <p>Title: queryBylayerGroupIdLayer</p>  
	 * <p>Description: 根据图层组ID查询图层</p>  
	 * @param record
	 * @return
	 */
	@Override
	@TargetDataSource("pg")
	public List<GisLayerControl> queryBylayerGroupIdLayer(GisLayerControl record,Map<String, String> map) throws Exception {
		if(record.getLayerGroupId().equals(GisLayerControl.BASICEDITINGLAYERGROUP_ID) || record.getLayerGroupId().equals(GisLayerControl.AIRCRAFTLAYERGROUP_ID) || record.getLayerGroupId().equals(GisLayerControl.AIRFIELDMAPGROUP_ID) || record.getLayerGroupId().equals(GisLayerControl.OPERATIONLAYERGROUP_ID)){
			record.setUserId(UserUtils.getUserId());//
		}
		List<GisLayerControl> list=gisLayerControlMapper.queryBylayerGroupIdLayer(record);
		for (int j = 0; j < list.size(); j++) {
			list.get(j).setLayerType(map.get(list.get(j).getLayerType()));
		}
		return list;
	}

	/**
	 * <p>Title: updateByIdlayerOrderAndlayerDisplay</p>  
	 * <p>Description: 根据主键ID修改图层排序和是否可视</p>  
	 * @param record
	 * @return
	 */
	@Override
	@TargetDataSource("pg")
	public boolean updateByIdlayerOrderAndlayerDisplay(GisLayerControl record) {
		boolean bool=false;
		int i=gisLayerControlMapper.updateByIdlayerOrderAndlayerDisplay(record);
		if(i>0){
			bool=true;
		}
		return bool;
	}

}
