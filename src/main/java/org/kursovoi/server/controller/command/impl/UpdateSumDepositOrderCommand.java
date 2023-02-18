package org.kursovoi.server.controller.command.impl;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.controller.command.Command;
import org.kursovoi.server.controller.command.CommandHolder;
import org.kursovoi.server.controller.command.CommandType;
import org.kursovoi.server.dto.UpdateSumDto;
import org.kursovoi.server.service.DepositOrderService;
import org.kursovoi.server.util.json.RequestDeserializer;
import org.kursovoi.server.util.json.ResponseSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class UpdateSumDepositOrderCommand implements Command {

    private final DepositOrderService service;
    private final RequestDeserializer<UpdateSumDto> deserializer;
    private final ResponseSerializer<String> serializer;

    @Override
    public String execute(String request) {
        UpdateSumDto dto = deserializer.apply(request, UpdateSumDto.class);
        service.updateSum(dto);
        return serializer.apply("Sum of deposit order updated");
    }

    @PostConstruct
    private void postConstruct() {
        CommandHolder.getInstance().addCommand(CommandType.UPDATE_SUM_DEPOSIT_ORDER, this);
    }
}
