package com.bosa.eshop.web.filter;

import com.bosa.eshop.model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter
public class LoginFilter implements Filter {
    @Override
    public void init( FilterConfig filterConfig ) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter( ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain ) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String servletPath = req.getServletPath();
        User user = (User) req.getSession().getAttribute("user");

        if(!servletPath.equals("/login") && !servletPath.equals("/main.css") && user == null){
            resp.sendRedirect("/login");
        } else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
