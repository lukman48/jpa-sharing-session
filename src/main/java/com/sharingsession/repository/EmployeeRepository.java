package com.sharingsession.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sharingsession.model.Employee;
import com.sharingsession.model.EmployeeIdentity;

@Transactional
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, EmployeeIdentity> {

    public Employee findByName(String name);

    public Employee findByEmployeeIdentity(EmployeeIdentity id);

    public List<Employee> findAll();

    @Query("select count(*) from Employee where name := name")
    public Long countEmployeeByNameHql(String nama);
    
    public Long countEmployeeByName(String nama);
    
}