package com.example.TODO.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String secondName;
    private String lastName;
    private String login;
    private Date birthday;
    private Date create_date;
    private Date change_date;

    @OneToMany(mappedBy = "user")
    private List<TaskEntity> tasks;
}