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
    private String phone;
    @Column(name = "login", nullable = false)
    private String login;
    @Column(name = "password", nullable = false)
    private String password;

    public User() {}

    public User(String login, String password, String phone) {
        this.login = login;
        this.password = password;
        this.phone = phone;
    }
}