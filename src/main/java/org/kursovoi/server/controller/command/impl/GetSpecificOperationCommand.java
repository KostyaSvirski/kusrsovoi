package org.kursovoi.server.controller.command.impl;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.controller.command.Command;
import org.kursovoi.server.controller.command.CommandHolder;
import org.kursovoi.server.controller.command.CommandType;
import org.kursovoi.server.dto.OperationDto;
import org.kursovoi.server.service.OperationService;
import org.kursovoi.server.util.json.RequestDeserializer;
import org.kursovoi.server.util.json.ResponseSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class GetSpecificOperationCommand implements Command {

    private final OperationService service;
    private final RequestDeserializer<Long> deserializer;
    private final ResponseSerializer<OperationDto> serializer;

    @Override
    public String execute(String request) {
        long idOperation = deserializer.apply(request);
        return serializer.apply(service.getSpecificOperationDto(idOperation));
    }

    @PostConstruct
    private void postConstruct() {
        CommandHolder.getInstance().addCommand(CommandType.GET_SPECIFIC_OPERATION, this);
    }
}
