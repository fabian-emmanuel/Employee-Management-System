package com.example.employee_management_system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "date")
    private LocalDate workDayDate;

    @Column(nullable = false, name = "time_in")
    private LocalTime checkInTime;

    @Column(nullable = false, name = "time_out")
    private LocalTime checkOutTime;

    @ManyToOne
    private User employee;
}
