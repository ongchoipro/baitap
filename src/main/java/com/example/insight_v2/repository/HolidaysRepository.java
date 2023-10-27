package com.example.insight_v2.repository;

import com.example.insight_v2.model.Holidays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidaysRepository extends JpaRepository<Holidays,Long> {
    Holidays findByHolidayCode(String code);
}
