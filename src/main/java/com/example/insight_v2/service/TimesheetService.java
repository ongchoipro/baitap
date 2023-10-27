package com.example.insight_v2.service;

import com.example.insight_v2.dto.TimesheetDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface TimesheetService {
    List<TimesheetDto> getByMonth(String month);
    TimesheetDto findTimesheetByEmpAndMonth(Optional<String> month, String employeeCode);
    List<TimesheetDto> getAllByMsonth(Optional<String> month, Optional<String> year);
    TimesheetDto findByEmpAndMonth(Optional<String> month, Optional<String> year,String employeeCode);
}
