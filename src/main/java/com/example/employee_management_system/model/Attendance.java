package com.example.employee_management_system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Date workDayDate;

    @Column(nullable = false)
    private Time checkInTime;

    @Column(nullable = false)
    private Time checkOutTime;

    @OneToOne
    private Employee employee;
}
