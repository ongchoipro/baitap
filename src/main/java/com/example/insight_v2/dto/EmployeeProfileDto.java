package com.example.insight_v2.dto;

import lombok.Data;

@Data
public class EmployeeProfileDto {
    private Long id;
    private String profileCode;
    private String fullName;
    private String employeeCode;
    private String employeeType;
    private String departmentName;
    private String titleName;
    private Integer status;
}
