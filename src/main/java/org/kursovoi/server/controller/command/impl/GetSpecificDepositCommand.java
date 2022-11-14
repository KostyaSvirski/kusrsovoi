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
public class GetSpecificDepositCommand implements Command {

    private final DepositService service;
    private final RequestDeserializer<Long> deserializer;
    private final ResponseSerializer<DepositDto> serializer;

    @Override
    public String execute(String request) {
        long idDeposit = deserializer.apply(request);
        return serializer.apply(service.findSpecificDepositDto(idDeposit));
    }

    @PostConstruct
    private void postConstruct() {
        CommandHolder.getInstance().addCommand(CommandType.GET_SPECIFIC_DEPOSIT, this);
    }
}
