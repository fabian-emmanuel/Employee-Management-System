package com.example.employee_management_system.repositories;

import com.example.employee_management_system.model.Attendance;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.OrderBy;
import java.util.List;


@Repository
public interface AttendanceRepo extends JpaRepository<Attendance, Long> {
    Attendance findAttendanceById(Long id);

    List<Attendance> findAllByEmployeeId(Long id);

}
