package org.kursovoi.server.controller.command;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CommandHolder {

    public static volatile CommandHolder instance;
    private final Map<CommandType, Command> commands;

    private CommandHolder() {
        this.commands = new HashMap<>();
    }

    public static CommandHolder getInstance() {
        if (instance == null) {
            synchronized (CommandHolder.class) {
                if (instance == null) {
                    instance = new CommandHolder();
                }
            }
        }
        return instance;
    }

    public synchronized void addCommand(CommandType type, Command command) {
        commands.put(type, command);
    }

    public Map<CommandType, Command> getCommands() {
        return commands;
    }
}
