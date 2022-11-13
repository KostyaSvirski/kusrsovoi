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
public class GetDepositOrdersOfUserCommand implements Command {

    private final DepositOrderService service;
    private final RequestDeserializer<Long> deserializer;
    private final ResponseSerializer<List<DepositOrderDto>> serializer;

    @Override
    public String execute(String request) {
        long idUser = deserializer.apply(request);
        return serializer.apply(service.findDepositOrdersOfUser(idUser));
    }

    @PostConstruct
    private void postConstruct() {
        CommandHolder.getInstance().addCommand(CommandType.GET_DEPOSIT_ORDERS_OF_USER, this);
    }

}
