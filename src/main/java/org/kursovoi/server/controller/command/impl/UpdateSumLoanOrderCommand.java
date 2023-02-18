package org.kursovoi.server.controller.command.impl;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.controller.command.Command;
import org.kursovoi.server.controller.command.CommandHolder;
import org.kursovoi.server.controller.command.CommandType;
import org.kursovoi.server.dto.UpdateSumDto;
import org.kursovoi.server.service.LoanOrderService;
import org.kursovoi.server.util.json.RequestDeserializer;
import org.kursovoi.server.util.json.ResponseSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class UpdateSumLoanOrderCommand implements Command {

    private final LoanOrderService service;
    private final RequestDeserializer<UpdateSumDto> deserializer;
    private final ResponseSerializer<String> serializer;

    @Override
    public String execute(String request) {
        UpdateSumDto dto = deserializer.apply(request, UpdateSumDto.class);
        service.updateSum(dto);
        return serializer.apply("Sum of loan order updated");
    }

    @PostConstruct
    private void postConstruct() {
        CommandHolder.getInstance().addCommand(CommandType.UPDATE_SUM_LOAN_ORDER, this);
    }
}
