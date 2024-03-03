package com.amexcode.cruddemo.dao;

import com.amexcode.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO{
    //define the field of entityManager
    private EntityManager entityManager;

    //set up constructor injection
    @Autowired
    public EmployeeDAOJpaImpl(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findAll() {

        //create a query
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);
        //execute query and get result list
        List<Employee> employees = theQuery.getResultList();
        //return the results
        return employees;
    }

    @Override
    public Employee findById(int theId) {
        //get Employee
        Employee theEmployee = entityManager.find(Employee.class, theId);
        //return Employee
        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        //save or update Employee
        Employee dbEmployee = entityManager.merge(theEmployee);
        return dbEmployee;
    }

    @Override
    public void deleteById(int theId) {
        //find the Employee by id
        Employee theEmployee = entityManager.find(Employee.class, theId);
        //remove Employee
        entityManager.remove(theEmployee);

    }
}
