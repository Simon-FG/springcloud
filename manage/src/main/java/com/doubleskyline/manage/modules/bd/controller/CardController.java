package com.doubleskyline.manage.modules.bd.controller;

import com.doubleskyline.core.config.mvc.MultiRequestBody;
import com.doubleskyline.core.controller.BaseController;
import com.doubleskyline.core.model.PageParam;
import com.doubleskyline.core.model.PageResult;
import com.doubleskyline.core.model.R;
import com.doubleskyline.manage.modules.bd.condition.SimplePageParam;
import com.doubleskyline.manage.modules.bd.entity.CardEntity;
import com.doubleskyline.manage.modules.bd.entity.SosEntity;
import com.doubleskyline.manage.modules.bd.mapper.CardMapper;
import com.doubleskyline.manage.modules.bd.service.CardService;
import com.doubleskyline.manage.modules.bd.service.LogService;
import com.doubleskyline.manage.modules.bd.service.SosService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 北斗卡
 *
 * @author Simon
 * @date 2020-03-21 11:38:26
 */
@Api(tags = "北斗卡")
@RestController
@RequestMapping("/")
@Slf4j
public class CardController extends BaseController {

    @Autowired
    private CardService cardService;
    @Autowired
    private LogService logService;

    /**
     * 北斗卡列表查询
     */
    @ApiOperation("北斗卡列表查询接口")
    @PostMapping("/v1/card")
    public R getList(SimplePageParam simplePageParam , CardEntity cardEntity) {
        PageParam pageParam = new PageParam();
        if(null != simplePageParam) {
            pageParam.setPageNum(simplePageParam.getPageNum());
            pageParam.setPageSize(simplePageParam.getPageSize());
        }

        if(logger.isDebugEnabled()){
            logger.debug(String.format("参数 --> sos:{}", cardEntity));
        }
        PageResult<CardEntity> page = cardService.page(pageParam, cardEntity);

        //日志保存
        logService.logSave();
        return R.ok().result(page);
    }

    /**
     * 北斗卡注册
     */
    @ApiOperation("北斗卡注册接口")
    @PostMapping("/v1/regcard")
    public R regCard(CardEntity cardEntity) {
        if(logger.isDebugEnabled()){
            logger.debug(String.format("参数 --> sos:{}", cardEntity));
        }
        cardEntity.setDelFlag("0");
        cardEntity.setCreateTime(new Date());
        cardService.save(cardEntity);
        //日志保存
        logService.logSave();
        return R.ok();
    }

}
