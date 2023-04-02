package com.doctor.doctorbasiccrud.service.response;

import com.doctor.doctorbasiccrud.biz.context.ErrorContext;

public abstract class BaseResponse {
    protected final ErrorContext errorContext;

    public BaseResponse(ErrorContext errorContext) {
        this.errorContext = errorContext;
    }

    public ErrorContext getErrorContext() {
        return errorContext;
    }
}
