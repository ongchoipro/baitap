package com.example.insight_v2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "holidays")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Holidays {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "holiday_id")
    private String holiday_id;
    @Column(name = "holiday_code")
    private String holidayCode;
    @Column(name = "description")
    private String description;
    @Column(name = "start_date")
    private LocalDate start_date;
    @Column(name = "end_date")
    private LocalDate end_date;
    @Column(name = "decision_code")
    private String decision_code;
    @Column(name = "decision_file")
    private String decision_file;
    @Column(name = "created_by")
    private String created_by;
    @Column(name = "created_date")
    private LocalDateTime created_date;
    @Column(name = "updated_date")
    private LocalDateTime updated_date;
    @Column(name = "holiday_name")
    private String holiday_name;
}
