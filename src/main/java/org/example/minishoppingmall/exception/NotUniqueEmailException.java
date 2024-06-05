package org.example.minishoppingmall.exception;

public class NotUniqueEmailException extends RuntimeException{
    public NotUniqueEmailException() {
        super();
    }

    public NotUniqueEmailException(String message) {
        super(message);
    }

    public NotUniqueEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotUniqueEmailException(Throwable cause) {
        super(cause);
    }

    protected NotUniqueEmailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
