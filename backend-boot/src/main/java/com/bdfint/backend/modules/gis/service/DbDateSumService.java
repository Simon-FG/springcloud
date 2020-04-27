package com.bdfint.backend.modules.gis.service;

import com.bdfint.backend.framework.common.BasePgService;
import com.bdfint.backend.modules.gis.bean.DbDateSum;
import com.bdfint.backend.modules.sys.bean.CrmNorthCard;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by lsl on 2018/4/8.
 */
public interface DbDateSumService extends BasePgService<DbDateSum> {
    Boolean addDaySum(DbDateSum dateSum);

    Boolean addDaySumList(List<DbDateSum> list);

    Map getDaySum(Boolean role,List list)throws Exception;

    PageInfo<DbDateSum> getSumList(DbDateSum sum) throws Exception;
}
