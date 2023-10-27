package com.example.insight_v2.controller;

import com.example.insight_v2.dto.EmployeeProfileDto;
import com.example.insight_v2.model.Employee;
import com.example.insight_v2.service.EmployeeProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeProfileController {
    @Autowired
    EmployeeProfileService employeeProfileService;
    @PutMapping("/updateProfile")
    public ResponseEntity<?> updateProfile(@RequestBody EmployeeProfileDto employeeProfileDto){
        return ResponseEntity.ok(employeeProfileService.updateEmpProfile(employeeProfileDto));
    }
    @PostMapping("/addEmployeeProfile")
    public ResponseEntity<?> addEmployeeProfile(@RequestBody EmployeeProfileDto employeeProfileDto){
        return ResponseEntity.ok(employeeProfileService.addEmpProfile(employeeProfileDto));
    }
    @GetMapping("/searchEmpProfile")
    public List<EmployeeProfileDto> searchEmprofile(@RequestBody EmployeeProfileDto employeeProfileDto){
        return employeeProfileService.searchEmpProfile(employeeProfileDto);
    }
}
