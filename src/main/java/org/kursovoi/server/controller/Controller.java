package org.kursovoi.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@MessageEndpoint
public class Controller {

    @ServiceActivator(inputChannel = "inboundChannel")
    public byte[] process(byte[] message) {
        return null;
    }
}
