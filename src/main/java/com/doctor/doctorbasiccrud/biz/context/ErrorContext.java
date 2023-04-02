package com.doctor.doctorbasiccrud.biz.context;

import com.doctor.doctorbasiccrud.biz.enums.ErrorCodeEnum;

public class ErrorContext {
    private final ErrorCodeEnum errorCode;
    private final String errorDescription;

    public ErrorContext(ErrorCodeEnum errorCode, String errorDescription) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public ErrorCodeEnum getErrorCode() {
        return errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }
}
