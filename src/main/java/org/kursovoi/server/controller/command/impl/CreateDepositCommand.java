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
public class CreateDepositCommand implements Command {

    private final DepositService service;
    private final RequestDeserializer<DepositDto> deserializer;
    private final ResponseSerializer<String> serializer;

    @Override
    public String execute(String request) {
        DepositDto dto = deserializer.apply(request);
        service.createDeposit(dto);
        return serializer.apply("Deposit created");
    }

    @PostConstruct
    private void postConstruct() {
        CommandHolder.getInstance().addCommand(CommandType.CREATE_DEPOSIT, this);
    }
}
