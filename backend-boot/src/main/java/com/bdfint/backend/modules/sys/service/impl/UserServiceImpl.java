/*
 * Copyright (c) 2017. <a href="cf">cf</a> All rights reserved.
 */

package com.bdfint.backend.modules.sys.service.impl;

import com.bdfint.backend.framework.common.BaseServiceImpl;
import com.bdfint.backend.framework.common.Global;
import com.bdfint.backend.framework.exception.CommonException;
import com.bdfint.backend.framework.util.Collections3;
import com.bdfint.backend.framework.util.Encodes;
import com.bdfint.backend.framework.util.StringUtils;
import com.bdfint.backend.modules.sys.bean.SysCompanyManager;
import com.bdfint.backend.modules.sys.bean.User;
import com.bdfint.backend.modules.sys.mapper.SysCompanyManagerMapper;
import com.bdfint.backend.modules.sys.mapper.UserMapper;
import com.bdfint.backend.modules.sys.service.UserService;
import com.bdfint.backend.modules.sys.utils.FileUtils;
import com.bdfint.backend.modules.sys.utils.UserUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户service实现类
 *
 * @author lucf
 * @version 2016-01-15 09:53:27
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SysCompanyManagerMapper companyManagerMapper;

    /**
     * 根据登录名查询
     *
     * @param loginName 用户名
     * @return User
     */
    @Override
    public User getUserByLoginName(String loginName) {
        User user = new User();
        user.setDelFlag("0");
        user.setLoginName(loginName);
        return userMapper.selectOne(user);
    }

    @Override
    public List<User> getUserByName(String name) {
        User user = new User();
        user.setName(name);
        return userMapper.select(user);
    }

    /**
     * 根据角色ID获取用户信息
     *
     * @param roleId 角色ID
     * @return List<User>
     */
    @Override
    public List<User> getUserByRoleId(String roleId) {
        return userMapper.selectUserByRoleId(roleId);
    }

    /**
     * 更新当前用户信息
     *
     * @param currentUser 当前用户
     */
    @Override
    @Transactional
    public void updateUserInfo(User currentUser) throws Exception {
        super.update(currentUser);
        UserUtils.clearCache();
        UserUtils.clearCache(currentUser);
    }

    /**
     * 根据机构ID获取用户信息
     *
     * @param officeId 机构ID
     * @return List<User>
     */
    @Override
    public List<User> getUserByOfficeId(String officeId) {
        User user = new User();
        user.setOfficeId(officeId);
        return userMapper.select(user);
    }

    /**
     * 更新用户状态
     */
    @Override
    @Transactional
    public int updateStatus(String ids, User user) throws Exception {
        String[] split = ids.split(",");
        for (String id : split) {
            user.setId(id);
            super.update(user);
            user.setLoginName(get(id).getLoginName());
            // 清除用户缓存
            UserUtils.clearCache(user);
        }
        return ids.length();
    }

    /**
     * 密码初始化
     *
     * @param ids      需要初始化的id
     * @param password 需要初始化的密码
     */
    @Override
    @Transactional
    public int initPassword(String ids, String password) throws Exception {
        String entryptPassword = Encodes.encryptPassword(password);
        User user;
        String[] split = ids.split(",");
        for (String id : split) {
            user = new User();
            user.setId(id);
            user.setPassword(entryptPassword);
            super.update(user);
            user.setLoginName(get(id).getLoginName());
            // 清除用户缓存
            UserUtils.clearCache(user);
        }
        return ids.length();
    }

    /**
     * 保存用户信息
     *
     * @param object User
     */
    @Override
    @Transactional
    public String save(User object) throws Exception {
        if (StringUtils.isNoneEmpty(object.getId())) {
            // 如果新密码为空，则不更换密码
            if (StringUtils.isNotBlank(object.getPassword())) {
                object.setPassword(Encodes.encryptPassword(object.getPassword()));
            } else {
                object.setPassword(null);
            }
            object.preUpdate();
            super.update(object);
            userMapper.deleteUserRoleByUserId(object.getId());
        } else {
            if (StringUtils.isNotBlank(object.getPassword())) {
                object.setPassword(Encodes.encryptPassword(object.getPassword()));
            } else {
                object.setPassword(Encodes.encryptPassword("123456"));
            }
            object.preInsert();
            super.insert(object);
            if(object.getRoleIdList() == null || object.getRoleIdList().size() == 0){
                ArrayList<String> roleList = new ArrayList<>();
                roleList.add("6");
                object.setRoleIdList(roleList);
            }
        }
        //更新用户角色关联
        userMapper.insertUserRole(object);
        // 清除当前用户缓存
        if (object.getLoginName().equals(UserUtils.getUser().getLoginName())) {
            UserUtils.clearCache();
        }
        // 清除用户缓存
        UserUtils.clearCache(object);
        return object.getId();
    }

    /**
     * 删除用户信息（逻辑删除）
     *
     * @param ids 要删除的ID
     */
    @Override
    @Transactional
    public int delete(String ids) throws Exception {
        User user;
        String[] split = ids.split(",");
        for (String id : split) {
            user = new User();
            user.setId(id);
            user.setDelFlag(User.DEL_FLAG_DELETE);
            super.update(user);
            user.setLoginName(get(id).getLoginName());
            // 清除用户缓存
            UserUtils.clearCache(user);
        }
        return ids.length();
    }

    /**
     * 停用用户
     *
     * @param ids 要停用的ID
     */
    @Override
    @Transactional
    public int stop(String ids, String loginFlag) throws Exception {
        User user;
        String[] split = ids.split(",");
        for (String id : split) {
            user = new User();
            user.setId(id);
            user.setLoginFlag(loginFlag);
            super.update(user);
            user.setLoginName(get(id).getLoginName());
            // 清除用户缓存
            UserUtils.clearCache(user);
        }
        return ids.length();
    }

    @Override
    public PageInfo<User> userList(User user){
        int pageSize = user.getPageSize() == 0 ? Global.PAGE_SIZE : user.getPageSize();
        if (pageSize != -1) {
            PageHelper.startPage(user.getPageNum(), pageSize);
        }
        List<User> users = userMapper.selectUserContainCompany(user);
        PageInfo<User> page = new PageInfo<>(users);
        page.setPageNum(user.getPageNum());
        page.setPageSize(pageSize);
        return page;
    }

    @Override
    @Transactional
    public Boolean realNameAuthentication(User user, List<String> list) throws Exception {
        User u = UserUtils.getUser();
        SysCompanyManager info = companyInfo(u.getId());
        if(list != null && list.size() == 2){
            info.setRegUserid(u.getId());
            info.setUploadIdFile1(list.get(0));
            info.setUploadIdFile2(list.get(1));
            info.setRealName(user.getRealName());
            info.setRegTime(new Date());
            info.setIdNomber(user.getIdNomber());
            Integer id1 = info.getId();
            if(id1 == 0){
                companyManagerMapper.insertUseGeneratedKeys(info);
            }else {
                companyManagerMapper.updateByPrimaryKeySelective(info);
            }
            u.setIsConfirm("1");
            updateUserInfo(u);
            return true;
        }else{
            throw new CommonException("请传入身份证正反面“两张”照片！(先“正”后“反”)");
        }
    }

    /**
     *  用户实名认证审核
     * @param isConfirm 认证状态（0:认证失败 1:认证成功）
     * @param userId    用户ID
     */
    @Override
    @Transactional
    public String realNameAudit(String isConfirm,String userId,String content) throws Exception {
        User user = get(userId);
        SysCompanyManager info = companyInfo(userId);
        String s1 = "";
        String s2 = "";
        if(user != null){
            if(isConfirm.equals("1")){
                user.setIsConfirm("5");
                user.setUserType("2");
                s1 = "认证成功！";
                s2 = "2";
            }else {
                user.setIsConfirm("3");
                info.setCause(content);
                s1 = "认证驳回！";
                s2 = "0";
            }
            user.preUpdate();
            update(user);

            info.setRegTime(new Date());
            companyManagerMapper.updateByPrimaryKey(info);

            UserUtils.createPrivateMessage(UserUtils.MSG_TARGET_AUTHEN_USER, s2, userId, content, null, null);
            UserUtils.clearCache(user);  //清空对应的redis缓存
            return s1;
        }
        throw new CommonException("该用户不存在！");
    }

    /**
     * 企业认证申请
     * @param list              资料存储路径
     */
    @Override
    @Transactional
    public Boolean companyAuthentication(User u, List<String> list) throws Exception {
        User user = UserUtils.getUser();
        Example example = new Example(SysCompanyManager.class);
        example.or().andEqualTo("regUserid", user.getId());
        List<SysCompanyManager> sysCompanyManagers = companyManagerMapper.selectByExample(example);
        SysCompanyManager manager;
        if(Collections3.isEmpty(sysCompanyManagers)){
            manager = new SysCompanyManager();
        }else{
            manager = sysCompanyManagers.get(0);
        }
        manager.setCompany(u.getCompanyName());
        manager.setRealName(u.getRealName());
        manager.setTelphone(u.getMobile());
        manager.setRegUserid(user.getId());
        manager.setRegTime(new Date());
        manager.setHandIdcardImg(list.get(0));
        manager.setBusinessLicenceImg(list.get(1));
        manager.setIdNomber(u.getIdNomber());
        if(manager.getId() == 0){
            companyManagerMapper.insertUseGeneratedKeys(manager);
        }else {
            companyManagerMapper.updateByPrimaryKeySelective(manager);
        }

        user.setRealName(manager.getRealName());
        user.setMobile(u.getMobile());
        user.setIsConfirm("2");
        user.setCompanyName(manager.getCompany());
        user.preUpdate();
        updateUserInfo(user);
        return true;
    }

    /**
     * 企业认证审核
     * @param companyId    认证id
     * @param confirm      0:认证失败(退回 -- 填写失败cause)  1：认证成功
     */
    @Override
    @Transactional
    public Boolean companyAudit(Integer companyId, String confirm, String content) throws Exception {
//        SysCompanyManager companyManager = companyManagerMapper.selectByPrimaryKey(companyId);
        Example example = new Example(SysCompanyManager.class);
        example.or().andEqualTo("id",companyId);
        SysCompanyManager companyManager = companyManagerMapper.selectByExample(example).get(0);
        String userid = companyManager.getRegUserid();
        User user = get(userid);
        String s = "";
        if(confirm.equals("1")){
            user.setIsConfirm("6");
            user.setUserType("3");
            s = "2";
        }else {
            companyManager.setCause(content);
            user.setIsConfirm("4");
            user.setCompanyName(null);
            s = "0";
        }
        companyManager.setRegTime(new Date());
        companyManagerMapper.updateByPrimaryKey(companyManager);

        user.preUpdate();
        update(user);

        UserUtils.createPrivateMessage(UserUtils.MSG_TARGET_AUTHEN_COMPANY, s, userid, content, null, null);
        UserUtils.clearCache(user);  //清空对应的redis缓存
        return true;
    }

    @Override
    public SysCompanyManager companyInfo(String userId) {
        String uId = StringUtils.isNotBlank(userId) ? userId : UserUtils.getUserId();
        Example example = new Example(SysCompanyManager.class);
        example.or().andEqualTo("regUserid", uId);
        List<SysCompanyManager> managers = companyManagerMapper.selectByExample(example);
        if(managers.size() > 0){
            return managers.get(0);
        }
        return new SysCompanyManager();
    }

    /**
     * 管理员新增/修改 用户实名、企业认证信息
     * @param companyManager 认证信息实体
     * @return
     */
    @Override
    @Transactional
    public Boolean saveCertified(SysCompanyManager companyManager, boolean updateUser) throws Exception {
        if (StringUtils.isEmpty(companyManager.getRegUserid())) {
            throw new RuntimeException("regUserId不能为空！");
        }
        int id = companyManager.getId();
        companyManager.setRegTime(new Date());
        if (id == 0) {
            companyManagerMapper.insertUseGeneratedKeys(companyManager);
        } else {
            companyManagerMapper.updateByPrimaryKeySelective(companyManager);
        }
        if (updateUser) {// 更新用户信息
            String userid = companyManager.getRegUserid();
            User user = get(userid);
            user.setRealName(companyManager.getRealName());
            user.setIsConfirm(companyManager.getConfirmNum()); //更新认证编号
            user.setCompanyName(companyManager.getCompany());
            user.preUpdate();
            update(user);
        }
        return true;
    }

    @Override
    public User getLoginName(User UserId) {
        return userMapper.selectOne(UserId);
    }
}
