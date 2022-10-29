package org.kursovoi.server.model;

import lombok.Data;
import org.kursovoi.server.model.constants.Status;

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

    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<LoanOrder> loanOrders;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Operation> operations;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<DepositOrder> depositOrders;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Account> accounts;
}
