package com.emp.management.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name="jobs")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Job {


    @Column(name = "job_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jobId;

    @Column(name="title")
    @NotNull
    private String title;

    @Column(name="min_salary")
    private BigDecimal minSalary;

    @Column(name="max_salary")
    private BigDecimal maxSalary;

    @OneToMany(mappedBy="job", cascade = CascadeType.ALL)
    private List<Employee> employees;

}
