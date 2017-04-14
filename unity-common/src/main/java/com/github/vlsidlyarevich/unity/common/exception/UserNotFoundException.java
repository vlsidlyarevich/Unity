package com.github.vlsidlyarevich.unity.common.exception;


public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 2967357473314163159L;

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
