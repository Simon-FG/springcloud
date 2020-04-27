package com.bdfint.backend.modules.gis.action;

import java.util.List;

import com.bdfint.backend.framework.common.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bdfint.backend.modules.gis.bean.GisFeaturesManager;
import com.bdfint.backend.modules.gis.service.GisFeaturesManagerService;
import com.github.pagehelper.PageInfo;

/**  
* <p>Title: GisFeaturesManagerAction</p>  
* <p>Description: 标注特征管理</p>  
* @author wangchao  
* @date 2018年2月27日  
*/ 
@RestController
@RequestMapping(value = "${adminPath}/featuresManager")
public class GisFeaturesManagerAction extends BaseAction {

	@Autowired
    private GisFeaturesManagerService gisFeaturesManagerService;
	
	/**
	 * <p>Title: insertFeaturesManager</p>  
	 * <p>Description: 新增标注特征</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/insertFeaturesManager",method=RequestMethod.POST)
	public GisFeaturesManager insertFeaturesManager(GisFeaturesManager record) throws Exception {
		return gisFeaturesManagerService.insertFeaturesManager(record);
	}
	
	/**
	 * <p>Title: findListByPage</p>  
	 * <p>Description: 分页查询标注特征</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/findListByPage",method=RequestMethod.GET)
	public PageInfo<GisFeaturesManager> findListByPage(GisFeaturesManager record) throws Exception {
		return gisFeaturesManagerService.findListByPage(record);
	}
	
	/**
	 * <p>Title: updateFeaturesManager</p>  
	 * <p>Description: 修改标注特征</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateFeaturesManager",method=RequestMethod.POST)
	public boolean updateFeaturesManager(GisFeaturesManager record) throws Exception {
		return gisFeaturesManagerService.updateFeaturesManager(record);
	}
	
	/**
	 * <p>Title: delBatch</p>  
	 * <p>Description: 删除标注特征</p>  
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/delBatch",method=RequestMethod.DELETE)
	public boolean delBatch(Integer... id) throws Exception {
		return gisFeaturesManagerService.delBatch(id);
	}
	
	/**
	 * <p>Title: queryById</p>  
	 * <p>Description: 根据主键ID查询标注特征</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/queryById",method=RequestMethod.GET)
	public GisFeaturesManager queryById(GisFeaturesManager record) throws Exception {
		return gisFeaturesManagerService.queryById(record);
	}
	
	/**
	 * <p>Title: queryBylayerIdFeaturesManager</p>  
	 * <p>Description: 根据图层ID查询标注特征</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/queryBylayerIdFeaturesManager",method=RequestMethod.GET)
	public List<GisFeaturesManager> queryBylayerIdFeaturesManager(GisFeaturesManager record) throws Exception {
		return gisFeaturesManagerService.queryBylayerIdFeaturesManager(record);
	}
}
