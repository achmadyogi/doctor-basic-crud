package com.doctor.doctorbasiccrud.repository.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Task {
    @Id
    private Long id;
    private String name;
    private String description;
    private boolean completed;
    private Date created_time;
    private Date modified_time;

    public Task() {}

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setCreated_time(Date created_time) {
        this.created_time = created_time;
    }

    public void setModified_time(Date modified_time) {
        this.modified_time = modified_time;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public Date getCreated_time() {
        return created_time;
    }

    public Date getModified_time() {
        return modified_time;
    }
}
