package com.mastery.java.task.service;

import com.mastery.java.task.dao.api.EmployeeDao;
import com.mastery.java.task.dto.EmployeeDto;
import com.mastery.java.task.entity.Employee;
import com.mastery.java.task.service.api.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeDao employeeDao;

    @Test
    public void saveTest(){
        EmployeeDto employeeDto = Mockito.mock(EmployeeDto.class);
        Employee employee = employeeService.save(employeeDto);

        Mockito.verify(employeeDao, Mockito.times(1)).save(employee);
    }

    @Test
    public void deleteTest(){
        Long num = 1L;

        employeeService.delete(num);
        Mockito.verify(employeeDao, Mockito.times(1)).delete(1L);
    }

    @Test
    public void updateTest(){
        EmployeeDto employeeDto = Mockito.mock(EmployeeDto.class);
        Employee employee = employeeService.update(employeeDto);

        Mockito.verify(employeeDao, Mockito.times(1)).update(employee);
    }

    @Test
    public void getByIdTest(){
        Long num = 1L;

        employeeService.getById(num);
        Mockito.verify(employeeDao, Mockito.times(1)).getById(1L);
    }

    @Test
    public void getAllTest(){
        employeeService.getAll();
        Mockito.verify(employeeDao, Mockito.times(1)).getAll();
    }
}
