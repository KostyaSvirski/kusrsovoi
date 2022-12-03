package org.kursovoi.server.dto;

import lombok.Data;

@Data
public class TransactionDto {

    private String idTo;
    private String idFrom;
    private String sum;
}
