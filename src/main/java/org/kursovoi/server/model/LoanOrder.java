package org.kursovoi.server.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
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

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "client_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;
}
