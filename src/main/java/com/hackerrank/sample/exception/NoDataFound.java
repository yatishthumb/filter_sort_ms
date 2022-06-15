package com.hackerrank.sample.exception;

public class NoDataFound extends RuntimeException {

    private ErrorCode errorCode;

    public NoDataFound() {
        super();
    }

    public NoDataFound(String message) {
        super(message);
    }

    public NoDataFound(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
