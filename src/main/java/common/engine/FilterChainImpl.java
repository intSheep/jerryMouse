package common.engine;

import jakarta.servlet.*;

import java.io.IOException;

/**
 * @description FilterChainImpl
 * @author: zpy
 * @Date: 2025/11/10
 */
public class FilterChainImpl implements FilterChain {
    final Filter[] filters;
    final int total;
    final Servlet servlet;
    int index = 0;

    public FilterChainImpl(Filter[] filters, Servlet servlet) {
        this.filters = filters;
        this.total = filters.length;
        this.servlet = servlet;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException, ServletException {
        if (index < total){
            int current = index;
            index++;
            filters[current].doFilter(servletRequest, servletResponse, this);
        }else{
            servlet.service(servletRequest, servletResponse);
        }
    }
}
