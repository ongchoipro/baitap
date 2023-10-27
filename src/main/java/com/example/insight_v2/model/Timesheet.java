package com.example.insight_v2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "timesheet")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Timesheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "employee_code")
    private String employee_code;
    @Column(name = "workday")
    private LocalDate workday;
    @Column(name="working_hour")
    private Float workingHour;
    @Column(name = "overtime_hours")
    private Float overtime_hours;
}
