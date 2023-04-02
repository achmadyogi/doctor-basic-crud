package com.doctor.doctorbasiccrud.biz.enums;

public enum ServiceNameEnum {
    GET_TASK(1, "GET_ONE_TASK"),
    GET_ALL_TASKS(2, "GET_ALL_TASKS"),
    PUT_TASK(3, "PUT_TASK"),
    DELETE_TASK(4, "DELETE_TASK"),
    SAVE_TASK(5, "SAVE_TASK")
    ;
    private final Integer id;
    private final String name;

    ServiceNameEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
