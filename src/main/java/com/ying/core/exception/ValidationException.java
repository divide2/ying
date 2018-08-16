package com.ying.core.exception;

import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * @author bvvy
 */
public class ValidationException extends RuntimeException {
    private List<ObjectError> errors;
    public ValidationException(List<ObjectError> errors) {
        super(errors.toString());
        this.errors = errors;
    }

    public ValidationException() {
        super();
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }

    protected ValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public List<ObjectError> getErrors() {
        return errors;
    }
}
