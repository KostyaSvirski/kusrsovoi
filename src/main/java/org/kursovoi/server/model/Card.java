package org.kursovoi.server.model;

import lombok.Data;
import org.kursovoi.server.model.constants.CardIssuer;
import org.kursovoi.server.model.constants.CardType;
import org.kursovoi.server.model.constants.Status;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String number;
    private String holderName;
    private LocalDate dateOfExpire;
    private String cvv;
    private String pin;
    private Status status;
    private CardIssuer cardIssuer;
    private CardType type;

    @ManyToOne
    @JoinColumn(name = "id_account")
    private Account account;
}
