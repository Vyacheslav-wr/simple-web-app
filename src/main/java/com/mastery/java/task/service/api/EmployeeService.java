package com.mastery.java.task.service.api;

import com.mastery.java.task.dto.EmployeeDto;
import com.mastery.java.task.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee save(EmployeeDto employeeDto);

    void delete(Long id);

    Employee update(EmployeeDto newEmployeeDto);

    EmployeeDto getById(Long id);

    List<EmployeeDto> getAll();
}
