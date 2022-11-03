package org.kursovoi.server.model;

import lombok.Data;
import org.kursovoi.server.model.constant.LoanOrderStatus;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "loan_order")
public class LoanOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate dateOfIssue;
    private long sum;
    private LoanOrderStatus status;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_loan")
    private Loan loan;
}
