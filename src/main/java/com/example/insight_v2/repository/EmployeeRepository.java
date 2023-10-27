package com.example.insight_v2.repository;

import com.example.insight_v2.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee,Long> {
    Employee findByEmployeeCode(String employee_code);
    Employee findByProfileCode(String code);
}
