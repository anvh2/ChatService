package com.vng.apigatewayservice.rest.interceptor;

import com.vng.apigatewayservice.grpc.GrpcClient;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle
            (HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String urlPattern = request.getRequestURI().substring(request.getContextPath().length());

//        if( urlPattern.equals("/chat") && !GrpcClient.checkToken(request.getHeader("auth")) ){

//            String test = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
//            JSONObject jsonObject = new JSONObject(test);
//            GrpcClient.login(jsonObject.getString("username"), jsonObject.getString("password"));
//            response.sendRedirect("/login");
//            return false;
//        }
        return true;
    }
}
