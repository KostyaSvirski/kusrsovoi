package org.kursovoi.server.controller.command.impl;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.controller.command.Command;
import org.kursovoi.server.controller.command.CommandHolder;
import org.kursovoi.server.controller.command.CommandType;
import org.kursovoi.server.dto.UpdateSumDto;
import org.kursovoi.server.dto.UserDto;
import org.kursovoi.server.service.UserService;
import org.kursovoi.server.util.json.RequestDeserializer;
import org.kursovoi.server.util.json.ResponseSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

@Component
@RequiredArgsConstructor
public class UpdateUserCommand implements Command {

    private final UserService service;
    private final RequestDeserializer<UserDto> deserializer;
    private final ResponseSerializer<String> serializer;

    @Override
    public String execute(String request) throws IllegalBlockSizeException, BadPaddingException {
        var user = deserializer.apply(request, UserDto.class);
        service.updateUser(user);
        return serializer.apply("User updated");
    }

    @PostConstruct
    private void postConstruct() {
        CommandHolder.getInstance().addCommand(CommandType.UPDATE_USER, this);
    }
}
