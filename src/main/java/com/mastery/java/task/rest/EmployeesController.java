package com.mastery.java.task.rest;

import com.mastery.java.task.dto.CreateEmployeeDto;
import com.mastery.java.task.entity.Employee;
import com.mastery.java.task.service.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> save(@RequestBody CreateEmployeeDto createEmployeeDto) {
        if (createEmployeeDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Employee employee = employeeService.save(createEmployeeDto);

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> delete(@PathVariable(name = "id") Long id) {
        if (employeeService.getById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Employee employee = employeeService.delete(id);

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> update(@RequestBody Employee newEmployee){
        if(employeeService.getById(newEmployee.getId()) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Employee employee = employeeService.update(newEmployee);

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> getById(@PathVariable(name = "id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Employee employee = employeeService.getById(id);

        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> getAll() {
        List<Employee> employees = employeeService.getAll();

        if (employees == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}
