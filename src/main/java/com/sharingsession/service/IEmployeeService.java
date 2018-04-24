package com.sharingsession.service;

import java.util.List;

import com.sharingsession.model.Employee;
import com.sharingsession.model.EmployeeIdentity;

public interface IEmployeeService {

    public Employee getEmployeeById(EmployeeIdentity id);

    public Employee getEmployeeByName(String name);

    public List<Employee> getAllEmployees();

    public boolean exists(String email);

    public Employee save(Employee employee);
    
}