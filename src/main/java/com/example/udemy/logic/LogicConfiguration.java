package com.example.udemy.logic;

import com.example.udemy.TaskConfigurationProperties;
import com.example.udemy.model.ProjectRepository;
import com.example.udemy.model.TaskGroupRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogicConfiguration {
    @Bean
    ProjectService service(ProjectRepository repository,
                           TaskGroupRepository taskGroupRepository,
                           TaskConfigurationProperties config)
    {
        return new ProjectService(repository, taskGroupRepository, config);
    }
}
