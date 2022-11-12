package org.kursovoi.server.controller;

import org.kursovoi.server.controller.command.Command;
import org.kursovoi.server.controller.command.CommandHolder;
import org.kursovoi.server.util.exception.UnknownCommandException;
import org.springframework.stereotype.Component;

@Component
public class RequestDispatcher {

    private final CommandHolder commandHolder = CommandHolder.getInstance();

    public Command matchCommand(String nameOfCommand) {
        var commands = commandHolder.getCommands();
        return commands
                .entrySet()
                .stream()
                .filter(command -> command.getKey().name().equals(nameOfCommand))
                .findFirst()
                .orElseThrow(() -> new UnknownCommandException("Unknown command!"))
                .getValue();
    }
}
