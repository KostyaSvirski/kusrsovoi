package org.kursovoi.server.controller.command.impl;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.controller.command.Command;
import org.kursovoi.server.controller.command.CommandHolder;
import org.kursovoi.server.controller.command.CommandType;
import org.kursovoi.server.dto.LoanOrderDto;
import org.kursovoi.server.dto.UpdateStatusDto;
import org.kursovoi.server.service.LoanOrderService;
import org.kursovoi.server.util.json.RequestDeserializer;
import org.kursovoi.server.util.json.ResponseSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class UpdateStatusLoanOrderCommand implements Command {

    private final LoanOrderService service;
    private final RequestDeserializer<UpdateStatusDto> deserializer;
    private final ResponseSerializer<String> serializer;

    @Override
    public String execute(String request) {
        UpdateStatusDto dto = deserializer.apply(request);
        service.updateStatus(dto);
        return serializer.apply("Status updated");
    }

    @PostConstruct
    private void postConstruct() {
        CommandHolder.getInstance().addCommand(CommandType.UPDATE_STATUS_LOAN_ORDER, this);
    }
}
