package common.engine;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;
import jakarta.servlet.ServletSecurityElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @description
 * @author: zpy
 * @Date: 2025/11/2 08:36
 */
public class ServletRegistrationImpl implements ServletRegistration.Dynamic {

    final ServletContext servletContext;




    @Override
    public void setLoadOnStartup(int i) {

    }

    @Override
    public Set<String> setServletSecurity(ServletSecurityElement servletSecurityElement) {
        return Set.of();
    }

    @Override
    public void setMultipartConfig(MultipartConfigElement multipartConfigElement) {

    }

    @Override
    public void setRunAsRole(String s) {

    }

    @Override
    public void setAsyncSupported(boolean b) {

    }

    @Override
    public Set<String> addMapping(String... strings) {
        return Set.of();
    }

    @Override
    public Collection<String> getMappings() {
        return List.of();
    }

    @Override
    public String getRunAsRole() {
        return "";
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getClassName() {
        return "";
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
}
