package com.example.udemy.logic;

import com.example.udemy.model.Task;
import com.example.udemy.model.TaskGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TempService {
    @Autowired
    List<String> temp(TaskGroupRepository repository) {
        //FIXME: N+1 of selects
        return repository.findAll().stream().flatMap(taskGroup -> taskGroup
                .getTasks().stream()).map(Task::getDescription).collect(Collectors.toList());
    }
}
