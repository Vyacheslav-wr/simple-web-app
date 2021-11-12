package com.mastery.java.task.entity;

import com.mastery.java.task.dto.Gender;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Long department;
    private String jobTittle;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Date birthday;
}
