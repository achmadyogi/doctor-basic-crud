package com.doctor.doctorbasiccrud.service.request;

import com.doctor.doctorbasiccrud.repository.model.Task;

import java.util.Date;

public class TaskRequest extends BaseRequest{
    private Long id;
    private String taskName;
    private String taskDescription;
    private Boolean completed;

    public static Task convert(TaskRequest request) {
        if (request == null) {
            return null;
        }
        Task task = new Task();
        task.setId(request.getId());
        task.setName(request.getTaskName());
        task.setDescription(request.getTaskDescription());
        task.setCompleted(request.getCompleted());
        task.setCreated_time(new Date());
        task.setModified_time(new Date());
        return task;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
