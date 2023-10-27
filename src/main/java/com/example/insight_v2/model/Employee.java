package com.example.insight_v2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "employee_code")
    private String employeeCode;
    @Column(name = "department_id")
    private String departmentId;
    @Column(name = "employee_type")
    private String employeeType;
    @Column(name = "profile_code")
    private String profileCode;
    @Column(name = "title_id")
    private String titleId;
    @Column(name = "status")
    private Integer status;
}
