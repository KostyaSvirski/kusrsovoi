package org.kursovoi.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDepositDto {

    private long idUser;
    private long idDeposit;
    private long sum;
}
