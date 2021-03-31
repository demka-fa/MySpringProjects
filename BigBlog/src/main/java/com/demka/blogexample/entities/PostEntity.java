package com.demka.blogexample.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @Column(unique = true)
    private  String slug;

    private String text;

    @ManyToOne
    @JoinColumn(name="author_id", nullable = false)
    private UserEntity author;


    //@OneToMany(mappedBy = "user")
    //private List<CommentEntity> comments;

    //private likes

    //private Set<> tags;
}