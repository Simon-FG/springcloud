package com.bdfint.backend.modules.sys.service;

import java.util.List;

import com.bdfint.backend.framework.common.BaseIntService;
import com.bdfint.backend.modules.sys.bean.CrmNorthCard;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lsl on 2018/1/16.
 */
public interface CrmNorthCardServie extends BaseIntService<CrmNorthCard> {

    public Boolean saveOne(String orderId, CrmNorthCard ... crmNorthCard) throws Exception;

    @Transactional
    List<String> addByAdmin(CrmNorthCard card) throws Exception;

    public Boolean delOne(CrmNorthCard crmNorthCard) throws Exception;

    public CrmNorthCard getByCardId(CrmNorthCard crmNorthCard) throws Exception;

    public PageInfo<CrmNorthCard> findListByPage(CrmNorthCard crmNorthCard) throws Exception;

    public List<CrmNorthCard> findListByUser(CrmNorthCard card) throws Exception;

    List<CrmNorthCard> findList();

    public Integer getCountByUser();

    public Boolean lossReq(CrmNorthCard crmNorthCard) throws Exception;

    public Boolean lossHandle(CrmNorthCard crmNorthCard) throws Exception;

    
    /**
     * <p>Title: findListByUserIdPage</p>  
     * <p>Description: 根据userId分页查询北斗卡信息</p>  
     * @param crmNorthCard
     * @return
     * @throws Exception
     */
    public PageInfo<CrmNorthCard> findListByUserIdPage(CrmNorthCard crmNorthCard) throws Exception;
    
    /**
     * <p>Title: updateByCardIdNorthCard</p>  
     * <p>Description: 根据北斗卡ID修改信息</p>  
     * @param record
     * @return
     * @throws Exception
     */
    public boolean updateByCardIdNorthCard(CrmNorthCard record)throws Exception;
    
    /**
     * <p>Title: queryNorthCard</p>  
     * <p>Description: 根据用户ID查询北斗卡及飞行器相关信息</p>  
     * @param crmNorthCard
     * @return
     * @throws Exception
     */
    public PageInfo<CrmNorthCard> queryNorthCard(CrmNorthCard crmNorthCard) throws Exception;

    public List verify(String startId, String endId);

    /**
     * 查询北斗卡及终端
     * @param record
     * @return
     */
    List<CrmNorthCard> queryNorthCardAndTerminal(CrmNorthCard record);

}
