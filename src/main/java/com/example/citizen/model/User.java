package com.example.citizen.model;

import lombok.Data;

import javax.persistence.*;

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
    public User() {}

    public User(String login, String password, Long phone, String token) {
        this.login = login;
        this.password = password;
        this.phone = phone;
        this.token = token;
    }
}