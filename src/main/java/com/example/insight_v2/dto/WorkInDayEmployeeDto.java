package com.example.insight_v2.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class WorkInDayEmployeeDto {
    private LocalDate dayWork;
    private Float workHour;
    private Boolean isHoliday;
    private Float isOvertime;
}
