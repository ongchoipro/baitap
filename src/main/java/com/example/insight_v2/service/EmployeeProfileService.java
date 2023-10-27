package com.example.insight_v2.service;

import com.example.insight_v2.dto.EmployeeProfileDto;
import com.example.insight_v2.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeProfileService {
    EmployeeProfileDto updateEmpProfile(EmployeeProfileDto employeeProfileDto);
    EmployeeProfileDto addEmpProfile(EmployeeProfileDto employeeProfileDto);
    List<EmployeeProfileDto> searchEmpProfile(EmployeeProfileDto employeeProfileDto);
}
