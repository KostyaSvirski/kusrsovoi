package org.kursovoi.server.controller;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.controller.command.Command;
import org.kursovoi.server.controller.command.CommandHolder;
import org.kursovoi.server.controller.command.CommandType;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Invoker {

    private final RequestDispatcher requestDispatcher;
    private final CommandHolder holder;

    public String invoke(byte[] message) {
        var request = new String(message);
        var parsedRequest = request.split(",", 2);
        String askedCommand = parsedRequest[0];
        String requestBody = parsedRequest.length == 2 ? parsedRequest[1] : null;
        Command command;
        try {
            command = requestDispatcher.matchCommand(askedCommand);
        } catch (Throwable ex) {
            command = holder.getCommands().get(CommandType.INCORRECT_ACTION);
        }
        return command.execute(requestBody);
    }
}
