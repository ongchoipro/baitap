package com.example.insight_v2;

import com.example.insight_v2.model.Employee;
import com.example.insight_v2.model.Holidays;
import com.example.insight_v2.model.Timesheet;
import com.example.insight_v2.repository.EmployeeRepository;
import com.example.insight_v2.repository.HolidaysRepository;
import com.example.insight_v2.repository.TimesheetReportRepository;
import com.example.insight_v2.repository.TimesheetRepository;
import com.example.insight_v2.service.TimesheetService;
import com.example.insight_v2.service.impl.TimesheetServiceImpl;
import lombok.Data;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Bean;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

@SpringBootApplication
public class InsightV2Application {

    public static void main(String[] args) {
        SpringApplication.run(InsightV2Application.class, args);
    }
    @Bean
    CommandLineRunner run(TimesheetReportRepository timesheetReportRepository, TimesheetService timesheetService,
                          TimesheetRepository timesheetRepository, TimesheetServiceImpl timesheetServiceImpl,
                          HolidaysRepository holidaysRepository,
                          EmployeeRepository employeeRepository

                          ){
        return args -> {
//            String month = "9";
//            String year = "2023";
//            Integer m1 = Integer.valueOf(month);;
//            Integer y1 = Integer.valueOf(year);
//            int m = 9;
//            int y = 2023;
//            YearMonth yearMonth = YearMonth.of(y1,m1);
//            int lengthOfMonth = yearMonth.lengthOfMonth();
//            System.out.println(lengthOfMonth);
//            List<LocalDate> dateList = new ArrayList<>();
//            for (int i = 1; i <= lengthOfMonth ; i++) {
//                LocalDate datedto = LocalDate.of(y,m,i);
//                dateList.add(datedto);
//            }
//            System.out.println(dateList);
            Employee employee1 = employeeRepository.findByEmployeeCode("namtq");
            System.out.println(employee1.getId());
        };
    }
}
