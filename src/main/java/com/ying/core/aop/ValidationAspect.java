package com.ying.core.aop;

import com.ying.core.exception.ValidationException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.stream.Collectors;

import static com.ying.core.val.Punctuation.COMMA;


/**
 * @author bvvy
 */
@Aspect
@Component
public class ValidationAspect {

    @Pointcut("execution(* com.ying.*.controller.*.*(..))")
    public void validPointcut() {

    }

    @Around("validPointcut() && args(..,br)")
    public Object validAround(ProceedingJoinPoint jp, Errors br) throws Throwable {
        if (br.hasErrors()) {
            List<ObjectError> allErrors = br.getAllErrors();
            String messages = allErrors.stream()
                    .map(objectError -> {
                        if (objectError instanceof FieldError) {
                            FieldError fieldError = (FieldError) objectError;
                            return fieldError.getField() + fieldError.getDefaultMessage();
                        } else {
                            return objectError.getObjectName() + objectError.getDefaultMessage();
                        }
                    })
                    .collect(Collectors.joining(COMMA));
            throw new ValidationException(messages);
        }
        return jp.proceed();
    }
}
