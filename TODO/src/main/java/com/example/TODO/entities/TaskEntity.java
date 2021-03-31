package com.example.TODO.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Tasks")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date create_date;
    private Date change_date;
    private String name;
    private String description;
    private Date complete_date;
    private Boolean completed;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private UserEntity user;

    @ManyToMany(mappedBy = "tasks")
    private Set<CategoryEntity> categories;
}
