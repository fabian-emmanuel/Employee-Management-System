package com.example.employee_management_system.controllers;

import com.example.employee_management_system.model.Employee;
import com.example.employee_management_system.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @GetMapping("/empDB")
    public String showEmployeeDashBoard(Model model){
        model.addAttribute("employee", new Employee());
        return "my_dashboard";
    }

    @PostMapping("/elogin")
    public String employeeLogin(Employee employee, Model model){
        Employee _employee = employeeService.getEmployeeByEmail(employee.getEmail());
        if (_employee == null){
            model.addAttribute("Invalid details", "Please enter correct details");
        }
        //_employee = employeeService.getEmployeeByEmailAndPassword();
        return "redirect:/empDB";
    }


}
