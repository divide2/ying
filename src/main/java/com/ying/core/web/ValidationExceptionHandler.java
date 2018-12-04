package com.ying.core.web;

import com.ying.core.data.resp.Messager;
import com.ying.core.er.Responser;
import com.ying.core.exception.AlreadyExistsException;
import com.ying.core.exception.ValidationException;
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
        return Responser.conflict(exception.getMessage());
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Messager> valid(AlreadyExistsException exception) {
        return Responser.conflict("exist");
    }

}
