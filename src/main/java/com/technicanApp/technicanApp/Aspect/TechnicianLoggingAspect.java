package com.technicanApp.technicanApp.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import java.util.logging.Logger;
@Aspect
@Component
public class TechnicianLoggingAspect {
    private Logger mylogger = Logger.getLogger(getClass().getName());

    //setup pointcut declarations
    @Pointcut("execution(* com.spring_course.spring_learning.Controller.*.*(..))")
    private void forControllerPackage(){

    }
    @Pointcut("execution(* com.spring_course.spring_learning.DAO.*.*(..))")
    private void forDAOPackage(){

    }

    @Pointcut("execution(* com.spring_course.spring_learning.TechnicianService.*.*(..))")
    private void forTechnicianServicePackage(){

    }

    @Pointcut("forControllerPackage() || forDAOPackage() || forTechnicianServicePackage() ")
    private void forAppFlow(){

    }
    @Before("forAppFlow()")
    public void before(JoinPoint jointPoint){
        String method=jointPoint.getSignature().toShortString();
        mylogger.info("====> in @Before: calling method: "+method);

        Object[] args=jointPoint.getArgs();

        for (Object arg: args){
            mylogger.info("===>> argument: "+arg);
        }

    }

    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "result"
    )
    public void AfterReturning(JoinPoint joinPoint,Object result){
        String method = joinPoint.getSignature().toShortString();
        mylogger.info("====> in @AfterReturning: from method : "+method);

        mylogger.info("====> result: "+result);
    }


}
