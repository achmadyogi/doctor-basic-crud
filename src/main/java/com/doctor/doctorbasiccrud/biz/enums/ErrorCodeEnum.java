package com.doctor.doctorbasiccrud.biz.enums;

import org.springframework.http.HttpStatus;

public enum ErrorCodeEnum {
    SUCCESS(1, "SUCCESS", "D01", HttpStatus.OK, "General success"),
    TASK_NOT_FOUND(2, "TASK_NOT_FOUND", "D02", HttpStatus.NOT_FOUND, "Task not found"),
    TASK_ALREADY_EXIST(3, "TASK_ALREADY_EXIST", "D03", HttpStatus.CONFLICT, "Task already exist"),
    UNKNOWN_ERROR(3, "UNKNOWN_ERROR", "D04", HttpStatus.INTERNAL_SERVER_ERROR, "Unknown error"),
    PARAM_ILLEGAL(4, "PARAM_ILLEGAL", "D05", HttpStatus.BAD_REQUEST, "Param illegal")
    ;
    private final Integer id;
    private final String name;
    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String description;

    ErrorCodeEnum(Integer id, String name, String errorCode, HttpStatus httpStatus, String description) {
        this.id = id;
        this.name = name;
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getDescription() {
        return description;
    }
}
