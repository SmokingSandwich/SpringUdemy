package com.example.udemy.model.projection;

import com.example.udemy.model.Task;
import com.example.udemy.model.TaskGroup;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class GroupTaskWriteModel {
    @NotBlank(message = "Task description can't be null !")
    private String description;
    private LocalDateTime deadline;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public Task toTask(TaskGroup group) {
        return new Task(description, deadline, group);
    }
}
