package com.lovnx.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

//import io.reactivex.netty.protocol.http.server.HttpServerResponse;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class FirstFilter extends ZuulFilter  {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        //HttpServletResponse response = ctx.getResponse();

        log.info("第一级过滤器！");

        log.info("===============");

//        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
//        System.out.println(request.getRequestURL());

//        Object accessToken = request.getParameter("accessToken");
//        if(accessToken == null) {
//            log.warn("access token is empty");
//            ctx.setSendZuulResponse(false);
//            ctx.setResponseStatusCode(401);
//            return null;
//        }
//        log.info("access token ok");
        return null;
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletRequest request = ctx.getRequest();
//
//        log.info("send {} request to {}", request.getMethod(), request.getRequestURL().toString());
//
////        //获取传来的参数accessToken
//        Object accessToken = request.getParameter("accessToken");
//
//        //是否是登录相关api
//        int i = request.getRequestURL().toString().indexOf("login") ;
//
//        if(accessToken == null && i == -1) {
//            log.warn("access token is empty");
//            //过滤该请求，不往下级服务去转发请求，到此结束
//            ctx.setSendZuulResponse(false);
//            ctx.setResponseStatusCode(401);
//            ctx.setResponseBody("{\"result\":\"accessToken is empty!\"}");
//            return null;
//        }
//        //如果有token，则进行路由转发
//        log.info("access token ok");
//        //这里return的值没有意义，zuul框架没有使用该返回值
//        return null;
    }

}
