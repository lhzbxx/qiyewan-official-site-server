package com.qiyewan.aop;

import com.qiyewan.exceptions.NoAuthException;
import com.qiyewan.service.TokenService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lhzbxx on 2016/10/27.
 *
 * 令牌处理
 */

@Aspect
@Component
public class AuthAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private TokenService tokenService;

    @Pointcut("execution(public * com.qiyewan.api..*.*(..))")
    public void apiAuth() {}

    @Before("apiAuth()")
    public void beforeApiAuth(JoinPoint joinPoint) throws Throwable {
        String authorization = request.getHeader("Authorization");
        if (authorization == null) throw new NoAuthException("Error.Auth.NO_TOKEN");
        Long userId = tokenService.getUserIdWithToken(authorization);
        request.setAttribute("userId", userId);
    }

}
