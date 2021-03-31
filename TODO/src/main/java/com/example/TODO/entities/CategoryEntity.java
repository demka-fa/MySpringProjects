package com.example.TODO.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@Table(name = "Categories")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Date create_date;
    private Date change_date;

    @ManyToMany
    @JoinTable(
            name = "categories_tasks",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    private Set<TaskEntity> tasks;
}
