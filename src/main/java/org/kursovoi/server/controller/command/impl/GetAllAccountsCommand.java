package org.kursovoi.server.controller.command.impl;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.controller.command.Command;
import org.kursovoi.server.controller.command.CommandHolder;
import org.kursovoi.server.controller.command.CommandType;
import org.kursovoi.server.dto.AccountDto;
import org.kursovoi.server.service.AccountService;
import org.kursovoi.server.util.json.ResponseSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllAccountsCommand implements Command {

    private final ResponseSerializer<List<AccountDto>> responseSerializer;
    private final AccountService service;

    @Override
    public String execute(String request) {
        return responseSerializer.apply(service.getAllAccounts());
    }

    @PostConstruct
    private void postConstruct() {
        CommandHolder.getInstance().addCommand(CommandType.GET_ALL_ACCOUNTS, this);
    }
}
