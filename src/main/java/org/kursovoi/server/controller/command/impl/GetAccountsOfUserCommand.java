package org.kursovoi.server.controller.command.impl;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.controller.command.Command;
import org.kursovoi.server.controller.command.CommandHolder;
import org.kursovoi.server.controller.command.CommandType;
import org.kursovoi.server.dto.AccountDto;
import org.kursovoi.server.service.AccountService;
import org.kursovoi.server.util.json.RequestDeserializer;
import org.kursovoi.server.util.json.ResponseSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAccountsOfUserCommand implements Command {

    private final AccountService service;
    private final RequestDeserializer<Long> deserializer;
    private final ResponseSerializer<List<AccountDto>> responseSerializer;

    @Override
    public String execute(String request) {
        var id = deserializer.apply(request, Long.class);
        return responseSerializer.apply(service.getAccountsOfUser(id));
    }

    @PostConstruct
    private void postConstruct() {
        CommandHolder.getInstance().addCommand(CommandType.GET_ALL_ACCOUNTS_OF_USER, this);
    }
}
