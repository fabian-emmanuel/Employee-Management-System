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
        return "redirect:/admin_db";
      }
      User employee = userService.getUserByEmailAndPassword(user.getEmail(), user.getPassword());
      if(employee == null){
        model.addAttribute("invalid", "Invalid details");
        return "login";
      }
      session.setAttribute("user", employee);
      return "redirect:/emp_db";
  }

  @GetMapping("/logout")
  public String logout(Model model, HttpSession session){
    if (session != null) session.invalidate();
    model.addAttribute("user", new User());
    return "redirect:/";
  }
  
  // ADMIN

  @GetMapping("/admin_db")
  public String showAdminDashboard(Model model, HttpSession session) {
    User user = (User) session.getAttribute("user");
    if(user == null) return "redirect:/";
    model.addAttribute("user", user);
    return "admin_dashboard";
  }

  @GetMapping("/reg_page")
  public String showRegPage(Model model, HttpSession session) {
    User user = (User) session.getAttribute("user");
    if (user == null) return "redirect:/";
    model.addAttribute("employee", user);
    return "reg_emp";
  }

  @PostMapping("/register")
  public String registerEmployee(User employee, HttpSession session){
    User user = (User) session.getAttribute("user");
    if (user == null)
      return "redirect:/";
    employee.setPassword(employee.getFirstName()+"123");
    userService.saveEmployee(employee);
    return "redirect:/emp_list_page";
  }

  @GetMapping("/emp_list_page")
  public String showEmployeeList(Model model, HttpSession session){
    User user = (User) session.getAttribute("user");
    if (user == null)
      return "redirect:/";
    model.addAttribute("employee", new User());
    model.addAttribute("delete_emp", new User());
    model.addAttribute("all_employee", userService.getAllUser());
    return "emp_list";

  }

  @GetMapping("/edit/{id}")
  public String showEditPage(@PathVariable("id") Long id, Model model, HttpSession session){
    User user = (User) session.getAttribute("user");
    if (user == null)
      return "redirect:/";
    User employee =  userService.getUserById(id);
    model.addAttribute("employee", employee);
    return "update_emp";
  }

  @PostMapping("/update/{id}")
  public String updateEmployeeDetails(@PathVariable("id") Long id, User employee, HttpSession session){
    User user = (User) session.getAttribute("user");
    if (user == null)
      return "redirect:/";
    userService.updateEmployee(employee, id);
    return "redirect:/emp_list_page";
  }

  @GetMapping("/delete/{id}")
  public String deleteEmployee(@PathVariable("id") Long id, HttpSession session){
    User user = (User) session.getAttribute("user");
    if (user == null)
      return "redirect:/";
    User employee = userService.getUserById(id);
    userService.delete(employee);
    return "redirect:/emp_list_page";
  }

  @GetMapping("/sal_rec_page")
  public String showSalaryRecordPage(Model model, HttpSession session){
    User user = (User) session.getAttribute("user");
    if(user == null) return "redirect:/";
    model.addAttribute("user", user);
    return "emp_salary";
  }


  // EMPLOYEE
  @GetMapping("/emp_db")
  public String showEmployeeDashBoard(Model model, HttpSession session) {
    User employee = (User) session.getAttribute("user");
    if (employee == null)
      return "redirect:/";
    model.addAttribute("employee", employee);
    return "my_dashboard";
  }

  @GetMapping("/attendance_page")
  public String showAttendancePage(Model model, HttpSession session){
    User employee = (User) session.getAttribute("user");
    if (employee == null) return "redirect:/";
    model.addAttribute("employee", employee);
    return "my_attendance";
  }

  @GetMapping("/record_page")
  public String showRecordPage(Model model, HttpSession session){
    User employee = (User) session.getAttribute("user");
    if(employee == null) return "redirect:/";
    model.addAttribute("employee", employee);
    return "my_record";
  }
}
