package org.kursovoi.server.configuration;

import org.kursovoi.server.controller.command.CommandHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CommandHolder commandHolder() {
        return CommandHolder.getInstance();
    }
}
