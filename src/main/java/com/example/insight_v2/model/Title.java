package com.example.insight_v2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "title")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Title {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title_id")
    private String titleId;
    @Column(name = "title_name")
    private String titleName;
    @Column(name = "title_code")
    private String titleCode;
}
