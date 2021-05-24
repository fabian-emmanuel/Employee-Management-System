package com.example.employee_management_system.controllers;

import com.example.employee_management_system.model.Attendance;
import com.example.employee_management_system.model.User;
import com.example.employee_management_system.service.AttendanceService;
import com.example.employee_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    UserService userService;
    @Autowired
    AttendanceService attendanceService;

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
        List<Attendance> allAttendance = attendanceService.getAttendancesByEmployeeId(employee.getId());
        model.addAttribute("employeeAttendance", allAttendance);
        model.addAttribute("employee", employee);
        model.addAttribute("attendance", new Attendance());
        return "my_attendance";
    }

    @GetMapping("/record_page")
    public String showRecordPage(Model model, HttpSession session){
        User employee = (User) session.getAttribute("user");
        if(employee == null) return "redirect:/";
        List<Attendance> allAttendance = attendanceService.getAttendancesByEmployeeId(employee.getId());
        model.addAttribute("employeeAttendance", allAttendance);
        model.addAttribute("employee", employee);
        return "my_record";
    }

}
