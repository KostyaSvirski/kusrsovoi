package org.kursovoi.server.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.kursovoi.server.model.constant.DepositOrderStatus;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "deposit_order")
public class DepositOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private DepositOrderStatus status;
    private long sum;
    private LocalDate dateOfIssue;
    private LocalDate dateOfExpire;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "client_id")
    private User user;
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "deposit_id")
    private Deposit deposit;
}
