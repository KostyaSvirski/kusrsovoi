package org.kursovoi.server.controller;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.controller.command.Command;
import org.kursovoi.server.controller.command.CommandHolder;
import org.kursovoi.server.controller.command.CommandType;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RequestDispatcher {

    private final CommandHolder commandHolder;

    public Command matchCommand(String nameOfCommand) {
        var commands = commandHolder.getCommands();
        return commands.getOrDefault(CommandType.valueOf(nameOfCommand),
                commandHolder.getCommands().get(CommandType.UNKNOWN_COMMAND));
    }
}
