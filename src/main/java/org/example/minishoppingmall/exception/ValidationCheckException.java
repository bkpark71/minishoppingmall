package org.example.minishoppingmall.exception;

public class ValidationCheckException extends RuntimeException{
    public ValidationCheckException() {
        super();
    }

    public ValidationCheckException(String message) {
        super(message);
    }

    public ValidationCheckException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationCheckException(Throwable cause) {
        super(cause);
    }

    protected ValidationCheckException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
