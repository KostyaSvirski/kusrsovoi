package org.kursovoi.server.controller.command.impl;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.controller.command.Command;
import org.kursovoi.server.controller.command.CommandHolder;
import org.kursovoi.server.controller.command.CommandType;
import org.kursovoi.server.dto.LoanDto;
import org.kursovoi.server.dto.UpdateSumDto;
import org.kursovoi.server.service.LoanOrderService;
import org.kursovoi.server.service.LoanService;
import org.kursovoi.server.util.json.RequestDeserializer;
import org.kursovoi.server.util.json.ResponseSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class GetSpecificLoanCommand implements Command {

    private final LoanService service;
    private final RequestDeserializer<Long> deserializer;
    private final ResponseSerializer<LoanDto> serializer;

    @Override
    public String execute(String request) {
        long idLoan = deserializer.apply(request);
        return serializer.apply(service.findSpecificLoanDto(idLoan));
    }

    @PostConstruct
    private void postConstruct() {
        CommandHolder.getInstance().addCommand(CommandType.GET_SPECIFIC_LOAN, this);
    }
}
