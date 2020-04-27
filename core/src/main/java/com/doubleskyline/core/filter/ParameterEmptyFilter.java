package com.doubleskyline.core.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Order(1)
@WebFilter(filterName = "parameterEmptyFilter", urlPatterns = "/*")
public class ParameterEmptyFilter implements Filter {

    protected final Log logger = LogFactory.getLog(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String method = ((HttpServletRequest) request).getMethod();
        if (HttpMethod.GET.name().equals(method) || HttpMethod.POST.name().equals(method)) {
            ParameterRequestWrapper requestWrapper = new ParameterRequestWrapper((HttpServletRequest) request);
            filterChain.doFilter(requestWrapper, response);
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
