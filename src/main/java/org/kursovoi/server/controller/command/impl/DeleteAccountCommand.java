package org.kursovoi.server.controller.command.impl;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.controller.command.Command;
import org.kursovoi.server.service.AccountService;
import org.kursovoi.server.util.json.DtoSerializer;
import org.kursovoi.server.util.json.RequestDeserializer;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteAccountCommand implements Command {

    private final AccountService service;
    private final RequestDeserializer<Long> deserializer;
    private final DtoSerializer<String> serializer;

    @Override
    public String execute(String request) {
        var id = deserializer.apply(request);
        service.deleteAccount(id);
        return serializer.apply("deleted");
    }
}
