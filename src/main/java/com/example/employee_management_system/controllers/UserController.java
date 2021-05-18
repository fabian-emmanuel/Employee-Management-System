package com.example.employee_management_system.controllers;

import javax.servlet.http.HttpSession;

import com.example.employee_management_system.model.User;
import com.example.employee_management_system.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

  @Autowired UserService userService;

  @GetMapping("/")
  public String loginPage(Model model){
    model.addAttribute("user", new User());
    return "login";

  }


  @PostMapping("/login")
  public String userLogin(User user, Model model, HttpSession session){
      if(user.getEmail().equals("admin@admin.com") && user.getPassword().equals("admin")){
        session.setAttribute("user", new User());
        return "redirect:/adminDB";
      }

      User employee = userService.getUserByEmailAndPassword(user.getEmail(), user.getPassword());
      if(employee == null){
        model.addAttribute("invalid", "Invalid details");
        return "login";
      }
      session.setAttribute("user", employee);
      return "redirect:/empDB";
  }
  
  // ADMIN

  @GetMapping("/admin_db")
  public String showAdminDashboard(Model model) {
    model.addAttribute("user", new User());
    return "admin_dashboard";
  }

  @GetMapping("/reg_page")
  public String showRegPage(Model model) {
    model.addAttribute("employee", new User());
    return "reg_emp";
  }

  @PostMapping("/register")
  public String registerEmployee(User employee){
    userService.saveEmployee(employee);
    return "redirect:/emp_list";
  }

  @GetMapping("/emp_list")
  public String showEmployeeList(Model model){
    model.addAttribute("employee_list", userService.getAllUser());
    return "emp_list";

  }

  @GetMapping("/edit/{id}")
  public String showEditPage(@PathVariable("id") Long id, Model model){
    User employee =  userService.getUserById(id);
    model.addAttribute("employee", employee);
    return "update_emp";
  }

  @PostMapping("/update/{id}")
  public String updateEmployeeDetails(@PathVariable("id") Long id, User employee){
    userService.updateEmployee(employee, id);
    return "redirect:/emp_list";
  }

  @PostMapping("/delete/{id}")
  public String deleteEmployee(@PathVariable("id") Long id){
    User employee = userService.getUserById(id);
    userService.delete(employee);
    return "redirect:/emp_list";
  }



  // EMPLOYEE
  @GetMapping("/emp_db")
  public String showEmployeeDashBoard(Model model) {
    model.addAttribute("employee", new User());
    return "my_dashboard";
  }
}
