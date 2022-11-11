package org.kursovoi.server.controller.command.impl;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.controller.command.Command;
import org.kursovoi.server.dto.AccountDto;
import org.kursovoi.server.service.AccountService;
import org.kursovoi.server.util.json.DtoSerializer;
import org.kursovoi.server.util.json.ListSerializer;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetAllAccountsCommand implements Command {

    private final DtoSerializer<AccountDto> dtoSerializer;
    private final ListSerializer listSerializer;
    private final AccountService service;

    @Override
    public String execute(String request) {
        var response = service.getAllAccounts();
        return listSerializer.apply(response.stream().map(dtoSerializer).collect(Collectors.toList()));
    }
}
