package com.old.time.aspect;

import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HttpAspect {

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.old.time.controller.UserController.*(..))")
    public void log() {

    }

    @Before("log()")
    public void doBefore() {
        logger.info("doBefore");

    }

    @After("log()")
    public void doAfter() {
        logger.info("doAfter");

    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        logger.info("response={}", object);

    }
}
