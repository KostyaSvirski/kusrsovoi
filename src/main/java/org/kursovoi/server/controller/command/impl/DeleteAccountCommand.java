package org.kursovoi.server.controller.command.impl;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.controller.command.Command;
import org.kursovoi.server.controller.command.CommandHolder;
import org.kursovoi.server.controller.command.CommandType;
import org.kursovoi.server.service.AccountService;
import org.kursovoi.server.util.json.RequestDeserializer;
import org.kursovoi.server.util.json.ResponseSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class DeleteAccountCommand implements Command {

    private final AccountService service;
    private final RequestDeserializer<Long> deserializer;
    private final ResponseSerializer<String> serializer;

    @Override
    public String execute(String request) {
        var id = deserializer.apply(request);
        service.deleteAccount(id);
        return serializer.apply("deleted");
    }

    @PostConstruct
    private void postConstruct() {
        CommandHolder.getInstance().addCommand(CommandType.DELETE_ACCOUNT, this);
    }
}
