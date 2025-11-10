package common;

import jakarta.servlet.*;

import java.util.*;

/**
 * @description
 * @author: zpy
 * @Date: 2025/11/10
 */
public class FilterRegistrationImpl implements FilterRegistration {
    final ServletContext servletContext;

    final  String name;

    final public  Filter filter;


    boolean initialized = false;

    public FilterRegistrationImpl(ServletContext servletContext, String name, Filter filter) {
        this.servletContext = servletContext;
        this.name = name;
        this.filter = filter;
    }
    @Override
    public void addMappingForServletNames(EnumSet<DispatcherType> enumSet, boolean b, String... strings) {

    }

    @Override
    public Collection<String> getServletNameMappings() {
        return List.of();
    }

    @Override
    public void addMappingForUrlPatterns(EnumSet<DispatcherType> enumSet, boolean b, String... strings) {

    }

    @Override
    public Collection<String> getUrlPatternMappings() {
        return List.of();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getClassName() {
        return filter.getClass().getName();
    }

    @Override
    public boolean setInitParameter(String s, String s1) {
        return false;
    }

    @Override
    public String getInitParameter(String s) {
        return "";
    }

    @Override
    public Set<String> setInitParameters(Map<String, String> map) {
        return Set.of();
    }

    @Override
    public Map<String, String> getInitParameters() {
        return Map.of();
    }

    public FilterConfig getFilterConfig(){
        return new FilterConfig() {
            @Override
            public String getFilterName() {
                return FilterRegistrationImpl.this.name;
            }

            @Override
            public ServletContext getServletContext() {
                return FilterRegistrationImpl.this.servletContext;
            }

            @Override
            public String getInitParameter(String s) {
                return "";
            }

            @Override
            public Enumeration<String> getInitParameterNames() {
                return null;
            }
        }

    }
}
