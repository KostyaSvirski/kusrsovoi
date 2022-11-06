package org.kursovoi.server.dto;

import lombok.Data;

@Data
public class UpdateStatusDto {

    private String newStatus;
    private long id;

}
