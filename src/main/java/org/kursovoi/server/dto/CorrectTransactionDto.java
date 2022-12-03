package org.kursovoi.server.dto;

import lombok.Data;

@Data
public class CorrectTransactionDto {

    private long idTo;
    private long idFrom;
    private long sum;
}
