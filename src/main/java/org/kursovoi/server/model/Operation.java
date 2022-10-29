package org.kursovoi.server.model;

import lombok.Data;
import org.kursovoi.server.model.constants.OperationType;

import javax.persistence.*;

@Data
@Entity
@Table(name = "operation_of_user")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private OperationType type;
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
}
