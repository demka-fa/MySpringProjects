package com.example.TODO.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@Table(name = "Categories")
public class CategoryEntity extends SuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "categories_tasks",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    private Set<TaskEntity> tasks;
}
