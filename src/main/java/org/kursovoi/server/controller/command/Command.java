package org.kursovoi.server.controller.command;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public interface Command {

    String execute(String request) throws IllegalBlockSizeException, BadPaddingException;
}
