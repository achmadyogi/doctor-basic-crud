package com.doctor.doctorbasiccrud.service.response;

import com.doctor.doctorbasiccrud.biz.context.ErrorContext;
import com.doctor.doctorbasiccrud.repository.model.Task;

import java.util.List;

public class TaskResponsePage extends BaseResponse{
    private List<Task> tasks;

    public TaskResponsePage(ErrorContext errorContext) {
        super(errorContext);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
