package com.mastery.java.task.dao;

import com.mastery.java.task.dao.api.EmployeeDao;
import com.mastery.java.task.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class EmployeeDaoImpl implements EmployeeDao {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Employee save(Employee employee) {
        log.info("Executing method save for the object = {}", employee);

        int state = jdbcTemplate.update("INSERT INTO employee(first_name, last_name, department_id, job_tittle, " +
                        "gender, date_of_birth) VALUES (?,?,?,?,?,?)", employee.getFirstName(), employee.getLastName(),
                employee.getDepartment(), employee.getJobTittle(), employee.getGender().toString(), employee.getBirthday());

        if (state == 0) {
            return null;
        } else {
            return employee;
        }
    }

    public Employee delete(Long id) {
        log.info("Executing method delete");
        log.debug("Executing method delete for employee with id = {}", id);

        Employee employee = getById(id);

        jdbcTemplate.update("DELETE FROM employee WHERE employee_id=?", id);

        return employee;
    }

    public Employee update(Employee newEmployee) {
        log.info("Executing method update for the object = {}", newEmployee);

        int state =  jdbcTemplate.update("UPDATE employee SET first_name=?, last_name=?, department_id=?, job_tittle=?, " +
                        "gender=?, date_of_birth=? WHERE employee_id=?", newEmployee.getFirstName(), newEmployee.getLastName(),
                newEmployee.getDepartment(), newEmployee.getJobTittle(), newEmployee.getGender().toString(), newEmployee.getBirthday(), newEmployee.getId());

        return getById(newEmployee.getId());
    }

    public Employee getById(Long id) {
        log.info("Execute method getById for the object with id = {}", id);
        return jdbcTemplate.query("SELECT * FROM employee WHERE employee_id=?", new EmployeeMapper(), id)
                .stream()
                .findAny()
                .orElse(null);
    }

    public List<Employee> getAll() {
        log.info("Execute method getALL");
        return jdbcTemplate.query("SELECT * FROM employee", new EmployeeMapper());
    }
}
