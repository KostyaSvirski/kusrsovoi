package org.kursovoi.server.model;

import lombok.Data;
import org.kursovoi.server.model.constant.Currency;
import org.kursovoi.server.model.constant.Status;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long sum;
    private LocalDate dateOfIssue;
    private Currency currency;
    private Status status;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User holder;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<Card> cards;
}
