package com.example.insight_v2.service.impl;

import com.example.insight_v2.dto.TimesheetDto;
import com.example.insight_v2.dto.WorkInDayDto;
import com.example.insight_v2.dto.WorkInDayEmployeeDto;
import com.example.insight_v2.model.Employee;
import com.example.insight_v2.model.Holidays;
import com.example.insight_v2.model.Timesheet;
import com.example.insight_v2.model.TimesheetReport;
import com.example.insight_v2.repository.EmployeeRepository;
import com.example.insight_v2.repository.HolidaysRepository;
import com.example.insight_v2.repository.TimesheetReportRepository;
import com.example.insight_v2.repository.TimesheetRepository;
import com.example.insight_v2.service.TimesheetService;
import com.fasterxml.jackson.core.format.DataFormatMatcher;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.record.PageBreakRecord;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class TimesheetServiceImpl implements TimesheetService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private TimesheetReportRepository timesheetReportRepository;
    @Autowired
    private TimesheetRepository timesheetRepository;
    @Autowired
    private HolidaysRepository holidaysRepository;
    @Override
    public List<TimesheetDto> getByMonth(String month) {
        List<TimesheetReport>  timesheetReports =timesheetReportRepository.findByMonth(month);
        List<TimesheetDto> timesheetDtos = new ArrayList<>();
        for (TimesheetReport timesheetReport: timesheetReports
             ) {
            Employee employee = employeeRepository.findByEmployeeCode(timesheetReport.getEmployeeCode());
            TimesheetDto timesheetDto = new TimesheetDto();
            timesheetDto.setEmployee_code(employee.getEmployeeCode());
            timesheetDto.setFull_name(employee.getFullName());
            timesheetDto.setOvertime_hours(timesheetReport.getOvertimeHours());
            timesheetDto.setTotal_work(timesheetReport.getTotalWork());
            timesheetDto.setComing_late_leave_early(timesheetReport.getComingLateLeaveEarly());
            timesheetDto.setDays_off_leave(timesheetReport.getDaysOffLeave());
            timesheetDto.setMonth(month);
            timesheetDtos.add(timesheetDto);
        }
        return timesheetDtos;
    }

    @Override
    public TimesheetDto findTimesheetByEmpAndMonth(Optional<String> month, String employeeCode) {
        TimesheetReport timesheetReport = timesheetReportRepository.findByMonthAndEmployeeCode(month.get(),employeeCode);
        Employee employee = employeeRepository.findByEmployeeCode(employeeCode);
        TimesheetDto timesheetDto =new TimesheetDto();
        timesheetDto.setEmployee_code(employeeCode);
        timesheetDto.setFull_name(employee.getFullName());
        timesheetDto.setOvertime_hours(timesheetReport.getOvertimeHours());
        timesheetDto.setTotal_work(timesheetReport.getTotalWork());
        timesheetDto.setComing_late_leave_early(timesheetReport.getComingLateLeaveEarly());
        timesheetDto.setDays_off_leave(timesheetReport.getDaysOffLeave());
        timesheetDto.setMonth(month.get());
        return timesheetDto;
    }
    @Override
    public List<TimesheetDto> getAllByMsonth(Optional<String> month, Optional<String> year) {
        String abc = month.get()+"/"+year.get();
        List<TimesheetReport> timesheetReports = timesheetReportRepository.findByMonth(abc);
        List<TimesheetDto> timesheetDtos = new ArrayList<>();
        for (TimesheetReport timesheetReport: timesheetReports){
            Employee employee= employeeRepository.findByEmployeeCode(timesheetReport.getEmployeeCode());
            List<Timesheet> timesheets = timesheetRepository.getAllByMonth(month.get(),year.get(),employee.getEmployeeCode());
            List<WorkInDayEmployeeDto> workInDayEmployeeDtos = mapperByWork(month.get(),year.get());
            List<LocalDate> dateList = new ArrayList<>();
            for (Timesheet  timesheet: timesheets){
                dateList.add(timesheet.getWorkday());
            }
            for (WorkInDayEmployeeDto workInDayEmployeeDto : workInDayEmployeeDtos
            ){
                if (dateList.contains(workInDayEmployeeDto.getDayWork())){
                    Timesheet  timesheet = timesheetRepository.findByWorkday(workInDayEmployeeDto.getDayWork());
                    workInDayEmployeeDto.setWorkHour(timesheet.getWorkingHour());
                    workInDayEmployeeDto.setIsOvertime(timesheet.getOvertime_hours());
                    workInDayEmployeeDto.setIsHoliday(chechHoliday(timesheet.getWorkday()));
                } else {
                    workInDayEmployeeDto.setWorkHour(null);
                    workInDayEmployeeDto.setIsOvertime(null);
                    workInDayEmployeeDto.setIsHoliday(false);
                }
            }
            TimesheetDto timesheetDto = new TimesheetDto();
            timesheetDto.setEmployee_code(employee.getEmployeeCode());
            timesheetDto.setFull_name(employee.getFullName());
            timesheetDto.setOvertime_hours(timesheetReport.getOvertimeHours());
            timesheetDto.setTotal_work(timesheetReport.getTotalWork());
            timesheetDto.setComing_late_leave_early(timesheetReport.getComingLateLeaveEarly());
            timesheetDto.setDays_off_leave(timesheetReport.getDaysOffLeave());
            timesheetDto.setWorkInDayEmployeeDtos(workInDayEmployeeDtos);
            timesheetDtos.add(timesheetDto);

        }
        return timesheetDtos;
    }
//        for (TimesheetReport timesheetReport : timesheetReports
//        ){
//            Employee employee= employeeRepository.findByEmployeeCode(timesheetReport.getEmployeeCode());
//            List<Timesheet> timesheets = timesheetRepository.getAllByMonth(month.get(),year.get(),employee.getEmployeeCode());
//            List<WorkInDayDto> workInDayDtos = new ArrayList<>();
//            for (Timesheet timesheet:timesheets
//            ) {
//                WorkInDayDto workInDayDto = new WorkInDayDto();
//                workInDayDto.setDayWork(timesheet.getWorkday());
//                workInDayDto.setWorkHour(timesheet.getWorkingHour());
//                workInDayDto.setIsOvertime(timesheet.getOvertime_hours());
//                workInDayDto.setIsHoliday(chechHoliday(timesheet.getWorkday()));
//                workInDayDtos.add(workInDayDto);
//            }
//            workInDayDtos.sort(Comparator.comparing(WorkInDayDto::getDayWork));
//             List<WorkInDayEmployeeDto> workInDayEmployeeDtos = mapperByWork(month.get(),year.get());
//            for (WorkInDayEmployeeDto workInDayEmployeeDto: workInDayEmployeeDtos
//            ) {
//                if (workInDayEmployeeDto.getDayWork().){
//
//                }
////                for (WorkInDayDto workInDayDto: workInDayDtos
////                ) {
////                    if (workInDayEmployeeDto.getDayWork().isEqual(workInDayDto.getDayWork())){
////                        workInDayEmployeeDto.setIsOvertime(workInDayDto.getIsOvertime());
////                        workInDayEmployeeDto.setWorkHour(workInDayDto.getWorkHour());
////                        workInDayEmployeeDto.setIsHoliday(workInDayDto.getIsHoliday());
////                    }
////                    if  (!workInDayEmployeeDto.getDayWork().isEqual(workInDayDto.getDayWork())){
////                        workInDayEmployeeDto.setWorkHour(null);
////                        workInDayEmployeeDto.setIsHoliday(false);
////                        workInDayEmployeeDto.setIsOvertime(null);
////                    }
////                }
//            }
//            TimesheetDto timesheetDto = new TimesheetDto();
//            timesheetDto.setEmployee_code(employee.getEmployeeCode());
//            timesheetDto.setFull_name(employee.getFullName());
//            timesheetDto.setOvertime_hours(timesheetReport.getOvertimeHours());
//            timesheetDto.setTotal_work(timesheetReport.getTotalWork());
//            timesheetDto.setComing_late_leave_early(timesheetReport.getComingLateLeaveEarly());
//            timesheetDto.setDays_off_leave(timesheetReport.getDaysOffLeave());
//            timesheetDto.setWorkInDayEmployeeDtos(workInDayEmployeeDtos);
//            timesheetDtos.add(timesheetDto);
//        }
//        return timesheetDtos;


    @Override
    public TimesheetDto findByEmpAndMonth(Optional<String> month, Optional<String> year, String employeeCode) {
        String abc = month.get()+"/"+year.get();
        TimesheetDto timesheetDto = new TimesheetDto();
        TimesheetReport timesheetReport = timesheetReportRepository.findByMonthAndEmployeeCode(abc,employeeCode);
        Employee employee = employeeRepository.findByEmployeeCode(timesheetReport.getEmployeeCode());
        List<Timesheet> timesheets = timesheetRepository.getAllByMonth(month.get(),year.get(),employeeCode);
        List<WorkInDayDto> workInDayDtos= new ArrayList<>();
        List<WorkInDayEmployeeDto> workInDayEmployeeDtos = mapperByWork(month.get(),year.get());
        List<LocalDate> dateList = new ArrayList<>();
        for (Timesheet timesheet : timesheets
        ){
            dateList.add(timesheet.getWorkday());
        }
        for (WorkInDayEmployeeDto workInDayEmployeeDto : workInDayEmployeeDtos
        ){
            if (dateList.contains(workInDayEmployeeDto.getDayWork())){
                Timesheet  timesheet = timesheetRepository.findByWorkday(workInDayEmployeeDto.getDayWork());
                workInDayEmployeeDto.setWorkHour(timesheet.getWorkingHour());
                workInDayEmployeeDto.setIsOvertime(timesheet.getOvertime_hours());
                workInDayEmployeeDto.setIsHoliday(chechHoliday(timesheet.getWorkday()));
            } else {
                workInDayEmployeeDto.setWorkHour(null);
                workInDayEmployeeDto.setIsOvertime(null);
                workInDayEmployeeDto.setIsHoliday(false);
            }
        }
        timesheetDto.setFull_name(employee.getFullName());
        timesheetDto.setEmployee_code(employeeCode);
        timesheetDto.setTotal_work(timesheetReport.getTotalWork());
        timesheetDto.setOvertime_hours(timesheetReport.getOvertimeHours());
        timesheetDto.setDays_off_leave(timesheetReport.getDaysOffLeave());
        timesheetDto.setComing_late_leave_early(timesheetReport.getComingLateLeaveEarly());
        timesheetDto.setWorkInDayEmployeeDtos(workInDayEmployeeDtos);
        timesheetDto.setMonth(month.get());
        return timesheetDto;
    }

    public List<WorkInDayEmployeeDto> mapperByWork(String month,String year){
        Integer m = Integer.valueOf(month);
        Integer y = Integer.valueOf(year);
        YearMonth yearMonth = YearMonth.of(y,m);
        int lengthOfMonth = yearMonth.lengthOfMonth();
        List<WorkInDayEmployeeDto>  workInDayEmployeeDtos = new ArrayList<>();
        for (int i = 1; i <= lengthOfMonth ; i++) {
            LocalDate date = LocalDate.of(y,m,i);
            WorkInDayEmployeeDto workInDayEmployeeDto = new WorkInDayEmployeeDto();
            workInDayEmployeeDto.setDayWork(date);
            workInDayEmployeeDtos.add(workInDayEmployeeDto);
        }
        return workInDayEmployeeDtos;
    }

    public boolean chechHoliday(LocalDate localDate){
        Holidays holidays1 = holidaysRepository.findByHolidayCode("VIS");
        Holidays holidays2 = holidaysRepository.findByHolidayCode("SIU");
            if (localDate.isBefore(holidays1.getStart_date())&& localDate.isAfter(holidays1.getEnd_date())){
                return  true;
            }
            if(localDate.isBefore(holidays2.getStart_date()) && localDate.isAfter(holidays2.getEnd_date())) {
                return true;
            }
        return false;
    }
}
