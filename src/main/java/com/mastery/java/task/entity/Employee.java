package com.mastery.java.task.entity;

import com.mastery.java.task.dto.Gender;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private Long department;
    private String jobTittle;
    private Gender gender;
    private LocalDate birthday;
}
