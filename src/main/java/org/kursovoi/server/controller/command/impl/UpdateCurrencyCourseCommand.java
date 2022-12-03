package org.kursovoi.server.controller.command.impl;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.controller.command.Command;
import org.kursovoi.server.controller.command.CommandHolder;
import org.kursovoi.server.controller.command.CommandType;
import org.kursovoi.server.dto.UpdateCurrencyCourseDto;
import org.kursovoi.server.service.CurrencyCourseService;
import org.kursovoi.server.util.json.RequestDeserializer;
import org.kursovoi.server.util.json.ResponseSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class UpdateCurrencyCourseCommand implements Command {

    private final CurrencyCourseService service;
    private final RequestDeserializer<UpdateCurrencyCourseDto> deserializer;
    private final ResponseSerializer<String> serializer;

    @Override
    public String execute(String request) {
        UpdateCurrencyCourseDto dto = deserializer.apply(request, UpdateCurrencyCourseDto.class);
        service.updateCurrencyCourse(dto);
        return serializer.apply("Currency course updated");
    }

    @PostConstruct
    private void postConstruct() {
        CommandHolder.getInstance().addCommand(CommandType.UPDATE_CURRENCY_COURSE, this);
    }
}
