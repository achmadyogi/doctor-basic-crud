package com.doctor.doctorbasiccrud.biz.impl;

import com.doctor.doctorbasiccrud.biz.context.ErrorContext;
import com.doctor.doctorbasiccrud.biz.enums.ErrorCodeEnum;
import com.doctor.doctorbasiccrud.biz.enums.ServiceNameEnum;
import com.doctor.doctorbasiccrud.biz.exception.TaskException;
import com.doctor.doctorbasiccrud.common.callback.Transaction;
import com.doctor.doctorbasiccrud.common.callback.TransactionCallback;
import com.doctor.doctorbasiccrud.common.util.AssertUtils;
import com.doctor.doctorbasiccrud.common.util.StringUtils;
import com.doctor.doctorbasiccrud.repository.TaskRepository;
import com.doctor.doctorbasiccrud.repository.model.Task;
import com.doctor.doctorbasiccrud.service.facade.TaskFacade;
import com.doctor.doctorbasiccrud.service.request.TaskRequest;
import com.doctor.doctorbasiccrud.service.response.BaseResponse;
import com.doctor.doctorbasiccrud.service.response.TaskResponse;
import com.doctor.doctorbasiccrud.service.response.TaskResponsePage;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskFacadeImpl implements TaskFacade {

    @Autowired
    private TaskRepository taskRepository;

    private final Map<Long, Task> database = new HashMap<>();

    @PostConstruct
    public void populateData() {
        for (Task task : taskRepository.findAll()) {
            database.put(task.getId(), task);
        }
    }

    @Override
    public TaskResponsePage getTasks() {
        return (TaskResponsePage) Transaction.process(new TransactionCallback() {
            @Override
            public ServiceNameEnum getServiceName() {
                return ServiceNameEnum.GET_ALL_TASKS;
            }

            @Override
            public void validate() {
                // No business validation needed
            }

            @Override
            public BaseResponse doTransaction() {
                List<Task> tasks = database.values().stream().toList();
                TaskResponsePage result = new TaskResponsePage(new ErrorContext(ErrorCodeEnum.SUCCESS, null));
                result.setTasks(tasks);
                return result;
            }
        });
    }

    @Override
    public TaskResponse getTask(Long id) {
        return (TaskResponse) Transaction.process(new TransactionCallback() {
            @Override
            public ServiceNameEnum getServiceName() {
                return ServiceNameEnum.GET_TASK;
            }

            @Override
            public void validate() {
                // No business validation needed
            }

            @Override
            public BaseResponse doTransaction() {
                if (database.containsKey(id)) {
                    return new TaskResponse(database.get(id), new ErrorContext(ErrorCodeEnum.SUCCESS, null));
                } else {
                    throw new TaskException(ErrorCodeEnum.TASK_NOT_FOUND, "Cannot find a taks with id:" + id);
                }
            }
        });
    }

    @Override
    public TaskResponse saveTask(TaskRequest request) {
        return (TaskResponse) Transaction.process(new TransactionCallback() {
            @Override
            public ServiceNameEnum getServiceName() {
                return ServiceNameEnum.SAVE_TASK;
            }

            @Override
            public void validate() {
                // Validate params
                AssertUtils.notBlank("taskName", request.getTaskName());
                AssertUtils.notNull("id", request.getId());

                // The id must not exist in the database
                if (database.containsKey(request.getId())) {
                    throw new TaskException(ErrorCodeEnum.TASK_ALREADY_EXIST, "Task with id:" + request.getId() + " already exists");
                }
            }

            @Override
            public BaseResponse doTransaction() {
                request.setCompleted(false);
                Task task = TaskRequest.convert(request);
                Task stored = taskRepository.save(task);
                database.put(stored.getId(), stored);
                return new TaskResponse(stored, new ErrorContext(ErrorCodeEnum.SUCCESS, null));
            }
        });
    }

    @Override
    public TaskResponse putTask(TaskRequest request) {
        return (TaskResponse) Transaction.process(new TransactionCallback() {
            @Override
            public ServiceNameEnum getServiceName() {
                return ServiceNameEnum.PUT_TASK;
            }

            @Override
            public void validate() {
                // At least one data is available
                boolean available = !StringUtils.isBlank(request.getTaskName())
                        || !StringUtils.isBlank(request.getTaskDescription())
                        || !StringUtils.isNull(request.getCompleted());
                if (!available) {
                    throw new TaskException(ErrorCodeEnum.PARAM_ILLEGAL, "At least one field must be available");
                }

                // Check whether the task exists
                if (!database.containsKey(request.getId())) {
                    throw new TaskException(ErrorCodeEnum.TASK_NOT_FOUND, "Task with id:" + request.getId() + " cannot be found");
                }
            }

            @Override
            public BaseResponse doTransaction() {
                if(database.containsKey(request.getId())) {
                    Task task = database.get(request.getId());
                    task.setName(StringUtils.isBlank(request.getTaskName()) ? task.getName() : request.getTaskName());
                    task.setDescription(StringUtils.isBlank(request.getTaskDescription()) ? task.getDescription() : request.getTaskDescription());
                    task.setCompleted(StringUtils.isNull(request.getCompleted()) ? task.isCompleted() : request.getCompleted());
                    task.setModified_time(new Date());
                    Task stored = taskRepository.save(task);
                    database.put(stored.getId(), stored);
                    return new TaskResponse(stored, new ErrorContext(ErrorCodeEnum.SUCCESS, null));
                } else {
                    // Should not happen because it is already validated
                    return new TaskResponse(new ErrorContext(ErrorCodeEnum.UNKNOWN_ERROR, null));
                }
            }
        });
    }

    @Override
    public TaskResponse deleteTask(Long id) {
        return (TaskResponse) Transaction.process(new TransactionCallback() {
            @Override
            public ServiceNameEnum getServiceName() {
                return ServiceNameEnum.DELETE_TASK;
            }

            @Override
            public void validate() {
                // No business validation needed
            }

            @Override
            public BaseResponse doTransaction() {
                taskRepository.deleteById(id);
                database.remove(id);
                return new TaskResponse(new ErrorContext(ErrorCodeEnum.SUCCESS, null));
            }
        });
    }
}
