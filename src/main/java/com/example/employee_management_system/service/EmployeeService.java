package com.example.employee_management_system.service;

import java.util.List;

import com.example.employee_management_system.model.Employee;


public interface EmployeeService {
    void saveEmployee(Employee employee);

    void updateEmployee(Employee employee, Long id);

    Employee getEmployeeById(Long id);

    Employee getEmployeeByEmail(String email);

    List<Employee> getAllEmployee();

    void deleteEmployee(Employee employee);
}
