package id.co.homecredit.configuration;//package com.hcid.urlshort.config;
//

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by muhammad.nizar01 on 9/21/2018.
 */
@Component
public class TransactionFilter extends OncePerRequestFilter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        System.out.println("START FILTERING ");
//    }

//    @Override
//    protected void doFilterInternal(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
////        HttpServletRequest request = (HttpServletRequest) servletRequest;
////        HttpServletResponse response = (HttpServletResponse) servletResponse;
////
////        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
////        response.setHeader("Access-Control-Allow-Credentials", "true");
////        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
////        response.setHeader("Access-Control-Max-Age", "3600");
//////        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
////        response.setHeader("Access-Control-Allow-Headers", "Authorization, Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, " +
////                "Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
////        response.addHeader("Access-Control-Expose-Headers", "xsrf-token");
////        filterChain.doFilter(servletRequest, servletResponse);
//
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        String origin = response.getHeader("Access-Control-Allow-Origin");
//        if (StringUtils.isEmpty(origin))
//            response.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
//
//        System.out.println(request.getMethod());
//        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
////            response.addHeader("Access-Control-Allow-Origin", "*");
//            response.setHeader("Access-Control-Allow-Methods", "POST,GET,DELETE");
//            response.setHeader("Access-Control-Max-Age", "3600");
//            response.setHeader("Access-Control-Allow-Headers", "content-type,access-control-request-headers,access-control-request-method,accept,origin,authorization,x-requested-with");
//            response.setStatus(HttpServletResponse.SC_OK);
//        } else {
//            filterChain.doFilter(request, response);
//        }
//
//    }

//    @Override
//    public void destroy() {
//        System.out.println("END FILTERING ");
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) httpServletRequest;
        HttpServletResponse response = (HttpServletResponse) httpServletResponse;

        String origin = response.getHeader("Access-Control-Allow-Origin");
        if (StringUtils.isEmpty(origin))
            response.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");

        System.out.println(request.getMethod());
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
//            response.addHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST,GET,DELETE");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "content-type,access-control-request-headers,access-control-request-method,accept,origin,authorization,x-requested-with");
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            filterChain.doFilter(request, response);
        }

    }
}
