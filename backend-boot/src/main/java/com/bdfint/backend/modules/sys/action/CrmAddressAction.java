package com.bdfint.backend.modules.sys.action;

import com.bdfint.backend.framework.common.BaseAction;
import com.bdfint.backend.framework.util.StringUtils;
import com.bdfint.backend.modules.sys.bean.CrmAddress;
import com.bdfint.backend.modules.sys.service.CrmAddressService;
import com.bdfint.backend.modules.sys.utils.UserUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lsl on 2018/1/12.
 *
 * --------接口--------
     --根据userId查询地址列表(pageNum、pageSize)
     http://localhost:8082/useradmin/address/findListByUserId
     --添加一条收件地址
     http://localhost:8082/useradmin/address/addOne
     --获取一条收件地址(id)
     http://localhost:8082/useradmin/address/getOne
     --编辑一条收件地址
     http://localhost:8082/useradmin/address/editOne
     --设置默认地址(id)
     http://localhost:8082/useradmin/address/setDefaultAddress
     --批量删除(Integer ... id)
     http://localhost:8082/useradmin/address/delById
 */
@RestController
@RequestMapping(value = "${adminPath}/address")
public class CrmAddressAction extends BaseAction {

    @Autowired
    private CrmAddressService crmAddressService;

    /**
     * 根据userId查询地址列表
     * @return
     * @throws Exception
     */
    @RequestMapping("/findListByUserId")
    public PageInfo<CrmAddress> findListByUserId(CrmAddress crmAddress) throws Exception {
        return crmAddressService.findListByPage(crmAddress);
    }

    @RequestMapping("/getOne")
    public CrmAddress getOne(@RequestParam Integer id) throws Exception {
        return crmAddressService.get(id);
    }

    @RequestMapping("/addOne")
    public Boolean addOne(CrmAddress crmAddress) throws Exception {
        crmAddress.setUserId(UserUtils.getUserId());
        String level = crmAddress.getLevel();
        if(StringUtils.isNotBlank(level) && level.equals("1")){
            crmAddressService.setNormalAddress(crmAddress);
        }
        crmAddressService.insert(crmAddress);
        return true;
    }

    @RequestMapping("/editOne")
    public Boolean editOne(CrmAddress crmAddress) throws Exception {
        crmAddress.setUserId(UserUtils.getUserId());
        String level = crmAddress.getLevel();
        if(StringUtils.isNotBlank(level) && level.equals("1")){
            crmAddressService.setNormalAddress(crmAddress);
        }
        crmAddressService.update(crmAddress);
        return true;
    }

    /**
     * 设置默认地址
     * @param crmAddress（userId、id(地址id)）
     * @return
     */
    @RequestMapping("/setDefaultAddress")
    public Boolean setDefaultAddress(CrmAddress crmAddress){
        return crmAddressService.setDefaultAddress(crmAddress);
    }

    @RequestMapping("/delById")
    public Boolean delById(Integer ... id){
        return crmAddressService.delById(id);
    }


}
