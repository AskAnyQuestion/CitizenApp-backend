package com.example.citizen.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Data
@Entity
public class Incident {
    @Id
    @Column(name = "idIncident")
    private int idIncident;

    @ManyToOne
    @JoinColumn(name = "idUser", foreignKey = @ForeignKey(name = "User"))
    private User user;

    @Column(name = "EventDescription")
    private String eventDescription;

    @Column(name = "EventLocation")
    private String eventLocation;

    @Column(name = "EventTime")
    private Time eventTime;

    @Column(name = "EventData")
    private Date eventData;

    @Column(name = "Path")
    private String path;
}
