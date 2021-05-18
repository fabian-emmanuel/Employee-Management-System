package com.example.employee_management_system.repositories;

import com.example.employee_management_system.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AttendanceRepo extends JpaRepository<Attendance, Long> {

}
