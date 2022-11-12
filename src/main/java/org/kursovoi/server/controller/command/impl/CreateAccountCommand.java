package org.kursovoi.server.controller.command.impl;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.controller.command.Command;
import org.kursovoi.server.dto.AccountDto;
import org.kursovoi.server.service.AccountService;
import org.kursovoi.server.util.json.RequestDeserializer;
import org.kursovoi.server.util.json.ResponseSerializer;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateAccountCommand implements Command {

    private final AccountService service;
    private final RequestDeserializer<AccountDto> deserializer;
    private final ResponseSerializer<String> serializer;

    @Override
    public String execute(String request) {
        var newAccount = deserializer.apply(request);
        service.createAccount(newAccount);
        return serializer.apply("created");
    }
}
