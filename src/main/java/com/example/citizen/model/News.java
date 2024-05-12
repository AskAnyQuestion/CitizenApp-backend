package com.example.citizen.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idNews")
    private int idNews;
    @Column(name = "eventTime")
    private Timestamp eventTime;
    @Column(name = "latitude")
    private Double latitude;
    @Column(name = "longitude")
    private Double longitude;
    @Column(name = "description")
    private String description;
    @Column(name = "addition")
    private String addition;
}
