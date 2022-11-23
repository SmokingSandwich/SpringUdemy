package com.example.udemy.logic;

import com.example.udemy.TaskConfigurationProperties;
import com.example.udemy.model.*;
import com.example.udemy.model.projection.GroupReadModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectService {
    ProjectRepository repository;
    TaskGroupRepository taskGroupRepository;
    TaskConfigurationProperties config;

    ProjectService(final ProjectRepository repository,final TaskGroupRepository taskGroupRepository, final TaskConfigurationProperties config) {
        this.repository = repository;
        this.taskGroupRepository = taskGroupRepository;
        this.config = config;
    }

    public List<Project> readAll() {
        return repository.findAll();
    }

    public Project save(final Project toSave) {
        return repository.save(toSave);
    }

    public GroupReadModel createGroup(LocalDateTime deadline, int projectId) {
        if (!config.getTemplate().isAllowMultipleTasks() && taskGroupRepository.existsByDoneIsFalseAndProjectId(projectId)) {
            throw new IllegalStateException("Only one undone group from project is allowed");
        }
        TaskGroup result = repository.findById(projectId).map(project -> {
            var targetGroup = new TaskGroup();
            targetGroup.setDescription(project.getDescription());
            targetGroup.setTasks(project.getSteps().stream()
                    .map(step -> new Task(step.getDescription(),
                            deadline.plusDays(step.getDaysToDeadline()))).collect(Collectors.toSet())
            );
            targetGroup.setProject(project);
            return taskGroupRepository.save(targetGroup);
        }).orElseThrow(() -> new IllegalArgumentException("Project with given id not found"));
        return new GroupReadModel(result);
    }
}
