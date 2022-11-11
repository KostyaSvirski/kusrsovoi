package org.kursovoi.server.controller.command.impl;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.kursovoi.server.controller.command.Command;
import org.kursovoi.server.dto.AccountDto;
import org.kursovoi.server.service.AccountService;
import org.kursovoi.server.util.json.DtoSerializer;
import org.kursovoi.server.util.json.RequestDeserializer;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateAccountCommand implements Command {

    private final AccountService service;
    private final RequestDeserializer<AccountDto> deserializer;
    private final DtoSerializer<String> serializer;

    @Override
    public String execute(String request) {
        var newAccount = deserializer.apply(request);
        service.createAccount(newAccount);
        return serializer.apply("created");
    }
}
