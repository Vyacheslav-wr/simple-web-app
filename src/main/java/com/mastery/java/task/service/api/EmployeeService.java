package com.mastery.java.task.service.api;

import com.mastery.java.task.dto.CreateEmployeeDto;
import com.mastery.java.task.dto.GetOrUpdateEmployeeDto;
import com.mastery.java.task.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee save(CreateEmployeeDto createEmployeeDto);

    Employee delete(Long id);

    Employee update(Employee newEmployee);

    Employee getById(Long id);

    List<Employee> getAll();
}
