package com.example.citizen.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
public class Notification {
    @Id
    @Column(name = "idNotification")
    private int idNotification;

    @Column(name = "DepartureDate")
    private Date departureDate;

    @ManyToOne
    @JoinColumn(name = "idIncident", foreignKey = @ForeignKey(name = "idIncident"))
    private Incident incident;

    @ManyToOne
    @JoinColumn(name = "idUser", foreignKey = @ForeignKey(name = "idUser"))
    private User user;
}
