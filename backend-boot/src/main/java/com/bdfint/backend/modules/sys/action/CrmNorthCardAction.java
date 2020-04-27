package com.bdfint.backend.modules.sys.action;

import java.util.List;

import com.bdfint.backend.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bdfint.backend.framework.common.BaseAction;
import com.bdfint.backend.modules.sys.bean.CrmNorthCard;
import com.bdfint.backend.modules.sys.bean.Dict;
import com.bdfint.backend.modules.sys.bean.Vo;
import com.bdfint.backend.modules.sys.service.CrmNorthCardServie;
import com.bdfint.backend.modules.sys.utils.DictUtils;
import com.github.pagehelper.PageInfo;


/**
 * Created by lsl on 2018/1/16.
 * ---------接口-----------
 *      添加(orderId、card必要信息)
 *      http://localhost:8082/admin/northCard/addOne
 *      删除(cardId)
 *      http://localhost:8082/admin/northCard/delOne
 *      根据cardId查询CrmNorthCard详情(cardId)
 *      http://localhost:8082/admin/northCard/getOneByCardId
 *      获取北斗卡分页列表(pageNum、pageSize)
 *      http://localhost:8082/admin/northCard/findListByPage
 *      获取用户相关的北斗卡列表
 *      http://localhost:8082/admin/northCard/findListByUser
 *      北斗卡挂失申请
 *      http://localhost:8082/admin/northCard/lossReq
 *      北斗卡挂失处理
 *      http://localhost:8082/admin/northCard/lossHandle
 *      验证要插入的卡号是否已存在（批量插入）（startId, endId）
 *      http://localhost:8082/admin/northCard/verify
 *      根据北斗卡ID修改信息
 *      http://localhost:8082/admin/updateByCardIdNorthCard
 *
 * ---字典---
 *      获取north_card_type列表
 *      http://localhost:8082/admin/northCard/getCardTypeList
 *      获取north_card_leven列表
 *      http://localhost:8082/admin/northCard/getCardLevenList
 *      获取card_price列表
 *      http://localhost:8082/admin/northCard/getCardPriceList
 *      获取card_operate_price列表
 *      http://localhost:8082/admin/northCard/getCardOperatePriceList
 *      获取north_card_location_type列表
 *      http://localhost:8082/admin/northCard/getLocationType
 *      获取north_card_status列表
 *      http://localhost:8082/admin/northCard/getStatusList
 *
 */
@RestController
@RequestMapping("${adminPath}/northCard")
public class CrmNorthCardAction extends BaseAction{

    @Autowired
    private CrmNorthCardServie crmNorthCardServie;

    /**
     * 根据订单添加北斗卡
     * @param vo
     * @return
     * @throws Exception
     */
    @RequestMapping("/addOne")
    @RequiresRoles(value = {UserUtils.YW_ROLE_ENNAME,UserUtils.YY_ROLE_ENNAME}, logical = Logical.OR)
    public Boolean addOne(@RequestBody Vo vo) throws Exception {
        List<CrmNorthCard> list = vo.getList();
        return crmNorthCardServie.saveOne(vo.getOrderId(), list.toArray(new CrmNorthCard []{}));
    }

    @RequestMapping("/addByAdmin")
    @RequiresRoles(value = {UserUtils.YW_ROLE_ENNAME,UserUtils.YY_ROLE_ENNAME}, logical = Logical.OR)
    public List<String> addByAdmin(CrmNorthCard card) throws Exception {
        card.setFrequentncy(card.getFrequentncy()+"s");
        return crmNorthCardServie.addByAdmin(card);
    }

    @RequestMapping("/delOne")
    @RequiresRoles(value = {UserUtils.YW_ROLE_ENNAME,UserUtils.YY_ROLE_ENNAME}, logical = Logical.OR)
    public Boolean delOne(CrmNorthCard crmNorthCard) throws Exception {
        return crmNorthCardServie.delOne(crmNorthCard);
    }

    /**
     * 根据cardId查询CrmNorthCard详情
     * @param crmNorthCard  (cardId)
     * @return
     * @throws Exception
     */
    @RequestMapping("/getOneByCardId")
    public CrmNorthCard getOneByCardId(CrmNorthCard crmNorthCard) throws Exception {
        return crmNorthCardServie.getByCardId(crmNorthCard);
    }

    @RequestMapping("/findListByPage")
    public PageInfo<CrmNorthCard> findListByPage(CrmNorthCard crmNorthCard) throws Exception {
        return crmNorthCardServie.findListByPage(crmNorthCard);
    }

    @RequestMapping("/findListByUser")
    public List<CrmNorthCard> findListByUser(CrmNorthCard card) throws Exception {
        return crmNorthCardServie.findListByUser(card);
    }

    /**
     * 北斗卡挂失申请
     * @param crmNorthCard （id、note。。。）
     * @return
     * @throws Exception
     */
    @RequestMapping("/lossReq")
    public Boolean lossReq(CrmNorthCard crmNorthCard) throws Exception {
        return crmNorthCardServie.lossReq(crmNorthCard);
    }

    /**
     * 挂失处理
     * @param crmNorthCard（id、note）
     * @return
     * @throws Exception
     */
    @RequestMapping("/lossHandle")
    public Boolean lossHandle(CrmNorthCard crmNorthCard) throws Exception {
        return crmNorthCardServie.lossHandle(crmNorthCard);
    }

    /**
     * 验证要插入的卡号是否已存在（批量插入）
     * @param startId （起始卡号）
     * @param endId （结束卡号）
     * @return 已存在的卡号列表
     */
    @RequestMapping("/verify")
    public List verify(String startId, String endId){
        return crmNorthCardServie.verify(startId, endId);
    }



    @RequestMapping("/getCardTypeList")
    public List<Dict> getCardTypeList(){
        return DictUtils.getDictList("card_type");
    }

    @RequestMapping("/getCardLevenList")
    public List<Dict> getCardLevenList(){
        return DictUtils.getDictList("card_leven");
    }

    @RequestMapping("/getCardPriceList")
    public List<Dict> getCardPriceList(){
        return DictUtils.getDictList("card_price");
    }

    @RequestMapping("/getCardOperatePriceList")
    public List<Dict> getCardOperatePriceList(){
        return DictUtils.getDictList("card_operate_price");
    }

    @RequestMapping("/getLocationType")
    public List<Dict> getLocationTypeList(){
        return DictUtils.getDictList("north_card_location_type");
    }

    @RequestMapping("/getStatusList")
    public List<Dict> getStatusList(){
        return DictUtils.getDictList("north_card_status");
    }

    
    /**
     * <p>Title: findListByUserIdPage</p>  
     * <p>Description: 根据userId分页查询北斗卡信息</p>  
     * @param crmNorthCard
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/findListByUserIdPage",method=RequestMethod.GET)
	public PageInfo<CrmNorthCard> findListByUserIdPage(CrmNorthCard crmNorthCard) throws Exception {
		return crmNorthCardServie.findListByUserIdPage(crmNorthCard);
	}
    
    /**
     * <p>Title: updateByCardIdNorthCard</p>  
     * <p>Description: 根据北斗卡ID修改信息</p>  
     * @param record
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/updateByCardIdNorthCard",method=RequestMethod.POST)
	public boolean updateByCardIdNorthCard(CrmNorthCard record) throws Exception {
        switch (record.getStatus()) {
            case "正常":
                record.setStatus("1");
                break;
            case "已挂失":
                record.setStatus("2");
                break;
            case "停用":
                record.setStatus("4");
                break;
            default:
                record.setStatus("1");
        }
        switch (record.getAppPurpose()) {
            case "油田用途":
                record.setAppPurpose("1");
                break;
            default:
                record.setAppPurpose("0");
        }
        record.setFrequentncy(record.getFrequentncy()+"s");
		return crmNorthCardServie.updateByCardIdNorthCard(record);
	}
    
    /**
     * <p>Title: queryNorthCard</p>  
     * <p>Description: 根据用户ID查询北斗卡及飞行器相关信息</p>  
     * @param crmNorthCard
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/queryNorthCard",method=RequestMethod.GET)
	public PageInfo<CrmNorthCard> queryNorthCard(CrmNorthCard record) throws Exception {
		return crmNorthCardServie.queryNorthCard(record);
	}

    /**
     * 查询北斗卡及终端
     * @param record
     * @return
     */
    @RequestMapping(value="/queryNorthCardAndTerminal")
    public List<CrmNorthCard> queryNorthCardAndTerminal(CrmNorthCard record){
        return crmNorthCardServie.queryNorthCardAndTerminal(record);
    }
}
