package org.kursovoi.server.controller.command.impl;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.controller.command.Command;
import org.kursovoi.server.dto.AccountDto;
import org.kursovoi.server.service.AccountService;
import org.kursovoi.server.util.json.DtoSerializer;
import org.kursovoi.server.util.json.ListSerializer;
import org.kursovoi.server.util.json.RequestDeserializer;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetAccountsOfUserCommand implements Command {

    private final AccountService service;
    private final RequestDeserializer<Long> deserializer;
    private final DtoSerializer<AccountDto> dtoSerializer;
    private final ListSerializer listSerializer;

    @Override
    public String execute(String request) {
        var id = deserializer.apply(request);
        return listSerializer
                .apply(service.getAccountsOfUser(id).stream().map(dtoSerializer).collect(Collectors.toList()));
    }
}
