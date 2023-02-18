package org.kursovoi.server.controller.command.impl;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.controller.command.Command;
import org.kursovoi.server.controller.command.CommandHolder;
import org.kursovoi.server.controller.command.CommandType;
import org.kursovoi.server.dto.CorrectTransactionDto;
import org.kursovoi.server.dto.TransactionDto;
import org.kursovoi.server.service.AccountService;
import org.kursovoi.server.util.json.RequestDeserializer;
import org.kursovoi.server.util.json.ResponseSerializer;
import org.kursovoi.server.util.rsa.RSADecipher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

@Component
@RequiredArgsConstructor
public class MakeTransactionCommand implements Command {

    private final RSADecipher decipher;
    private final AccountService service;
    private final RequestDeserializer<TransactionDto> deserializer;
    private final ResponseSerializer<String> serializer;

    @Override
    public String execute(String request) throws IllegalBlockSizeException, BadPaddingException {
        TransactionDto dto = deserializer.apply(request, TransactionDto.class);
        CorrectTransactionDto transactionDto = new CorrectTransactionDto();
        transactionDto.setSum(Long.parseLong(decipher.decipher(dto.getSum())));
        transactionDto.setIdFrom(Long.parseLong(decipher.decipher(dto.getIdFrom())));
        transactionDto.setIdTo(Long.parseLong(decipher.decipher(dto.getIdTo())));
        service.makeTransaction(transactionDto);
        return serializer.apply("Transaction completed");
    }

    @PostConstruct
    private void postConstruct() {
        CommandHolder.getInstance().addCommand(CommandType.MAKE_TRANSACTION, this);
    }

}
