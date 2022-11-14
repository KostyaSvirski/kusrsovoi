package org.kursovoi.server.controller.command.impl;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.controller.command.Command;
import org.kursovoi.server.controller.command.CommandHolder;
import org.kursovoi.server.controller.command.CommandType;
import org.kursovoi.server.dto.CurrencyCourseDto;
import org.kursovoi.server.service.CurrencyCourseService;
import org.kursovoi.server.util.json.ResponseSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllCurrencyCoursesCommand implements Command {

    private final CurrencyCourseService service;
    private final ResponseSerializer<List<CurrencyCourseDto>> serializer;

    @Override
    public String execute(String request) {
        return serializer.apply(service.getAllCurrencyCourses());
    }

    @PostConstruct
    private void postConstruct() {
        CommandHolder.getInstance().addCommand(CommandType.GET_ALL_CURRENCY_COURSES, this);
    }
}
