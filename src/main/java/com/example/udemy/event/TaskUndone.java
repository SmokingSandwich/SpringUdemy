package com.example.udemy.event;

import com.example.udemy.model.Task;

import java.time.Clock;

public class TaskUndone extends TaskEvent {
    public TaskUndone(Task source) {
        super(source.getId(), Clock.systemDefaultZone());
    }
}
