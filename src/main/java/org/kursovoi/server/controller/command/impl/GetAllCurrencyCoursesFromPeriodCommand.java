package org.kursovoi.server.controller.command.impl;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.controller.command.Command;
import org.kursovoi.server.controller.command.CommandHolder;
import org.kursovoi.server.controller.command.CommandType;
import org.kursovoi.server.dto.CurrencyCourseDto;
import org.kursovoi.server.dto.PeriodOfCurrencyCoursesDto;
import org.kursovoi.server.service.CurrencyCourseService;
import org.kursovoi.server.util.json.RequestDeserializer;
import org.kursovoi.server.util.json.ResponseSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllCurrencyCoursesFromPeriodCommand implements Command {

    private final CurrencyCourseService service;
    private final RequestDeserializer<PeriodOfCurrencyCoursesDto> deserializer;
    private final ResponseSerializer<List<CurrencyCourseDto>> serializer;

    @Override
    public String execute(String request) {
        PeriodOfCurrencyCoursesDto dto = deserializer.apply(request, PeriodOfCurrencyCoursesDto.class);
        return serializer.apply(service.getAllCurrencyCoursesFromPeriod(dto));
    }

    @PostConstruct
    private void postConstruct() {
        CommandHolder.getInstance().addCommand(CommandType.GET_ALL_CURRENCY_COURSES_FROM_PERIOD, this);
    }
}
