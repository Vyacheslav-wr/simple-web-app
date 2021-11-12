package com.mastery.java.task.dao.api;

import com.mastery.java.task.entity.Employee;

import java.util.List;

public interface EmployeeDao {

    void save(Employee employee);

    void delete(Long id);

    void update(Employee newEmployee);

    Employee getById(Long id);

    List<Employee> getAll();
}
