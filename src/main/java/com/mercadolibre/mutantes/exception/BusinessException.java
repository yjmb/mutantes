package com.mercadolibre.mutantes.exception;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -1190373922636228891L;

    public BusinessException() {
        super();
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }
}
