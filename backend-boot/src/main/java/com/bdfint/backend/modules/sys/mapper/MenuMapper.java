/*
 * Copyright (c) 2017. <a href="cf">cf</a> All rights reserved.
 */

package com.bdfint.backend.modules.sys.mapper;

import com.bdfint.backend.framework.common.CommonMapper;
import com.bdfint.backend.modules.sys.bean.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author cf
 * @version 2017/2/28
 */
@Mapper
@Repository
public interface MenuMapper extends CommonMapper<Menu> {

    List<Menu> selectMenuByUserId(String userId);

    List<Menu> selectMenuByRoleId(String roleId);

    void deleteRoleMenuByMenuIds(@Param("array") String[] ids);
}
