package com.example.udemy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

@SpringBootApplication
public class TodoApplication{

    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }

	@Bean
	Validator validator() {
		return new LocalValidatorFactoryBean();
	}
}
