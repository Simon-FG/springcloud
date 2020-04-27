package com.bdfint.backend.modules.gis.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdfint.backend.framework.common.BasePgServiceImpl;
import com.bdfint.backend.framework.config.TargetDataSource;
import com.bdfint.backend.modules.gis.bean.GisFeaturesManager;
import com.bdfint.backend.modules.gis.mapper.GisFeaturesManagerMapper;
import com.bdfint.backend.modules.gis.service.GisFeaturesManagerService;
import com.bdfint.backend.modules.sys.utils.UserUtils;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;
/**  
* <p>Title: GisFeaturesManagerServiceImpl</p>  
* <p>Description: 标注特征管理</p>  
* @author wangchao  
* @date 2018年2月27日  
*/ 
@Service
public class GisFeaturesManagerServiceImpl extends BasePgServiceImpl<GisFeaturesManager> implements GisFeaturesManagerService {

	@Autowired
    private GisFeaturesManagerMapper gisFeaturesManagerMapper;

	/**
	 * <p>Title: insertFeaturesManager</p>  
	 * <p>Description: 新增标注特征</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional
	@TargetDataSource("pg")
	public GisFeaturesManager insertFeaturesManager(GisFeaturesManager record) throws Exception {
		//获取当前登录用户
		record.setUserId(UserUtils.getUserId());//
		gisFeaturesManagerMapper.insertGisFeaturesManager(record);
		return record;
	}

	/**
	 * <p>Title: findListByPage</p>  
	 * <p>Description: 分页查询标注特征</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@Override
	@TargetDataSource("pg")
	public PageInfo<GisFeaturesManager> findListByPage(GisFeaturesManager record) throws Exception {
		Example example = new Example(GisFeaturesManager.class);
		Example.Criteria criteria = example.or();
		criteria.andEqualTo("userId",UserUtils.getUserId());
		return getPage(record,example);
	}

	/**
	 * <p>Title: updateFeaturesManager</p>  
	 * <p>Description: 修改标注特征</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional
	@TargetDataSource("pg")
	public boolean updateFeaturesManager(GisFeaturesManager record) throws Exception {
		boolean bool=false;
		record.setUserId(UserUtils.getUserId());
		int i=gisFeaturesManagerMapper.updateByPrimaryKeySelective(record);
		if(i>0){
			bool=true;
		}
		return bool;
	}

	/**
	 * <p>Title: delBatch</p>  
	 * <p>Description: 删除标注特征</p>  
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional
	@TargetDataSource("pg")
	public boolean delBatch(Integer... id) throws Exception {
		boolean bool=false;
		int i=gisFeaturesManagerMapper.delBatch(id);
		if(i>0){
			bool=true;
		}
		return bool;
	}

	/**
	 * <p>Title: queryById</p>  
	 * <p>Description: 根据主键ID查询标注特征</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@Override
	@TargetDataSource("pg")
	public GisFeaturesManager queryById(GisFeaturesManager record) throws Exception {
		return gisFeaturesManagerMapper.selectByPrimaryKey(record.getId());
	}

	/**
	 * <p>Title: queryBylayerIdFeaturesManager</p>  
	 * <p>Description: 根据图层ID查询标注特征</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@Override
	@TargetDataSource("pg")
	public List<GisFeaturesManager> queryBylayerIdFeaturesManager(GisFeaturesManager record) throws Exception {
		return gisFeaturesManagerMapper.queryBylayerIdFeaturesManager(record);
	}
	
	
}
