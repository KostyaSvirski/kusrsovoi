package org.kursovoi.server.controller.command.impl;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.controller.command.Command;
import org.kursovoi.server.controller.command.CommandHolder;
import org.kursovoi.server.controller.command.CommandType;
import org.kursovoi.server.dto.LoanOrderDto;
import org.kursovoi.server.dto.OperationDto;
import org.kursovoi.server.service.LoanOrderService;
import org.kursovoi.server.service.OperationService;
import org.kursovoi.server.util.json.ResponseSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllPendingLoanOrdersCommand implements Command {

    private final LoanOrderService service;
    private final ResponseSerializer<List<LoanOrderDto>> serializer;

    @Override
    public String execute(String request) {
        return serializer.apply(service.findAllPendingLoans());
    }

    @PostConstruct
    private void postConstruct() {
        CommandHolder.getInstance().addCommand(CommandType.GET_ALL_PENDING_LOAN_ORDERS, this);
    }
}
