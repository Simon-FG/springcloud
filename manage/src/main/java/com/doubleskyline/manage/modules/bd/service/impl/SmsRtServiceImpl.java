package com.doubleskyline.manage.modules.bd.service.impl;

import com.doubleskyline.core.service.impl.ServiceImpl;
import com.doubleskyline.manage.modules.bd.entity.SmsRtEntity;
import com.doubleskyline.manage.modules.bd.mapper.SmsRtMapper;
import com.doubleskyline.manage.modules.bd.service.SmsRtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 北斗报文实时
 *
 * @author SIMON
 * @date 2020-04-27 11:38:26
 */
@Service("smsRtService")
@Slf4j
@Transactional
public class SmsRtServiceImpl extends ServiceImpl<SmsRtMapper, SmsRtEntity> implements SmsRtService {

}
