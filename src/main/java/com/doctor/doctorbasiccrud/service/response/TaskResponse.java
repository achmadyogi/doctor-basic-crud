package com.doctor.doctorbasiccrud.service.response;

import com.doctor.doctorbasiccrud.biz.context.ErrorContext;
import com.doctor.doctorbasiccrud.repository.model.Task;

public class TaskResponse extends BaseResponse {
    private Task task;

    public TaskResponse(ErrorContext errorContext) {
        super(errorContext);
    }

    public TaskResponse(Task task, ErrorContext errorContext) {
        super(errorContext);
        this.task = task;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
