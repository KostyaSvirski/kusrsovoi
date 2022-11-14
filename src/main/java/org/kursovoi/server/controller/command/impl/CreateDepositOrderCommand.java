package org.kursovoi.server.controller.command.impl;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.controller.command.Command;
import org.kursovoi.server.controller.command.CommandHolder;
import org.kursovoi.server.controller.command.CommandType;
import org.kursovoi.server.dto.CreateDepositDto;
import org.kursovoi.server.service.DepositOrderService;
import org.kursovoi.server.util.json.RequestDeserializer;
import org.kursovoi.server.util.json.ResponseSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class CreateDepositOrderCommand implements Command {

    private final DepositOrderService service;
    private final RequestDeserializer<CreateDepositDto> deserializer;
    private final ResponseSerializer<String> serializer;

    @Override
    public String execute(String request) {
        CreateDepositDto dto = deserializer.apply(request);
        service.createDepositOrder(dto);
        return serializer.apply("Deposit order created");
    }

    @PostConstruct
    private void postConstruct() {
        CommandHolder.getInstance().addCommand(CommandType.CREATE_DEPOSIT_ORDER, this);
    }
}
