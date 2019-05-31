package com.vng.uiwebapp.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle
            (HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        String urlPattern = request.getRequestURI().substring(request.getContextPath().length());

//        if( urlPattern.equals("/chat") && !GrpcClient.checkToken(request.getHeader("auth")) ){

//            String test = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
//            JSONObject jsonObject = new JSONObject(test);
//            GrpcClient.login(jsonObject.getString("username"), jsonObject.getString("password"));
//            response.sendRedirect("/login");
//            return false;
//        }
        HttpSession session = request.getSession();
        return true;
    }
}
