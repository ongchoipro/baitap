package com.example.insight_v2.repository;

import com.example.insight_v2.model.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet,Long> {
    @Query(nativeQuery = true, value = "select * from  timesheet t where MONTH(t.workday)=:month and YEAR(workday)=:year and t.employee_code =:employeeCode")
    List<Timesheet> getAllByMonth(@Param("month") String month, @Param("year") String year,@Param("employeeCode") String employeeCode);
    Optional<Timesheet> findById(Long id);
    Timesheet findByWorkday(LocalDate date);
}
