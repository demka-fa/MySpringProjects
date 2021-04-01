package com.example.TODO.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class SuperEntity {
    LocalDate createdDate = LocalDate.now();
    LocalDate changedDate = LocalDate.now();
}
