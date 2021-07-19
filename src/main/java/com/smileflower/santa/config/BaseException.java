package com.smileflower.santa.config;



public class BaseException extends Exception {
    private BaseResponseStatus status;

    public BaseException(BaseResponseStatus status) {
        this.status = status;
    }

    public BaseException(String message, BaseResponseStatus status) {
        super(message);
        this.status = status;
    }

    public BaseException(String message, Throwable cause, BaseResponseStatus status) {
        super(message, cause);
        this.status = status;
    }

    public BaseException(Throwable cause, BaseResponseStatus status) {
        super(cause);
        this.status = status;
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, BaseResponseStatus status) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.status = status;
    }

    public BaseResponseStatus getStatus() {
        return status;
    }

    public void setStatus(BaseResponseStatus status) {
        this.status = status;
    }
}