package com.example.employee_management_system.repositories;

import com.example.employee_management_system.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    Employee findEmployeeById(Long id);
    Employee findEmployeeByEmail(String email);
}
