package com.bdfint.backend.modules.gis.service.impl;

import java.util.List;

import com.bdfint.backend.framework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdfint.backend.framework.common.BasePgServiceImpl;
import com.bdfint.backend.framework.common.Global;
import com.bdfint.backend.framework.config.TargetDataSource;
import com.bdfint.backend.modules.gis.bean.GisAirportManager;
import com.bdfint.backend.modules.gis.mapper.GisAirportManagerMapper;
import com.bdfint.backend.modules.gis.service.GisAirportManagerService;
import com.github.pagehelper.PageHelper;
//import com.ctc.wstx.util.StringUtil;
import com.github.pagehelper.PageInfo;
/**  
* <p>Title: GisAirportManagerServiceImpl</p>  
* <p>Description: 机场管理</p>  
* @author wangchao  
* @date 2018年2月28日  
*/ 
@Service
public class GisAirportManagerServiceImpl extends BasePgServiceImpl<GisAirportManager> implements GisAirportManagerService {

	@Autowired
    private GisAirportManagerMapper gisAirportManagerMapper;

	/**
	 * <p>Title: insertAirportManager</p>  
	 * <p>Description: 新增机场信息</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional
	@TargetDataSource("pg")
	public GisAirportManager insertAirportManager(GisAirportManager record) throws Exception {
		gisAirportManagerMapper.insertAirport(record);
		return record;
	}

	/**
	 * <p>Title: findListByPage</p>  
	 * <p>Description: 分页查询机场信息</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@Override
	@TargetDataSource("pg")
	public PageInfo<GisAirportManager> findListByPage(GisAirportManager record) throws Exception {
		int pageSize = record.getPageSize() == null ? Global.PAGE_SIZE : record.getPageSize();
        if (pageSize != -1) {
            PageHelper.startPage(record.getPageNum(), pageSize);
        }
		String value = record.getValue();
		if(StringUtils.isNotBlank(value)){
			record.setValue(value.toUpperCase());
		}
        List<GisAirportManager> list = gisAirportManagerMapper.queryByParamGisAirport(record);
        PageInfo<GisAirportManager> page = new PageInfo<>(list);
        page.setPageNum(record.getPageNum());
        page.setPageSize(pageSize);
        return page;
	}

	/**
	 * <p>Title: updateAirportManager</p>  
	 * <p>Description: 修改机场信息</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional
	@TargetDataSource("pg")
	public boolean updateAirportManager(GisAirportManager record) throws Exception {
		this.update(record);
		return true;
	}

	/**
	 * <p>Title: delBatch</p>  
	 * <p>Description: 删除机场信息</p>  
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional
	@TargetDataSource("pg")
	public boolean delBatch(Integer... id) throws Exception {
		boolean bool=false;
		int i=gisAirportManagerMapper.delBatch(id);
		if(i>0){
			bool=true;
		}
		return bool;
	}

	/**
	 * <p>Title: queryById</p>  
	 * <p>Description: 根据主键ID查询机场信息</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@Override
	@TargetDataSource("pg")
	public GisAirportManager queryById(GisAirportManager record) throws Exception {
		return this.get(record.getGid());
	}

}
