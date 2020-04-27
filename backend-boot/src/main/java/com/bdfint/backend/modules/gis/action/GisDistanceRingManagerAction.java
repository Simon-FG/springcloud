package com.bdfint.backend.modules.gis.action;

import java.util.List;

import com.bdfint.backend.framework.common.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bdfint.backend.modules.gis.bean.GisDistanceRingManager;
import com.bdfint.backend.modules.gis.service.GisDistanceRingManagerService;
import com.github.pagehelper.PageInfo;

/**  
* <p>Title: GisDistanceRingManagerAction</p>  
* <p>Description: 距离环管理</p>  
* @author wangchao  
* @date 2018年2月28日  
*/ 
@RestController
@RequestMapping(value = "${adminPath}/distanceRing")
public class GisDistanceRingManagerAction extends BaseAction {
	
	@Autowired
    private GisDistanceRingManagerService gisDistanceRingService;

	/**
	 * <p>Title: insertDistanceRing</p>  
	 * <p>Description: 新增距离环信息</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/insertDistanceRing",method=RequestMethod.POST)
	public GisDistanceRingManager insertDistanceRing(GisDistanceRingManager record) throws Exception{
		return gisDistanceRingService.insertDistanceRing(record);
	}
	
	/**
	 * <p>Title: findListByPage</p>  
	 * <p>Description: 分页查询距离环信息</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/findListByPage",method=RequestMethod.GET)
	public PageInfo<GisDistanceRingManager> findListByPage(GisDistanceRingManager record) throws Exception {
		return gisDistanceRingService.findListByPage(record);
	}
	
	/**
	 * <p>Title: updateDistanceRing</p>  
	 * <p>Description: 修改距离环信息</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateDistanceRing",method=RequestMethod.POST)
	public boolean updateDistanceRing(GisDistanceRingManager record) throws Exception{
		return gisDistanceRingService.updateDistanceRing(record);
	}
	
	/**
	 * <p>Title: delBatch</p>  
	 * <p>Description: 根据主键ID删除距离环信息</p>  
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/delBatch",method=RequestMethod.DELETE)
	public boolean delBatch(Integer... id) throws Exception {
		return gisDistanceRingService.delBatch(id);
	}
	
	/**
	 * <p>Title: queryById</p>  
	 * <p>Description: 根据主键ID查询距离环信息</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/queryById",method=RequestMethod.GET)
	public GisDistanceRingManager queryById(GisDistanceRingManager record) throws Exception {
		return gisDistanceRingService.queryById(record);
	}
	
	/**
	 * <p>Title: queryByParamDistanceRing</p>  
	 * <p>Description: 根据条件查询距离环</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/queryByParamDistanceRing",method=RequestMethod.GET)
	public List<GisDistanceRingManager> queryByParamDistanceRing(GisDistanceRingManager record) throws Exception {
		return gisDistanceRingService.queryByParamDistanceRing(record);
	}
}
