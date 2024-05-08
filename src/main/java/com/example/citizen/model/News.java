package com.example.citizen.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
public class News {
    @Id
    @Column(name = "idNews")
    private int idNews;
    @Column(name = "Title")
    private String title;
    @Column(name = "Description")
    private String description;
    @Column(name = "PublicationTime")
    private Date publicationTime;
    @Column(name = "City")
    private String city;
    @Column(name = "Path")
    private String path;
    @Column(name = "LikeCount")
    private int likeCount;
    @ManyToOne
    @JoinColumn(name = "idUser", foreignKey = @ForeignKey(name = "idUser"))
    private User user;
}
