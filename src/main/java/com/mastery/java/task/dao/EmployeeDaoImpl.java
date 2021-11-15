package com.mastery.java.task.dao;

import com.mastery.java.task.dao.api.EmployeeDao;
import com.mastery.java.task.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Slf4j
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Employee save(Employee employee) {
        log.info("Executing method save for the object = {}", employee);
        entityManager.persist(employee);
        return employee;
    }

    @Transactional
    public void delete(Long id) {
        log.info("Executing method delete");
        log.debug("Executing method delete for employee with id = {}", id);
        entityManager.remove(entityManager.find(Employee.class, id));
    }

    @Transactional
    public void update(Employee newEmployee) {
        log.info("Executing method update for the object = {}", newEmployee);
        entityManager.merge(newEmployee);
    }

    public Employee getById(Long id) {
        log.info("Execute method getById for the object with id = {}", id);
        return entityManager.find(Employee.class, id);
    }

    public List<Employee> getAll() {
        log.info("Execute method getALL");
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
        Root<Employee> from = query.from(Employee.class);
        return entityManager.createQuery(query).getResultList();
    }
}
