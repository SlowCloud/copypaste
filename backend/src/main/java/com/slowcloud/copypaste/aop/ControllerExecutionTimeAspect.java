package com.slowcloud.copypaste.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class ControllerExecutionTimeAspect {

    private static final Logger logger = LoggerFactory.getLogger(ControllerLoggerAspect.class);

    @Pointcut("within(com.slowcloud.copypaste..controller..*)")
    private void controllerPointcut() {}

    @Around("controllerPointcut()")
    public Object doLogging(ProceedingJoinPoint joinPoint) throws Throwable {


        Signature signature = joinPoint.getSignature();
        String targetClassName = signature.getDeclaringTypeName();

        logger.info(signature.toShortString());

        long startTime = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        }
        finally {
            long endTime = System.currentTimeMillis();
            logger.info("{} :: executed in {} ms", targetClassName, endTime - startTime);
        }

    }

}
