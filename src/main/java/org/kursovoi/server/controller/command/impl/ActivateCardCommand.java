package org.kursovoi.server.controller.command.impl;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.controller.command.Command;
import org.kursovoi.server.controller.command.CommandHolder;
import org.kursovoi.server.controller.command.CommandType;
import org.kursovoi.server.dto.ActivateCardDto;
import org.kursovoi.server.service.CardService;
import org.kursovoi.server.util.json.RequestDeserializer;
import org.kursovoi.server.util.json.ResponseSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class ActivateCardCommand implements Command {

    private final CardService service;
    private final RequestDeserializer<ActivateCardDto> deserializer;
    private final ResponseSerializer<String> serializer;

    @Override
    public String execute(String request) {
        ActivateCardDto dto = deserializer.apply(request, ActivateCardDto.class);
        service.activateCard(dto);
        return serializer.apply("card activated");
    }

    @PostConstruct
    private void postConstruct() {
        CommandHolder.getInstance().addCommand(CommandType.ACTIVATE_CARD, this);
    }
}
