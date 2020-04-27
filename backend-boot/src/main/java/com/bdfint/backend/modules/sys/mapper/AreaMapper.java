/*
 * Copyright (c) 2017. <a href="cf">cf</a> All rights reserved.
 */

package com.bdfint.backend.modules.sys.mapper;

import com.bdfint.backend.framework.common.CommonMapper;
import com.bdfint.backend.modules.sys.bean.Area;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author cf
 * @version 2017/2/28
 */
@Mapper
@Repository
public interface AreaMapper extends CommonMapper<Area> {

}
