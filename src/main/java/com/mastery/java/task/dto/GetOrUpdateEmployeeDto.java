package com.mastery.java.task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrUpdateEmployeeDto {
    private Long id;
    private String firstName;
    private Gender gender;
    private String lastName;
    private Long department;
    private String jobTittle;
    private LocalDate birthday;
}