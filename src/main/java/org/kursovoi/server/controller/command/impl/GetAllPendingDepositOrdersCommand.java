package org.kursovoi.server.controller.command.impl;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.controller.command.Command;
import org.kursovoi.server.controller.command.CommandHolder;
import org.kursovoi.server.controller.command.CommandType;
import org.kursovoi.server.dto.DepositOrderDto;
import org.kursovoi.server.dto.LoanOrderDto;
import org.kursovoi.server.service.DepositOrderService;
import org.kursovoi.server.service.LoanOrderService;
import org.kursovoi.server.util.json.ResponseSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllPendingDepositOrdersCommand implements Command {

    private final DepositOrderService service;
    private final ResponseSerializer<List<DepositOrderDto>> serializer;

    @Override
    public String execute(String request) {
        return serializer.apply(service.findAllPendingDeposits());
    }

    @PostConstruct
    private void postConstruct() {
        CommandHolder.getInstance().addCommand(CommandType.GET_ALL_PENDING_DEPOSIT_ORDERS, this);
    }
}
