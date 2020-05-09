package com.example.demo.web.filter;

import javax.servlet.*;
import java.io.IOException;

public class MyFilter implements Filter {
    /**
     * 需要初始化一个Config
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.err.println("this is start MyFilter");
        filterChain.doFilter(servletRequest, servletResponse);
        System.err.println("this is end MyFilter");
    }

}
