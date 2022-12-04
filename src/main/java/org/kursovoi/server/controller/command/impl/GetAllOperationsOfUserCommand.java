package org.kursovoi.server.controller.command.impl;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.controller.command.Command;
import org.kursovoi.server.controller.command.CommandHolder;
import org.kursovoi.server.controller.command.CommandType;
import org.kursovoi.server.dto.DepositOrderDto;
import org.kursovoi.server.dto.OperationDto;
import org.kursovoi.server.service.DepositOrderService;
import org.kursovoi.server.service.OperationService;
import org.kursovoi.server.util.json.RequestDeserializer;
import org.kursovoi.server.util.json.ResponseSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllOperationsOfUserCommand implements Command {

    private final OperationService service;
    private final RequestDeserializer<Long> deserializer;
    private final ResponseSerializer<List<OperationDto>> serializer;

    @Override
    public String execute(String request) {
        long idUser = deserializer.apply(request, Long.class);
        return serializer.apply(service.findAllOperationsOfUser(idUser));
    }

    @PostConstruct
    private void postConstruct() {
        CommandHolder.getInstance().addCommand(CommandType.GET_ALL_OPERATIONS_OF_USER, this);
    }

}
