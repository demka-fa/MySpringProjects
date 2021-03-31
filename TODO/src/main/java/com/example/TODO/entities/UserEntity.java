package com.example.TODO.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Users")
public class UserEntity extends SuperEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String secondName;
    private String lastName;
    private String login;
    private LocalDate birthday;

    @OneToMany(mappedBy = "user")
    private List<TaskEntity> tasks;
}