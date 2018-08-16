package com.ying.core.exception;

/**
 * @author bvvy
 * 已经存在的exception
 */
public class AlreadyExistsException extends ValidationException {

    public AlreadyExistsException(String message) {
        super(message);
    }

    public AlreadyExistsException() {
        super();
    }

    public AlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
