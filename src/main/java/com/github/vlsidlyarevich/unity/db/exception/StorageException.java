package com.github.vlsidlyarevich.unity.db.exception;


public class StorageException extends RuntimeException {

    private static final long serialVersionUID = -8837336635325252439L;

    public StorageException() {
        super();
    }

    public StorageException(final String message) {
        super(message);
    }

    public StorageException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
