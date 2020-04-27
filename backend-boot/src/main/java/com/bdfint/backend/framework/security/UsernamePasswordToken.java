package com.bdfint.backend.framework.security;

/**
 * 用户和密码（包含验证码）令牌类
 */
public class UsernamePasswordToken extends org.apache.shiro.authc.UsernamePasswordToken {

    private static final long serialVersionUID = 1L;

    private String captcha;
    private String smsCode;
    private boolean mobileLogin;

    public UsernamePasswordToken() {
        super();
    }

    public UsernamePasswordToken(String username, char[] password,
                                 boolean rememberMe, String host, String captcha, String smsCode, boolean mobileLogin) {
        super(username, password, rememberMe, host);
        this.captcha = captcha;
        this.smsCode = smsCode;
        this.mobileLogin = mobileLogin;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public boolean isMobileLogin() {
        return mobileLogin;
    }

}
