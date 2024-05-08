package com.example.citizen.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser")
    private Integer idUser;
    @Column(name = "phone", nullable = false)
    private Long phone;
    @Column(name = "login", nullable = false)
    private String login;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "ipv4", nullable = false)
    private String ipv4;
    @Column(name = "token", nullable = false)
    private String token;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @CollectionTable(name = "notifications")
    private List<Notification> notifications = new ArrayList<>();
    public User() {}
}