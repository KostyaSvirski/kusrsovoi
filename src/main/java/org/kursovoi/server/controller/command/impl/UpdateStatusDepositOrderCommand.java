package org.kursovoi.server.controller.command.impl;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.controller.command.Command;
import org.kursovoi.server.controller.command.CommandHolder;
import org.kursovoi.server.controller.command.CommandType;
import org.kursovoi.server.dto.DepositOrderDto;
import org.kursovoi.server.dto.UpdateStatusDto;
import org.kursovoi.server.service.DepositOrderService;
import org.kursovoi.server.util.json.RequestDeserializer;
import org.kursovoi.server.util.json.ResponseSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class UpdateStatusDepositOrderCommand implements Command {

    private final DepositOrderService service;
    private final RequestDeserializer<UpdateStatusDto> deserializer;
    private final ResponseSerializer<String> serializer;

    @Override
    public String execute(String request) {
        UpdateStatusDto dto = deserializer.apply(request, UpdateStatusDto.class);
        service.updateStatus(dto);
        return serializer.apply("Status changed");
    }

    @PostConstruct
    private void postConstruct() {
        CommandHolder.getInstance().addCommand(CommandType.UPDATE_STATUS_DEPOSIT_ORDER, this);
    }
}
