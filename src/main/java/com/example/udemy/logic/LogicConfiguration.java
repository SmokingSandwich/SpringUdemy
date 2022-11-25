package com.example.udemy.logic;

import com.example.udemy.TaskConfigurationProperties;
import com.example.udemy.model.ProjectRepository;
import com.example.udemy.model.TaskGroupRepository;
import com.example.udemy.model.TaskRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
public class LogicConfiguration {
    @Bean
    ProjectService service(ProjectRepository repository,
                           TaskGroupRepository taskGroupRepository,
                           TaskConfigurationProperties config)
    {
        return new ProjectService(repository, taskGroupRepository, config);
    }

    @Bean
    TaskGroupService taskGroupService(TaskGroupRepository taskGroupRepository,
                                      TaskRepository taskRepository) {
        return new TaskGroupService(taskGroupRepository, taskRepository);
    }
}
