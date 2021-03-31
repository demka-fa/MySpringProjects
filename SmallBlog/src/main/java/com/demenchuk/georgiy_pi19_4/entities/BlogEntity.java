package com.demenchuk.georgiy_pi19_4.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;


/**
 * The type Blog entity.
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "Blogs")
public class BlogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private LocalDate dateAdded;
    private LocalDate dateEdited;
    private String text;
    private String authorName;
    private String tag;

}

