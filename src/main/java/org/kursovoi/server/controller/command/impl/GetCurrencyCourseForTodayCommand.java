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

@Component
@RequiredArgsConstructor
public class GetCurrencyCourseForTodayCommand implements Command {

    private final CurrencyCourseService service;
    private final ResponseSerializer<CurrencyCourseDto> serializer;

    @Override
    public String execute(String request) {
        return serializer.apply(service.getCurrencyCourseForToday());
    }

    @PostConstruct
    private void postConstruct() {
        CommandHolder.getInstance().addCommand(CommandType.GET_CURRENCY_COURSE_FOR_TODAY, this);
    }
}
