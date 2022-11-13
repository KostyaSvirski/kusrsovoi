package org.kursovoi.server.controller.command.impl;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.controller.command.Command;
import org.kursovoi.server.controller.command.CommandHolder;
import org.kursovoi.server.controller.command.CommandType;
import org.kursovoi.server.dto.DepositOrderDto;
import org.kursovoi.server.service.DepositOrderService;
import org.kursovoi.server.util.json.RequestDeserializer;
import org.kursovoi.server.util.json.ResponseSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetSpecificDepositOrderCommand implements Command {

    private final DepositOrderService service;
    private final RequestDeserializer<Long> deserializer;
    private final ResponseSerializer<DepositOrderDto> serializer;

    @Override
    public String execute(String request) {
        long idDepositOrder = deserializer.apply(request);
        return serializer.apply(service.findDepositOrder(idDepositOrder));
    }

    @PostConstruct
    private void postConstruct() {
        CommandHolder.getInstance().addCommand(CommandType.GET_SPECIFIC_DEPOSIT_ORDER, this);
    }
}
