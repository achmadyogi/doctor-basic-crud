package com.doctor.doctorbasiccrud.service.facade;

import com.doctor.doctorbasiccrud.service.request.TaskRequest;
import com.doctor.doctorbasiccrud.service.response.TaskResponse;
import com.doctor.doctorbasiccrud.service.response.TaskResponsePage;

public interface TaskFacade {
    TaskResponsePage getTasks();
    TaskResponse getTask(Long id);
    TaskResponse saveTask(TaskRequest request);
    TaskResponse putTask(TaskRequest request);
    TaskResponse deleteTask(Long id);
}
