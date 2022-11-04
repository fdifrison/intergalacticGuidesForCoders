package dev.fdifrison.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    /*
     The annotation @Before and @After determines when the pointcut has to take place with respect to the method to which
     it is attached.
     The first "*" after execution( stands for "any return type"; we can also replace parts of the
     execution path with "*" to generalize the application of the pointcut (es. aop.*.checkout means to consider each
     class in the aop package that has a method checkout.
     The 2 dots inside the methods stand for "execute for any number of argument passed to the method", if not specified
     the pointcut will be executed only if the method accept no argument (or only the specific argument specified
    */
    @Before("execution(* dev.fdifrison.aop.ShoppingCart.checkout(..))")
    public void beforeLogger(JoinPoint jp) {
        System.out.println("Getting signature ... " + jp.getSignature());
        System.out.println("Getting args ... " + Arrays.toString(jp.getArgs()));
        System.out.println("Logger executed before");
    }

    @After("execution(* dev.fdifrison.aop.*.checkout(..))")
    public void afterLogger() {
        System.out.println("Logger executed after");
    }

    @Pointcut("execution(* dev.fdifrison.aop.ShoppingCart.quantity(..))")
    public  void afterReturningPointCut() {
    }

    @AfterReturning(pointcut = "afterReturningPointCut()", returning = "returnVal")
    public void afterReturning(Integer returnVal) {
        System.out.println("After returning ... " + returnVal);
    }


}
