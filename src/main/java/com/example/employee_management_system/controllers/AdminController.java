package com.example.employee_management_system.controllers;

import com.example.employee_management_system.model.User;
import com.example.employee_management_system.service.AttendanceService;
import com.example.employee_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    AttendanceService attendanceService;

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
    public String showEditPage(@PathVariable Long id, Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null)
            return "redirect:/";
        User employee =  userService.getUserById(id);
        model.addAttribute("employee", employee);
        return "update_emp";
    }

    @PostMapping("/update/{id}")
    public String updateEmployeeDetails(@PathVariable Long id, User employee, HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null)
            return "redirect:/";
        userService.updateEmployee(employee);
        return "redirect:/emp_list_page";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id, HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null)
            return "redirect:/";
        User employee = userService.getUserById(id);
        userService.delete(employee);
        return "redirect:/emp_list_page";
    }

    @GetMapping("/emp_attendance_page")
    public String showEmployeesAttendancePage (Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null) return "redirect:/";
        model.addAttribute("user", user);
        model.addAttribute("employeesList", userService.getAllUser());
        model.addAttribute("attendanceList", attendanceService.getAllAttendance());
        return "emp_attendance";
    }

    @GetMapping("/sal_rec_page")
    public String showSalaryRecordPage(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null) return "redirect:/";
        model.addAttribute("user", user);
        model.addAttribute("myList", attendanceService.getAllAttendance());
        return "emp_record";
    }

}
