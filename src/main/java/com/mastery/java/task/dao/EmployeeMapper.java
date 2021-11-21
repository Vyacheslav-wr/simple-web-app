package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Gender;
import com.mastery.java.task.entity.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = new Employee();
        employee.setId(rs.getLong("employee_id"));
        employee.setFirstName(rs.getString("first_name"));
        employee.setLastName(rs.getString("last_name"));
        employee.setDepartment(rs.getLong("department_id"));
        employee.setGender(Gender.valueOf(rs.getString("gender")));
        employee.setJobTittle(rs.getString("job_tittle"));
        employee.setBirthday(rs.getDate("date_of_birth").toLocalDate());
        return employee;
    }
}
