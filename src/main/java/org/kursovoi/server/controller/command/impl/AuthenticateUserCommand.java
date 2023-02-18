package org.kursovoi.server.controller.command.impl;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.kursovoi.server.controller.command.Command;
import org.kursovoi.server.controller.command.CommandHolder;
import org.kursovoi.server.controller.command.CommandType;
import org.kursovoi.server.dto.AuthRequestDto;
import org.kursovoi.server.dto.UserDto;
import org.kursovoi.server.service.UserService;
import org.kursovoi.server.util.json.RequestDeserializer;
import org.kursovoi.server.util.json.ResponseSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;

@Component
@RequiredArgsConstructor
public class AuthenticateUserCommand implements Command {

    private final UserService service;
    private final RequestDeserializer<AuthRequestDto> deserializer;
    private final ResponseSerializer<UserDto> serializer;

    @Override
    public String execute(String request) {
        AuthRequestDto dto = deserializer.apply(request, AuthRequestDto.class);
        return serializer.apply(service.authenticate(dto));
    }

    @PostConstruct
    private void postConstruct() {
        CommandHolder.getInstance().addCommand(CommandType.AUTHENTICATE_USER, this);
    }
}
