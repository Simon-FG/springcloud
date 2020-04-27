package com.bdfint.backend.modules.gis.action;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bdfint.backend.framework.common.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bdfint.backend.modules.gis.bean.GisLayerControl;
import com.bdfint.backend.modules.gis.service.GisLayerControlService;
import com.bdfint.backend.modules.sys.bean.Dict;
import com.bdfint.backend.modules.sys.utils.DictUtils;
import com.github.pagehelper.PageInfo;

/**  
* <p>Title: GisLayerControlAction</p>  
* <p>Description: 图层控制</p>  
* @author wangchao  
* @date 2018年2月27日  
*/ 
@RestController
@RequestMapping(value = "${adminPath}/layerControl")
public class GisLayerControlAction extends BaseAction {

	@Autowired
    private GisLayerControlService gisLayerControlService;
	
	/**
	 * <p>Title: insertLayerControl</p>  
	 * <p>Description: 新增图层控制</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/insertLayerControl",method=RequestMethod.POST)
	public GisLayerControl insertLayerControl(GisLayerControl record) throws Exception{
		return gisLayerControlService.insertLayerControl(record);
	}
	
	/**
	 * <p>Title: findListByPage</p>  
	 * <p>Description: 分页查询图层控制</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/findListByPage",method=RequestMethod.GET)
	public PageInfo<GisLayerControl> findListByPage(GisLayerControl record) throws Exception {
		return gisLayerControlService.findListByPage(record);
	}
	
	/**
	 * <p>Title: updateLayerControl</p>  
	 * <p>Description: 修改图层控制</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateLayerControl",method=RequestMethod.POST)
	public boolean updateLayerControl(GisLayerControl record) throws Exception{
		return gisLayerControlService.updateLayerControl(record);
	}
	
	/**
	 * <p>Title: delBatch</p>  
	 * <p>Description: 删除图层控制</p>  
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/delBatch",method=RequestMethod.DELETE)
	public boolean delBatch(Integer... id) throws Exception {
		return gisLayerControlService.delBatch(id);
	}
	
	/**
	 * <p>Title: queryById</p>  
	 * <p>Description: 根据主键ID查询图层控制</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/queryById",method=RequestMethod.GET)
	public GisLayerControl queryById(GisLayerControl record) throws Exception {
		return gisLayerControlService.queryById(record);
	}
	
	/**
	 * <p>Title: queryTypeDict</p>  
	 * <p>Description: 根据type类型获取图层父节点</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/queryTypeDict",method=RequestMethod.GET)
	public List<Dict> queryTypeDict() throws Exception {
		List<Dict> list= DictUtils.getDictList(GisLayerControl.DICT_TYPE);
		Collections.sort(list, new Comparator<Object>(){  
	        @Override  
	        public int compare(Object o1, Object o2) {  
	        	Dict dict=(Dict)o1;  
	        	Dict newdict=(Dict)o2;  
	            if(dict.getSort()>newdict.getSort()){  
	                return 1;  
	            }else if(dict.getSort()==newdict.getSort()){  
	                return 0;  
	            }else{  
	                return -1;  
	            }  
	        }             
	    }); 
		return list;
	}
	
	/**
	 * <p>Title: queryBylayerGroupIdLayer</p>  
	 * <p>Description: 根据图层组ID查询图层</p>  
	 * @param record
	 * @return
	 */
	@RequestMapping(value="/queryBylayerGroupIdLayer",method=RequestMethod.GET)
	public List<GisLayerControl> queryBylayerGroupIdLayer(GisLayerControl record) throws Exception {
		//获取字典表中的图层类型
		List<Dict> dictlist= DictUtils.getDictList(GisLayerControl.DICT_TYPE_LAYER_TYPE);
		Map<String, String> map=new HashMap<>();
		for (int i = 0; i < dictlist.size(); i++) {
			map.put(dictlist.get(i).getId(), dictlist.get(i).getValue());
		}
		List<GisLayerControl> list= gisLayerControlService.queryBylayerGroupIdLayer(record,map);
		return list;
	}
	
	/**
	 * <p>Title: updateByIdlayerOrderAndlayerDisplay</p>  
	 * <p>Description: 根据主键ID修改图层排序和是否可视</p>  
	 * @param record
	 * @return
	 */
	@RequestMapping(value="/updateByIdlayerOrderAndlayerDisplay",method=RequestMethod.POST)
	public boolean updateByIdlayerOrderAndlayerDisplay(GisLayerControl record) {
		return gisLayerControlService.updateByIdlayerOrderAndlayerDisplay(record);
	}
}
