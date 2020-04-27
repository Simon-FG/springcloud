package com.bdfint.backend.modules.sys.service.impl;

import com.bdfint.backend.framework.common.BaseIntServiceImpl;
import com.bdfint.backend.framework.common.BaseServiceImpl;
import com.bdfint.backend.framework.common.Global;
import com.bdfint.backend.framework.exception.CommonException;
import com.bdfint.backend.framework.util.DateUtils;
import com.bdfint.backend.framework.util.StringUtils;
import com.bdfint.backend.modules.operator.service.OrderOperatorService;
import com.bdfint.backend.modules.sys.bean.CrmNorthCard;
import com.bdfint.backend.modules.sys.bean.CrmNorthCardRequest;
import com.bdfint.backend.modules.sys.bean.User;
import com.bdfint.backend.modules.sys.mapper.CrmNorthCardMapper;
import com.bdfint.backend.modules.sys.service.*;
import com.bdfint.backend.modules.sys.utils.DictUtils;
import com.bdfint.backend.modules.sys.utils.UserUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

import static org.apache.commons.io.filefilter.FileFilterUtils.or;

/**
 * Created by lsl on 2018/1/16.
 */
@Service
public class CrmNorthCardServiceImpl extends BaseIntServiceImpl<CrmNorthCard> implements CrmNorthCardServie {

    @Autowired
    private CrmAirplaneCardBindService crmAirplaneCardBindService;

    @Autowired
    private FeignService feignService;

    @Autowired
    private CrmNorthCardRequestService crmNorthCardRequestService;

    @Autowired
    private OrderOperatorService orderOperatorService;

    @Autowired
    private CrmNorthCardMapper crmNorthCardMapper;
    

    @Autowired
    private CrmOrderItemService crmOrderItemService;


    @Override
    @Transactional
    public Boolean saveOne(String orderId, CrmNorthCard ... crmNorthCard) throws Exception {
        CrmNorthCardRequest crmNorthCardRequest = crmNorthCardRequestService.getByRequestId(orderId);
        if(crmNorthCardRequest == null){
            throw new CommonException("订单id不存在！");
        }
        for(CrmNorthCard card: crmNorthCard){
            if(StringUtils.isBlank(card.getCardId())){
                throw new CommonException("cardId不能为空！");
            }
            CrmNorthCard byCardId = getByCardId(card);
            if(byCardId != null){
                throw new CommonException("cardId:"+card.getId()+",已存在");
            }
            Date date = new Date();
            card.setStartTime(date);
            card.setEndTime(DateUtils.addDate(date,365));
            card.setStatus("1");

            String cardType = crmNorthCardRequest.getCardType();
            card.setCardType(cardType);
            card.setCardLeven(crmNorthCardRequest.getCardLeven());
            card.setOrderId(orderId);
            card.setUserId(crmNorthCardRequest.getUserId());
            card.setUsageMode(crmNorthCardRequest.getUsageMode());
            card.setUsageArea(crmNorthCardRequest.getUsageArea());
//            card.setFrequentncy(crmNorthCardRequest.getFrequentncy()); //管理员可改变添加的北斗卡频度（与申请中不同）
            String content = crmNorthCardRequest.getContent();
            String[] split = content.split(",");
            if(cardType.equals("1")){
                card.setPrice(500);
                if(split[0].equals("航空用途")){
                    card.setAppPurpose("0");
                    card.setOperatePrice(0);
                }else{
                    card.setAppPurpose("1");
                    card.setOperatePrice(1000);
                }
            }else{
                card.setPrice(300);
                if(split[0].equals("航空用途")){
                    card.setAppPurpose("0");
                    card.setOperatePrice(0);
                }else{
                    card.setAppPurpose("1");
                    card.setOperatePrice(600);
                }
            }
            insert(card);
        }
        Example example = new Example(CrmNorthCard.class);
        Example.Criteria or = example.or();
        or.andEqualTo("orderId",orderId);
        or.andEqualTo("delFlag", "0");
        int i = mapper.selectCountByExample(example);
        Integer total = crmNorthCardRequest.getTotal();
        if(i == total){
            return crmOrderItemService.orderHandle(orderId, "1", crmNorthCardRequest.getUserId());
        }
        return true;
    }

    /**
     * 管理员添加北斗卡
     * @param card
     * @return  未成功添加的卡号列表（数据库中已存在的北斗卡号）
     * @throws Exception
     */
    @Override
    @Transactional
    public List<String> addByAdmin(CrmNorthCard card) throws Exception {
        if (StringUtils.isEmpty(card.getUserId()) || StringUtils.isEmpty(card.getCardId()) || null==card.getNum()) {
            throw new RuntimeException("userId、cardId、num不能为空！");
        }
        Date date = new Date();
        card.setStartTime(date);
        card.setEndTime(DateUtils.addDate(date,365));
        card.setStatus("1");
        if("1".equals(card.getCardType())){
            card.setPrice(500);
            if("航空用途".equals(card.getAppPurpose())){
                card.setAppPurpose("0");
                card.setOperatePrice(0);
            }else{
                card.setAppPurpose("1");
                card.setOperatePrice(1000);
            }
        }else{
            card.setPrice(300);
            if("航空用途".equals(card.getAppPurpose())){
                card.setAppPurpose("0");
                card.setOperatePrice(0);
            }else{
                card.setAppPurpose("1");
                card.setOperatePrice(600);
            }
        }

        ArrayList<String> errorList = new ArrayList<>();
        Integer num = card.getNum();
        String cardId = card.getCardId();
        for (int i = 0; i <num ; i++) {
            card.setCardId((Integer.parseInt(cardId)+i)+"");
            CrmNorthCard byCardId = getByCardId(card);
            if(byCardId != null){
                errorList.add(card.getCardId());
            }else {
                insert(card);
            }
        }
        return errorList;
    }

    @Override
    @Transactional
    public Boolean delOne(CrmNorthCard crmNorthCard) throws Exception {
//        crmNorthCard.setDelFlag("1");
        Example example = new Example(CrmNorthCard.class);
        example.or().andEqualTo("cardId",crmNorthCard.getCardId());
//        int i = mapper.updateByExampleSelective(crmNorthCard, example);
        List<CrmNorthCard> select = mapper.selectByExample(example);
        if(select.size() > 0){
            int i = mapper.deleteByExample(example);
//            if(i == 1){
//                String orderId = select.get(0).getOrderId();
//                String userId = select.get(0).getUserId();
//                return crmOrderItemService.orderHandle(orderId, "0", userId);
//            }
            return true;
        }
        return false;
    }

    @Override
    public CrmNorthCard getByCardId(CrmNorthCard crmNorthCard) throws Exception {
        if(StringUtils.isNotBlank(crmNorthCard.getCardId())){
            Example example = new Example(CrmNorthCard.class);
            Example.Criteria or = example.or();
            or.andEqualTo("cardId",crmNorthCard.getCardId());
            List<CrmNorthCard> list = mapper.selectByExample(example);
            if(list != null && list.size() > 0){
                return list.get(0);
            }
        }
        return null;
    }

    @Override
    public PageInfo<CrmNorthCard> findListByPage(CrmNorthCard crmNorthCard) throws Exception {
        Example example = new Example(CrmNorthCard.class);
        Example.Criteria criteria = example.or();
        if(StringUtils.isNotBlank(crmNorthCard.getCardId())){
            criteria.andEqualTo("cardId",crmNorthCard.getCardId());
        } else {
            if(StringUtils.isNotBlank(crmNorthCard.getOrderId())){
                criteria.andEqualTo("orderId",crmNorthCard.getOrderId());
            }else {
                if(!UserUtils.isYWorYY()){
                    criteria.andEqualTo("userId",UserUtils.getUserId());
                }else {
                    criteria.andEqualTo("userId", crmNorthCard.getUserId());
                }
            }
            if(StringUtils.isNotBlank(crmNorthCard.getCardType())){
                criteria.andEqualTo("cardType",crmNorthCard.getCardType());
            }
            if(StringUtils.isNotBlank(crmNorthCard.getCardLeven())){
                criteria.andEqualTo("cardLeven",crmNorthCard.getCardLeven());
            }
            if(StringUtils.isNotBlank(crmNorthCard.getStatus())) {
                criteria.andEqualTo("status", crmNorthCard.getStatus());
            }
            example.setOrderByClause("start_time desc");
        }
        return getPage(crmNorthCard,example);
    }

    @Override
    public List<CrmNorthCard> findListByUser(CrmNorthCard card) throws Exception {
        Example example = new Example(CrmNorthCard.class);
        Example.Criteria or = example.or();
        String userId = UserUtils.getUserId();
        if(!UserUtils.isYWorYY()){
            or.andEqualTo("userId",userId);
        }
        if(StringUtils.isNotBlank(card.getCardId())){
            or.andLike("cardId", card.getCardId()+"%");
        }
        or.andNotEqualTo("delFlag","1");
        example.setOrderByClause("start_time desc");
        return mapper.selectByExample(example);
    }

    @Override
    public List<CrmNorthCard> findList(){
        Example example = new Example(CrmNorthCard.class);
        Example.Criteria or = example.or();
        or.andNotEqualTo("delFlag","1");
        return mapper.selectByExample(example);
    }

    @Override
    public Integer getCountByUser() {
        String userId = UserUtils.getUserId();
        Example example = new Example(CrmNorthCard.class);
        Example.Criteria or = example.or();
        or.andEqualTo("userId",userId);
        return mapper.selectCountByExample(example);
    }

    @Override
    @Transactional
    public Boolean lossReq(CrmNorthCard crmNorthCard) throws Exception {
        crmNorthCard.setStatus("2");
        crmNorthCard.setNoteTime(new Date());
        Example example = new Example(CrmNorthCard.class);
        example.or().andEqualTo("cardId",crmNorthCard.getCardId());
        mapper.updateByExampleSelective(crmNorthCard,example);
        return true;
    }

    @Override
    @Transactional
    public Boolean lossHandle(CrmNorthCard crmNorthCard) throws Exception {
        crmNorthCard.setStatus("4");
        Example example = new Example(CrmNorthCard.class);
        example.or().andEqualTo("cardId",crmNorthCard.getCardId());
        mapper.updateByExampleSelective(crmNorthCard,example);
        crmAirplaneCardBindService.changeBind(crmNorthCard.getCardId(),crmNorthCard.getNote());
        return true;
    }


    /**
     * <p>Title: findListByUserIdPage</p>  
     * <p>Description: 根据userId分页查询北斗卡信息</p>  
     * @param crmNorthCard
     * @return
     * @throws Exception
     */
	@Override
	public PageInfo<CrmNorthCard> findListByUserIdPage(CrmNorthCard crmNorthCard) throws Exception {
		Example example = new Example(CrmNorthCard.class);
        Example.Criteria criteria = example.or();
        criteria.andEqualTo("userId",UserUtils.getUserId()).andNotEqualTo("delFlag","1");
        example.setOrderByClause("start_time desc");
		return getPage(crmNorthCard,example);
	}

	/**
     * <p>Title: updateByCardIdNorthCard</p>  
     * <p>Description: 根据北斗卡ID修改信息</p>  
     * @param record
     * @return
     * @throws Exception
     */
	@Override
	public boolean updateByCardIdNorthCard(CrmNorthCard record) throws Exception {
		return crmNorthCardMapper.updateByCardIdNorthCard(record);
	}

    /**
     * <p>Title: queryNorthCard</p>  
     * <p>Description: 根据用户ID查询北斗卡及飞行器相关信息</p>  
     * @param record
     * @return
     * @throws Exception
     */
	@Override
	public PageInfo<CrmNorthCard> queryNorthCard(CrmNorthCard record) throws Exception {
		//设置当前登录用户
		record.setUserId(UserUtils.getUserId());
		int pageSize = record.getPageSize() == 0 ? 10 : record.getPageSize();
        if (pageSize != -1) {
            PageHelper.startPage(record.getPageNum(), pageSize);
        }
        List<CrmNorthCard> list = crmNorthCardMapper.queryNorthCard(record);
        PageInfo<CrmNorthCard> page = new PageInfo<>(list);
        page.setPageNum(record.getPageNum());
        page.setPageSize(pageSize);
        return page;
	}


    @Override
    public List verify(String startId, String endId) {
        int start = Integer.parseInt(startId);
        int end = Integer.parseInt(endId);
        List<String> existList = new ArrayList<>();
        while (start <= end){
            String s = String.valueOf(start);
            Example example = new Example(CrmNorthCard.class);
            example.or().andEqualTo("cardId",s);
            int i = mapper.selectCountByExample(example);
            if(i>0){
                existList.add(s);
            }
            start ++ ;
        }
        return existList;
    }

    @Override
    public List<CrmNorthCard> queryNorthCardAndTerminal(CrmNorthCard record) {
        if("1".equals(UserUtils.getUserId()) || "5851e9744bcd4eaebb167c9c23cd3976".equals(UserUtils.getUserId())){
            record.setUserId("");
        }
        record.setDelFlag("0");

        //查询符合条件的所有卡信息及终端信息
        List<CrmNorthCard> crmNorthCards = crmNorthCardMapper.queryNorthCardAndTerminal(record);

        //获取最新的终端位置
        Map map = null;

        try {
            map = feignService.getLocCache();
        }catch (Exception e){
            return crmNorthCards;
        }

        if(map != null){
            //轮询卡信息
            for(Object key : map.keySet()){
                for (int i = 0; i < crmNorthCards.size(); i++) {
                    //如果缓存中的卡Id 等于 查出来的信息
                    if(key.toString().equals(crmNorthCards.get(i).getCardId())){
                        Map value = (Map)map.get(key);
                        Object lon = value.get("lon");
                        Object lat = value.get("lat");
                        crmNorthCards.get(i).setLon(lon.toString());
                        crmNorthCards.get(i).setLat(lat.toString());
                    }
                }
            }
        }

        return crmNorthCards;
    }

}
