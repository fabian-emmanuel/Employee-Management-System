package com.example.employee_management_system.service.serviceImpl;

import com.example.employee_management_system.model.Employee;
import com.example.employee_management_system.repositories.EmployeeRepo;
import com.example.employee_management_system.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    @Override
    public void saveEmployee(Employee employee) {
        employeeRepo.save(employee);
    }

    @Override
    public void updateEmployee(Employee employee, Long id) {
        Employee _employee = employeeRepo.findEmployeeById(id);
        _employee.setFirstName(employee.getFirstName());
        _employee.setLastName(employee.getLastName());
        _employee.setEmail(employee.getEmail());
        employeeRepo.save(_employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepo.findEmployeeById(id);
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        return employeeRepo.findEmployeeByEmail(email);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepo.findAll();
    }

    @Override
    public void deleteEmployee(Employee employee) {
        employeeRepo.delete(employee);
    }
}
