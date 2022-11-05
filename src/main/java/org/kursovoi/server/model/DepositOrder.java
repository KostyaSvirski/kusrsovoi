package org.kursovoi.server.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.kursovoi.server.model.constant.DepositOrderStatus;

import javax.persistence.*;

@Data
@Entity
@Table(name = "deposit_order")
public class DepositOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private DepositOrderStatus status;
    private long sum;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "id_deposit")
    private Deposit deposit;
}
