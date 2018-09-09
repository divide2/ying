package com.ying.core.aop;

import com.ying.core.exception.ValidationException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;


/**
 * @author bvvy
 */
@Aspect
@Component
public class ValidationAspect {

    @Pointcut("execution(* com.ying.*.*.controller.*.*(..))")
    public void validPointcut() {

    }

    @Around("validPointcut() && args(..,br)")
    public Object validAround(ProceedingJoinPoint jp, BindingResult br) throws Throwable {
        if (br.hasErrors()) {
            throw new ValidationException(br.getAllErrors());
        }
        return jp.proceed();
    }
}
