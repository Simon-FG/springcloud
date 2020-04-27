/*
 * Copyright (c) 2017. <a href="cf">cf</a> All rights reserved.
 */

package com.bdfint.backend.modules.sys.utils;

import com.bdfint.backend.framework.cache.JedisUtils;
import com.bdfint.backend.framework.common.CommonMapper;
import com.bdfint.backend.framework.common.SpringContextHolder;
import com.bdfint.backend.framework.config.TargetDataSource;
import com.bdfint.backend.framework.exception.CommonException;
import com.bdfint.backend.framework.security.SecurityRealm;
import com.bdfint.backend.framework.servlet.ValidateCodeServlet;
import com.bdfint.backend.framework.util.CookieUtils;
import com.bdfint.backend.framework.util.Servlets;
import com.bdfint.backend.framework.util.StringUtils;
import com.bdfint.backend.modules.sys.action.RegisterAction;
import com.bdfint.backend.modules.sys.bean.*;
import com.bdfint.backend.modules.sys.mapper.CrmPrivateMessageMapper;
import com.bdfint.backend.modules.sys.service.*;
import com.google.common.collect.Lists;

import org.apache.shiro.authc.AuthenticationException;
import tk.mybatis.mapper.entity.Example;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户工具类
 *
 * @author cf
 * @version 2016/7/28
 */
public class UserUtils {

    private static UserService userService = SpringContextHolder.getBean(UserService.class);
    private static RoleService roleService = SpringContextHolder.getBean(RoleService.class);
    private static MenuService menuService = SpringContextHolder.getBean(MenuService.class);
    private static AreaService areaService = SpringContextHolder.getBean(AreaService.class);
    private static OfficeService officeService = SpringContextHolder.getBean(OfficeService.class);
    private static CrmNorthCardServie cardServie = SpringContextHolder.getBean(CrmNorthCardServie.class);

    public static final String USER_CACHE_ = "userCache_";
    public static final String USER_CACHE_ID_ = "id_";
    public static final String USER_CACHE_LOGIN_NAME_ = "loginName_";

    public static final String CACHE_ROLE_LIST = "roleList";
    public static final String CACHE_MENU_LIST = "menuList";
    public static final String CACHE_AREA_LIST = "areaList";
    public static final String CACHE_OFFICE_LIST = "officeList";
    public static final String CACHE_OFFICE_ALL_LIST = "officeAllList";
    public static final String CARD_LIST = "card_list";

    public static final String YY_ROLE_ID = "5851e9744bcd4eaebb167c9c23cd3976";
    public static final String YW_ROLE_ID = "1";
    public static final String YY_ROLE_ENNAME = "opertaion";
    public static final String YW_ROLE_ENNAME = "operation   devops";

    public static final String DEL_FLAG = "setDelFlag";//更新删除
    public static final String DEL_VAL = "1";//删除值(北斗卡绑定status为1执行已绑定,飞机尾号绑定值,飞行器申请未审核)
    
    public static final String ORDER_STATUS = "setStatus";//更新删除
    public static final String STATUS_VALb = "2";//订单已审核(北斗卡已绑定)/审核通过
    public static final String STATUS_VALc = "3";//订单已完成(订单处理完成)
    public static final String STATUS_VALd = "0";//已退回(北斗卡已解绑/私有消息未读/机尾号解绑/飞行器审核通过)/审核未通过
    
    public static final String CARD_BIND_SUCCESS = "北斗卡已关联成功";
    public static final String CARD_CHANGE_SUCCESS = "北斗卡已变更成功";
    public static final String CARD_BIND_CANCEL = "北斗卡已解绑";
    
    public static final String ORDER_TYPE_CARD = "1";//北斗卡购买申请
    public static final String ORDER_TYPE_HARDWARE = "2";//硬件购买申请
    public static final String ORDER_TYPE_SMS = "3";//增值服务购买申请
    public static final String ORDER_TYPE_SMSCHANGE = "4";//转网购买申请

    public static final String ORDER_OPERATOR_NEW = "新订单";
    public static final String ORDER_OPERATOR_PASS = "审核通过";
    public static final String ORDER_OPERATOR_FINISH = "已完成";
    public static final String ORDER_OPERATOR_OUT = "已退回";
    public static final String ORDER_OPERATOR_DELETE = "订单已删除";


    public static final String ORDER_OPERATOR_PUTCARD = "已发卡";
    public static final String ORDER_OPERATOR_BOUNDCARD = "已关联";

    public static final String ORDER_OPERATOR_SENDED = "已发货";
    public static final String ORDER_OPERATOR_ACCEPT  = "已受理";
    
    public static final String REQUEST_KEY = "1";//更新用户申请表
    public static final String ORDER_KEY = "2";//更新订单表

    //-------私有消息--------
    public static final String MSG_TARGET_CARD = "北斗卡申请订单";
    public static final String MSG_TARGET_HARDWARE = "设备申请订单";
    public static final String MSG_TARGET_CARD_ZW = "北斗卡转网申请订单";
    public static final String MSG_TARGET_ZZ = "增值业务申请订单";
    public static final String MSG_TARGET_AUTHEN_USER = "用户实名认证申请";
    public static final String MSG_TARGET_AUTHEN_COMPANY = "用户企业认证申请";


    public static User addCertifiedInfo(User user){
        SysCompanyManager info = userService.companyInfo(user.getId());
        if(info != null){
            user.setRealName(info.getRealName());
            user.setIdNomber(info.getIdNomber());
            user.setUploadIdFile1(info.getUploadIdFile1());
            user.setUploadIdFile2(info.getUploadIdFile2());
            user.setCompanyName(info.getCompany());
            user.setHandIdcardImg(info.getHandIdcardImg());
            user.setBusinessLicenceImg(info.getBusinessLicenceImg());
            user.setCause(info.getCause());
        }
        return user;
    }


    /**
     * 根据ID获取用户
     *
     * @param id 用户ID
     * @return 取不到返回null
     */
    public static User get(String id) {
        User user = (User) JedisUtils.getObject(USER_CACHE_ + USER_CACHE_ID_ + id);
        if (user == null) {
            try {
                user = userService.get(id);
                if (user != null) {
                    user.setRoleList(roleService.getRoleByUserId(id));
                    user = addCertifiedInfo(user);
                    JedisUtils.setObject(USER_CACHE_ + USER_CACHE_ID_ + id, user, 0);
                }
            } catch (Exception e) {
                return null;
            }
        }
        return user;
    }

    /**
     * 根据登录名获取用户
     *
     * @param loginName 登录名
     * @return 取不到返回null
     */
    public static User getUserByLoginName(String loginName) {
        User user = (User) JedisUtils.getObject(USER_CACHE_ + USER_CACHE_LOGIN_NAME_ + loginName);
        if (user == null) {
            user = userService.getUserByLoginName(loginName);
            if (user == null) {
                return null;
            }
            user.setRoleList(roleService.getRoleByUserId(user.getId()));
            user = addCertifiedInfo(user);
            JedisUtils.setObject(USER_CACHE_ + USER_CACHE_LOGIN_NAME_ + loginName, user, 0);
        }
        return user;
    }

    /**
     * 获取当前用户
     *
     * @return 取不到返回 new User()
     */
    public static User getUser() {
        SecurityRealm.Principal principal = getPrincipal();
        if (principal != null) {
            User user = get(principal.getId());
            if (user != null) {
                user = addCertifiedInfo(user);
                return user;
            }
            return new User();
        }
        return new User();
    }

    /**
     * 获取当前用户角色列表
     *
     * @return 角色列表
     */
    public static List<Role> getRoleList() {
        @SuppressWarnings("unchecked")
        List<Role> roleList = (List<Role>) getCache(CACHE_ROLE_LIST);
        try {
            if (roleList == null) {
                User user = getUser();
                if (user.isAdmin()) {
                    roleList = roleService.getList(new Role());
                } else {
                    roleList = roleService.getRoleByUserId(getUserId());
                }
                putCache(CACHE_ROLE_LIST, roleList);
            }
        } catch (Exception e) {
            roleList = new ArrayList<>();
        }
        return roleList;
    }

    /**
     * 获取当前用户授权菜单
     *
     * @return 权限菜单列表
     */
    public static List<Menu> getMenuList() {
        @SuppressWarnings("unchecked")
        List<Menu> menuList = (List<Menu>) getCache(CACHE_MENU_LIST);
        try {
            if (menuList == null) {
                User user = getUser();
                if (user.isAdmin()) {
                    Menu Menu = new Menu();
                    menuList = menuService.getList(Menu);
                } else {
                    menuList = menuService.getMenuByUserId(getUserId());
                }
                putCache(CACHE_MENU_LIST, menuList);
            }
        } catch (Exception e) {
            menuList = new ArrayList<>();
        }
        List<Menu> list = Lists.newArrayList();
        Menu.sort(menuList);
        Menu.sortList(list, menuList, Menu.getRootId(), true);
        return list;
    }

    /**
     * 获取当前用户授权的区域
     *
     * @return
     */
    public static List<Area> getAreaList() {
        @SuppressWarnings("unchecked")
        List<Area> areaList = (List<Area>) getCache(CACHE_AREA_LIST);
        try {
            if (areaList == null) {
                areaList = areaService.getList(new Area());
                putCache(CACHE_AREA_LIST, areaList);
            }
        } catch (Exception e) {
            areaList = new ArrayList<>();
        }
        return areaList;
    }

    /**
     * 获取当前用户有权限访问的部门
     */
    public static List<Office> getOfficeList() {
        @SuppressWarnings("unchecked")
        List<Office> officeList = (List<Office>) getCache(CACHE_OFFICE_LIST);
        try {
            if (officeList == null) {
                User user = getUser();
                if (user.isAdmin()) {
                    officeList = officeService.getList(new Office());
                } else {
                    officeList = officeService.getOfficeByUserId(getUserId());
                }
                putCache(CACHE_OFFICE_LIST, officeList);
            }
        } catch (Exception e) {
            officeList = new ArrayList<>();
        }
        return officeList;
    }

    /**
     * 获取授权主要对象
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 获取当前登录者ID
     *
     * @return 取不到返回 new User()
     */
    public static String getUserId() {
        SecurityRealm.Principal principal = getPrincipal();
//        return principal.getId();
        return principal != null ? principal.getId() : null;
    }

    /**
     * 获取当前登录者对象
     */
    public static SecurityRealm.Principal getPrincipal() {
        Subject subject = SecurityUtils.getSubject();
        return (SecurityRealm.Principal) subject.getPrincipal();
    }

    /**
     * 获取当前用户session
     *
     * @return 当前用户session
     */
    public static Session getSession() {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession(false);
        if (session == null) {
            session = subject.getSession();
        }
        return session;
    }

    // ============== User Cache ==============

    /**
     * 清除当前用户缓存
     */
    public static void clearCache() {
        removeCache(CACHE_ROLE_LIST);
        removeCache(CACHE_MENU_LIST);
        removeCache(CACHE_AREA_LIST);
        removeCache(CACHE_OFFICE_LIST);
        removeCache(CACHE_OFFICE_ALL_LIST);
        removeCache(CARD_LIST);
    }

    /**
     * 清除指定用户缓存
     *
     * @param user 当前用户
     */
    public static void clearCache(User user) {
        JedisUtils.delObject(USER_CACHE_ + USER_CACHE_ID_ + user.getId());
        JedisUtils.delObject(USER_CACHE_ + USER_CACHE_LOGIN_NAME_ + user.getLoginName());
        JedisUtils.delObject(USER_CACHE_ + USER_CACHE_LOGIN_NAME_ + user.getOldLoginName());
    }


    public static Object getCache(String key) {
        return getCache(key, null);
    }

    public static Object getCache(String key, Object defaultValue) {
        Object obj = getSession().getAttribute(key);
        return obj == null ? defaultValue : obj;
    }

    public static void putCache(String key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static void removeCache(String key) {
        getSession().removeAttribute(key);
    }
    
    //根据实体类更新状态及删除
  	public static String updateDataToDatabase(Class clazz,CommonMapper commonMapper,String orderId,String parameter,String val,String type) throws ClassNotFoundException
  	{		
  		Example exampleRequest = new Example(clazz);
  		if(type == "1"){
  	  		exampleRequest.createCriteria().andEqualTo("requestId", orderId);
  		}else{
  	  		exampleRequest.createCriteria().andEqualTo("orderId", orderId);

  		}
  		
  		List<?> selectByExample = commonMapper.selectByExample(exampleRequest);
  		if(selectByExample.size()==1)
  		{
  			Object object = selectByExample.get(0);
  			try {
  				Method method = clazz.getMethod(parameter, String.class);
  				method.invoke(object, val);
  				Example exampleRequest1 = new Example(clazz);
  				if(type == "1"){
  					exampleRequest1.createCriteria().andCondition("request_id='"+orderId+"'");
  				}else{
  					exampleRequest1.createCriteria().andCondition("order_id='"+orderId+"'");
  				}
  				
  				int updateByExample1 = commonMapper.updateByExample(object, exampleRequest1);
  				
  			} catch (Exception e) {
  				// TODO: handle exception
  				e.printStackTrace();
  			}
  		}
		return val;
  	}

    //创建私有消息
    public static boolean createPrivateMessage(String msg, String status, String userId, String content, String menuId, Integer parentId){
        CrmPrivateMessage crmPrivateMessage = new CrmPrivateMessage();
        String name = msg;
        switch (status) {
            case UserUtils.STATUS_VALb:
                name += ORDER_OPERATOR_PASS;
                break;
            case UserUtils.STATUS_VALc:
                name += ORDER_OPERATOR_FINISH;
                break;
            default:
                name += ORDER_OPERATOR_OUT;
                break;
        }
        crmPrivateMessage.setName(name);
        crmPrivateMessage.setContent(content);//如果订单审核未通过则需要填写理由
        crmPrivateMessage.setMenuId(menuId);
        crmPrivateMessage.setParentId(parentId);
        crmPrivateMessage.setUserId(userId);
        crmPrivateMessage.setStatus("0");
        crmPrivateMessage.setStartTime(new Date());
        CrmPrivateMessageMapper messageMapper = SpringContextHolder.getBean(CrmPrivateMessageMapper.class);
        messageMapper.insert(crmPrivateMessage);

        return true;
    }

    //----短信验证码校验-----
    public static Boolean smsVerify(HttpServletRequest request, String mobile, String sms){
        String userMobile = CookieUtils.getCookie(request, "userMobile");
        if(StringUtils.isEmpty(userMobile)){
            throw new AuthenticationException("msg:短信验证码未填写或已过期，请重新发送！");
        }
        if(!userMobile.equals(mobile)){
            throw new AuthenticationException("msg:短信验证码与手机号不符！");
        }
        String smsCode = (String) UserUtils.getSession().getAttribute(RegisterAction.SUBMAIL_CODE);
        if(sms == null || !sms.equals(smsCode)){
            throw new AuthenticationException("msg:短信验证码错误，请重试！");
        }
        return true;
    }

    //-----图片验证码校验------
    public static Boolean valiVerify(String vCode){
        Session session = UserUtils.getSession();
        String valiCode = "";
        if (session != null) {
            valiCode = (String) session.getAttribute(ValidateCodeServlet.VALIDATE_CODE);
        }
        if (vCode == null || !vCode.toUpperCase().equals(valiCode)) {
            throw new AuthenticationException("msg:图片验证码错误, 请重试.");
        }
        return true;
    }

    //------是否是运维或运营人员------
    public static Boolean isYWorYY(){
        List<Role> roleList = getRoleList();
        for(Role role: roleList){
            if(role.getId().equals(YW_ROLE_ID) || role.getId().equals(YY_ROLE_ID)){
                return true;
            }
        }
        return false;
    }

    public static void responseError(int status, String msg) throws IOException {
        HttpServletResponse res = Servlets.getResponse();
        res.reset();
        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
        res.sendError(status,msg);
    }

    //------获取自己的北斗卡号列表-------
    public static ArrayList<String> getCardList() throws Exception {
        Boolean aBoolean = isYWorYY();
        if(aBoolean){
            return new ArrayList<>();
        }
        Session session = getSession();
        Object o = session.getAttribute(CARD_LIST);
        if(o == null){
            CrmNorthCard northCard = new CrmNorthCard();
            northCard.setUserId(getUserId());
            List<CrmNorthCard> cardList = cardServie.findListByUser(northCard);
            ArrayList<String> ids = new ArrayList<>();
            for(CrmNorthCard card: cardList){
                ids.add(card.getCardId());
            }
            session.setAttribute(CARD_LIST,ids);
            o = ids;
        }
        return (ArrayList<String>) o;
    }
    // cardList改变时，重置cache,还是何时？？？或是不改变，重新登录后刷新

    //--------自己是否拥有这张北斗卡--------
    public static void isOwnCard(String card) throws Exception {
        ArrayList<String> cardList = getCardList();
        if(cardList != null && cardList.size() > 0){
            if(cardList.contains(card)){
                return;
            }
        }
        if(isYWorYY()){
            return;
        }
        throw new CommonException("该北斗卡号不属于你，无权操作！");
    }
}

