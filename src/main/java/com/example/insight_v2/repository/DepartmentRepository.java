package com.example.insight_v2.repository;

import com.example.insight_v2.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
    Department findByDepartmentName(String name);
    Department findByDepartmentId(String departmentId);
}
