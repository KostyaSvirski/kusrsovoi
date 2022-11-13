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
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetCardsOfAccountCommand implements Command {

    private final CardService service;
    private final RequestDeserializer<Long> deserializer;
    private final ResponseSerializer<List<CardDto>> serializer;

    @Override
    public String execute(String request) {
        long idAccount = deserializer.apply(request);
        return serializer.apply(service.getCardsOfAccount(idAccount));
    }

    @PostConstruct
    private void postConstruct() {
        CommandHolder.getInstance().addCommand(CommandType.GET_CARDS_OF_ACCOUNT, this);
    }
}
