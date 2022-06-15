package com.hackerrank.sample.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
@Component
public class MetricsLogger {

    @Around("execution(* com.hackerrank.sample.controller..*.* (..))")
    public Object logBeforeAndAfterServiceMethods(ProceedingJoinPoint pjp) throws Throwable {

        // Get intercepted method details
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        String classAndMethodName = className + "->" + methodName;

        log.info("{} has started execution.", classAndMethodName);

        // Measure method execution time
        StopWatch stopWatch = new StopWatch(classAndMethodName);
        stopWatch.start(methodName);
        Object resultOfMethodCall = pjp.proceed();
        stopWatch.stop();

        log.info("{} finished execution", classAndMethodName);
        log.info("Execution time: {}", stopWatch.prettyPrint());

        return resultOfMethodCall;
    }

}
