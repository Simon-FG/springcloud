package com.bdfint.backend.modules.sys.action;

import com.bdfint.backend.framework.common.BaseAction;
import com.bdfint.backend.framework.common.Param;
import com.bdfint.backend.framework.exception.CommonException;
import com.bdfint.backend.framework.servlet.ValidateCodeServlet;
import com.bdfint.backend.framework.util.BeanValidators;
import com.bdfint.backend.framework.util.DateUtils;
import com.bdfint.backend.framework.util.Encodes;
import com.bdfint.backend.framework.util.StringUtils;
import com.bdfint.backend.framework.util.excel.ExportExcel;
import com.bdfint.backend.framework.util.excel.ImportExcel;
import com.bdfint.backend.modules.sys.bean.Office;
import com.bdfint.backend.modules.sys.bean.Role;
import com.bdfint.backend.modules.sys.bean.SysCompanyManager;
import com.bdfint.backend.modules.sys.bean.User;
import com.bdfint.backend.modules.sys.service.OfficeService;
import com.bdfint.backend.modules.sys.service.RoleService;
import com.bdfint.backend.modules.sys.service.SysCompanyManagerService;
import com.bdfint.backend.modules.sys.service.UserService;
import com.bdfint.backend.modules.sys.utils.FileUtils;
import com.bdfint.backend.modules.sys.utils.UploadUtils;
import com.bdfint.backend.modules.sys.utils.UserUtils;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 用户管理
 *
 * ------接口--------
 *      修改密码（oldPass、newPass）
 *      http://localhost:8082/admin/sys/user/editPassword
 *      实名认证申请
 *      http://localhost:8082/admin/sys/user/realNameAuthentication
 *      实名认证审核（isConfirm, userId, content）
 *      http://localhost:8082/admin/sys/user/realNameAudit
 *      企业认证申请(company、mobile、realName、idNomber)
 *      http://localhost:8082/admin/sys/user/companyAuthentication
 *      企业认证审核（companyId、confirm）
 *      http://localhost:8082/admin/sys/user/companyAudit
 *      更换手机号
 *      http://localhost:8082/admin/sys/user/bindMobile
 *      更换登录名（loginName、smsCode）
 *      http://localhost:8082/admin/sys/user/changeLoginName
 *      绑定邮箱
 *      http://localhost:8082/admin/sys/user/bindEmail
 *      修改名字
 *      http://localhost:8082/admin/sys/user/editName
 *      用户注册
 *      http://localhost:8082/admin/sys/user/registerUser
 *      用户信息
 *      http://localhost:8082/admin/sys/user/info
 *      企业信息
 *      http://localhost:8082/admin/sys/user/companyInfo
 *      用户列表(name、mobile、companyName、isReq("1"资质审核列表))
 *      http://localhost:8082/admin/sys/user/userList
 *      企业名称模糊查询
 *      /sys/user/queryLikeCompany
 *      根据企业认证用户ID查询企业名称
 *      /sys/user/queryCompanyName
 *      删除(逻辑删除)(String ids(多个用“，”隔开))
 *      admin/sys/user/del
 *      停用(loginFlag = “0”),列表中还可以看到(String ids(多个用“，”隔开))
 *      admin/sys/user/stop
 *      更新用户信息
 *      admin/sys/user/updateUserInfo
 *
 */
@RestController
@RequestMapping(value = "${adminPath}/sys/user")
public class UserAction extends BaseAction {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private OfficeService officeService;
    @Autowired
    private SysCompanyManagerService sysCompanyManagerService;

    /**
     * 修改密码
     * @param oldPass 要比对的旧密码
     * @param newPass 要设置的新密码
     * @return
     * @throws Exception
     */
    @RequestMapping("/editPassword")
    //@RequiresPermissions("sys:user:edit")
    public Boolean editPassword(HttpServletRequest request ,@RequestParam String oldPass, @RequestParam String newPass) throws Exception {
        User user = UserUtils.getUser();
        boolean b = Encodes.validatePassword(oldPass, user.getPassword());
        if(!b){
            throw new CommonException("密码验证错误，请重试！");
        }
        userService.initPassword(user.getId(), newPass);
        return true;
    }

    /**
     * 实名认证申请
     * @param request
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping("/realNameAuthentication")
    //@RequiresPermissions("sys:user:edit")
    public Boolean realNameAuthentication(HttpServletRequest request, User user) throws Exception {
        List<String> list = FileUtils.fileUpload(request, "USER-ID");
        return userService.realNameAuthentication(user, list);
    }

    /**
     * 实名认证审核
     * @param isConfirm
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping("/realNameAudit")
    //@RequiresPermissions("sys:user:edit")
    public String realNameAudit(@RequestParam String isConfirm,@RequestParam String userId, String content) throws Exception {
        return userService.realNameAudit(isConfirm, userId, content);
    }

    /**
     * 企业认证申请
     * @return
     */
    @RequestMapping("/companyAuthentication")
    public Boolean companyAuthentication(HttpServletRequest request, User user) throws Exception {
        List<String> list = FileUtils.fileUpload(request, "COMPANY-ID");
        if(list != null && list.size() == 2){
            return userService.companyAuthentication(user, list);
        }else{
            throw new CommonException("请传入“手持证件照”和“营业执照”两张照片！");
        }
    }

    /**
     * 企业认证审核
     * @param companyId
     * @param confirm
     * @return
     */
    @RequestMapping("/companyAudit")
    public Boolean companyAudit(@RequestParam Integer companyId, @RequestParam String confirm, String content) throws Exception {
        return userService.companyAudit(companyId, confirm, content);
    }

    @RequestMapping("/bindMobile")
    //@RequiresPermissions("sys:user:edit")
    public Boolean bindMobile(User user) throws Exception {
        User u = UserUtils.getUser();
        if(StringUtils.isNotBlank(user.getMobile())){
            u.setMobile(user.getMobile());
            u.preUpdate();
            userService.updateUserInfo(u);
            return true;
        }
        return false;
    }

    @RequestMapping("/changeLoginName")
    public Boolean changeLoginName(HttpServletRequest request, @RequestParam String loginName, @RequestParam String smsCode) throws Exception {
        User name = userService.getUserByLoginName(loginName);
        if(name != null){
            throw new CommonException("该登录名已存在！");
        }
        UserUtils.smsVerify(request,loginName,smsCode);
        User user = new User();
        user.setId(UserUtils.getUser().getId());
        user.setOldLoginName(user.getLoginName());
        user.setLoginName(loginName);
        user.preUpdate();
        userService.updateUserInfo(user);
        return true;
    }

    @RequestMapping("/bindEmail")
    //@RequiresPermissions("sys:user:edit")
    public Boolean bindEmail(User user) throws Exception {
        User u = UserUtils.getUser();
        if(StringUtils.isNotBlank(user.getEmail())){
            u.setEmail(user.getEmail());
            u.preUpdate();
            userService.updateUserInfo(u);
            return true;
        }
        return false;
    }

    @RequestMapping("/editName")
    //@RequiresPermissions("sys:user:edit")
    public Boolean editName(HttpServletRequest request, User user) throws Exception {
        User u = UserUtils.getUser();
        if(StringUtils.isNotBlank(user.getName())){
            u.setName(user.getName());
            u.preUpdate();
            userService.updateUserInfo(u);
            return true;
        }
        return false;
    }

    @RequestMapping("/userList")
    public PageInfo<User> userList(User user){
        return userService.userList(user);
    }
    
    /**
     * 企业名称模糊查询
     * @param company
     * @return
     */
    @RequestMapping("/queryLikeCompany")
    public List<SysCompanyManager> queryLikeCompany(String company){
    	
		return sysCompanyManagerService.queryLikeCompany(company);
    	
    }
    /**
     * 根据企业用户ID查询企业名称
     * @param userId
     * @return
     */
    @RequestMapping("/queryCompanyName")
    public String queryCompanyName(String userId){
    	
		return sysCompanyManagerService.queryCompanyName(userId);
    	
    }

    @RequestMapping("/companyInfo")
    public SysCompanyManager companyInfo(String userId){
        return userService.companyInfo(userId);
    }

    /**
     * 初始化数据
     *
     * @param id ID
     * @return User
     */
    public User get(@RequestParam(required = false) String id) throws Exception {
        User User;
        if (StringUtils.isNotEmpty(id)) {
            User = userService.get(id);
            if (User != null) {
                User.setRoleList(roleService.getRoleByUserId(id));
                Office office = officeService.get(User.getOfficeId());
                User.setOfficeName(office.getName());
                Office company = officeService.get(User.getCompanyId());
                User.setCompanyName(company.getName());
            } else {
                User = new User();
            }
        } else {
            User = new User();
        }
        return User;
    }

    /**
     * 用户首页
     *
     * @return ModelAndView
     */
    @RequestMapping(value = "index")
    //@RequiresPermissions("sys:user:view")
    public String index() {
        return "modules/sys/userIndex";
    }

    /**
     * 用户列表
     * @return PageInfo<User>
     */
    @RequestMapping(value = "list")
    //@RequiresPermissions("sys:user:view")
    public PageInfo<User> list( User object, HttpServletRequest request, HttpServletResponse response)
            throws Exception {


        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(object.getOrderBy())) {
            example.setOrderByClause(object.getOrderBy());
        } else {
            example.setOrderByClause("create_date DESC");
        }

        if (StringUtils.isNotEmpty(object.getLoginName())) {
            criteria.andLike("loginName", "%" + object.getLoginName() + "%");
        }
        if (StringUtils.isNotEmpty(object.getName())) {
            criteria.andLike("name", "%" + object.getName() + "%");
        }
        if (StringUtils.isNotEmpty(object.getMobile())) {
            criteria.andLike("mobile", "%" + object.getMobile() + "%");
        }
        if (StringUtils.isNotEmpty(object.getCompanyName())) {
            criteria.andLike("companyName", "%" + object.getCompanyName() + "%");
        }
        if (StringUtils.isNotEmpty(object.getUserType())) {
            criteria.andEqualTo("userType", object.getUserType());
        }
        if (StringUtils.isNotEmpty(object.getIsReq()) && object.getIsReq().equals("1")) {
            String[] strings = new String[]{"1","2"};
            criteria.andIn("isConfirm",Arrays.asList(strings));
        }

        //根据当前组织机构ID查询数据
//        List<String> ids = Lists.newArrayList();
//        if (StringUtils.isNotEmpty(object.getOfficeId())) {
//            ids.add(object.getOfficeId());
//            List<Office> offices = officeService.getByParentIdsLike(object.getOfficeId());
//            if (offices != null && offices.size() > 0) {
//                for (Office office : offices) {
//                    ids.add(office.getId());
//                }
//            }
//        }
//        if (ids.size() > 0) {
//            criteria.andIn("officeId", ids);
//        }
        PageInfo<User> page = userService.getPage(object, example);
        return page;
    }

    /**
     * 表单输入页面
     *
     * @return 
     */
    @RequestMapping(value = "form")
    //@RequiresPermissions("sys:user:view")
    public String form(User user) throws Exception {
        return "";
    }
    /**
     * 保存
     *
     * @return 
     */
    @RequestMapping(value = "save")
    //@RequiresPermissions("sys:user:edit")
    public String save(User user) throws Exception {
        return userService.save(user);
    }

    /**
     * 更新认证信息
     * @param companyManager
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "saveCertified")
    public Boolean saveCertified(HttpServletRequest request, SysCompanyManager companyManager) throws Exception {
        String fileChars = companyManager.getFileChars();
        if (StringUtils.isNotEmpty(fileChars)) {
            char[] chars = fileChars.toCharArray();
            List<String> list = FileUtils.fileUpload(request, chars);
            for (int i=0; i<list.size(); i++) {
                setUrl(companyManager, list.get(i), chars[i]);
            }
        }
        return userService.saveCertified(companyManager, true);
    }

    private void setUrl (SysCompanyManager manager, String url, char c) {
        switch (c){
            case 'a':
                manager.setUploadIdFile1(url);
                break;
            case 'b':
                manager.setUploadIdFile2(url);
                break;
            case 'c':
                manager.setHandIdcardImg(url);
                break;
            case 'd':
                manager.setBusinessLicenceImg(url);
        }
    }
//    @RequestMapping(value = "/registerUser",method=RequestMethod.POST)
//    public String registerUser(User user)
//    {
//    	//验证码识别
//    	 Session session = UserUtils.getSession();
//         String code = null;
//         if (session != null) {
//             code = (String) session.getAttribute(ValidateCodeServlet.VALIDATE_CODE);
//         }
//         if (user.getVildataCode().toUpperCase().equals(code)) {
//             throw new AuthenticationException("msg:验证码错误, 请重试.");
//         }
//         try {
//			userService.save(user);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//    	return "注册成功";
//    }
    /**
     * 更新状态
     *
     * @return 
     */
    @RequestMapping(value = "updateStatus")
    //@RequiresPermissions("sys:user:edit")
    public String updateStatus(User user, Param param, RedirectAttributes redirectAttributes) throws Exception {
        userService.updateStatus(param.getIds(), user);
        return "更新成功";
    }

    /**
     * 密码初始化
     *
     * @return 
     */
    @RequestMapping(value = "initPassword")
    //@RequiresPermissions("sys:user:edit")
    public String initPassword(User user, Param param, RedirectAttributes redirectAttributes) throws Exception {
        userService.initPassword(param.getIds(), user.getPassword());
        return "操作成功";
    }

    /**
     * 删除
     *
     * @return 
     */
    @RequestMapping(value = "delete")
    //@RequiresPermissions("sys:user:edit")
    public String delete(Model model, User user, Param param, RedirectAttributes redirectAttributes) throws Exception {
        userService.delete(param.getIds());
        return "删除用户成功";
    }

    /**
     * 验证用户名是否有效
     *
     * @param oldLoginName 旧用户名
     * @param loginName    用户名
     * @return String
     */
    @ResponseBody
    //@RequiresPermissions("sys:user:edit")
    @RequestMapping(value = "checkUsername")
    public String checkLoginName(String oldLoginName, String loginName) {
        if (loginName != null && loginName.equals(oldLoginName)) {
            return "true";
        } else if (loginName != null && userService.getUserByLoginName(loginName) == null) {
            return "true";
        }
        return "false";
    }

    /**
     * 导出用户数据
     *
     * @param user               User
     * @param response           HttpServletResponse
     * @param redirectAttributes RedirectAttributes
     * @return ModelAndView
     */
    //@RequiresPermissions("sys:user:edit")
    @RequestMapping(value = "export", method = RequestMethod.POST)
    public String exportFile(User user, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "用户数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
            PageInfo<User> page = userService.getPage(user, new Example(User.class));
            new ExportExcel("用户数据", User.class).setDataList(page.getList()).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, "导出用户失败！失败信息：" + e.getMessage());
        }
        return "redirect:" + adminPath + "/sys/user/list?repage";
    }

    /**
     * 导入用户数据
     *
     * @param file               MultipartFile
     * @param redirectAttributes RedirectAttributes
     * @return ModelAndView
     */
    //@RequiresPermissions("sys:user:edit")
    @RequestMapping(value = "import", method = RequestMethod.POST)
    public String importFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            int successNum = 0;
            int failureNum = 0;
            StringBuilder failureMsg = new StringBuilder();
            ImportExcel importExcel = new ImportExcel(file, 1, 0);
            List<User> list = importExcel.getDataList(User.class);
            for (User user : list) {
                try {
                    if ("true".equals(checkLoginName("", user.getLoginName()))) {
                        user.setPassword(Encodes.encryptPassword("123456"));
                        BeanValidators.validateWithException(validator, user);
                        userService.save(user);
                        successNum++;
                    } else {
                        failureMsg.append("<br/>登录名 ").append(user.getLoginName()).append(" 已存在; ");
                        failureNum++;
                    }
                } catch (ConstraintViolationException ex) {
                    failureMsg.append("<br/>登录名 ").append(user.getLoginName()).append(" 导入失败：");
                    List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
                    for (String message : messageList) {
                        failureMsg.append(message).append("; ");
                        failureNum++;
                    }
                } catch (Exception ex) {
                    failureMsg.append("<br/>登录名 ").append(user.getLoginName()).append(" 导入失败：").append(ex.getMessage());
                }
            }
            if (failureNum > 0) {
                failureMsg.insert(0, "，失败 " + failureNum + " 条用户，导入信息如下：");
            }
            addMessage(redirectAttributes, "已成功导入 " + successNum + " 条用户" + failureMsg);
        } catch (Exception e) {
            addMessage(redirectAttributes, "导入用户失败！失败信息：" + e.getMessage());
        }
        return "redirect:" + adminPath + "/sys/user/list?repage";
    }

    /**
     * 下载导入用户数据模板
     *
     * @param response           HttpServletResponse
     * @param redirectAttributes RedirectAttributes
     * @return ModelAndView
     */
    //@RequiresPermissions("sys:user:edit")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "用户数据导入模板.xlsx";
            List<User> list = Lists.newArrayList();
            list.add(UserUtils.getUser());
            new ExportExcel("用户数据", User.class, 2).setDataList(list).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
        }
        return "redirect:" + adminPath + "/sys/user/list?repage";
    }

    /**
     * 用户头像显示编辑保存
     *
     * @param user  User
     * @param model Model
     * @return ModelAndView
     */
    //@RequiresPermissions("sys:user:edit")
    @RequestMapping(value = "imageEdit")
    public String imageEdit(User user, Model model) throws Exception {
        User currentUser = UserUtils.getUser();
        if (StringUtils.isNotBlank(user.getName())) {
            if (user.getPhoto() != null) {
                currentUser.setPhoto(user.getPhoto());
            }
            userService.updateUserInfo(currentUser);
            model.addAttribute("message", "保存用户信息成功");
            return "modules/sys/userInfo";
        }
        model.addAttribute("user", currentUser);
        return "modules/sys/userImageEdit";
    }

    /**
     * 用户头像显示编辑保存
     *
     * @return 
     */
    //@RequiresPermissions("sys:user:edit")
    @RequestMapping(value = "imageUpload")
    public String imageUpload(HttpServletRequest request, MultipartFile file) throws Exception {
        User currentUser = UserUtils.getUser();
        String fileName = UploadUtils.uploadImage(file, 1);
        // 判断文件是否为空
        if (StringUtils.isNotEmpty(fileName)) {
            currentUser.setPhoto(fileName);
            userService.updateUserInfo(currentUser);
        }
        return "modules/sys/userImageEdit";
    }

    /**
     * 当前用户信息显示
     *
     * @param 
     * @return 
     */
    @RequestMapping(value = "info")
    //@RequiresPermissions("sys:user:view")
    public User info() {
        User currentUser = UserUtils.getUser();
        return currentUser;
    }
    
    /**
     * 获取当前用户角色
     * @return
     */
    @RequestMapping(value = "userRole")
    //@RequiresPermissions("sys:user:view")
    public List<Role> userRole(){
    	
    	return UserUtils.getRoleList();
    }

    /**
     * 获取用户树结构
     *
     * @param officeId officeId
     */
    @ResponseBody
    //@RequiresPermissions("user")
    @RequestMapping(value = "treeData")
    public List<Map<String, Object>> treeData(@RequestParam(required = false) String officeId) {
        List<Map<String, Object>> mapList = Lists.newArrayList();
        List<User> list = userService.getUserByOfficeId(officeId);
        if (list != null && list.size() > 0) {
            for (User e : list) {
                Map<String, Object> map = Maps.newHashMap();
                map.put("id", "u_" + e.getId());
                map.put("pId", officeId);
                map.put("name", StringUtils.replace(e.getName(), " ", ""));
                mapList.add(map);
            }
        }
        return mapList;
    }

    /**
     * 删除(逻辑删除)
     */
    @RequestMapping(value = "/del")
    //@RequiresPermissions("sys:user:edit")
    public Boolean delete(String ids) throws Exception {
        if(UserUtils.isYWorYY()){
            userService.delete(ids);
            return true;
        }
        throw new CommonException("您没有权限这么做！");
    }

    /**
     * 停用(loginFlag = “0”停用，“1”启用),列表中还可以看到
     */
    @RequestMapping(value = "stop")
    //@RequiresPermissions("sys:user:edit")
    public Boolean stop(String ids, String loginFlag) throws Exception {
        if(UserUtils.isYWorYY()){
            userService.stop(ids,loginFlag);
            return true;
        }
        throw new CommonException("您没有权限这么做！");
    }

    /**
     * 更新用户信息
     */
    @RequestMapping(value = "updateUserInfo")
    //@RequiresPermissions("sys:user:edit")
    public boolean updateUserInfo(User user) throws Exception {
        if(UserUtils.isYWorYY()){
            if(StringUtils.isNotBlank(user.getPassword())){
                user.setPassword(Encodes.encryptPassword(user.getPassword()));
            }
            userService.updateUserInfo(user);
            return true;
        }
        throw new CommonException("您没有权限这么做！");
    }

    /**
     * 根据用户id查询登录名称
     * @param userId
     * @return
     */
    @RequestMapping(value = "getUserName")
    public String getLoginName(User userId){
        User loginName = userService.getLoginName(userId);
        if(null != loginName){
            return loginName.getLoginName();
        }
        return "";
    }

}
