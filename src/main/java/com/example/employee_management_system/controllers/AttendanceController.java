package com.example.employee_management_system.controllers;

import com.example.employee_management_system.model.Attendance;
import com.example.employee_management_system.model.User;
import com.example.employee_management_system.service.AttendanceService;
import com.example.employee_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class AttendanceController {
    @Autowired
    AttendanceService attendanceService;
    @Autowired
    UserService userService;


    @GetMapping("/check_in")
    public String checkIn( Model model, HttpSession session){
        User employee = (User) session.getAttribute("user");
        if(employee == null) return "redirect:/";
        model.addAttribute("employee", employee);
        Attendance attendance = new Attendance();
        attendance.setEmployee(employee);
        attendanceService.saveCheckIn(attendance);
        return "redirect:/attendance_page";
    }

    @GetMapping("/check-out")
    public String doCheckOut(@RequestParam Long id, HttpSession session){
        User employee = (User) session.getAttribute("user");
        if(employee == null) return "redirect:/";
        Attendance attendance = attendanceService.getAttendancesById(id);
        attendanceService.saveCheckOut(attendance);
        return "redirect:/attendance_page";
    }

}
