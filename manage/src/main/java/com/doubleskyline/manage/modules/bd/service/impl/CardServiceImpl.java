package com.doubleskyline.manage.modules.bd.service.impl;

import com.doubleskyline.core.service.impl.ServiceImpl;
import com.doubleskyline.manage.modules.bd.entity.CardEntity;
import com.doubleskyline.manage.modules.bd.entity.SosEntity;
import com.doubleskyline.manage.modules.bd.mapper.CardMapper;
import com.doubleskyline.manage.modules.bd.mapper.SosMapper;
import com.doubleskyline.manage.modules.bd.service.CardService;
import com.doubleskyline.manage.modules.bd.service.SosService;
import com.doubleskyline.manage.modules.mq.service.MqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 北斗卡
 *
 * @author Simon
 * @date 2020-03-21 11:38:26
 */
@Service("cardService")
@Slf4j
public class CardServiceImpl extends ServiceImpl<CardMapper, CardEntity> implements CardService {

}
