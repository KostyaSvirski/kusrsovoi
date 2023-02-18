package org.kursovoi.server.controller.command.impl;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.controller.command.Command;
import org.kursovoi.server.controller.command.CommandHolder;
import org.kursovoi.server.controller.command.CommandType;
import org.kursovoi.server.dto.DepositDto;
import org.kursovoi.server.service.DepositService;
import org.kursovoi.server.util.json.RequestDeserializer;
import org.kursovoi.server.util.json.ResponseSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class DeleteDepositCommand implements Command {

    private final DepositService service;
    private final RequestDeserializer<Long> deserializer;
    private final ResponseSerializer<String> serializer;

    @Override
    public String execute(String request) {
        long idDeposit = deserializer.apply(request, Long.class);
        service.deleteDeposit(idDeposit);
        return serializer.apply("Deposit deleted");
    }

    @PostConstruct
    private void postConstruct() {
        CommandHolder.getInstance().addCommand(CommandType.DELETE_DEPOSIT, this);
    }
}
