package com.doctor.doctorbasiccrud.biz.exception;

import com.doctor.doctorbasiccrud.biz.enums.ErrorCodeEnum;

public class TaskException extends RuntimeException{
    private final ErrorCodeEnum errorCodeEnum;

    public TaskException(ErrorCodeEnum errorCodeEnum, String message) {
        super(message);
        this.errorCodeEnum = errorCodeEnum;
    }

    public ErrorCodeEnum getErrorCodeEnum() {
        return errorCodeEnum;
    }
}
