package com.example.employee_management_system.controllers;

import com.example.employee_management_system.model.User;
import com.example.employee_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

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
}
