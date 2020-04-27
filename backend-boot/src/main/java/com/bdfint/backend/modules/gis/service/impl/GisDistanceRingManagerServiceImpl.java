package com.bdfint.backend.modules.gis.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdfint.backend.framework.common.BasePgServiceImpl;
import com.bdfint.backend.framework.config.TargetDataSource;
import com.bdfint.backend.framework.util.StringUtils;
import com.bdfint.backend.modules.gis.bean.GisDistanceRingManager;
import com.bdfint.backend.modules.gis.bean.GisStyleControl;
import com.bdfint.backend.modules.gis.mapper.GisDistanceRingManagerMapper;
import com.bdfint.backend.modules.gis.mapper.GisStyleControlMapper;
import com.bdfint.backend.modules.gis.service.GisDistanceRingManagerService;
import com.bdfint.backend.modules.sys.utils.UserUtils;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;
/**  
* <p>Title: GisDistanceRingManagerServiceImpl</p>  
* <p>Description: 距离环管理</p>  
* @author wangchao  
* @date 2018年2月28日  
*/
@Service
public class GisDistanceRingManagerServiceImpl extends BasePgServiceImpl<GisDistanceRingManager> implements GisDistanceRingManagerService {

	@Autowired
    private GisDistanceRingManagerMapper gisDistanceRingManagerMapper;
	
	@Autowired
    private GisStyleControlMapper gisStyleControlMapper;
	
	/**
	 * <p>Title: insertDistanceRing</p>  
	 * <p>Description: 新增距离环信息</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional
	@TargetDataSource("pg")
	public GisDistanceRingManager insertDistanceRing(GisDistanceRingManager record) throws Exception {
		//获取当前登录用户
		record.setUserId(UserUtils.getUserId());
		record.setCreateTime(new Date());
		gisDistanceRingManagerMapper.insertDistanceRing(record);
		return record;
	}

	/**
	 * <p>Title: findListByPage</p>  
	 * <p>Description: 分页查询距离环信息</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@Override
	@TargetDataSource("pg")
	public PageInfo<GisDistanceRingManager> findListByPage(GisDistanceRingManager record) throws Exception {
		Example example = new Example(GisDistanceRingManager.class);
		Example.Criteria criteria = example.or();
		criteria.andEqualTo("userId",UserUtils.getUserId());
		if(StringUtils.isNotBlank(record.getName())){
			criteria.andLike("name", record.getName());
		}
		example.setOrderByClause("id desc");
		return getPage(record,example);
	}

	/**
	 * <p>Title: updateDistanceRing</p>  
	 * <p>Description: 修改距离环信息</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional
	@TargetDataSource("pg")
	public boolean updateDistanceRing(GisDistanceRingManager record) throws Exception {
		boolean bool=false;
		int i=gisDistanceRingManagerMapper.updateByPrimaryKeySelective(record);
		if(i>0){
			bool=true;
		}
		return bool;
	}

	/**
	 * <p>Title: delBatch</p>  
	 * <p>Description: 根据主键ID删除距离环信息</p>  
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional
	@TargetDataSource("pg")
	public boolean delBatch(Integer... id) throws Exception {
		boolean bool=false;
		int i=gisDistanceRingManagerMapper.delBatch(id);
		if(i>0){
			bool=true;
		}
		return bool;
	}

	/**
	 * <p>Title: queryById</p>  
	 * <p>Description: 根据主键ID查询距离环信息</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@Override
	@TargetDataSource("pg")
	public GisDistanceRingManager queryById(GisDistanceRingManager record) throws Exception {
		return gisDistanceRingManagerMapper.selectByPrimaryKey(record.getId());
	}

	/**
	 * <p>Title: queryByParamDistanceRing</p>  
	 * <p>Description: 根据条件查询距离环</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@Override
	@TargetDataSource("pg")
	public List<GisDistanceRingManager> queryByParamDistanceRing(GisDistanceRingManager record) throws Exception {
		//获取当前登录用户
		record.setUserId(UserUtils.getUserId());
		List<GisDistanceRingManager> list=gisDistanceRingManagerMapper.queryByParamDistanceRing(record);
		//循环查询样式ID并查询样式信息
		for (int i = 0; i < list.size(); i++) {
			GisStyleControl style=new GisStyleControl();
			style.setId(list.get(i).getStyleId());
			style=gisStyleControlMapper.queryById(style);
			list.get(i).setGisStyleControl(style);
		}
		return list;
	}

}
