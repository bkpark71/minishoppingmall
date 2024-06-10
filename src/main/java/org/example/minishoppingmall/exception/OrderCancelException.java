package org.example.minishoppingmall.exception;

public class OrderCancelException extends RuntimeException{
    public OrderCancelException() {
        super();
    }

    public OrderCancelException(String message) {
        super(message);
    }

    public OrderCancelException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderCancelException(Throwable cause) {
        super(cause);
    }

    protected OrderCancelException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
