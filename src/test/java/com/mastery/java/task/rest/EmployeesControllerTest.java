package com.mastery.java.task.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastery.java.task.dto.CreateEmployeeDto;
import com.mastery.java.task.dto.Gender;
import com.mastery.java.task.dto.GetOrUpdateEmployeeDto;
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

import java.time.LocalDate;
import java.time.Month;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeesController.class)
public class EmployeesControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    EmployeeService employeeService;
    @Autowired
    ObjectMapper mapper;

    @Test
    public void saveTest() throws Exception{
        CreateEmployeeDto createEmployeeDto = CreateEmployeeDto.builder()
                .firstName("oleg")
                .lastName("snark")
                .department(2L)
                .gender(Gender.MALE)
                .jobTittle("teacher")
                .birthday(LocalDate.of(1467, Month.AUGUST, 12))
                .build();

        Employee employee = new Employee();
        employee.setId(2L);

        Mockito.when(employeeService.save(createEmployeeDto)).thenReturn(employee);

        String str = this.mapper.writeValueAsString(createEmployeeDto);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(createEmployeeDto));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTest() throws Exception{
        Employee employee = Employee.builder()
                .id(1L)
                .build();

        Mockito.when(employeeService.getById(employee.getId())).thenReturn(employee);
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/employees?id=1"))
                .andExpect(status().isOk());
    }

    @Test
    public void getByIdTest() throws Exception{
        Employee employee = Employee.builder()
                .id(1L)
                .build();
        Mockito.when(employeeService.getById(employee.getId())).thenReturn(employee);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/employees?id=1"))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/employees"))
                .andExpect(status().isOk());
    }
}
