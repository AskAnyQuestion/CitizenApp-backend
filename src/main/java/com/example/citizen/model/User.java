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

    @Column(name = "Username")
    private String username;

    @Column(name = "Password")
    private String password;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Email")
    private String email;

    @Column(name = "AccessLevel")
    private String accessLevel;

    public User() {}

    public User(String username, String password, String phone, String email, String accessLevel) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.accessLevel = accessLevel;
    }
}