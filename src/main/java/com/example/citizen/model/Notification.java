package com.example.citizen.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idNotification")
    private Integer idNotification;
    @ManyToOne
    @JoinColumn(name = "idIncident", foreignKey = @ForeignKey(name = "idIncident"))
    @JsonIgnore
    private Incident incident;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "idUser", foreignKey = @ForeignKey(name = "idUser"))
    private User user;

    public Notification(Incident incident, User user) {
        this.incident = incident;
        this.user = user;
    }
    public Notification() {}
}
