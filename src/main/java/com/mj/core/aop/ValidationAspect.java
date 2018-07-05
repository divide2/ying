package com.mj.core.aop;

import com.mj.core.er.Responser;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.validation.BindingResult;

/**
 * @author bvvy
 */
@Aspect
public class ValidationAspect {

    @Pointcut("execution(* com.mj.*.*.controller.*.*(..))")
    public void validPointcut() {

    }

    @Around("validPointcut() && args(..,br)")
    public Object validArount(ProceedingJoinPoint jp, BindingResult br) throws Throwable {
        if (br.hasErrors()) {

            return Responser.conflict(br.getAllErrors().toString());
        }
        return jp.proceed();
    }
}
