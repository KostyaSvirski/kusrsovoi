package org.kursovoi.server.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.kursovoi.server.model.constant.Status;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "clients")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private Status status;
    private String email;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<LoanOrder> loanOrders;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Operation> operations;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<DepositOrder> depositOrders;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "holder", fetch = FetchType.LAZY)
    private List<Account> accounts;
}
