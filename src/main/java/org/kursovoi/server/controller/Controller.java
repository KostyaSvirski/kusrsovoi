package org.kursovoi.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
@MessageEndpoint
public class Controller {

    private final Invoker invoker;

    @ServiceActivator(inputChannel = "inboundChannel")
    public byte[] process(byte[] message) {
        return invoker.invoke(message).getBytes();
    }
}
