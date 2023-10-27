package com.example.insight_v2.repository;

import com.example.insight_v2.model.TimesheetReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimesheetReportRepository extends JpaRepository<TimesheetReport,Long> {
    TimesheetReport findByEmployeeCode(String employee_code);
    List<TimesheetReport> findByMonth(String month);
    TimesheetReport findByMonthAndEmployeeCode(String month,String employeeCode);

}
