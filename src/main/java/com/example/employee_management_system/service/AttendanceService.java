package com.example.employee_management_system.service;

import com.example.employee_management_system.model.Attendance;

import java.util.List;

public interface AttendanceService {
    void saveCheckIn(Attendance attendance);
    void saveCheckOut(Attendance attendance);
    List<Attendance> getAttendancesByEmployeeId(Long id);
    Attendance getAttendancesById(Long id);
    List<Attendance> getAllAttendance();
}
