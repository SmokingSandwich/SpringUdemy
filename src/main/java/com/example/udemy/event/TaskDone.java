package com.example.udemy.event;

import com.example.udemy.model.Task;

import java.time.Clock;

public class TaskDone extends TaskEvent {
    public TaskDone(Task source) {
        super(source.getId(), Clock.systemDefaultZone());
    }
}
