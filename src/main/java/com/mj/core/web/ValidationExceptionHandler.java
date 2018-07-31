package com.mj.core.web;

import com.mj.core.data.resp.Messager;
import com.mj.core.er.Responser;
import com.mj.core.exception.AlreadyExistsException;
import com.mj.core.exception.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author bvvy
 */
@ControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Messager> valid(ValidationException exception) {

        for (ObjectError objectError : exception.getErrors()) {
            System.out.println(objectError.getObjectName());
            System.out.println(objectError.getDefaultMessage());
        }
        return Responser.conflict(exception.getErrors().toString());
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Messager> valid(AlreadyExistsException exception) {
        return Responser.conflict("exist");
    }

}
