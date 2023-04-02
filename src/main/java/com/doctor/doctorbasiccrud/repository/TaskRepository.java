package com.doctor.doctorbasiccrud.repository;

import com.doctor.doctorbasiccrud.repository.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TaskRepository extends CrudRepository<Task, Long>, PagingAndSortingRepository<Task, Long> {

}
