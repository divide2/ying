package com.mj.core.web;

import com.mj.core.data.resp.Messager;
import com.mj.core.er.Responser;
import com.mj.core.exception.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author bvvy
 */
@ControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Messager> valid(ValidationException exception) {

        return Responser.conflict(exception.getErrors().toString());
    }
}
