package org.kursovoi.server.dto;

import lombok.Data;

@Data
public class UpdateStatusUserDto {

    private String newStatus;
    private long userId;

}
