package org.kursovoi.server.controller.command.impl;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.controller.command.Command;
import org.kursovoi.server.controller.command.CommandHolder;
import org.kursovoi.server.controller.command.CommandType;
import org.kursovoi.server.dto.CreateCardDto;
import org.kursovoi.server.service.CardService;
import org.kursovoi.server.util.json.RequestDeserializer;
import org.kursovoi.server.util.json.ResponseSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class CreateCardCommand implements Command {

    private final CardService service;
    private final RequestDeserializer<CreateCardDto> deserializer;
    private final ResponseSerializer<String> serializer;

    @Override
    public String execute(String request) {
        CreateCardDto dto = deserializer.apply(request, CreateCardDto.class);
        service.createCard(dto);
        return serializer.apply("Card created");
    }

    @PostConstruct
    private void postConstruct() {
        CommandHolder.getInstance().addCommand(CommandType.CREATE_CARD, this);
    }
}
