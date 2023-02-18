package org.kursovoi.server.controller.command.impl;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.controller.command.Command;
import org.kursovoi.server.controller.command.CommandHolder;
import org.kursovoi.server.controller.command.CommandType;
import org.kursovoi.server.dto.ChangeStatusOfCardDto;
import org.kursovoi.server.service.CardService;
import org.kursovoi.server.util.json.RequestDeserializer;
import org.kursovoi.server.util.json.ResponseSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class UpdateStatusOfCardCommand implements Command {

    private final CardService service;
    private final RequestDeserializer<ChangeStatusOfCardDto> deserializer;
    private final ResponseSerializer<String> serializer;

    @Override
    public String execute(String request) {
        ChangeStatusOfCardDto dto = deserializer.apply(request, ChangeStatusOfCardDto.class);
        service.changeStatusOfCard(dto);
        return serializer.apply("Status of card changed");
    }

    @PostConstruct
    private void postConstruct() {
        CommandHolder.getInstance().addCommand(CommandType.UPDATE_STATUS_OF_CARD, this);
    }
}
