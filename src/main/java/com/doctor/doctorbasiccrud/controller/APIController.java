package com.doctor.doctorbasiccrud.controller;

import com.doctor.doctorbasiccrud.biz.context.ErrorContext;
import com.doctor.doctorbasiccrud.service.facade.TaskFacade;
import com.doctor.doctorbasiccrud.service.request.TaskRequest;
import com.doctor.doctorbasiccrud.service.response.TaskResponse;
import com.doctor.doctorbasiccrud.service.response.TaskResponsePage;
import com.doctor.doctorbasiccrud.biz.enums.ErrorCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class APIController {

    @Autowired
    TaskFacade taskFacade;

    @GetMapping(path="/tasks")
    public @ResponseBody ResponseEntity<TaskResponsePage> getTasks() {
        TaskResponsePage response = taskFacade.getTasks();
        return new ResponseEntity<>(response, response.getErrorContext().getErrorCode().getHttpStatus());
    }

    @GetMapping(path="/tasks/{id}")
    public @ResponseBody ResponseEntity<TaskResponse> getTask(@PathVariable(name = "id") String id) {
        try {
            Long idLong = Long.parseLong(id);
            TaskResponse response = taskFacade.getTask(idLong);
            return new ResponseEntity<>(response, response.getErrorContext().getErrorCode().getHttpStatus());
        } catch (NumberFormatException e) {
            ErrorContext context = new ErrorContext(ErrorCodeEnum.PARAM_ILLEGAL, "Wrong format for task id");
            return new ResponseEntity<>(new TaskResponse(context), context.getErrorCode().getHttpStatus());
        }
    }

    @PostMapping(path="/tasks")
    public @ResponseBody ResponseEntity<TaskResponse> saveTasks(@RequestBody TaskRequest request) {
        TaskResponse response = taskFacade.saveTask(request);
        return new ResponseEntity<>(response, response.getErrorContext().getErrorCode().getHttpStatus());
    }

    @PutMapping(path="/tasks/{id}")
    public @ResponseBody ResponseEntity<TaskResponse> putTask(@RequestBody TaskRequest request,
                                               @PathVariable(name = "id") String id) {
        try {
            Long idLong = Long.parseLong(id);
            request.setId(idLong);
            TaskResponse response = taskFacade.putTask(request);
            return new ResponseEntity<>(response, response.getErrorContext().getErrorCode().getHttpStatus());
        } catch (NumberFormatException e) {
            ErrorContext context = new ErrorContext(ErrorCodeEnum.PARAM_ILLEGAL, "Wrong format for task id");
            return new ResponseEntity<>(new TaskResponse(context), context.getErrorCode().getHttpStatus());
        }
    }

    @DeleteMapping(path = "/tasks/{id}")
    public @ResponseBody ResponseEntity<TaskResponse> deleteTask(@PathVariable(name = "id") String id) {
        try {
            TaskResponse response = taskFacade.deleteTask(Long.parseLong(id));
            return new ResponseEntity<>(response, response.getErrorContext().getErrorCode().getHttpStatus());
        } catch (NumberFormatException e) {
            ErrorContext context = new ErrorContext(ErrorCodeEnum.PARAM_ILLEGAL, "Wrong format for task id!");
            return new ResponseEntity<>(new TaskResponse(context), context.getErrorCode().getHttpStatus());
        }
    }
}
