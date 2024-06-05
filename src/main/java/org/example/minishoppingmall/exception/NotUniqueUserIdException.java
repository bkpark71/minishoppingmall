package org.example.minishoppingmall.exception;

public class NotUniqueUserIdException extends RuntimeException{
    public NotUniqueUserIdException() {
        super();
    }

    public NotUniqueUserIdException(String message) {
        super(message);
    }

    public NotUniqueUserIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotUniqueUserIdException(Throwable cause) {
        super(cause);
    }

    protected NotUniqueUserIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
