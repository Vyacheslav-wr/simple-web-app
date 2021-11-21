package com.mastery.java.task.dao.api;

import com.mastery.java.task.entity.Employee;

import java.util.List;

public interface EmployeeDao {

    Employee save(Employee employee);

    Employee delete(Long id);

    Employee update(Employee newEmployee);

    Employee getById(Long id);

    List<Employee> getAll();
}
