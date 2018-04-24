package com.sharingsession.repository;

import com.sharingsession.model.EmployeeIdentity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.sharingsession.model.Employee;
import com.sharingsession.repository.EmployeeRepository;

import java.util.UUID;

@RunWith(SpringRunner.class)
@DataJpaTest 
public class EmployeeRepositoryTest {
	
	@Autowired
    private TestEntityManager entityManager;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	public void whenFindByName_thenReturnEmployee() {
		/** given **/
		EmployeeIdentity employeeIdentityAlex= new EmployeeIdentity(UUID.randomUUID().toString(),UUID.randomUUID().toString());
		Employee alex = new Employee(employeeIdentityAlex,"alex","alex@testing.co.id","82123456");

		entityManager.persist(alex);
	    entityManager.flush();
	 
	    /** when **/
	    Employee found = employeeRepository.findByName(alex.getName());
	 
	    /** then **/
	    Assert.assertEquals(alex.getName(), found.getName());
	}
}
