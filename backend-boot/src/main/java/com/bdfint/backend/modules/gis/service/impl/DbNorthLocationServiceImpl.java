package com.bdfint.backend.modules.gis.service.impl;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.bdfint.backend.framework.exception.CommonException;
import com.bdfint.backend.framework.util.DateUtils;
import com.bdfint.backend.framework.util.StringUtils;
import com.bdfint.backend.framework.util.excel.ExportExcel;
import com.bdfint.backend.modules.gis.bean.OnLineSumOut;
import com.bdfint.backend.modules.sys.bean.CrmNorthCard;
import com.bdfint.backend.modules.sys.utils.UserUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdfint.backend.framework.common.BasePgServiceImpl;
import com.bdfint.backend.framework.common.Global;
import com.bdfint.backend.framework.config.TargetDataSource;
import com.bdfint.backend.modules.gis.bean.DbNorthLocation;
import com.bdfint.backend.modules.gis.mapper.DbNorthLocationMapper;
import com.bdfint.backend.modules.gis.service.DbNorthLocationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.entity.Example;

/**
* <p>Title: DbNorthLocationServiceImpl</p>  
* <p>Description: 飞行器历史航迹业务处理</p>  
* @author wangchao  
* @date 2018年3月22日
 */
@Service
public class DbNorthLocationServiceImpl extends BasePgServiceImpl<DbNorthLocation> implements DbNorthLocationService {

    @Autowired
    private DbNorthLocationMapper dbNorthLocationMapper;

    /**
	 * <p>Title: findListByPage</p>  
	 * <p>Description: 根据条件分页查询飞行器航迹</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@Override
	@TargetDataSource("pg")
	public PageInfo<DbNorthLocation> findListByPage(DbNorthLocation record) throws Exception {
		int pageSize = record.getPageSize() == null ? Global.PAGE_SIZE : record.getPageSize();
        if (pageSize != -1) {
            PageHelper.startPage(record.getPageNum(), pageSize);
        }
        List<DbNorthLocation> list = dbNorthLocationMapper.queryByParamNorthLocation(record);
        PageInfo<DbNorthLocation> page = new PageInfo<>(list);
        page.setPageNum(record.getPageNum());
        page.setPageSize(pageSize);
        return page;
	}

	@Override
	@TargetDataSource("pg")
	public PageInfo<DbNorthLocation> getList1(DbNorthLocation record) throws Exception {
        Boolean aBoolean = UserUtils.isYWorYY();
        int pageSize = record.getPageSize() == null ? Global.PAGE_SIZE : record.getPageSize();
		if (pageSize != -1) {
			PageHelper.startPage(record.getPageNum(), pageSize);
		}
        ArrayList<String> strings = new ArrayList<>();
        ArrayList<String> s = new ArrayList<>(); //---普通用户卡号列表
        if(!aBoolean){
            s = UserUtils.getCardList();
        }
        String hx = record.getHX();
        if(StringUtils.isNotBlank(hx)){
            if(!aBoolean){
                if(!s.contains(hx)){
                    return new PageInfo<>(new ArrayList<>());
                }
            }
            strings.add(hx);
        }else {
            if(!aBoolean){
                strings = s;
                if(strings.size() == 0){
                    return null;
                }
            }
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("pageNum",record.getPageNum());
        map.put("pageSize",pageSize);
        map.put("startTime",record.getStartTime());
        map.put("endTime",record.getEndTime());
        map.put("list",strings);
        List<DbNorthLocation> list = dbNorthLocationMapper.getList(map);
		PageInfo<DbNorthLocation> page = new PageInfo<>(list);
		page.setPageNum(record.getPageNum());
		page.setPageSize(pageSize);
		return page;
	}

	/**
	 * <p>Title: queryByParamNorthLocation</p>  
	 * <p>Description: 根据条件查询飞行器航迹数据</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@Override
	@TargetDataSource("pg")
	public List<DbNorthLocation> queryByParamNorthLocation(DbNorthLocation record) throws Exception {
		return dbNorthLocationMapper.queryByParamNorthLocation(record);
	}

	/**
	 * <p>Title: exportWithResponse</p>  
	 * <p>Description: 根据条件查询导出飞行器历史航迹数据</p>  
	 * @param record
	 * @throws Exception
	 */
	@Override
	@TargetDataSource("pg")
	public List<DbNorthLocation> exportWithResponse(DbNorthLocation record)throws Exception{
		List<DbNorthLocation> list = dbNorthLocationMapper.queryByParamNorthLocation(record);
		return list;
	}

	@Override
	@TargetDataSource("pg")
	public int getDaySum(DbNorthLocation location){
		return dbNorthLocationMapper.getDaySum(location);
	}

	@Override
    @TargetDataSource("pg")
	public Map getOnLineTimeAndSuccessRate(DbNorthLocation location) throws Exception {
        String hx = location.getHX();
        if(StringUtils.isBlank(hx)){
            throw new CommonException("北斗卡号不能为空！");
        }
        UserUtils.isOwnCard(hx);
        int offLineSum = 0;   //---离线时间总和
//        int offLineTime = Global.getOffLineTime() * 60 * 1000;   //---离线时间（offLineTime时间后视为离线）
        int offLineTime = location.getOffLineTime() * 60 * 1000;   //---离线时间（offLineTime时间后视为离线）
        int bdInterval = Global.getBdInterval() * 1000;     //---北斗上报时间间隔
        int g4Interval = Global.getG4Interval() * 1000;     //---4G上报时间间隔
        int tSize = offLineTime / bdInterval;       //分段大小（分段查询，优化）
        int tSize1 = tSize - 1;
        List<DbNorthLocation> locations = dbNorthLocationMapper.getList1(location);
        int size = locations.size();            //---上报总数
        if(size == 0){
            return new HashMap();
        }
        for(int i=0; i<size; i+=tSize){
            if(i >= size){
                break;
            }
            int i1 = i + tSize1;
            if(i1 >= size){
                i1 = size-1;
            }
            System.out.println(i);
            System.out.println(i1);
            long l = DateUtils.parseDate(locations.get(i).getTE()).getTime() - DateUtils.parseDate(locations.get(i1).getTE()).getTime();
            if(l > offLineTime){
                int jSize = i1 > size ? size-i-1 : tSize-1;
                for(int j=0; j<jSize; j++){
                    long interval = DateUtils.parseDate(locations.get(i + j).getTE()).getTime() - DateUtils.parseDate(locations.get(i + j + 1).getTE()).getTime();
                    if(interval > offLineTime){
                        offLineSum += interval;
                    }
                }
            }
        }

        int bdNum = 0;              //-----------北斗上报数量
        for(DbNorthLocation loc: locations){
            if(loc.getFT().equals("1")){
                bdNum ++;
            }
        }
        int g4Num = size - bdNum;       //--------4G上报数量
        long totalTime = DateUtils.parseDate(locations.get(0).getTE()).getTime() - DateUtils.parseDate(locations.get(size - 1).getTE()).getTime();
        long onLineTime = totalTime - offLineSum;       //-----在线时长
        int bdTime = bdInterval * bdNum;        //------北斗在线时长（成功上报）
        int g4Time = g4Interval * g4Num;        //------4G在线时长（成功上报）
        float successRate = (float)(bdTime + g4Time) / onLineTime;  //-------上报成功率
        HashMap<String, Object> map = new HashMap<>();
        map.put("onLineTime",onLineTime);
        map.put("successRate",successRate);
        map.put("bdTime",bdTime);
        map.put("g4Time",g4Time);
        map.put("bdNum",bdNum);
        map.put("g4Num",g4Num);
        return map;
	}

    @Override
    public List<OnLineSumOut> successRateBatchOut(DbNorthLocation location) throws Exception {
        Boolean aBoolean = UserUtils.isYWorYY();
        if(aBoolean){
            throw new CommonException("管理员不应该调用该接口！");
        }
        ArrayList<String> cardList = UserUtils.getCardList();
        ArrayList<OnLineSumOut> list = new ArrayList<>();
        for(String card: cardList){
            DbNorthLocation loc = new DbNorthLocation();
            loc.setHX(card);
            loc.setStartTime(location.getStartTime());
            loc.setEndTime(location.getEndTime());
            Map map = getOnLineTimeAndSuccessRate(loc);
            OnLineSumOut out = new OnLineSumOut();
            out.setCardId(card);
            out.setOnLineTime(map.get("onLineTime").toString());
            out.setSuccessRate(map.get("successRate").toString());
            out.setBdTime(map.get("bdTime").toString());
            out.setG4Time(map.get("g4Time").toString());
            out.setBdNum(map.get("bdNum").toString());
            out.setG4Num(map.get("g4Num").toString());
            list.add(out);
        }
        return list;
    }


}
