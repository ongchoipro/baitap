package com.example.insight_v2.service.impl;

import com.example.insight_v2.dto.EmployeeProfileDto;
import com.example.insight_v2.model.Department;
import com.example.insight_v2.model.Employee;
import com.example.insight_v2.model.Title;
import com.example.insight_v2.repository.DepartmentRepository;
import com.example.insight_v2.repository.EmployeeRepository;
import com.example.insight_v2.repository.TitleRepository;
import com.example.insight_v2.service.EmployeeProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class EmployeeProfileServiceImpl implements EmployeeProfileService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    TitleRepository titleRepository;
    @Override
    public EmployeeProfileDto updateEmpProfile(EmployeeProfileDto employeeProfileDto) {
        Department department = departmentRepository.findByDepartmentName(employeeProfileDto.getDepartmentName());
        Title title = titleRepository.findByTitleName(employeeProfileDto.getTitleName());
        Employee employee1 = employeeRepository.findByProfileCode(employeeProfileDto.getProfileCode());
        Employee employee = new Employee();
        employee.setId(employee1.getId());
        employee.setFullName(employeeProfileDto.getFullName());
        employee.setEmployeeCode(employeeProfileDto.getEmployeeCode());
        employee.setDepartmentId(department.getDepartmentId());
        employee.setEmployeeType(employeeProfileDto.getEmployeeType());
        employee.setProfileCode(employeeProfileDto.getProfileCode());
        employee.setTitleId(title.getTitleId());
        employee.setStatus(employeeProfileDto.getStatus());
         employeeRepository.save(employee);
         return  employeeProfileDto;
    }

    @Override
    public EmployeeProfileDto addEmpProfile(EmployeeProfileDto employeeProfileDto) {
        Department department = departmentRepository.findByDepartmentName(employeeProfileDto.getDepartmentName());
        Title title = titleRepository.findByTitleName(employeeProfileDto.getTitleName());
        Employee employee = new Employee();
        employee.setFullName(employeeProfileDto.getFullName());
        employee.setEmployeeCode(employeeProfileDto.getEmployeeCode());
        employee.setDepartmentId(department.getDepartmentId());
        employee.setEmployeeType(employeeProfileDto.getEmployeeType());
        employee.setProfileCode(employeeProfileDto.getProfileCode());
        employee.setTitleId(title.getTitleId());
        employeeRepository.save(employee);
        return employeeProfileDto;
    }

    @Override
    public List<EmployeeProfileDto> searchEmpProfile(EmployeeProfileDto employeeProfileDto) {
        Employee employee = new Employee();
        employee.setEmployeeCode(employeeProfileDto.getEmployeeCode());
        employee.setFullName(employeeProfileDto.getEmployeeCode());
        employee.setEmployeeType(employeeProfileDto.getEmployeeType());
        employee.setProfileCode(employeeProfileDto.getProfileCode());
        if (employeeProfileDto.getDepartmentName()!=null){
            Department department = departmentRepository.findByDepartmentName(employeeProfileDto.getDepartmentName());
            employee.setDepartmentId(department.getDepartmentId());
        }
        if ((employeeProfileDto.getTitleName()!=null)){
            Title title = titleRepository.findByTitleName(employeeProfileDto.getTitleName());
            employee.setTitleId(employee.getTitleId());

        }
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
        Example<Employee> exampleQuery = Example.of(employee, matcher);
        List<Employee> employees = employeeRepository.findAll(exampleQuery);
        List<EmployeeProfileDto> employeeProfileDtos = new ArrayList<>();
        for (Employee employee1: employees
             ) {
            EmployeeProfileDto employeeProfileDto1 =  new EmployeeProfileDto();
            employeeProfileDto1.setEmployeeCode(employee1.getEmployeeCode());
            employeeProfileDto1.setFullName(employee1.getFullName());
            employeeProfileDto1.setEmployeeType(employee1.getEmployeeType());
            employeeProfileDto1.setProfileCode(employee1.getProfileCode());
            employeeProfileDto1.setStatus(employee1.getStatus());
            Department department = departmentRepository.findByDepartmentId(employee1.getDepartmentId());
            Title title = titleRepository.findByTitleId(employee1.getTitleId());
            employeeProfileDto1.setDepartmentName(department.getDepartmentName());
            employeeProfileDto1.setTitleName(title.getTitleName());
            employeeProfileDtos.add(employeeProfileDto1);
        }
        return employeeProfileDtos;
    }
}
