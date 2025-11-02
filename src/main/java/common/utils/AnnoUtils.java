package common.utils;

import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @description
 * @author: zpy
 * @Date: 2025/11/2 08:52
 */
public class AnnoUtils {
    public static String getServletName(Class<?> servletClass) {
        WebServlet w= servletClass.getAnnotation(WebServlet.class);
        if (w != null && !w.name().isEmpty()) {
            return w.name();
        }
        return getDefaultNameByClass(servletClass);
    }

    public static String getFilterName(Class<?> clazz){
        WebFilter w= clazz.getAnnotation(WebFilter.class);
        if (w != null && !w.filterName().isEmpty()) {
            return w.filterName();
        }
        return getDefaultNameByClass(clazz);
    }

    public static Map<String,String>getServletInitParams(Class<?> servletClass){
        WebServlet w= servletClass.getAnnotation(WebServlet.class);
       if (w == null){
           return Map.of();
       }
         return initParamsToMap(w.initParams());
    }

    public static Map<String,String>getFilterInitParams(Class<?> filterClass){
        WebFilter w= filterClass.getAnnotation(WebFilter.class);
        if (w == null){
            return Map.of();
        }
        return initParamsToMap(w.initParams());
    }

    public static String[] getServletUrlPatterns(Class<?> servletClass){
        WebServlet w= servletClass.getAnnotation(WebServlet.class);
        if (w == null){
            return new String[0];
        }
        return arraysToSet(w.value(),w.urlPatterns()).toArray(String[]::new);
    }

    public static String[] getFilterUrlPatterns(Class<?> filterClass){
        WebFilter w= filterClass.getAnnotation(WebFilter.class);
        if (w == null){
            return new String[0];
        }
        return arraysToSet(w.value(),w.urlPatterns()).toArray(String[]::new);
    }

    private static Map<String, String> initParamsToMap(WebInitParam[] params) {
        return Arrays.stream(params).
                collect(Collectors.toMap(WebInitParam::name, WebInitParam::value));
    }


    private static  String getDefaultNameByClass(Class<?> clazz) {
        String name = clazz.getSimpleName();
        name = name.substring(0, 1).toLowerCase() + name.substring(1);
        return name;
    }

    public static Set<String> arraysToSet(String[] arr1){
        Set<String> set = new LinkedHashSet<>();
        for (String s : arr1) {
            set.add(s);
        }
        return set;
    }

    public static Set<String> arraysToSet(String[] arr1,String[]arr2){
        Set<String> set = arraysToSet(arr1);
        set.addAll(arraysToSet(arr2));
        return set;
    }
}
