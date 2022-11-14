package org.kursovoi.server.controller.command.impl;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.controller.command.Command;
import org.kursovoi.server.controller.command.CommandHolder;
import org.kursovoi.server.controller.command.CommandType;
import org.kursovoi.server.dto.LoanOrderDto;
import org.kursovoi.server.service.LoanOrderService;
import org.kursovoi.server.util.json.RequestDeserializer;
import org.kursovoi.server.util.json.ResponseSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetSpecificLoanOrderCommand implements Command {

    private final LoanOrderService service;
    private final RequestDeserializer<Long> deserializer;
    private final ResponseSerializer<LoanOrderDto> serializer;

    @Override
    public String execute(String request) {
        long idLoanOrder = deserializer.apply(request);
        return serializer.apply(service.findSpecificLoanOrder(idLoanOrder));
    }

    @PostConstruct
    private void postConstruct() {
        CommandHolder.getInstance().addCommand(CommandType.GET_SPECIFIC_LOAN_ORDER, this);
    }
}
