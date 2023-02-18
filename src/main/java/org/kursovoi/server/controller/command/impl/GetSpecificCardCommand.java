package org.kursovoi.server.controller.command.impl;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.controller.command.Command;
import org.kursovoi.server.controller.command.CommandHolder;
import org.kursovoi.server.controller.command.CommandType;
import org.kursovoi.server.dto.CardDto;
import org.kursovoi.server.service.CardService;
import org.kursovoi.server.util.json.RequestDeserializer;
import org.kursovoi.server.util.json.ResponseSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class GetSpecificCardCommand implements Command {

    private final CardService service;
    private final RequestDeserializer<Long> deserializer;
    private final ResponseSerializer<CardDto> serializer;

    @Override
    public String execute(String request) {
        long idCard = deserializer.apply(request, Long.class);
        return serializer.apply(service.getSpecificCardDto(idCard));
    }

    @PostConstruct
    private void postConstruct() {
        CommandHolder.getInstance().addCommand(CommandType.GET_SPECIFIC_CARD, this);
    }
}
