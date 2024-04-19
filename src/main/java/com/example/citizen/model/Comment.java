package com.example.citizen.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
public class Comment {
    @Id
    @Column(name = "idComment")
    private int idComment;

    @Column(name = "DateWrite")
    private Date dateWrite;

    @Column(name = "MessageText")
    private String messageText;

    @ManyToOne
    @JoinColumn(name = "idUser", foreignKey = @ForeignKey(name = "idUser"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "idNews", foreignKey = @ForeignKey(name = "idNews"))
    private News news;
}
