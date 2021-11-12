package com.mastery.java.task.dto;

import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private String firstName;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String lastName;
    private Long department;
    private String jobTittle;
    private Date birthday;
}
