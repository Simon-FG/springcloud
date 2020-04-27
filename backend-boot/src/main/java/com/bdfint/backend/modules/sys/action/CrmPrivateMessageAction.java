package com.bdfint.backend.modules.sys.action;

import com.bdfint.backend.framework.common.BaseAction;
import com.bdfint.backend.modules.sys.bean.CrmPrivateMessage;
import com.bdfint.backend.modules.sys.service.CrmPrivateMessageService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lsl on 2018/1/17.
 *
 * --------接口---------
 *      获取用户未读消息条数
 *      http://localhost:8082/useradmin/privateMessage/getCountNotRead
 *      获取用户未读消息列表
 *      http://localhost:8082/useradmin/privateMessage/findListNotRead
 *      设置已读（Integer ... id）
 *      http://localhost:8082/useradmin/privateMessage/setReaded
 *      批量删除（id=1&id=2...）
 *      http://localhost:8082/useradmin/privateMessage/delById
 *
 */
@RestController
@RequestMapping("{adminPath}/privateMessage")
public class CrmPrivateMessageAction extends BaseAction {

    @Autowired
    private CrmPrivateMessageService crmPrivateMessageService;

    @RequestMapping("/findListNotRead")
    public PageInfo<CrmPrivateMessage> findListNotRead(CrmPrivateMessage crmPrivateMessage) throws Exception {
        return crmPrivateMessageService.findListNotRead(crmPrivateMessage);
    }

    @RequestMapping("/getCountNotRead")
    public int getCountNotRead(CrmPrivateMessage crmPrivateMessage){
        return crmPrivateMessageService.getCountNotRead(crmPrivateMessage);
    }

    @RequestMapping("/findAll")
    public PageInfo<CrmPrivateMessage> findAll(CrmPrivateMessage crmPrivateMessage) throws Exception {
        return crmPrivateMessageService.findAll(crmPrivateMessage);
    }

    @RequestMapping("/setReaded")
    public Boolean setReaded(Integer ... id){
        return crmPrivateMessageService.setReaded(id);
    }

    @RequestMapping("/delById")
    public Boolean delById(Integer ... id){
        return crmPrivateMessageService.delById(id);
    }
}
