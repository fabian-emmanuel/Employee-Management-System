package com.example.employee_management_system.service.serviceImpl;

import com.example.employee_management_system.model.Attendance;
import com.example.employee_management_system.repositories.AttendanceRepo;
import com.example.employee_management_system.service.AttendanceService;
import com.example.employee_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.OrderBy;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    AttendanceRepo attendanceRepo;
    @Autowired
    UserService userService;
    @Override
    public void saveCheckIn(Attendance attendance) {
        attendance.setWorkDayDate(LocalDate.now());
        attendance.setCheckInTime(LocalTime.now());
        attendance.setCheckOutTime(LocalTime.now());
        attendance.setSalary(0.00);
        attendanceRepo.save(attendance);
    }

    @Override
    public void saveCheckOut(Attendance attendance) {
        attendance.setCheckOutTime(LocalTime.now());
        int hoursWorked = Math.abs(attendance.getCheckInTime().getHour() - attendance.getCheckOutTime().getHour());
        attendance.setWorkHours(hoursWorked);
        attendance.setSalary(hoursWorked * 50.00);
        attendanceRepo.save(attendance);
    }

    @Override
    @OrderBy("id desc")
    public List<Attendance> getAttendancesByEmployeeId(Long id) {
        return attendanceRepo.findAllByEmployeeId(id);
    }


    @Override
    public Attendance getAttendancesById(Long id) {
        return attendanceRepo.findAttendanceById(id);
    }

    @Override
    public List<Attendance> getAllAttendance() {
        return attendanceRepo.findAll();
    }
}
