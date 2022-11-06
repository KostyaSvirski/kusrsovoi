package org.kursovoi.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLoanOrderDto {

    private long idUser;
    private long idLoan;
    private long sum;
}
