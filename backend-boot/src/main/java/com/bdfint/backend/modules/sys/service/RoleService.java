/*
 * Copyright (c) 2017. <a href="cf">cf</a> All rights reserved.
 */

package com.bdfint.backend.modules.sys.service;


import com.bdfint.backend.framework.common.BaseService;
import com.bdfint.backend.modules.sys.bean.Role;
import com.bdfint.backend.modules.sys.bean.User;

import java.util.List;

/**
 * 系统角色service
 *
 * @author lucf
 * @version 2016-01-15 09:56:22
 */
public interface RoleService extends BaseService<Role> {

    /**
     * 根据用户ID获取角色列表
     *
     * @param userId 用户ID
     * @return List
     */
    List<Role> getRoleByUserId(String userId);

    /**
     * 给角色分配用户
     *
     * @param role Role
     * @param ids  用户ids
     */
    void assignUserToRole(Role role, String ids);

    /**
     * 给角色移除用户
     *
     * @param role Role
     * @param user 用户ids
     */
    boolean outUserInRole(Role role, User user);
}
