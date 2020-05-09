package com.example.demo.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Map;

@WebFilter(filterName = "My2Filter", urlPatterns = "/student/findStudent/*")
public class My2Filter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        Map<String, String[]> parameterMap = servletRequest.getParameterMap();
//        String[] admins = parameterMap.get("admin");
//        if ("123".equals(admins[0])){
//            System.out.println("this is start My2Filter");
//            filterChain.doFilter(servletRequest, servletResponse);
//            System.out.println("this is end My2Filter");
//        } else {
//            System.out.println("失败");
//        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
