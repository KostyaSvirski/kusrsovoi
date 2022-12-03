package org.kursovoi.server.dto;

import lombok.Data;

@Data
public class AccountDto {

    private long id;
    private long sum;
    private String dateOfIssue;
    private String currency;
    private String status;
    private long holderId;
}
