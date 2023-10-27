package com.example.insight_v2.dto;

import lombok.Data;

import java.util.List;

@Data
public class TimesheetDto {
    private String employee_code;
    private String full_name;
    private Float total_work;
    private Float overtime_hours;
    private int coming_late_leave_early;
    private Float days_off_leave;
    private String month;
    private List<WorkInDayEmployeeDto> workInDayEmployeeDtos;
}
