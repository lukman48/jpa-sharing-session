package com.sharingsession.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.UUID;

import com.sharingsession.model.EmployeeIdentity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.sharingsession.model.Employee;
import com.sharingsession.repository.EmployeeRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void whenFindByName_thenReturnEmployee() {
        EmployeeIdentity employeeIdentityAlex= new EmployeeIdentity(UUID.randomUUID().toString(),UUID.randomUUID().toString());
        Employee alex = new Employee(employeeIdentityAlex,"alex","alex@testing.co.id","82123456");

        entityManager.persistAndFlush(alex);

        Employee found = employeeRepository.findByName(alex.getName());
        assertThat(found.getName()).isEqualTo(alex.getName());
    }

    @Test
    public void whenInvalidName_thenReturnNull() {
        Employee fromDb = employeeRepository.findByName("doesNotExist");
        assertThat(fromDb).isNull();
    }

    @Test
    public void whenFindById_thenReturnEmployee() {
        EmployeeIdentity employeeIdentityAlex= new EmployeeIdentity(UUID.randomUUID().toString(),UUID.randomUUID().toString());
        Employee emp = new Employee(employeeIdentityAlex,"test","test@testing.co.id","82123456");

        entityManager.persistAndFlush(emp);

        Employee fromDb = employeeRepository.findByEmployeeIdentity(employeeIdentityAlex);
        assertThat(fromDb.getName()).isEqualTo(emp.getName());
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Employee fromDb = employeeRepository.findByEmployeeIdentity(new EmployeeIdentity());
        assertThat(fromDb).isNull();
    }

    @Test
    public void givenSetOfEmployees_whenFindAll_thenReturnAllEmployees() {
        EmployeeIdentity employeeIdentityAlex= new EmployeeIdentity(UUID.randomUUID().toString(),UUID.randomUUID().toString());
        Employee alex = new Employee(employeeIdentityAlex,"alex","alex@testing.co.id","821234561");

        EmployeeIdentity employeeIdentityJhon= new EmployeeIdentity(UUID.randomUUID().toString(),UUID.randomUUID().toString());
        Employee ron = new Employee(employeeIdentityJhon,"ron","ron@testing.co.id","821234562");

        EmployeeIdentity employeeIdentityBob= new EmployeeIdentity(UUID.randomUUID().toString(),UUID.randomUUID().toString());
        Employee bob = new Employee(employeeIdentityBob,"bob","bob@testing.co.id","821234563");


        entityManager.persist(alex);
        entityManager.persist(bob);
        entityManager.persist(ron);
        entityManager.flush();

        List<Employee> allEmployees = employeeRepository.findAll();

        assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName());
    }
}