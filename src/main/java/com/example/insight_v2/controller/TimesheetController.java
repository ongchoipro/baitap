package com.example.insight_v2.controller;

import com.example.insight_v2.dto.TimesheetDto;
import com.example.insight_v2.service.TimesheetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/timesheet")
@RequiredArgsConstructor
public class TimesheetController {
    @Autowired
    private TimesheetService timesheetService;
    @GetMapping("/getByMonth")
    public List<TimesheetDto> getByMonth(@RequestParam("month")Optional<String> month){
        return timesheetService.getByMonth(month.get());
    }
    @GetMapping("/getTimesheetByMonth")
    public List<TimesheetDto> getByMonth(@RequestParam("month") Optional<String> month,
                                             @RequestParam("year") Optional<String> year){
        return timesheetService.getAllByMsonth(month,year);
    }
    @GetMapping("/find/emp-and-moth")
    public ResponseEntity<TimesheetDto> findTimesheetByEmpAndMonth(@RequestParam("month") Optional<String> month,
                                                                   @RequestParam("year") Optional<String> year,
                                                                   @RequestParam("employeeCode") Optional<String> employeeCode){
        return ResponseEntity.ok(timesheetService.findByEmpAndMonth(month,year,employeeCode.get()));

    }
}
