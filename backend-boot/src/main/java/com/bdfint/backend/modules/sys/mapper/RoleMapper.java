/*
 * Copyright (c) 2017. <a href="cf">cf</a> All rights reserved.
 */

package com.bdfint.backend.modules.sys.mapper;

import com.bdfint.backend.framework.common.CommonMapper;
import com.bdfint.backend.modules.sys.bean.Role;
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
public interface RoleMapper extends CommonMapper<Role> {

    List<Role> selectRoleByUserId(String userId);

    void deleteRoleMenuByRoleId(String roleId);

    void deleteUserRoleByRoleId(String roleId);

    void insertRoleMenu(@Param("roleId") String roleId, @Param("menuIds") String[] menuIds);

    void outUserInRole(@Param("roleId") String roleId, @Param("userId") String userId);

    void assignUserToRole(@Param("roleId") String roleId, @Param("userIds") String[] userIds);
}
