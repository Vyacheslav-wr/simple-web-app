package com.mastery.java.task.service;

import com.mastery.java.task.dao.api.EmployeeDao;
import com.mastery.java.task.dto.CreateEmployeeDto;
import com.mastery.java.task.entity.Employee;
import com.mastery.java.task.service.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public Employee save(CreateEmployeeDto createEmployeeDto) {
        Employee employee = Employee.builder()
                .firstName(createEmployeeDto.getFirstName())
                .lastName(createEmployeeDto.getLastName())
                .department(createEmployeeDto.getDepartment())
                .jobTittle(createEmployeeDto.getJobTittle())
                .birthday(createEmployeeDto.getBirthday())
                .gender(createEmployeeDto.getGender())
                .build();
        return employeeDao.save(employee);
    }

    public Employee delete(Long id) {
        return employeeDao.delete(id);
    }

    public Employee update(Employee newEmployee) {
        return employeeDao.update(newEmployee);
    }

    public Employee getById(Long id) {
       return employeeDao.getById(id);
    }

    public List<Employee> getAll() {
        return employeeDao.getAll();
    }
}
