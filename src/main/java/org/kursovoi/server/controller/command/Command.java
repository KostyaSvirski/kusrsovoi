package org.kursovoi.server.controller.command;

import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

@Component
public interface Command {

    String execute(String request) throws IllegalBlockSizeException, BadPaddingException;
}
