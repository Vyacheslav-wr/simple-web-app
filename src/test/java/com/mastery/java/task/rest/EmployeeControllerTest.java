package com.mastery.java.task.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastery.java.task.dto.EmployeeDto;
import com.mastery.java.task.dto.Gender;
import com.mastery.java.task.entity.Employee;
import com.mastery.java.task.service.api.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    EmployeeService employeeService;
    @Autowired
    ObjectMapper mapper;

    @Test
    public void hireEmployeeTest() throws Exception{
        EmployeeDto employeeDto = EmployeeDto.builder()
                .firstName("oleg")
                .lastName("snark")
                .department(2L)
                .gender(Gender.MALE)
                .jobTittle("teacher")
                .build();

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(employeeDto));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());

    }

    @Test
    public void fireEmployeeTest() throws Exception{
        Employee employee = Employee.builder()
                .id(1L)
                .build();
        EmployeeDto employeeDto = EmployeeDto.builder()
                .firstName("andrei")
                .build();

        Mockito.when(employeeService.getById(employee.getId())).thenReturn(employeeDto);
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/employee?id=1"))
                .andExpect(status().isOk());
    }

    @Test
    public void getEmployeeByIdTest() throws Exception{
        Employee employee = Employee.builder()
                .id(1L)
                .build();
        EmployeeDto employeeDto = EmployeeDto.builder()
                .firstName("andrei")
                .build();
        Mockito.when(employeeService.getById(employee.getId())).thenReturn(employeeDto);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/employee?id=1"))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllEmployeesTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/employee/all"))
                .andExpect(status().isOk());
    }
}
