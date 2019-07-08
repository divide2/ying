package com.divide2.core.web;

import com.divide2.core.data.resp.Messager;
import com.divide2.core.er.Responser;
import com.divide2.core.exception.AlreadyExistsException;
import com.divide2.core.exception.NotFoundException;
import com.divide2.core.exception.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

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

    @ExceptionHandler({NotFoundException.class, EntityNotFoundException.class})
    public ResponseEntity<Messager> valid(NotFoundException exception) {
        return ResponseEntity.notFound().build();
    }
}
