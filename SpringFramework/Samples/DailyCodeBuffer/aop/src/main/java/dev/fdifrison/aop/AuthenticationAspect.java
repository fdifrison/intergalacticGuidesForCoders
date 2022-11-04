package dev.fdifrison.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthenticationAspect {

    @Pointcut("within(dev.fdifrison.aop..*)")
    public void authenticatingPointCut() {

    }

    @Pointcut("within(dev.fdifrison.aop..*)")
    public void authorizationPointCut() {

    }

    @Before("authenticatingPointCut() && authorizationPointCut()")
    public void authenticate() {
        System.out.println("Authenticating and Authorizing the request...");
    }

}
