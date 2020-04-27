package com.doubleskyline.core.context;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * http请求 工具类
 *
 * @Auther: NPF
 * @date 2016年12月21日 下午12:53:33
 */
public class ServletContext {

	private static ThreadLocal<HttpServletRequest> requestThreadLocal = new ThreadLocal<>();

	public static void setRequest(HttpServletRequest request) {
		requestThreadLocal.set(request);
	}
	public static HttpServletRequest getRequest() {
		if(null == requestThreadLocal.get()){
			return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		}
		return requestThreadLocal.get();
	}

	/**
	 * 获取url中的域名
	 */
	public static String getDomain(){
		HttpServletRequest request = getRequest();
		StringBuffer url = request.getRequestURL();
		return url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
	}

	/**
	 * 获取 head 中的 Access-Control-Allow-Origin
	 */
	public static String getOrigin(){
		HttpServletRequest request = getRequest();
		return request.getHeader("Origin");
	}


	public static String getValue(String paramName) {
        HttpServletRequest request = getRequest();

		Enumeration<String> enumeration = request.getHeaderNames();
		while (enumeration.hasMoreElements()) {
			String key = enumeration.nextElement();
			if(paramName.equalsIgnoreCase(key)){
				return request.getHeader(key);
			}
		}

		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()){
			String key = paramNames.nextElement();
			if(paramName.equals(key)){
				return request.getParameter(key);
			}
		}

		Enumeration<String> attrNames = request.getAttributeNames();
		while (attrNames.hasMoreElements()){
			String key = attrNames.nextElement();
			if(paramName.equals(key)){
				return request.getAttribute(key).toString();
			}
		}

        Cookie[] cookies = request.getCookies();
        if(null != cookies){
            for (Cookie cookie:cookies){
                if(paramName.equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }

		return null;
	}
}
