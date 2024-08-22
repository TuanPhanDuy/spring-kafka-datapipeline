package com.backend.datapipeline.aspect;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Aspect
@Slf4j
@Component
public class LoggingAspect {

    @Pointcut("execution(* com.backend.datapipeline.controller.*.*(..))")
    public void loggingControllerPointCut() {
        throw new UnsupportedOperationException();
    }

    @Pointcut("execution(* com.backend.datapipeline.service.*.*(..))")
    public void loggingServicePointCut() {
        throw new UnsupportedOperationException();
    }

    @Before(value = "loggingControllerPointCut()")
    public void before(JoinPoint joinPoint) {
        log.info("{}{}", MessageFormat.format("{0} method invoke ", "Before") + joinPoint.getSignature());
    }

    @SneakyThrows
    @Around(value = "loggingServicePointCut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        log.info("{}{}", MessageFormat.format("{0} method invoke ", "Before"), joinPoint.getSignature());

        if (joinPoint.getArgs().length > 0)
            log.info("method invoked:: {}", joinPoint.getArgs()[0]);

        log.info("{}{}", MessageFormat.format("{0} method invoke ", "After"), joinPoint.getSignature());
        return joinPoint.proceed();
    }

    @AfterReturning(value = "loggingControllerPointCut()")
    public void afterReturning(JoinPoint joinPoint) {
        log.info("{}{}", MessageFormat.format("{0} method invoke ", "After returning") + joinPoint.getSignature());
    }

    @AfterThrowing(value = "loggingServicePointCut()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        if (e instanceof RuntimeException)
            log.error("{}{}", MessageFormat.format("{0} method invoke ", "After throwing") + e.getMessage());
    }
}
