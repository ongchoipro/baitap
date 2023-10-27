package com.example.insight_v2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "timesheet_report")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimesheetReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "employee_code")
    private String employeeCode;
    @Column(name = "total_work")
    private Float totalWork;
    @Column(name = "overtime_hours")
    private Float overtimeHours;
    @Column(name = "coming_late_leave_early")
    private int comingLateLeaveEarly;
    @Column(name = "days_off_leave")
    private Float daysOffLeave;
    @Column(name = "month")
    private String month;

}
