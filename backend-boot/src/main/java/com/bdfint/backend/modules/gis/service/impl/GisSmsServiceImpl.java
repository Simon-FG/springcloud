package com.bdfint.backend.modules.gis.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bdfint.backend.framework.exception.CommonException;
import com.bdfint.backend.framework.util.StringUtils;
import com.bdfint.backend.modules.sys.bean.CrmNorthCard;
import com.bdfint.backend.modules.sys.utils.UserUtils;
import org.apache.commons.net.ntp.TimeStamp;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdfint.backend.framework.common.BasePgServiceImpl;
import com.bdfint.backend.framework.common.Global;
import com.bdfint.backend.framework.config.TargetDataSource;
import com.bdfint.backend.modules.gis.bean.GisSms;
import com.bdfint.backend.modules.gis.mapper.GisSmsMapper;
import com.bdfint.backend.modules.gis.service.GisSmsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.entity.Example;

/**
* <p>Title: GisSmsServiceImpl</p>  
* <p>Description: </p>  
* @author wangchao  
* @date 2018年4月9日
 */
@Service
public class GisSmsServiceImpl extends BasePgServiceImpl<GisSms> implements GisSmsService {

	@Autowired
    private GisSmsMapper gisSmsMapper;
	
	/**
	 * <p>Title: findListByPage</p>  
	 * <p>Description: 分页查询消息</p>  卡号模糊查询
	 * @param sms
	 * @return
	 * @throws Exception
	 */
	@Override
	@TargetDataSource("pg")
	public PageInfo<GisSms> findListByPage(GisSms sms) throws Exception {
        Example example = new Example(GisSms.class);
        Example.Criteria or = example.or();
        Example.Criteria or1 = example.or();
        ArrayList<String> ids = new ArrayList<>();
        Boolean aBoolean = UserUtils.isYWorYY();
        if(!aBoolean){
            ids = UserUtils.getCardList();
        }
        String cardId = sms.getCardId();
        if(StringUtils.isNotBlank(cardId)){
            Boolean contain = false;
            if(!aBoolean){
                for (String id: ids){
                    boolean b = id.startsWith(cardId);
                    if(b){
                        contain = true;
                        break;
                    }
                }
            }
            if(aBoolean || contain){
                or.andLike("cardId",cardId+"%");
                or1.andLike("toCardId",cardId+"%");
            }
        }else {
            if(!aBoolean){
                or.andIn("cardId",ids);
                or1.andIn("toCardId",ids);
            }
        }
        if(StringUtils.isNotBlank(sms.getStartTime())){
            or.andGreaterThanOrEqualTo("sendTime", Timestamp.valueOf(sms.getStartTime()));
            or1.andGreaterThanOrEqualTo("sendTime",Timestamp.valueOf(sms.getStartTime()));
        }
        if(StringUtils.isNotBlank(sms.getEndTime())){
            or.andLessThanOrEqualTo("sendTime",Timestamp.valueOf(sms.getEndTime()));
            or1.andLessThanOrEqualTo("sendTime",Timestamp.valueOf(sms.getEndTime()));
        }
        example.setOrderByClause("send_time desc");
        return getPage(sms,example);
	}

    /**
     * 查询用户下的短报文通信列表
     * @param sms（cardId、toCardId、sendTime）
     * @return page
     * @throws Exception
     */
    @Override
    @TargetDataSource("pg")
	public PageInfo<GisSms> getListByPage(GisSms sms) throws Exception {
        Example example = new Example(GisSms.class);
        Example.Criteria or = example.or();
        Example.Criteria or1 = example.or();
        ArrayList<String> ids = new ArrayList<>();
        Boolean aBoolean = UserUtils.isYWorYY();
        if(!aBoolean){
            ids = UserUtils.getCardList();
        }
        if(StringUtils.isNotBlank(sms.getCardId())){
            if(aBoolean || ids.contains(sms.getCardId())){
                or.andEqualTo("cardId",sms.getCardId());
                or1.andEqualTo("toCardId",sms.getCardId());
            }else{
                throw new CommonException("该北斗卡，卡号："+sms.getCardId()+"不属于你,你没有权限查询！");
            }
        }else {
            if(!aBoolean && ids.size()>0){
                or.andIn("cardId",ids);
                or1.andIn("toCardId",ids);
            }
        }
        if(StringUtils.isNotBlank(sms.getStartTime())){
            or.andGreaterThanOrEqualTo("sendTime",Timestamp.valueOf(sms.getStartTime()));
            or1.andGreaterThanOrEqualTo("sendTime",Timestamp.valueOf(sms.getStartTime()));
        }
        if(StringUtils.isNotBlank(sms.getEndTime())){
            or.andLessThanOrEqualTo("sendTime",Timestamp.valueOf(sms.getEndTime()));
            or1.andLessThanOrEqualTo("sendTime",Timestamp.valueOf(sms.getEndTime()));
        }
        example.setOrderByClause("send_time desc");
        return getPage(sms,example);
    }

	/**
	 * <p>Title: insertGisSms</p>  
	 * <p>Description: 新增消息</p>  
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@Override
	@TargetDataSource("pg")
	public GisSms insertGisSms(GisSms record) throws Exception {
		record.setSendTime(new Date());
		record.setContentType(GisSms.SEND_MSG_TYPE);
		gisSmsMapper.insertGisSms(record);
		return record;
	}

    /**
     * 根据北斗卡号统计当日通信数量
     * @param sms
     * @return
     */
    @Override
    @TargetDataSource("pg")
    public int getDaySum(GisSms sms){
        return gisSmsMapper.getDaySum(sms);
    }


}
