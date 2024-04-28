package com.example.realtimestreaming.Config;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.HashSet;
import java.util.Set;

@Component
public class FirstFilter implements Filter {
    private static final Set<String> ALLOWED_ORIGINS = new HashSet<>();

    static {
        ALLOWED_ORIGINS.add("http://158.247.240.142:3000/");
        ALLOWED_ORIGINS.add("http://49.142.106.179:3000"); // 허용하려는 다른 도메인 추가
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String origin = req.getHeader("Origin");

        if (ALLOWED_ORIGINS.contains(origin)) {
            res.addHeader("Access-Control-Allow-Origin", origin);
        }

        chain.doFilter(req, res);
    }
}