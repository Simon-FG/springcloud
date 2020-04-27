package com.bdfint.backend.modules.sys.action;

import com.bdfint.backend.framework.common.BaseAction;
import com.bdfint.backend.framework.exception.CommonException;
import com.bdfint.backend.framework.servlet.ValidateCodeServlet;
import com.bdfint.backend.framework.util.CookieUtils;
import com.bdfint.backend.framework.util.StringUtils;
import com.bdfint.backend.framework.util.SubMailUtils;
import com.bdfint.backend.modules.sys.bean.CrmAirplaneCardBind;
import com.bdfint.backend.modules.sys.bean.Role;
import com.bdfint.backend.modules.sys.bean.SysCompanyManager;
import com.bdfint.backend.modules.sys.bean.User;
import com.bdfint.backend.modules.sys.init.ServerInit;
import com.bdfint.backend.modules.sys.service.CrmAirplaneCardBindService;
import com.bdfint.backend.modules.sys.service.UserService;
import com.bdfint.backend.modules.sys.utils.FileUtils;
import com.bdfint.backend.modules.sys.utils.UserUtils;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by lsl on 2018/3/8.
 *
 * ------接口------
 *  用户注册
 *  /noUser/register/registerUser
 *  忘记密码（username、pass、smsCode）
 *  /noUser/register/forgetPass
 *  获取短信验证码（mobile）
 *  /noUser/register/smsCode
 */
@RestController
@RequestMapping(value = "/noUser/register")
public class RegisterAction extends BaseAction{

    public static final String SUBMAIL_CODE = "smsCode";
    public static final String SMS_COOKIE = "userMobile";

    @Autowired
    private UserService userService;

    @Autowired
    private CrmAirplaneCardBindService crmAirplaneCardBindService;

    @RequestMapping(value = "/registerUser",method= RequestMethod.POST)
    public String registerUser(User user, HttpServletRequest request, HttpServletResponse response, String ... role) throws UnsupportedEncodingException {
        User loginName = userService.getUserByLoginName(user.getLoginName());
        if(loginName != null){
            throw new CommonException("该登录名已存在！");
        }
        List<Role> roles = UserUtils.getRoleList();
        if(null == roles){
            throw new CommonException("registerUser接口有问题！");
        }
        if(roles.size()>0){              //运维或运营用户新增用户操作
//            String id = roles.get(0).getId();
            for(Role r: roles){
                String id = r.getId();
                if(id.equals(UserUtils.YW_ROLE_ID)){        //运维用户新增用户操作时，可赋予用户角色（权限）
                    ArrayList<String> list = new ArrayList<>();
                    if(null != role){
                        Collections.addAll(list, role);
                    }
                    user.setRoleIdList(list);
                    break;
                }
            }

            List<String> list = FileUtils.fileUpload(request, "HEAD-PHOTO");  //获取头像存储路径信息
            if(list != null && list.size() == 1){
                user.setPhoto(list.get(0));
            }
        }else{                          //注册界面注册用户
            //验证码识别
//            UserUtils.valiVerify(user.getVildataCode());
            UserUtils.smsVerify(request,user.getLoginName(),user.getSmsCode());
        }


        try {
            user.setMobile(user.getLoginName());
            userService.save(user);

            if(roles.size()>0){     //管理员添加用户时添加认证信息
                User byLoginName = userService.getUserByLoginName(user.getLoginName());
                SysCompanyManager manager = new SysCompanyManager();
                manager.setCompany(user.getCompanyName());
                manager.setRealName(user.getName());
                manager.setTelphone(user.getMobile());
                manager.setRegUserid(byLoginName.getId());
                manager.setRegTime(new Date());
                manager.setConfirmNum(user.getIsConfirm());
                userService.saveCertified(manager, false);
            }
            CookieUtils.setCookie(response,SMS_COOKIE,"",0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "注册成功";
    }


    /**
     * 忘记密码
     * @param username  登录名
     * @param pass      新密码
     * @param smsCode   短信验证码
     * @throws Exception
     */
    @RequestMapping("/forgetPass")
    public Boolean forgetPass(@RequestParam String username, @RequestParam String pass, @RequestParam String smsCode,
                              HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserUtils.smsVerify(request,username,smsCode);
        User user = UserUtils.getUserByLoginName(username);
        if(user != null){
            user.setPassword(pass);
            userService.save(user);
            CookieUtils.setCookie(response,SMS_COOKIE,"",0);
            return true;
        }else{
            throw new CommonException("用户名："+username+" 的用户不存在！");
        }
    }

    @RequestMapping("/smsCode")
    public Boolean smsCode(@RequestParam String mobile, HttpServletResponse response){
        String smsCode = SubMailUtils.randomCode();
        Session session = UserUtils.getSession();
        session.setAttribute(SUBMAIL_CODE,smsCode);
        CookieUtils.setCookie(response,SMS_COOKIE,mobile,60*5);
        String send = SubMailUtils.send(mobile, smsCode);
        return true;
    }

    @RequestMapping("/test")
    public Boolean test(){
        System.out.println("test-------");
        throw new AuthenticationException("sdf");
    }

    /**
     * 获取已绑定的北斗卡与机位号
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryBindList")
    public String queryBindList()throws Exception{
        return crmAirplaneCardBindService.queryBindList();
    }

    /**
     * 获取所有cards
     * @return
     */
    @RequestMapping("/getcards")
    public String[] getCards() {
        return ServerInit.getCards();
    }
}
