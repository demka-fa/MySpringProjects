package com.example.TODO.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Tasks")
public class TaskEntity extends SuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDate completedDate;
    private Boolean isReady;

    @ManyToMany(mappedBy = "tasks")
    private Set<CategoryEntity> categories;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private UserEntity user;
}
