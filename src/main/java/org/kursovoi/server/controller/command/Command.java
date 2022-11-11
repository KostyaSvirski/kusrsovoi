package org.kursovoi.server.controller.command;

public interface Command {

    public byte[] execute(String message);
}
