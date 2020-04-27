package com.bdfint.backend.framework.security;

import org.apache.shiro.authc.AuthenticationException;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lsl on 2018/4/10.
 */
//@Service
public class UserFilter extends org.apache.shiro.web.filter.authc.UserFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse res = (HttpServletResponse) response;
        res.reset();
        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
        res.sendError(403,"您尚未登录或超时，请重新登录");
        return false;
    }
}
