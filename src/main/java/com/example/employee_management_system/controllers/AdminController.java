package com.example.employee_management_system.controllers;

import com.example.employee_management_system.model.Employee;
import com.example.employee_management_system.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/")
    public String getLogin(){
        return "login";
    }

    @GetMapping("/adminDB")
    public String showAdminDashboard(Model model){
        model.addAttribute("admin",  new Employee());
        return "admin_dashboard";
    }

    @PostMapping("/alogin")
    public String adminLogin(){
        return "redirect:/adminDB";
    }


    @GetMapping("/empList")
    public String getHome(Model model){
        model.addAttribute("employeesList", employeeService.getAllEmployee());
        return "emp_list";
    }

    @GetMapping("/reg")
    public String showRegPage(Model model){
        model.addAttribute("employee", new Employee());
        return "reg_emp";
    }

    @PostMapping("/register")
    public String saveEmployee(Employee employee){
        employeeService.saveEmployee(employee);
        return "redirect:/empList";
    }

    @GetMapping("/edit/{id}")
    public String showEditPage(@PathVariable("id") Long id, Model model){
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("_employee", employee);
        return "update_emp";
    }
    
    @PostMapping("/update/{id}")
    public String updateEmployee(@PathVariable("id") Long id, Employee employee){
        employeeService.updateEmployee(employee, id);
        return "redirect:/empList";
    }

    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id){
        Employee _employee = employeeService.getEmployeeById(id);
        employeeService.deleteEmployee(_employee);
        return "redirect:/empList";
    }
}
