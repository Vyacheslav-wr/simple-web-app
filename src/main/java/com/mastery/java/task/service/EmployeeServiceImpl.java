package com.mastery.java.task.service;

import com.mastery.java.task.dao.api.EmployeeDao;
import com.mastery.java.task.dto.EmployeeDto;
import com.mastery.java.task.entity.Employee;
import com.mastery.java.task.service.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public Employee save(EmployeeDto employeeDto) {
        Employee employee = Employee.builder()
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .department(employeeDto.getDepartment())
                .jobTittle(employeeDto.getJobTittle())
                .birthday(employeeDto.getBirthday())
                .gender(employeeDto.getGender())
                .build();
        employeeDao.save(employee);
        return employee;
    }

    public void delete(Long id) {
        employeeDao.delete(id);
    }

    public Employee update(EmployeeDto newEmployeeDto) {
        Employee newEmployee = Employee.builder()
                .firstName(newEmployeeDto.getFirstName())
                .lastName(newEmployeeDto.getLastName())
                .department(newEmployeeDto.getDepartment())
                .jobTittle(newEmployeeDto.getJobTittle())
                .birthday(newEmployeeDto.getBirthday())
                .gender(newEmployeeDto.getGender())
                .build();
        employeeDao.update(newEmployee);
        return newEmployee;
    }

    public EmployeeDto getById(Long id) throws NullPointerException{
        Employee employee = employeeDao.getById(id);
        try{
            EmployeeDto employeeDto = EmployeeDto.builder()
                    .firstName(employee.getFirstName())
                    .lastName(employee.getLastName())
                    .department(employee.getDepartment())
                    .jobTittle(employee.getJobTittle())
                    .birthday(employee.getBirthday())
                    .gender(employee.getGender())
                    .build();
            return employeeDto;
        }
        catch (NullPointerException ex){
            ex.fillInStackTrace();
            return  null;
        }
    }

    public List<EmployeeDto> getAll() {
        try{
        List<EmployeeDto> employeeDtos = employeeDao
                .getAll()
                .stream()
                .map(employee ->
                    EmployeeDto
                            .builder()
                            .firstName(employee.getFirstName())
                            .lastName(employee.getLastName())
                            .department(employee.getDepartment())
                            .jobTittle(employee.getJobTittle())
                            .birthday(employee.getBirthday())
                            .gender(employee.getGender())
                            .build()
                ).collect(Collectors.toList());
        return employeeDtos;
        }
        catch (NullPointerException ex){
            ex.fillInStackTrace();
        }
        return  null;
    }
}
