package com.bdfint.backend.modules.gis.action;

import java.util.List;

import com.bdfint.backend.framework.common.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bdfint.backend.modules.gis.bean.GisQuickView;
import com.bdfint.backend.modules.gis.service.GisQuickViewService;
import com.github.pagehelper.PageInfo;

/**  
* <p>Title: GisQuickViewAction</p>  
* <p>Description: 快视图</p>  
* @author wangchao  
* @date 2018年2月27日  
*/ 
@RestController
@RequestMapping(value = "${adminPath}/quickView")
public class GisQuickViewAction extends BaseAction {
	
	@Autowired
    private GisQuickViewService gisQuickViewService;
	
	/**
	 * <p>Title: insertQuickView</p>  
	 * <p>Description: 新增快视图</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/insertQuickView",method=RequestMethod.POST)
	public GisQuickView insertQuickView(GisQuickView record) throws Exception {
		return gisQuickViewService.insertQuickView(record);
	}
	
	/**
	 * <p>Title: findListByPage</p>  
	 * <p>Description: 分页查询快视图</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/findListByPage",method=RequestMethod.GET)
	public PageInfo<GisQuickView> findListByPage(GisQuickView record) throws Exception {
		return gisQuickViewService.findListByPage(record);
	}
	
	/**
	 * <p>Title: updateQuickView</p>  
	 * <p>Description: 修改快视图</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateQuickView",method=RequestMethod.POST)
	public boolean updateQuickView(GisQuickView record) throws Exception {
		return gisQuickViewService.updateQuickView(record);
	}
	
	/**
	 * <p>Title: delBatch</p>  
	 * <p>Description: 删除快视图</p>  
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/delBatch",method=RequestMethod.DELETE)
	public boolean delBatch(Integer... id) throws Exception {
		return gisQuickViewService.delBatch(id);
	}
	
	/**
	 * <p>Title: queryById</p>  
	 * <p>Description: 根据主键ID查询快视图</p>  
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/queryById",method=RequestMethod.GET)
	public GisQuickView queryById(@RequestParam Integer id) throws Exception {
		return gisQuickViewService.queryById(id);
	}
	
	/**
	 * <p>Title: queryByPid</p>  
	 * <p>Description: 根据用户ID、是否公开查询快视图</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/queryByUserIdAndWhetherOpen",method=RequestMethod.GET)
	public List<GisQuickView> queryByUserIdAndWhetherOpen(GisQuickView record) throws Exception {
		return gisQuickViewService.queryByUserIdAndWhetherOpen(record);
	}
}
